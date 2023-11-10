package com.restaurante.api.domain.exceptions;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String message) {
		super(message);
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
	
		this(String.format("Não existe um cadastro de permissão com o código %d", permissaoId));
		
	}
}
