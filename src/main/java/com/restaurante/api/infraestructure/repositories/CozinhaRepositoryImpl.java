package com.restaurante.api.infraestructure.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repositories.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> findAll() {		
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha find(Long id) {
		
		return manager.find(Cozinha.class, id);
	}

	@Override
	@Transactional
	public Cozinha save(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		
		Cozinha cozinha = manager.find(Cozinha.class, id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinha);
		
	}

	@Override
	public List<Cozinha> consultaPorNome(String nome) {
		
		return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	
	

}
