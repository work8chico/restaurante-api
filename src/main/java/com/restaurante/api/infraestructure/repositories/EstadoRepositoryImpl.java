package com.restaurante.api.infraestructure.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Estado> findAll() {
		return entityManager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado find(Long id) {

		return entityManager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado save(Estado estado) {
		return entityManager.merge(estado);
	}

	@Override
	@Transactional
	public void remove(Long id) {

		Estado estado = find(id);

		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		entityManager.remove(estado);

	}

}
