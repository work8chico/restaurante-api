package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.Permissao;
import com.restaurante.api.infraestructure.repositories.PermissaoRepositoryImpl;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepositoryImpl repo = applicationContext.getBean(PermissaoRepositoryImpl.class);
		
		List<Permissao> permissoes = repo.findAll();
		
		for (Permissao permissao: permissoes) {
			System.out.println(permissao.getNome());
		}

	}

}
