package com.jlab.education;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
	"com.jlab.education.controller",
	"com.jlab.education.dao",
	"com.jlab.education.dto",
	"com.jlab.education.service",
})
public class JLabApplication {

	public static AnnotationConfigApplicationContext ctx;
	
	private static final Logger log = LoggerFactory.getLogger(JLabApplication.class);

	public static void main(String[] args) {
		ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(JLabApplication.class);
			ctx.refresh();
			
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			log.info("Add Beans: "+ Arrays.asList(beanNames));
			
		} finally {
			ctx.close();
		}
	}
}
