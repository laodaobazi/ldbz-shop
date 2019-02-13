package cn.ldbz.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminLoginService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.redis.service.JedisClient;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminLoginServiceImpl implements AdminLoginService{

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginServiceImpl.class);
	
    private final String REDIS_KEY_VERIFYCODE = "VERIFYCODE:" ;
    private static final String REDIS_KEY_ADMIN_SESSION = "ldbz-admin-";
    
    /**
     * redis中session过期时间
     */
    @Value("${redisKey.expire_time:1800}")
    private Integer EXPIRE_TIME;
    

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;
    
//    @Autowired
//    TbManageUserMapper mapper ;
    
	@Override
	public LdbzResult userLogin(String account, String password, String code, String uid) {
//		if(StringUtils.isEmpty(account)) {
//    		return LdbzResult.build(401, "用户名不能为空");
//    	}
//    	if(StringUtils.isEmpty(password)) {
//    		return LdbzResult.build(401, "密码不能为空");
//    	}
//    	if(StringUtils.isEmpty(code)) {
//    		return LdbzResult.build(401, "验证码不能为空");
//    	}
//		//判断验证码
//		String verifycode = "";
//        try {
//        	verifycode = jedisClient.get(REDIS_KEY_VERIFYCODE + uid);
//        } catch (Exception e) {
//            logger.error("Redis服务出错", e);
//        }
//        if (StringUtils.isBlank(verifycode)) {
//            return LdbzResult.build(400, "图片验证码已过期，请重新获取");
//        }
//        if(!verifycode.equalsIgnoreCase(code)) {
//        	return LdbzResult.build(400, "验证码输入有误");
//        }
//        //校验用户
//        TbManageUserExample example = new TbManageUserExample();
//        TbManageUserExample.Criteria criteria = example.createCriteria();
//        criteria.andUsernameEqualTo(account);
//        List<TbManageUser> users = mapper.selectByExample(example);
//        if (users == null || users.size() == 0) {
//            return LdbzResult.build(401, "用户不存在");
//        }
//        TbManageUser user = users.get(0);
//        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
//        	return LdbzResult.build(400, "用户名密码错误");
//        }
//        TbManageUser result = new TbManageUser();
//        result.setUsername(user.getUsername());
//        result.setId(user.getId());
//        String token = UUID.randomUUID().toString().replaceAll("-","");
//        String key = REDIS_KEY_ADMIN_SESSION + token;
//        jedisClient.set(key, FastJsonConvert.convertObjectToJSON(result));
//        jedisClient.expire(key, EXPIRE_TIME);
//		return LdbzResult.ok(token);
		return LdbzResult.ok();
	}

	
}
