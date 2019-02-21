package cn.ldbz.tags.address;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.address.service.AddressService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzAddress;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("default_address_tag")
public class DefaultAddressTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(DefaultAddressTag.class);
	
	@Reference(version = Const.LDBZ_SHOP_ADDRESS_VERSION)
	private AddressService addressService ;

	@Override
	public void render() {
		Object userId = this.getAttributeValue("userId");
		logger.debug("获取默认邮寄地址");
		if(userId!=null) {
			LdbzAddress entity = addressService.getDefaultAddressByUser(userId);
			this.binds(entity);
			this.doBodyRender();
		}
	}
	
}
