package com.ead.authuser.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	
	@Bean  //metodo produtor para retornar o rest template para usar a injecao (restCliente)
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		//aqui ele cria o rest e retorna mas ,pode costomizar e depois passar no builder.
		return builder.build();
	}
}
