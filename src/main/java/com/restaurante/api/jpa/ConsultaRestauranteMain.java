package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.infraestructure.repositories.RestauranteRepositoryImpl;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepositoryImpl repo = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		List<Restaurante> restaurantes = repo.findAll();
		
		for (Restaurante restaurante : restaurantes) {
			System.out.printf("%s - %f - %s \n",
					restaurante.getNome(),
					restaurante.getTaxaFrete(),
					restaurante.getCozinha().getNome());
		}

	}

}
