package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.infraestructure.repositories.CozinhaRepositoryImpl;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl repo = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		List<Cozinha> cozinhas = repo.findAll();
		
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}

	}

}
