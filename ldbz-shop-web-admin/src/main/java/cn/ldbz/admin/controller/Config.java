package cn.ldbz.admin.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.ldbz.utils.StorageFactory;

@Configuration
public class Config {

	@Bean
	public StorageFactory storageFactory(){
		return new StorageFactory();
	}
}
