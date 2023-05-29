package com.nwt.desafio.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	GroupedOpenApi applicationApi() {
	    String packagesToScan[] = {"com.nwt.desafio.controller"};
	    return GroupedOpenApi.builder()
	            .group("application")
	            .pathsToMatch("/rest-api/application/**")
	            .packagesToScan(packagesToScan)
	            .build();
	}
	
	public OpenAPI customOpenApi() {
		
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Fluxo de Caixa REST API")
						.description("API para ocntrole fluxo de caixa"));
	}
}
