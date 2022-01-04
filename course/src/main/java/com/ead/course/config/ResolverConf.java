package com.ead.course.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@Configurable
public class ResolverConf extends WebMvcConfigurationSupport{ // Web.. para converter os dados que vem indiretamente pra java

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		argumentResolvers.add(new SpecificationArgumentResolver());//adicionando o metodo do specification
		PageableHandlerMethodArgumentResolver resolver =  new PageableHandlerMethodArgumentResolver(); // paginacao
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);                 //usando o super para acesar a classe principal
		
}
	 
	
}
