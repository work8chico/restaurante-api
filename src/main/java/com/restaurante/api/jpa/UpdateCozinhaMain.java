package com.restaurante.api.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.infraestructure.repositories.CozinhaRepositoryImpl;

public class UpdateCozinhaMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl repo = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		var c1 = new Cozinha(1L, "Brasileira");
		
		Cozinha cozinhaAtualizada = repo.save(c1);
		
		System.out.println(cozinhaAtualizada.getNome());
		

	}

}
