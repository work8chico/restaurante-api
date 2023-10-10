package com.restaurante.api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FormaDePagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FormaDePagamentoNaoEncontradaException(String message) {
		super(message);
	}
	
	public FormaDePagamentoNaoEncontradaException(Long formaDePagamentoId) {
	
		this(String.format("Não existe um cadastro de Forma De Pagamento com o código %d", formaDePagamentoId));
		
	}
}
