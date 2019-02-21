package cn.ldbz.address.service;

import cn.ldbz.pojo.LdbzAddress;
import cn.ldbz.pojo.LdbzResult;

/**
 * 地址操作
 */
public interface AddressService {

	/**
	 * 根据当前用户获取其默认的邮寄地址
	 * @param userId
	 * @return
	 */
	LdbzAddress getDefaultAddressByUser(Object userId);

	/**
	 * 新建邮寄地址
	 * @param entity
	 * @return
	 */
	LdbzResult insertByEntity(LdbzAddress entity);
	
	/**
	 * 获取邮寄地址
	 * @param entity
	 * @return
	 */
	LdbzResult selectByEntity(LdbzAddress entity);

	/**
	 * 根据ID修改邮寄地址为默认
	 * @param entity
	 * @return
	 */
	LdbzResult updateDefaultById(LdbzAddress entity);

	/**
	 * 删除指定的地址
	 * @param id
	 * @return
	 */
	LdbzResult deleteByKey(Long id);
	
}
