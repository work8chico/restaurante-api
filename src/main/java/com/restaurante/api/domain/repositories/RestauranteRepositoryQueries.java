package com.restaurante.api.domain.repositories;

import java.math.BigDecimal;
import java.util.List;

import com.restaurante.api.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findComFreteGratis(String nome);
}