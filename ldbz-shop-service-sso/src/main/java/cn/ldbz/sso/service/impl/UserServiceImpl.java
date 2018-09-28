package cn.ldbz.sso.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.mapper.TbUserMapper;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.TbUser;
import cn.ldbz.pojo.TbUserExample;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.utils.FastJsonConvert;

/**
 * 用户登录相关服务 Service 实现
 */
@Component
@Service(version = Const.LDBZ_SHOP_SSO_VERSION)
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final Integer RANDOM_NUMBER = 50000 ;
    private static final String IS_NICK_ENGAGED = "nick" ;
    private static final String IS_EMAIL_ENGAGED = "email" ;
    private static final String EMAIL_LOGIN_CODE = "EMAIL_LOGIN_CODE:";

    private final String REDIS_KEY_USER_SESSION = "_ldbz-us";
    private final String REDIS_KEY_VERIFYCODE = "VERIFYCODE:" ;

    @Autowired
    private TbUserMapper userMapper;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    /**
     * redis中session过期时间
     */
    @Value("${redisKey.expire_time:1800}")
    private Integer EXPIRE_TIME;

    /**
     * 注册成功之后跳转URL
     */
    @Value("${login.success_url}")
    private String SUCCESS_URL;
    
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
				case "redisKey.expire_time" : 
					EXPIRE_TIME = Integer.valueOf(change.getNewValue()) ;
				case "login.success_url" : 
					SUCCESS_URL = change.getNewValue() ;
			}
		}
	}

    /**
     * 请求格式 POST
     * 用户登录
     *
     * @param user Tbuser POJO Json
     * @return {
     *          status: 200 //200 成功 400 登录失败 500 系统异常
     *          msg: "OK" //错误 用户名或密码错误,请检查后重试.
     *          data: "fe5cb546aeb3ce1bf37abcb08a40493e" //登录成功，返回token
     *         }
     */
    @Override
    public LdbzResult login(TbUser user) {
        if (user == null) {
            return LdbzResult.build(400, "error", "数据为空");
        }
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return LdbzResult.build(400, "用户名不存在");
        }
        TbUser check = list.get(0);
        if (!check.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
            return LdbzResult.build(401, "用户名或密码错误");
        }
        TbUser result = new TbUser();
        result.setUsername(check.getUsername());
        result.setId(check.getId());
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String key = REDIS_KEY_USER_SESSION + token;
        jedisClient.set(key, FastJsonConvert.convertObjectToJSON(result));
        jedisClient.expire(key, EXPIRE_TIME);
        return LdbzResult.ok(token);
    }

    /**
     * 请求格式 GET
     * 根据token值获取用户信息
     *
     * @param token    token值
     * @param callback 可选参数 有参表示jsonp调用
     * @return {
     *          status: 200 //200 成功 400 没有此token 500 系统异常
     *          msg: "OK" //错误 没有此token.
     *          data: {"username":"xbin","id":"id"} //返回用户名
     *         }
     */
    @Override
    public LdbzResult token(String token, String callback) {
        if (StringUtils.isNotBlank(callback)) {
            return LdbzResult.ok(callback);
        }
        try {
            String user = jedisClient.get(REDIS_KEY_USER_SESSION + token);
            if (StringUtils.isNotBlank(user)) {
                return LdbzResult.ok(user);
            }
        } catch (Exception e) {
            logger.error("Redis服务出错");
        }
        return LdbzResult.build(400, "没有此用户");
    }

    /**
     * 请求格式 GET
     * 根据token值 退出登录
     *
     * @param token    token值
     * @param callback 可选参数 有参表示jsonp调用
     * @return {
     *          status: 200 //200 成功 400 没有此token 500 系统异常
     *          msg: "OK" //错误 没有此token.
     *          data: null
     *         }
     */
    @Override
    public LdbzResult logout(String token, String callback) {
        if (StringUtils.isNotBlank(callback)) {
            return LdbzResult.ok(callback);
        }
        try {
            jedisClient.del(REDIS_KEY_USER_SESSION + token);
        } catch (Exception e) {
            logger.error("没有登录", e);
            return LdbzResult.build(400, "没有登录");
        }
        return LdbzResult.ok();
    }

    /**
     * 请求格式 POST
     * 注册检查是否可用
     *
     * @param isEngaged 需要检查是否使用的名称
     * @return {
     *          "success": true 可用 false 不可用
     *          "morePin":["sssss740","sssss5601","sssss76676"] //isEngaged = isPinEngaged时返回推荐
     *         }
     */
    @Override
    public String validateUser(String isEngaged,String regName,String email) {
        HashMap<String, Object> map = new HashMap<>();
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(isEngaged)) {
            if (isEngaged.equals(IS_NICK_ENGAGED) && StringUtils.isNotBlank(regName)) {
                criteria.andUsernameEqualTo(regName);
                List<TbUser> users = userMapper.selectByExample(example);
                if (users == null || users.size() == 0) {
                    map.put("success", true);
                    map.put("info", "用户名可用");
                    return FastJsonConvert.convertObjectToJSON(map);
                }
                //用户名 不可用
                map.put("success", false);
                ArrayList<String> morePin = new ArrayList<>();
                Random random = new Random();
                morePin.add(regName + random.nextInt(RANDOM_NUMBER));
                morePin.add(regName + random.nextInt(RANDOM_NUMBER));
                morePin.add(regName + random.nextInt(RANDOM_NUMBER));
                // 不考虑生成的用户名继续重名
                map.put("info", "用户名重复，建议"+morePin);
                return FastJsonConvert.convertObjectToJSON(map);
            } else {
                if (isEngaged.equals(IS_EMAIL_ENGAGED) && StringUtils.isNotBlank(email)) {
                    criteria.andEmailEqualTo(email);
                    List<TbUser> users = userMapper.selectByExample(example);
                    if (users == null || users.size() == 0) {
                        //email 可用
                        map.put("success", true);
                        map.put("info", "邮箱账号可用");
                        return FastJsonConvert.convertObjectToJSON(map);
                    }
                    //email 不可用
                    map.put("success", false);
                    map.put("info", "邮箱账号已存在");
                    return FastJsonConvert.convertObjectToJSON(map);
                }
            }
        }
        logger.error("传递类型出错！");
        map.put("success", false);
        map.put("info", "系统繁忙稍后再试");
        return FastJsonConvert.convertObjectToJSON(map);
    }

    /**
     * 用户注册
     *
     * @param regName       注册名
     * @param pwd           第一次密码
     * @param pwdRepeat     第二次密码
     * @param phone         电话
     * @param emailCode    邮箱验证码
     * @param email         邮箱
     * @param authCode      输入的验证码
     * @param uuid          Redis验证码uuid
     * @return	{success:xxx , info:xxx}
     */
    @Override
    public String register(String regName, String pwd, String pwdRepeat, String emailCode, String uuid, String authCode, String email) {
        if (!pwd.equals(pwdRepeat)) {
            return "{\"success\":false , \"info\":\"两次密码不一致\"}";
        }
        if (StringUtils.isNotBlank(authCode)) {
            String code = "";
            try {
                code = jedisClient.get(REDIS_KEY_VERIFYCODE + uuid);
            } catch (Exception e) {
                logger.error("Redis服务出错", e);
            }
            if (StringUtils.isBlank(code)) {
                return "{\"success\":false , \"info\":\"图片验证码已过期，请重新获取\"}";
            }
            if (!code.equalsIgnoreCase(authCode)) {
            	return "{\"success\":false , \"info\":\"图片验证码输入有误\"}";
            }
        } else {
            return "{\"success\":false , \"info\":\"验证码不能为空\"}";
        }
        if (StringUtils.isNotBlank(email) || StringUtils.contains(email, "@")) {
            String emailcode = "";
            try {
            	emailcode = jedisClient.get(EMAIL_LOGIN_CODE + email);
            } catch (Exception e) {
                logger.error("Redis服务出错");
            }
            if (StringUtils.isBlank(emailcode)) {
                return "{\"success\":false , \"info\":\"邮件验证码已过期,请重新获取\"}";
            }
            if (!emailcode.equals(emailCode)) {
            	return "{\"success\":false , \"info\":\"邮件验证码输入有误\"}";
            }
        } else {
            return "{\"success\":false , \"info\":\"邮箱账号输入有误\"}";
        }
        
        //后台校验账号是否重复
        String result = validateUser("nick",regName,"") ;
        if(StringUtils.contains(result ,"false")) {
        	return result ;
        }
        //后台校验邮箱是否重复
        result = validateUser("email","",email);
        if(StringUtils.contains(result ,"false")) {
        	return result ;
        }
        
        if (StringUtils.isNotBlank(regName)) {
            TbUser user = new TbUser();
            user.setUsername(regName);
            user.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
            user.setCreated(new Date());
            user.setUpdated(new Date());
            user.setEmail(email);
            userMapper.insert(user);
            jedisClient.del(EMAIL_LOGIN_CODE + email);
            //注册成功 忽略noAuth这个词
            return "{\"success\":true , \"info\":\"" + SUCCESS_URL + "?username=" + regName + "\"}";
        }
        //注册失败
        return "{\"success\":false , \"info\":\"服务器内部错误稍后再试。\"}";
    }
    
}