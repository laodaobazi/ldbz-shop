package cn.ldbz.admin.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.admin.service.AdminLoginService;
import cn.ldbz.admin.vo.ManageUserVO;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.utils.CookieUtils;
import cn.ldbz.utils.FastJsonConvert;

/**
 * Admin 首页Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	@Value("${sso.web.url}")
	private String SSO_WEB_URL ;
	
    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminLoginService adminLoginService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
    	model.addAttribute("sso_web_url", SSO_WEB_URL);
    	model.addAttribute("uid", UUID.randomUUID().toString());
        return "login";
    }
    
    @ResponseBody
    @RequestMapping("/login")
    public String userLogin(String account, String password , String code , String uid , HttpServletRequest req , HttpServletResponse response) {
    	LdbzResult ret = adminLoginService.userLogin(account, password, code, uid);
    	if(ret.isOK()) {
    		CookieUtils.setCookie(req, response, Const.TOKEN_LOGIN, ret.getData().toString());
    	}
    	return FastJsonConvert.convertObjectToJSON(ret);
    }
    
    @RequestMapping("/info")
    public String showAdmin(Model model) {
        ManageUserVO userVO = new ManageUserVO();
        userVO.setCreated(new Date());
        userVO.setName("老刀把子");
        userVO.setJob("CEO");
        model.addAttribute("user", userVO);
        return "info";
    }

}
