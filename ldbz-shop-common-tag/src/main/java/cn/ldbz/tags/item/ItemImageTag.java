package cn.ldbz.tags.item;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("item_image_tag")
public class ItemImageTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(ItemImageTag.class);

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private ItemService itemService;

	@Override
	public void render() {
		
			Object code = this.getAttributeValue("code");
			Object type = this.getAttributeValue("type");
			Object redis = this.getAttributeValue("redis");
			logger.debug("getCategory code : {} , type:{}" , code , type);
			String images = "" ;
			if("false".equals(redis)) {
				images = itemService.getItemImage(code , type);
			}else {
				images = itemService.getItemImageByRedis(code , type);
			}
			if(StringUtils.isNoneEmpty(images)) {
				for (String str : images.split(",")){
					if(StringUtils.isNoneEmpty(str)) {
						this.binds(str);
						this.doBodyRender();
					}
				}
			}
			
	}

}
