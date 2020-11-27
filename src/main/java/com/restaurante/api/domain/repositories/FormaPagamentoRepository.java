package com.restaurante.api.domain.repositories;

import java.util.List;

import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
	
	List<FormaPagamento> findAll();
	FormaPagamento find(Long id);
	FormaPagamento save(FormaPagamento formaPagamento);
	void delete(FormaPagamento formaPagamento);

}
