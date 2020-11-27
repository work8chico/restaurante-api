package com.restaurante.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repositories.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha save(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void remove(Long id) {
		
		try {
			
			cozinhaRepository.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("A cozinha com o código %d não foi encontrada!", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("A cozinha com o código %d não pode ser removida pois está em uso no sistema", id));
		}
		
		
	}

}
