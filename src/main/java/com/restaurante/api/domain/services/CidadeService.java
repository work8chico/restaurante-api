package com.restaurante.api.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.CidadeRepository;
import com.restaurante.api.domain.repositories.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("O Estado com o código %d não foi encontrado", estadoId));
		}
		
		cidade.setEstado(estado.get());
		return cidadeRepository.save(cidade);
		
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("A cidade com o código %d não foi encontrada!", id));
		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format("A cidade com o código %d não pode ser removida pois está em uso no sistema", id));
		}
	}
	
}
