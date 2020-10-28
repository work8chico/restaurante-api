package com.restaurante.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.api.model.Cozinha;
import com.restaurante.api.repositories.CozinhaRespository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRespository repo;
	
	public Cozinha insert(Cozinha cozinha){		
		return repo.save(cozinha);				
	}
	
	public List<Cozinha> findAll(){		
		List<Cozinha> list = repo.findAll();		
		return list;
	}
	
	public Optional<Cozinha> findById(Long id) {
		return repo.findById(id);
	}	

}
