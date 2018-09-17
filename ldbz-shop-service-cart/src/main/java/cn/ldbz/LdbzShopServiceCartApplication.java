package cn.ldbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@EnableDubboConfiguration
@EnableApolloConfig
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "cn.ldbz.mapper")
public class LdbzShopServiceCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdbzShopServiceCartApplication.class, args);
	}
	
}
