package cn.ldbz.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.constant.Const;
import cn.ldbz.portal.service.IndexService;

@Component
@Service(version = Const.LDBZ_SHOP_PORTAL_VERSION)
public class IndexServiceImpl implements IndexService {

    private static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

}
