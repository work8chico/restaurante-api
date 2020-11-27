package com.restaurante.api.domain.repositories;

import java.util.List;

import com.restaurante.api.domain.model.Cidade;

public interface CidadeRepository {
	
	List<Cidade> findAll();
	Cidade find(Long id);
	Cidade save(Cidade cidade);
	void remove(Long id);

}
