package cn.ldbz.mapper;

import cn.ldbz.pojo.LdbzAddress;

public interface LdbzAddressMapper extends LdbzBaseMapper<LdbzAddress>{
	
	LdbzAddress getDefaultAddressByUser(Object userId) ;
	
	int updateDefaultByUser(LdbzAddress entity);
	
	int updateDefaultById(LdbzAddress entity);
	
	int deleteByKey(Long id);
}