package cn.ldbz.item.controller;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.item.vo.TbItemVO;
import cn.ldbz.pojo.TbItemDesc;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 商品查询 Controller
 *
 */

@Controller
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private ItemService itemService;

    @RequestMapping("/item/{id}")
    public String  getItemByItemId(@PathVariable("id") Long itemId, Model model) {

//        TbItemVO item = new TbItemVO(itemService.getItemById(itemId));
//
//        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
//
//        model.addAttribute("item", item);
//        model.addAttribute("itemDesc", itemDesc);

        return "item";
    }

}
