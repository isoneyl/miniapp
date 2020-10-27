/**
 * All rights Reserved, Designed By www.rongdasoft.com 
 * @version V1.0
 * @Title: FilterConfig.java
 * @Description:
 * @author: hecong
 * @date: 2019年3月16日
 * @Copyright: 2019年3月16日 www.rongdasoft.com Inc. All rights reserved.
 */
package com.mini.app.common.config;

import com.mini.app.common.filter.HttpServletFilter;
import com.mini.app.common.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FilterConfig
 * @Description: 过滤器配置
 */
@Configuration
public class FilterConfig {

	public FilterConfig() {
		System.out.println("过滤器实例化.....");
	}

	@Bean
	public FilterRegistrationBean registFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new HttpServletFilter());
		registration.addUrlPatterns("/*");
		registration.setName("HttpServletFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean xssFilter() {
		FilterRegistrationBean xssFilter = new FilterRegistrationBean();
		xssFilter.setFilter(new XSSFilter());
		xssFilter.addUrlPatterns("/*");
		xssFilter.setName("xssFilter");
		xssFilter.setOrder(2);
		return xssFilter;
	}
}
