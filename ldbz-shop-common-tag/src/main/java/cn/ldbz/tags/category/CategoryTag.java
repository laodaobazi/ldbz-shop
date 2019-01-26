package cn.ldbz.tags.category;

import java.util.List;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.tags.annotation.BeetlTagName;


@Service
@Scope("prototype")
@BeetlTagName("category_tag")
public class CategoryTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(CategoryTag.class);

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private CategoryService categoryService;

	@Override
	public void render() {
		
			Object level = this.getAttributeValue("level");
			Object fid = this.getAttributeValue("fid");
			logger.debug("getCategory level : {} , fid:{}" , level , fid);
			List<LdbzCategory> list = categoryService.getCategoryTreeRedis(level , fid);
			for (LdbzCategory c : list){
				this.binds(c);
				this.doBodyRender();
	        }
			
	}

}
