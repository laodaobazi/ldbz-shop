package cn.ldbz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzItem;

public interface LdbzItemMapper extends LdbzBaseMapper<LdbzItem>{

	List<LdbzItem> selectItemListBySheetKey(String key);
	
	LdbzItem selectByCode(Long code);
	
	/**
	 * 获取商品图片
	 * @param code
	 * @param type 1:商品预览图 ; 2:商品描述详解图
	 * @return
	 */
	String getItemImageByType(@Param("code")Object code , @Param("type")Object type);

	float getItemPriceByCode(long itemCode);
}