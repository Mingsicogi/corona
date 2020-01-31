package com.mins.corona.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * MVC 필요한 설정(정적 자원, view uri, message resource, interceptor)
 *
 * @author 전민석
 *
 */
@ComponentScan(basePackages = {"com.mins"}, includeFilters = {@Filter(value = org.springframework.stereotype.Controller.class)})
@EnableWebMvc
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * HTTP GET 요청에서 사용할 리소스내용을 위해서 /resources 디렉토리의 파일을 가져온다. 
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//@formatter:off
		registry
			.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
					.setCachePeriod(3600)
					.resourceChain(true)
					.addResolver(new PathResourceResolver());
		registry
			.addResourceHandler("/favicon.ico")
				.addResourceLocations("/resources/")
					.setCachePeriod(3600)
					.resourceChain(true)
					.addResolver(new PathResourceResolver());
		//@formatter:on
	}

	/**
	 * ViewResolver Setting Bean
	 *
	 * @return
	 */
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
}
