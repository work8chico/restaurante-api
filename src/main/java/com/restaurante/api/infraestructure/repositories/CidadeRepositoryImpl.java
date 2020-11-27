package com.restaurante.api.infraestructure.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.domain.repositories.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cidade> findAll() {		
		return em.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade find(Long id) {
		
		return em.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade save(Cidade cidade) {
		return em.merge(cidade);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		
		Cidade cidade = find(id);
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		em.remove(cidade);
		
	}
	

}
