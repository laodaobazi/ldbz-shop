package cn.ldbz.notify.service.impl;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.notify.service.NotifyUserService;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.FastJsonConvert;

/**
 * 用户通知服务实现
 */
@Component
@Service(version = Const.LDBZ_SHOP_NOTIFY_VERSION , timeout=3000)
public class NotifyUserServiceImpl implements NotifyUserService {

    private static final Logger logger = LoggerFactory.getLogger(NotifyUserServiceImpl.class);
    
    @Autowired
    private JavaMailSender mailSender ;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION , timeout=30000)
    private JedisClient jedisClient;
    
    private final String EMAIL_NUMBER_CEILING = "3" ;//一段时间允许发送的邮件数量
    private final String EMAIL_LOGIN_CODE = "EMAIL_LOGIN_CODE:";//邮件验证码key
    private final String EMAIL_LOGIN_TIME = "EMAIL_LOGIN_TIME:";//记录发送验证码的时间key

    @Value("${redisKey.prefix.email_login_code.expire_time:180}")
    private Integer EMAIL_LOGIN_CODE_EXPIRE;//验证码的过期时间

    @Value("${redisKey.prefix.email_login_time.expire_time:3600}")
    private Integer EMAIL_LOGIN_TIME_EXPIRE;//发送的频次时间

	@Value("${mail.auth.name}")
	private String EMAIL_AUTHNAME;
    
    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
			switch(key) {
				case "redisKey.prefix.email_login_time.expire_time" : 
					EMAIL_LOGIN_TIME_EXPIRE = Integer.valueOf(change.getNewValue()) ;
				case "redisKey.prefix.email_login_code.expire_time" : 
					EMAIL_LOGIN_CODE_EXPIRE = Integer.valueOf(change.getNewValue()) ;
			}
		}
	}

    /**
     * 发送邮件
     *
     * @param email 邮箱
     * @return {"success":true} 成功
     *         {"success":false}  失败
     */
    @Override
    public String emailNotify(String email) {

        HashMap<String, Object> map = new HashMap<>();
        
        if(!StringUtils.contains(email, "@") || StringUtils.length(email)<3) {
        	map.put("success", false);
            map.put("message", "邮箱格式有误");
            return FastJsonConvert.convertObjectToJSON(map);
        }
        
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        logger.info("email code: {}", code);

        //查询Redis  号码1小时获取次数
        try {
            String key = EMAIL_LOGIN_TIME + email;
            String key1 = EMAIL_LOGIN_CODE + email;
            String time = jedisClient.get(key);
            //查询不到
            if (StringUtils.isBlank(time)) {
            	if(!sendEmailCode(email, code)) {
            		//邮件发送失败
            		map.put("success", false);
                    map.put("message", "服务器繁忙，稍后再试");
                    return FastJsonConvert.convertObjectToJSON(map);
            	}
                //保存登录次数到Redis
                //初始化次数为3次
                jedisClient.set(key, EMAIL_NUMBER_CEILING);
                //设置过期时间
                jedisClient.expire(key, EMAIL_LOGIN_TIME_EXPIRE);
                //保存code到Redis
                jedisClient.set(key1, code + "");
                //设置过期时间
                jedisClient.expire(key1, EMAIL_LOGIN_CODE_EXPIRE);
                map.put("success", true);
                map.put("message", "发送成功");
                return FastJsonConvert.convertObjectToJSON(map);
            }
            //查询到 判断是否为0 次数减一
            int nub = Integer.parseInt(time);
            if (nub == 0) {
                jedisClient.del(key1);
                map.put("sucess", false);
                map.put("message", "次数过多，一小时后再试。");
                return FastJsonConvert.convertObjectToJSON(map);
            }
        	if(!sendEmailCode(email, code)) {
        		//邮件发送失败
        		map.put("success", false);
                map.put("message", "服务器繁忙，稍后再试");
                return FastJsonConvert.convertObjectToJSON(map);
        	}
            jedisClient.set(key, --nub + "");
            // 发送短信==================
            //保存code到Redis
            jedisClient.set(key1, code + "");
            //设置过期时间
            jedisClient.expire(key1, EMAIL_LOGIN_CODE_EXPIRE);
            String result = "该邮箱还可获取" + nub + "次验证码，请尽快完成验证" ;
            map.put("success", true);
            map.put("message", result);
            return FastJsonConvert.convertObjectToJSON(map);
        } catch (Exception e) {
            logger.error("Redis 服务出错", e);
        }
        map.put("success", false);
        map.put("message", "服务器繁忙，稍后再试");
        return FastJsonConvert.convertObjectToJSON(map);
    }
    
    private boolean sendEmailCode(String toUser , int code) {
    	try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
			messageHelper.setTo(toUser);
			messageHelper.setFrom(EMAIL_AUTHNAME);
			messageHelper.setSubject("LDBZ-验证码");
			messageHelper.setText("您的邮件验证码为：" + code, true);
			mailSender.send(mailMessage);
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
    }
    
}