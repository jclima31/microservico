package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleContextResolver localResolver = new AcceptHeaderLocaleContextResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localeResolver();
	}
	
	
	/*Nao e mais necessario por foi adicionado
	 * spring.messages.basename=messages no application properties
	 * public ResourceBundleMessageSource bundleMessageSource(){
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}*/
	
}
