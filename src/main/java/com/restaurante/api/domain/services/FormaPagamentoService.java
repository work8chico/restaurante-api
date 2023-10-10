package com.restaurante.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.FormaDePagamentoNaoEncontradaException;
import com.restaurante.api.domain.model.FormaPagamento;
import com.restaurante.api.domain.repositories.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public void remover(Long formaPagamentoId) {
		try {
			
			formaPagamentoRepository.deleteById(formaPagamentoId);
		} catch (EmptyResultDataAccessException e) {
			throw new FormaDePagamentoNaoEncontradaException(formaPagamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Forma de pagamento com o código %d está em uso no sistema e não pode ser removida", formaPagamentoId));
		}
	}
}
