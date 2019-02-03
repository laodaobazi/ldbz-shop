package cn.ldbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@EnableDubboConfiguration
@EnableApolloConfig
@SpringBootApplication
@MapperScan(basePackages = "cn.ldbz.mapper")
public class LdbzShopServiceSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdbzShopServiceSearchApplication.class, args);
	}
	
}
