package com.amit.service.utils.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.serializationInclusion(Include.NON_EMPTY);
		builder.indentOutput(true).dateFormat(new SimpleDateFormat("dd-MM-yyyy HH:mm"));
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
		converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
	}

	@Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.favorPathExtension(false).
            favorParameter(false).
            ignoreAcceptHeader(false).
            useJaf(false);
	}

	@Bean
	public HttpSessionListener javaMelodyListener() {
		return new SessionListener();
	}

	@Bean
	public Filter javaMelodyFilter() {
		return new MonitoringFilter();
	}

}
