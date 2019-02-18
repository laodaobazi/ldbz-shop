package cn.ldbz.tags.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzUser;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.tags.annotation.BeetlTagName;
import cn.ldbz.utils.CookieUtils;

@Service
@Scope("prototype")
@BeetlTagName("current_user_tag")
public class CurrentUserTag  extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(CurrentUserTag.class);

    @Reference(version = Const.LDBZ_SHOP_SSO_VERSION, timeout=30000)
    private UserService userService;

	@Override
	public void render() {
		Object attr = this.getAttributeValue("attr");
		logger.debug("获取用户属性 attr" , attr);
		
		HttpServletRequest request = (HttpServletRequest)this.ctx.getGlobal("request");
		String token = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
        if(StringUtils.isNoneEmpty(token)) {
        	LdbzUser user = userService.token(token);
        	if(user!=null) {
        		//用户在线
        		if("username".equals(attr)) {
        			//获取指定属性
        			this.binds(user.getUsername()+"欢迎你！");
        	        this.doBodyRender();
        	        return ;
        		}else if("id".equals(attr)) {
        			//获取指定属性
        			this.binds(user.getId());
        	        this.doBodyRender();
        	        return ;
        		}else {
        			//仅仅判断用户是否在线
        			this.binds("true");
        			this.doBodyRender();
        			return ;
        		}
        	}
        }
        //用户不在线
        this.binds("false");
		this.doBodyRender();
		return ;
	}

}
