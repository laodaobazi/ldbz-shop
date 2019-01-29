package cn.ldbz.item.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemCommentService;
import cn.ldbz.pojo.LdbzItemComment;
import cn.ldbz.pojo.LdbzResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品评论查询 Controller
 */
@Controller
@RequestMapping("comment")
public class ItemCommentController {

    private static final Logger logger = LoggerFactory.getLogger(ItemCommentController.class);
    
    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private ItemCommentService itemCommentService;

    @ApiOperation(value="分页获取商品评论", notes="根据实体LdbzItemComment分页获取商品评论")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzItemComment实体", required = true, dataType = "LdbzItemComment"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getItemCommentPage" , method = RequestMethod.POST)
    public LdbzResult  getItemCommentPage(LdbzItemComment entity , int page , int limit) {
    	logger.debug("getItemCommentPage entity:{} , page:{} , limit:{}" , entity , page , limit);
    	return itemCommentService.getItemCommentPage(entity, page, limit);
    }

}
