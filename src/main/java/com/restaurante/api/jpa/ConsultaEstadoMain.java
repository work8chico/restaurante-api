package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.infraestructure.repositories.EstadoRepositoryImpl;

public class ConsultaEstadoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepositoryImpl repo = applicationContext.getBean(EstadoRepositoryImpl.class);
		
		List<Estado> estados= repo.findAll();
		
		for (Estado estado: estados) {
			System.out.println(estado.getNome());
		}

	}

}
