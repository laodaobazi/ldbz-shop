package cn.ldbz.tags.sheet;

import java.util.List;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.SheetService;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("sheet_tag")
public class SheetTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(SheetTag.class);

    @Reference(version = Const.LDBZ_SHOP_SHEET_VERSION, timeout=30000)
    private SheetService sheetService;

	@Override
	public void render() {
		Object sheetKey = this.getAttributeValue("key");
		logger.debug("根据板块Key {} 获取分配的商品" , sheetKey);
		List<LdbzSheet> list = sheetService.getEnableSheetsByKeys(sheetKey);
		for (LdbzSheet entity : list){
			this.binds(entity);
			this.doBodyRender();
        }
	}

}