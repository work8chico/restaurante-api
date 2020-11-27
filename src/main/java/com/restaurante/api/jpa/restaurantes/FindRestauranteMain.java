package com.restaurante.api.jpa.restaurantes;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.infraestructure.repositories.RestauranteRepositoryImpl;

public class FindRestauranteMain {
	
	public static void main(String args[]) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepositoryImpl repo = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		Restaurante restaurante = repo.find(4L);
		
		System.out.println("Restaurante encontrado: " + restaurante.getNome());
		
	}
}
