package com.restaurante.api.infraestructure.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repositories.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Restaurante> findAll() {
		return em.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante find(Long id) {
		return em.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante save(Restaurante restaurante) {
		return em.merge(restaurante);
	}

	@Override
	@Transactional
	public void remove(Restaurante restaurante) {
		Restaurante res = find(restaurante.getId());
		em.remove(res);
	}
	

}
