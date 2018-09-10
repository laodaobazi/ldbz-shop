package cn.ldbz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableDubboConfiguration
//@EnableApolloConfig
@SpringBootApplication
//@EnableWebMvc
public class XbinStoreWebAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbinStoreWebAdminApplication.class, args);
	}
	
}
