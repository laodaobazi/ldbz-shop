启动
1、ldbz-shop-service-redis		缓存
2、ldbz-shop-web-portal		网站首页
3、ldbz-shop-service-item		商品服务
4、ldbz-shop-service-advertisement	广告服务
5、ldbz-shop-web-admin		后台管理




SpringBoot之class is not visible from class loader
    方案一，排查掉spring-boot-devtools模块模块的maven引入可以解决，这时候所有类都是使用APPClassloader加载。
    方案二，可以引入spring-boot-devtools模块，但是禁用禁用reStart功能 
    public static void main( String[] args )
   {
       System.setProperty("spring.devtools.restart.enabled", "false");
 
       SpringApplication.run(Application.class, args);
   }
   
   dubbo默认超时会重试两次，这样的重试有时会产生很大问题，所以建议关闭。关闭有两种方式：

在服务上加注解:
@Service(interfaceClass = AccountUpdateApi.class,retries = -1)
注意这里重试次数写成-1，经测试，dubbo版本(2.6.0)写成0无效。
另一种方式，配置文件：spring.dubbo.provider.retries=-1
   
阿波罗在本机存放配置的位置：对于Windows，文件位置为C:\opt\settings\server.properties

privateKey:MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEArGK/GML72YZwqKe8e2oKUGhDscCMijl6G/QcVkR+gG4Vj+CGL11Hg5jXADk6P9lJQv6uipB0qtYdor9bpqdpwQIDAQABAkAFWEp0ihck93YmxIyjhvYq6BCxQjcZUg7nLvpx8
13zUis12/6kC7er75/UsnnaKg6oL3lBi28MzIPBAiEA2m8MuxZrfo7GQSWs8ipqawNrpyf31sumct79mXMk1ycCIQDKCFiUVWbiJk7+quF7h2lariZceEbgG+TnG9vjboaI1wIgB+flChBxq473VfxLGii22VCyYMZtjfSo/ZdcjmVAfBkCIHpOoSlzpixEm
25pPWn/mpCz8liDAhRmZeXTAiAOfPV6lXSpZlbWysHNMAatn0hTMSBaCBKEMlVegeg1cg==

