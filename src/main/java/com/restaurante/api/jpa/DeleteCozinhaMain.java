package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.infraestructure.repositories.CozinhaRepositoryImpl;

public class DeleteCozinhaMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl repo = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		var c1 = new Cozinha();
		c1.setId(1L);
		
//		repo.remove(c1);
		
		List<Cozinha> cozinhas = repo.findAll();
		
		System.out.println("O tamanho da lista é " + cozinhas.size() + "\n");
		
		for(Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}

	}

}
