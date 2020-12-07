package com.imperialof.online.ImperialOF.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${app.config.cors.origin}")
	private String[] corsOrigins;

	@Value("${app.config.cors.allow_headers}")
	private String[] corsHeaders;

	@Value("${app.config.cors.methods}")
	private String[] corsMethods;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(corsOrigins)
				.allowedMethods(corsMethods)
				.allowedHeaders(corsHeaders);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
