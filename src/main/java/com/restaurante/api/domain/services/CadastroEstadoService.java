package com.restaurante.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.exceptions.EstadoNaoEncontradoException;
import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	
	private static final String MSG_ESTADO_EM_USO
	= "O estado de código %d não pode ser removido pois está em uso.";

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		
		return estadoRepository.save(estado);
	}
	
	public void remover(Long estadoId) {
		try {
			
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException	(estadoId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
	
	public Estado buscarOufalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradoException(
						String.format("O estado de código %d não foi encontrado", estadoId)));
	}
}
