package cn.ldbz.wishlist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.utils.CookieUtils;

/**
 * 拦截用户登录
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Reference(version = Const.LDBZ_SHOP_SSO_VERSION)
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //执行handler之前执行此方法 true 放行 false 拦截
        String cookieValue = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
        //获取访问URL
        String url = request.getRequestURL().toString();

        if (StringUtils.isBlank(cookieValue) ||
        		userService.token(cookieValue, "").getStatus() != 200) {
            //跳转登录页面
        	response.sendRedirect(request.getContextPath() + "/sso/login?returnUrl=" + url);
            //拦截
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
    
}
