package com.restaurante.api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String message) {
		super(message);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
	
		this(String.format("Não existe um cadastro de estado com o código %d", estadoId));
		
	}
}
