package com.restaurante.api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String message) {
		super(message);
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
	
		this(String.format("Não existe um cadastro de permissão com o código %d", permissaoId));
		
	}
}
