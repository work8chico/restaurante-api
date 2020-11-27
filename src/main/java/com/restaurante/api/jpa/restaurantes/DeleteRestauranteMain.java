package com.restaurante.api.jpa.restaurantes;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.infraestructure.repositories.RestauranteRepositoryImpl;

public class DeleteRestauranteMain {
	
	public static void main(String args[]) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepositoryImpl repo = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		Restaurante restaurante = repo.find(2L);
		
		repo.remove(restaurante);
		
		List<Restaurante> restaurantes = repo.findAll();
		
		for (Restaurante rest : restaurantes) {
			System.out.println("Restaurante ID: " + rest.getId() + " -- " + "Restaurante NOME: " + rest.getNome());
		}
	}

}
