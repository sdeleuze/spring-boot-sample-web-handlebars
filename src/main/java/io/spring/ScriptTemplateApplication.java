package io.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

@SpringBootApplication
public class ScriptTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScriptTemplateApplication.class, args);
	}

	@Bean
	public ScriptTemplateConfigurer handlebarsConfigurer() {
	ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
		configurer.setEngineName("nashorn");
 		configurer.setScripts("/static/polyfill.js",
				"/META-INF/resources/webjars/handlebars/3.0.0-1/handlebars.js",
				"/static/render.js");
		configurer.setRenderFunction("render");
		return configurer;
	}

	@Bean
	public ViewResolver viewResolver() {
		ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
		viewResolver.setPrefix("/static/templates/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

}
