## LDBZ-SHOP
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/laodaobazi/ldbz-shop.svg?style=social&label=Stars)](https://github.com/laodaobazi/ldbz-shop)[![GitHub forks](https://img.shields.io/github/forks/laodaobazi/ldbz-shop.svg?style=social&label=Fork)](https://github.com/laodaobazi/ldbz-shop)

使用技术(个人时间和精力有限，项目不定期更新中....):

* 后台
	* ![使用`Spring Boot` 构建整个项目 去除 XML 配置](https://github.com/spring-projects/spring-boot)
	* ![`Maven`构建项目](http://maven.apache.org/)
	* `Jenkins`作为持续集成
	* ![采用`Dubbo`作为RPC框架](http://dubbo.apache.org/zh-cn/)
	* ![使用 `Apollo` 分布式配置中心](https://github.com/ApolloAuto/apollo/blob/master/README_cn.md)
	* 使用`Spring`+`Spring MVC`+`MyBatis`SSM框架
	* ![数据库连接池使用`druid`](https://github.com/alibaba/druid/)
	* 数据存储使用`MySQL`和`Redis`
	* ![页面引擎采用 `Beetl`](http://ibeetl.com/guide/)
	* 网页采用`freemarker`生成静态化页面
	* ![采用`SolrCloud`实现搜索服务](https://lucene.apache.org/solr/)
	* ![`Swagger2` 生成 RESTful Apis文档](https://swagger.io/)
	* 负载均衡使用`Nginx`、`keepalived`实现高可用
	* ![tcc-transaction分布式事务](https://github.com/changmingxie/tcc-transaction/blob/master-1.2.x/README.md)


## Web应用的端口
|	名称	|	端口	|	说明	|
|:---------------:|:---------------:|:---------------:|
| Admin	|	8100 |	管理端	|
| Cart  |	8101 |	购物车	|
| Item  |	8102 |	商品详细	|
| Order |	8103	|	订单	|
| Portal |	8104	|	首页	|
| Search |	8105	|		检索商品	|
| SSO	|	8106	|	单点	|
| Wishlist	|	8107	|	商品收藏	|


## Dubbo服务端口
| 服务名称|Dubbo服务端口|服务说明|Web端口|
|:---------------:|:---------------:|:---------------:|:---------------:|
| Admin-Service      | 20880 |管理端服务| 8510 |
| Advertisement-Service |20881 |广告服务| 8511 |
| Cart-Service     | 20882 |购物车服务| 8512 |
| Item-Service     | 20883 |商品详细服务| 8513 |
| Notify-Service   | 20884 |消息服务| 8514 |
| Order-Service    | 20885 |订单服务| 8515 |
| Portal-Service   | 20886 |首页服务| 8516|
| Redis-Service    | 20887 |缓存服务| 8517|
| Search-Service   | 20888 |检索服务| 8518 |
| SSO-Service      | 20889 |单点服务| 8519 |
| Wishlist-Service      | 20890 |收藏服务| 8520 |

## Dubbo Admin 管控台
![dubbo.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/dubbo.png)

## Apollo 配置中心
![apollo.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/apollo.png)

## SolrCloud 检索
![solrcloud.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/solrcloud.png)

## maven 构建
![build.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/build.png)

## project 骨架
![frame.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/frame.png)

## 首页效果图
![index.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/index.png)

## 商品明细页效果图
![item.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/item.png)

## 商品检索页效果图
![search.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/search.png)

## 购物车页效果图
![cart.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/cart.png)

## 商品收藏页效果图
![wishlist.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/wishlist.png)

## 后台管理页效果图
![admin.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/admin.png)

## 微信交流群
![wechat.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/wechat.png)

##个人联系方式
### 📮：biao.li@neusoft.com
- 个人QQ [![](http://pub.idqqimg.com/wpa/images/group.png)](http://sighttp.qq.com/msgrd?v=1&uin=444823046)

- 如果这个项目让你有所收获，请Star✨ and Fork有时间我会持续更新下去的。
- 注：如果遇到问题还请Issues,我会尽快回复。