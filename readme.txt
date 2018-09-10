后台服务
1、ldbz-shop-service-redis ----XbinStoreServiceRedisApplication

2、ldbz-shop-service-sso ---- XbinStoreServiceSSOApplication

3、ldbz-shop-service-cart ---- XbinStoreServiceCartApplication

4、ldbz-shop-service-item ---- XbinStoreServiceItemApplication

5、ldbz-shop-service-order ---- XbinStoreServiceOrderApplication

6、ldbz-shop-service-protal ---- XbinStoreServicePortalApplication

7、ldbz-shop-service-search ---- XbinStoreServiceSearchApplication

8、ldbz-shop-service-notify ---- XbinStoreServiceNotifyApplication

9、ldbz-shop-service-admin ---- XbinStoreServiceAdminApplication


前端页面
1、ldbz-shop-web-sso ---- XbinStoreWebSSOApplication ---- 8104
		登录页面 ：/login
		注册页面 ：/register
		
2、ldbz-shop-web-portal ---- XbinStoreWebPortalApplication ---- 8101
		首页：/index

3、ldbz-shop-web-order ---- XbinStoreWebOrderApplication ---- 8108

4、ldbz-shop-web-item ---- XbinStoreWebItemApplication ---- 8103
		单个商品明细页面：/item/148630639229938

5、ldbz-shop-web-cart ---- XbinStoreWebCartApplication ---- 8017
		购物车：/cart







Dubbox服务地址
服务名称 					Dubbox服务端口 			rest服务端口
Admin-Service 			192.168.125.1:20880 	rest:8510
Redis-Service 			192.168.125.1:20881 		rest:8511
Search-Service 			192.168.125.1:20882 	rest:8512
Portal-Service 			192.168.125.1:20883 	rest:8513
Item-Service 			192.168.125.1:20884 	rest:8514
SSO-Service 			192.168.125.1:20885 	rest:8515
Notify-Service 			192.168.125.1:20886 	rest:8516
Cart-Service 			192.168.125.1:20887 	rest:8517
Order-Service 			192.168.125.1:20888 	rest:8518
Home -Service 			192.168.125.1:20889 	rest:8519
Recommended-Service 	192.168.125.1:20890 	rest:8520
AD-Service 				192.168.125.1:20891 		rest:8521
Ranking-Service 		192.168.125.1:20892 	rest:8522
Mymoney-Service 		192.168.125.1:20893 	rest:8523
Pay-Service 				192.168.125.1:20894 	rest:8524
Baitiao-Service 			192.168.125.1:20895 	rest:8525
Coupons-Service 	192.168.125.1:20896 	rest:8526
Seckill-Service 	192.168.125.1:20897 	rest:8527
CS-Service 	192.168.125.1:20898 	rest:8528





Tomcat地址(本机)
名称 			IP 							完成情况
Portal 		192.168.125.1:8101 	完成情况
Search 		192.168.125.1:8102 	完成情况
Item 		192.168.125.1:8103 	完成情况
SSO 		192.168.125.1:8104 	完成情况
Admin 		192.168.125.1:8105 	完成情况
Cart 			192.168.125.1:8106 	完成情况
Order 		192.168.125.1:8107 	完成情况
Recommended 	192.168.125.1:8109 	|
AD 			192.168.125.1:8110 	|
Ranking 	192.168.125.1:8111 	|
Mymoney 	192.168.125.1:8112 	|
Pay 			192.168.125.1:8113 	|
Baitiao 	192.168.125.1:8114 	|
Coupons 	192.168.125.1:8115 	|
Seckill 		192.168.125.1:8116 	|
CS 			192.168.125.1:8117 	|
API 			192.168.125.1:8118 	|









SpringBoot之class is not visible from class loader
    方案一，排查掉spring-boot-devtools模块模块的maven引入可以解决，这时候所有类都是使用APPClassloader加载。
    方案二，可以引入spring-boot-devtools模块，但是禁用禁用reStart功能 
    public static void main( String[] args )
   {
       System.setProperty("spring.devtools.restart.enabled", "false");
 
       SpringApplication.run(Application.class, args);
   }