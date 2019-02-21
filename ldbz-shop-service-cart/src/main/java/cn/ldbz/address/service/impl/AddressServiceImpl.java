package cn.ldbz.address.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.address.service.AddressService;
import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzAddressMapper;
import cn.ldbz.pojo.LdbzAddress;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ADDRESS_VERSION)
public class AddressServiceImpl implements AddressService {

	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private LdbzAddressMapper mapper ;

	@Override
	public LdbzAddress getDefaultAddressByUser(Object userId) {
		return mapper.getDefaultAddressByUser(userId);
	}

	@Override
	@Transactional
	public LdbzResult insertByEntity(LdbzAddress entity) {
		logger.debug("insertByEntity entity : {} " , entity);
		if(entity.getIsDefault()==1) {
			//如果将新加入的记录作为默认地址，则清空之前的默认地址
			mapper.updateDefaultByUser(entity);
		}
		if(mapper.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult selectByEntity(LdbzAddress entity) {
		logger.debug("selectByEntity entity : {} " , entity);
		List<LdbzAddress> ret = mapper.selectByEntity(entity, 1 , 1);
		return LdbzResult.build(200, "", ret);
	}

	@Override
	@Transactional
	public LdbzResult updateDefaultById(LdbzAddress entity) {
		logger.debug("updateDefaultById entity : {} " , entity);
		mapper.updateDefaultByUser(entity);//首先置为0
		entity.setIsDefault(1);
		int ret = mapper.updateDefaultById(entity);//根据id设置为默认
		return LdbzResult.build(200, "", ret>0);
	}
	
	@Override
	public LdbzResult deleteByKey(Long id) {
		int ret = mapper.deleteByKey(id);
		return LdbzResult.build(200, "", ret>0);
	}

}
