package com.restaurante.api.domain.repositories;

import java.util.List;

import com.restaurante.api.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> findAll();
	Estado find(Long id);
	Estado save(Estado estado);
	void remove(Long id);

}
