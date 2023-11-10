package com.restaurante.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");
	
	private String uri;
	private String title;
	
	private ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" +path;
		this.title = title;
	}
	
	

}
