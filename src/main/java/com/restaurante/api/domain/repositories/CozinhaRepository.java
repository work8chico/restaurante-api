package com.restaurante.api.domain.repositories;

import java.util.List;

import com.restaurante.api.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> findAll();
	List<Cozinha> consultaPorNome(String nome);
	Cozinha find(Long id);
	Cozinha save(Cozinha cozinha);
	void remove(Long id);

}
