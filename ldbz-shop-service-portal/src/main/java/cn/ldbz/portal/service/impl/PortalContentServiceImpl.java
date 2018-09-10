package cn.ldbz.portal.service.impl;

import cn.ldbz.constant.Const;
import cn.ldbz.mapper.TbContentMapper;
import cn.ldbz.pojo.TbContent;
import cn.ldbz.pojo.TbContentExample;
import cn.ldbz.portal.service.PortalContentService;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.FastJsonConvert;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页内容Service
 */
@Component
@Service(version = Const.XBIN_STORE_PORTAL_VERSION)
@Transactional
public class PortalContentServiceImpl implements PortalContentService {

    private static Logger logger = Logger.getLogger(PortalContentServiceImpl.class);

    @Autowired
    private TbContentMapper contentMapper;

    @Reference(version = Const.XBIN_STORE_REDIS_VERSION)
    private JedisClient jedisClient;

    @Value("${redisKey.prefix.index_ad}")
    private String INDEX_AD;


    @Override
    public List<TbContent> getContentByCid(long bigAdIndex) {

        //先查询缓存

        try {
            logger.info("=====>查询Redis");
            String list = jedisClient.hget(INDEX_AD, bigAdIndex + "");

            if (StringUtils.isNotBlank(list)) {

                logger.info("=======>查询Redis 返回结果");
                return FastJsonConvert.convertJSONToArray(list, TbContent.class);
            }

        } catch (Exception e) {
            logger.error(e);
        }


        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(bigAdIndex);

        logger.info("=======>查询数据库");
        List<TbContent> list = contentMapper.selectByExample(example);

        //添加缓存
        try {
            logger.info("=======>添加缓存");
            jedisClient.hset(INDEX_AD, bigAdIndex + "", FastJsonConvert.convertObjectToJSON(list));
        } catch (Exception e) {
            logger.error(e);
        }

        return list;

    }

}
