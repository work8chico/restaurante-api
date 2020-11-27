package com.restaurante.api.domain.repositories;

import java.util.List;

import com.restaurante.api.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> findAll();
	Restaurante find(Long id);
	Restaurante save(Restaurante restaurante);
	void remove(Restaurante restaurante);

}
