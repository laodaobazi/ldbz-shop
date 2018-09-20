package cn.ldbz.advertisement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.advertisement.service.AdvertisementService;
import cn.ldbz.constant.Const;

@Component
@Service(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
public class AdvertisementServiceImpl implements AdvertisementService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

}
