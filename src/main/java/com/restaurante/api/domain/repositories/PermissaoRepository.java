package com.restaurante.api.domain.repositories;

import java.util.List;

import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.domain.model.FormaPagamento;
import com.restaurante.api.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> findAll();
	Permissao find(Long id);
	Permissao save(Permissao permissao);
	void delete(Permissao permissao);

}
