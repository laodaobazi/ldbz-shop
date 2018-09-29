package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzResult;

public interface AdminLoginService {

	/**
	 * 管理员登录
	 * @param account 账号
	 * @param password 密码
	 * @param code 验证码
	 * @param uid 随机码
	 * @return
	 */
	LdbzResult userLogin(String account, String password , String code, String uid);
	
	
}