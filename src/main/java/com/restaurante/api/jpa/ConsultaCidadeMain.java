package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.infraestructure.repositories.CidadeRepositoryImpl;

public class ConsultaCidadeMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepositoryImpl repo = applicationContext.getBean(CidadeRepositoryImpl.class);
		
		List<Cidade> cidades = repo.findAll();
		
		for (Cidade cidade: cidades) {
			System.out.println(cidade.getNome());
		}

	}

}
