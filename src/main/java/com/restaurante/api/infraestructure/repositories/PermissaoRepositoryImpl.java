package com.restaurante.api.infraestructure.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.api.domain.model.Permissao;
import com.restaurante.api.domain.repositories.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Permissao> findAll() {		
		return em.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao find(Long id) {
		
		return em.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao save(Permissao permissao) {
		return em.merge(permissao);
	}

	@Override
	@Transactional
	public void delete(Permissao permissao) {
		
		Permissao permissao1 = em.find(Permissao.class, permissao.getId());
		em.remove(permissao1);
		
	}
	

}
