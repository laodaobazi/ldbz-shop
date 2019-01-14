package cn.ldbz.admin.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.admin.service.AdminIndexSlideAdService;
import cn.ldbz.admin.service.AdminLoginService;
import cn.ldbz.admin.service.AdminRoleService;
import cn.ldbz.admin.service.AdminUserService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.item.service.SheetService;
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
    
    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminUserService adminUserService;
    
    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminRoleService adminRoleService;

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION, timeout=30000)
    private ItemService itemService;

    @Reference(version = Const.LDBZ_SHOP_SHEET_VERSION, timeout=30000)
    private SheetService sheetService;

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION, timeout=30000)
    private CategoryService categoryService;

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminIndexSlideAdService adminIndexSlideAdService;

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
        return "adminInfo";
    }
    
    @RequestMapping("/welcome")
    public String welcome(Model model) {
    	LdbzResult ret1 = adminUserService.countAdminUser(null);
    	model.addAttribute("admin_users" , ret1.getData());
    	
    	LdbzResult ret2 = adminRoleService.countAdminRole(null);
    	model.addAttribute("admin_roles" , ret2.getData());
    	
    	LdbzResult ret5 = categoryService.countCategory(null);
    	model.addAttribute("categorys" , ret5.getData());
    	
    	LdbzResult ret4 = sheetService.countSheet(null);
    	model.addAttribute("sheets" , ret4.getData());
    	
    	LdbzResult ret3 = itemService.countItem(null);
    	model.addAttribute("items" , ret3.getData());
    	
    	LdbzResult ret6 = adminIndexSlideAdService.countIndexSlideAd(null);
    	model.addAttribute("indexSlideAds" , ret6.getData());
    	return "adminWelcome" ;
    }

}
