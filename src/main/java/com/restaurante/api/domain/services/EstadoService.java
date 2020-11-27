package com.restaurante.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado save(Estado estado) {
		
		return estadoRepository.save(estado);
	}
	
	public void remove(Long id) {
		try {
			
			estadoRepository.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("O estado de código %d não foi encontrado", id));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("O estado de código %d não pode ser excluído", id));
		}
	}
}
