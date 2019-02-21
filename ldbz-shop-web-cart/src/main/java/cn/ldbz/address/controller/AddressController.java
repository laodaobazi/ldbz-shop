package cn.ldbz.address.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.address.service.AddressService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzAddress;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzUser;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.utils.CookieUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 地址 Controller
 */
@Controller
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Reference(version = Const.LDBZ_SHOP_ADDRESS_VERSION)
    private AddressService addressService;

    @Reference(version = Const.LDBZ_SHOP_SSO_VERSION, timeout=30000)
    private UserService userService;

    @ApiOperation(value="新增用户地址", notes="创建一条新的地址记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAddress实体", required = true, dataType = "LdbzAddress")
    @ResponseBody
    @RequestMapping(value="/cart/address/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(HttpServletRequest request , LdbzAddress entity) {
    	logger.debug("insertByEntity entity : {} " ,  entity);

		String token = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
		LdbzUser user = userService.token(token);
    	
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	entity.setUserId(user.getId());
    	return addressService.insertByEntity(entity);
    }

    @ApiOperation(value="获取用户地址", notes="获取当前用户的邮寄地址")
    @ResponseBody
    @RequestMapping(value="/cart/address/selectByEntity" , method = RequestMethod.POST)
    public LdbzResult selectByEntity(HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
		LdbzUser user = userService.token(token);
    	LdbzAddress entity = new LdbzAddress();
    	entity.setUserId(user.getId());
    	return addressService.selectByEntity(entity);
    }

    @ApiOperation(value="设置默认地址", notes="将选中的地址设置为默认地址")
    @ResponseBody
    @RequestMapping(value="/cart/address/updateDefaultById" , method = RequestMethod.POST)
    public LdbzResult updateDefaultById(LdbzAddress entity , HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
		LdbzUser user = userService.token(token);
    	entity.setUserId(user.getId());
    	return addressService.updateDefaultById(entity);
    }
    
    @ApiOperation(value="删除指定的地址", notes="根据id物理删除一条邮寄地址")
    @ApiImplicitParam(name = "id", value = "地址id", required = true, dataType = "Long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/cart/address/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")Long id) {
    	return addressService.deleteByKey(id);
    }
    
}