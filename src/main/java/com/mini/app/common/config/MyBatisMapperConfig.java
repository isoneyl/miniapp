package com.mini.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @Description tk.mapper配置
 * @Date 15:56 2020/10/21
 * @Param
 * @return
 **/
@Configuration
public class MyBatisMapperConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.mini.app.**.dao");// 扫描该路径下的dao
		Properties properties = new Properties();
		properties.setProperty("mappers", "com.mini.app.baseDao.BaseDao");// 通用dao
		properties.setProperty("notEmpty", "false");
		properties.setProperty("IDENTITY", "MYSQL");
		mapperScannerConfigurer.setProperties(properties);
		return mapperScannerConfigurer;
	}

}
