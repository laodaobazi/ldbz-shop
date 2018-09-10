package cn.ldbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableDubboConfiguration
//@EnableApolloConfig
@SpringBootApplication
@MapperScan(basePackages = "cn.ldbz.search.mapper")
public class XbinStoreServiceSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbinStoreServiceSearchApplication.class, args);
	}
	
}
