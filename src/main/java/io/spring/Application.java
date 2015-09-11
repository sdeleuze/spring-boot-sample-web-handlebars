package io.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ScriptTemplateConfigurer viewConfigurer() {
	ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
		configurer.setEngineName("nashorn");
 		configurer.setScripts("/static/polyfill.js",
				"/META-INF/resources/webjars/handlebars/3.0.0-1/handlebars.js",
				"/static/render.js");
		configurer.setRenderFunction("render");
		configurer.setSharedEngine(false);
		return configurer;
	}

	@Bean
	ViewResolver viewResolver() {
		ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
		viewResolver.setPrefix("/static/templates/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Bean
	WebMvcConfigurer mvcConfig() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addRedirectViewController("/", "home");
			}
		};
	}

}
