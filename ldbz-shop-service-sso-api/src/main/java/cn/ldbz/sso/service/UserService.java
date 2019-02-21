package cn.ldbz.sso.service;

import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzUser;

/**
 * 用户登录相关服务
 */
public interface UserService {

    /**
     * 请求格式 POST
     * 用户登录
     *
     * @param user Tbuser POJO Json
     * @return {
     *           status: 200 //200 成功 400 登录失败 500 系统异常
     *           msg: "OK" //错误 用户名或密码错误,请检查后重试.
     *           data: "fe5cb546aeb3ce1bf37abcb08a40493e" //登录成功，返回token
     *         }
     */
    LdbzResult login(LdbzUser user);

    /**
     * 请求格式 GET
     * 根据token值获取用户信息(判断当前用户是否存在)
     *
     * @param token token值
     */
    LdbzUser token(String token);

    /**
     * 请求格式 GET
     * 根据token值 退出登录
     *
     * @param token token值
     * @param callback 可选参数 有参表示jsonp调用
     * @return {
     *           status: 200 //200 成功 400 没有此token 500 系统异常
     *           msg: "OK" //错误 没有此token.
     *           data: null
     *         }
     */
    LdbzResult logout(String token, String callback);

    /**
     * 注册检查是否可用
     *
     * @param isEngaged 需要检查nick，email
     * @return {
     *           "success": true 可用 false不可用
     *           "morePin":["sssss740","sssss5601","sssss76676"] //isEngaged = isPinEngaged时返回推荐
     *         }
     */
    String validateUser(String isEngaged,String regName,String email);

    /**
     * 用户注册
     *
     * @param regName       注册名
     * @param pwd           第一次密码
     * @param pwdRepeat     第二次密码
     * @param emailCode    邮箱验证码
     * @param email         邮箱
     * @param authCode      输入的验证码
     * @param uuid          Redis验证码uuid
     * @return
     */
    String register(String regName, String pwd, String pwdRepeat, String emailCode, String uuid, String authCode, String s);
}
