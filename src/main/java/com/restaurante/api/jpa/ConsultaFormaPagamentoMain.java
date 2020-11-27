package com.restaurante.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.restaurante.api.RestauranteApiApplication;
import com.restaurante.api.domain.model.FormaPagamento;
import com.restaurante.api.infraestructure.repositories.FormaPagamentoRepositoryImpl;

public class ConsultaFormaPagamentoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestauranteApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepositoryImpl repo = applicationContext.getBean(FormaPagamentoRepositoryImpl.class);
		
		List<FormaPagamento> formasPagamento = repo.findAll();
		
		for (FormaPagamento formaPagamento: formasPagamento) {
			System.out.println(formaPagamento.getDescricao());
		}

	}

}
