package cn.ldbz.wishlist.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzUser;
import cn.ldbz.pojo.LdbzWishlist;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.utils.CookieUtils;
import cn.ldbz.utils.FastJsonConvert;
import cn.ldbz.wishlist.service.WishlistService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 收藏Controller
 */
@Controller
public class WishlistController {

    private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);
    
    //图片的URL路径
    @Value("${redisKey.nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;

    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug("Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
			switch(key) {
				case "redisKey.nginxImage.url.key" : 
					INDEX_NGINX_IMAGE_URL = change.getNewValue();
			}
		}
	}

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Reference(version = Const.LDBZ_SHOP_WISHLIST_VERSION)
    private WishlistService wishlistService;
    
    @Reference(version = Const.LDBZ_SHOP_SSO_VERSION)
    private UserService userService;
    
    @RequestMapping("/wishlist")
    public String wishlist(Model model) {
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);
    	return "wishlist";
    }

    @ApiOperation(value="分页获取收藏的商品", notes="根据当前登录用户获取其收藏的商品")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/wishlist/getItemPage" , method=RequestMethod.POST)
    public LdbzResult getItemPage(HttpServletRequest request,
    		@RequestParam(defaultValue = "1")int pn , 
    		@RequestParam(defaultValue = "10")int limit) {
    	String token = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
    	LdbzResult ret = userService.token(token, "") ;
    	LdbzUser user = FastJsonConvert.convertJSONToObject(ret.getData().toString(), LdbzUser.class);
    	LdbzWishlist entity = new LdbzWishlist();
    	entity.setUserId(user.getId());
    	return wishlistService.getItemPage(entity, pn, limit);
    }

    @ApiOperation(value="收藏商品", notes="收藏一条商品")
    @ApiImplicitParam(name = "entity", value = "LdbzWishlist实体", required = true, dataType = "LdbzWishlist")
    @ResponseBody
    @RequestMapping(value="/wishlist/insertByEntity" , method=RequestMethod.POST)
    public LdbzResult insertByEntity(HttpServletRequest request,LdbzWishlist entity) {
    	String token = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
    	LdbzResult ret = userService.token(token, "") ;
    	LdbzUser user = FastJsonConvert.convertJSONToObject(ret.getData().toString(), LdbzUser.class);
    	entity.setUserId(user.getId());
    	entity.setCreated(new Date());
    	entity.setCreator(user.getId());
    	entity.setCreatorName(user.getUsername());
    	return wishlistService.insertByEntity(entity);
    }

    @ApiOperation(value="删除收藏的商品", notes="根据id物理删除一条收藏的商品")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/wishlist/deleteByKey/{id}" , method=RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return wishlistService.deleteByKey(id);
    }

}
