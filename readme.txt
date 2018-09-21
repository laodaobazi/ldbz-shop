
SpringBoot之class is not visible from class loader
    方案一，排查掉spring-boot-devtools模块模块的maven引入可以解决，这时候所有类都是使用APPClassloader加载。
    方案二，可以引入spring-boot-devtools模块，但是禁用禁用reStart功能 
    public static void main( String[] args )
   {
       System.setProperty("spring.devtools.restart.enabled", "false");
 
       SpringApplication.run(Application.class, args);
   }
   
   
privateKey:MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEArGK/GML72YZwqKe8e2oKUGhDscCMijl6G/QcVkR+gG4Vj+CGL11Hg5jXADk6P9lJQv6uipB0qtYdor9bpqdpwQIDAQABAkAFWEp0ihck93YmxIyjhvYq6BCxQjcZUg7nLvpx8
13zUis12/6kC7er75/UsnnaKg6oL3lBi28MzIPBAiEA2m8MuxZrfo7GQSWs8ipqawNrpyf31sumct79mXMk1ycCIQDKCFiUVWbiJk7+quF7h2lariZceEbgG+TnG9vjboaI1wIgB+flChBxq473VfxLGii22VCyYMZtjfSo/ZdcjmVAfBkCIHpOoSlzpixEm
25pPWn/mpCz8liDAhRmZeXTAiAOfPV6lXSpZlbWysHNMAatn0hTMSBaCBKEMlVegeg1cg==
