package com.restaurante.api.domain.exceptions;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String message) {
		super(message);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
	
		this(String.format("Não existe um cadastro de estado com o código %d", estadoId));
		
	}
}
