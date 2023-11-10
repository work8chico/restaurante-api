package com.restaurante.api.domain.exceptions;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String message) {
		super(message);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
	
		this(String.format("Não existe um cadastro de cidade com o código %d", cidadeId));
		
	}
}
