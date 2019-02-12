## LDBZ-SHOP
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/laodaobazi/ldbz-shop.svg?style=social&label=Stars)](https://github.com/laodaobazi/ldbz-shop)[![GitHub forks](https://img.shields.io/github/forks/laodaobazi/ldbz-shop.svg?style=social&label=Fork)](https://github.com/laodaobazi/ldbz-shop)

使用技术(个人时间好精力有限，项目不定期更新中....):

* 后台
	* 使用`Spring Boot` 构建整个项目 去除 XML 配置
	* `Maven`构建项目
	* `Jenkins`作为持续集成
	* 采用`Dubbo`作为RPC框架
	* 使用 `Apollo` 分布式配置中心
	* 使用`Spring`+`Spring MVC`+`MyBatis`SSM框架
	* 数据库连接池使用`druid`
	* 数据存储使用`MySQL`和`Redis`
	* 页面引擎采用 `Beetl`
	* 网页采用`freemarker`生成静态化页面
	* 采用`SolrCloud`实现搜索服务
	* `Swagger2` 生成 RESTful Apis文档
	* 负载均衡使用`Nginx`、`keepalived`实现高可用


## Web应用的端口
|名称|端口|
|:---------------:|:---------------:|
| Admin|8100 |
| Cart  |8101 |
| Item  |8102 |
| Order |8103|
| Portal |8104|
| Search |8105|
| SSO|8106|


## Dubbo服务端口
| 服务名称|Dubbo服务端口|
|:---------------:|:---------------:|
| Admin-Service      | 20880 |
| Advertisement-Service |20881 |
| Cart-Service     | 20882 |
| Item-Service     | 20883 |
| Notify-Service   | 20884 |
| Order-Service    | 20885 |
| Portal-Service   | 20886 |
| Redis-Service    | 20887 |
| Search-Service   | 20888 |
| SSO-Service      | 20889 |

## Dubbo Admin 管控台
![dubbo.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/dubbo.png)

## Apollo 配置中心
![apollo.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/apollo.png)

## SolrCloud 检索
![solrcloud.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/solrcloud.png)

## mavne 构建
![build.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/build.png)

## project 骨架
![frame.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/frame.png)

## 首页效果图
![index.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/index.png)

## 商品明细页效果图
![item.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/item.png)

## 商品检索页效果图
![search.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/search.png)

## 后台管理页效果图
![admin.png](https://github.com/laodaobazi/ldbz-shop/blob/master/ldbz-images/admin.png)
