package com.lxy.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.lxy.filter.RequestHeaderFilter;

//@Configuration
public class FilterConfig {

	/**
	 * 请求头过滤
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<RequestHeaderFilter> requestHeaderFilterConfig() {
		FilterRegistrationBean<RequestHeaderFilter> filterRegBean = new FilterRegistrationBean<>();
		   filterRegBean.setFilter(new RequestHeaderFilter());
		   filterRegBean.addUrlPatterns("/*");
		   filterRegBean.addInitParameter("targetFilterLifecycle","true");
		   filterRegBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/app/home");
//		   filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
		   filterRegBean.setOrder(1);
		   return filterRegBean;
	}

}
