package cn.ldbz.portal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.portal.service.PortalContentService;

@Controller
public class IndexController {

    @Reference(version = Const.LDBZ_SHOP_PORTAL_VERSION)
    private PortalContentService portalContentService;

    @Value("${big_ad_index}")
    private long Big_AD_INDEX;

    @RequestMapping("/index")
    public String index(Model model) {
    	return "index";
    }
    
    //[LString;{"buttonPositionTop":false,"buttonPositionLeft":false,"largeIcon":true,"assistantFirstStart":false,"showWarnings":true,"scriptVersion":1}
    @RequestMapping("/adguard-ajax-api/api")
    @ResponseBody
    public String adguard() {
        return "[LString;{\"buttonPositionTop\":false,\"buttonPositionLeft\":false,\"largeIcon\":true,\"assistantFirstStart\":false,\"showWarnings\":true,\"scriptVersion\":1}";
    }
    
}
