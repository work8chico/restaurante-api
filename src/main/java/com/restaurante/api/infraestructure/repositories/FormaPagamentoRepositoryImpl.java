package com.restaurante.api.infraestructure.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.api.domain.model.FormaPagamento;
import com.restaurante.api.domain.repositories.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FormaPagamento> findAll() {		
		return em.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}

	@Override
	public FormaPagamento find(Long id) {
		
		return em.find(FormaPagamento.class, id);
	}

	@Override
	@Transactional
	public FormaPagamento save(FormaPagamento formaPagamento) {
		return em.merge(formaPagamento);
	}

	@Override
	@Transactional
	public void delete(FormaPagamento formaPagamento) {
		
		FormaPagamento formaPagamento1 = em.find(FormaPagamento.class, formaPagamento.getId());
		em.remove(formaPagamento1);
		
	}
	

}
