package com.restaurante.api.domain.exceptions;

public class FormaDePagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FormaDePagamentoNaoEncontradaException(String message) {
		super(message);
	}
	
	public FormaDePagamentoNaoEncontradaException(Long formaDePagamentoId) {
	
		this(String.format("Não existe um cadastro de Forma De Pagamento com o código %d", formaDePagamentoId));
		
	}
}
