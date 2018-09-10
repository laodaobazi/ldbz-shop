package cn.ldbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.container.Main;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableDubboConfiguration
//@EnableApolloConfig
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "cn.ldbz.mapper")
public class XbinStoreServiceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbinStoreServiceAdminApplication.class, args);
		Main.main(args);
	}
	
}
