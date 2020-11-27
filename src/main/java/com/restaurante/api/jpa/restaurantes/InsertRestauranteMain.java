package com.restaurante.api.jpa.restaurantes;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.infraestructure.repositories.RestauranteRepositoryImpl;

public class InsertRestauranteMain {

	public static void main(String args[]) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepositoryImpl repo = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		var r1 = new Restaurante();
		var r2 = new Restaurante();
		
		Restaurante r1Salvo = repo.save(r1);
		Restaurante r2Salvo = repo.save(r2);
		
		System.out.println(r1Salvo.getNome());
		System.out.println(r2Salvo.getNome());
	}
}
