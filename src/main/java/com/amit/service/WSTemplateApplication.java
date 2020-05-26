package com.amit.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.amit.service.config.ProjectConfig;

@SpringBootApplication
public class WSTemplateApplication {
	
	@Autowired 
	private ProjectConfig config; 
	
	public static void main(String[] args) {
		SpringApplication.run(WSTemplateApplication.class, args);
	}
	 
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US); // Set default Locale as US
		return slr;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames(config.getMessage().getErrorFile()); 
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");  
		return lci;
	}
}
