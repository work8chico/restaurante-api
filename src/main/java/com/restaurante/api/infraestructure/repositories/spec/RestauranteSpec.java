package com.restaurante.api.infraestructure.repositories.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.restaurante.api.domain.model.Restaurante;

public class RestauranteSpec {
	
	public static Specification<Restaurante> comFreteGratis(){
		return (root, query, builder) ->
			builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return (root, query, builder) ->
			builder.like(root.get("nome"), "%" + nome + "%");
	}

}
