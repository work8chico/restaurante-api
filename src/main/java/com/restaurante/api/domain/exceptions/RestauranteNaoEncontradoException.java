package com.restaurante.api.domain.exceptions;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String message) {
		super(message);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
	
		this(String.format("Não existe um cadastro de restaurante com o código %d", restauranteId));
		
	}
}
