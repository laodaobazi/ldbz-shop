package cn.ldbz.notify.service;

/**
 * 用户通知服务接口
 */
public interface NotifyUserService {
	
    /**
     * 发送邮件
     *
     * @param email 邮箱
     * @return {"success":true} 成功
     *         {"success":false}  失败
     */
    String emailNotify(String email);

}