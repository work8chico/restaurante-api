package com.restaurante.api.infraestructure.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.restaurante.api.domain.repositories.CustomJPARepository;

public class CustomJPARepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
	implements CustomJPARepository<T, ID> {
	
	private EntityManager em;

	public CustomJPARepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		
		super(entityInformation, entityManager);
		 this.em = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {

		var jpql = "from " + getDomainClass().getName();
		
		T entity = em.createQuery(jpql, getDomainClass())
				.setMaxResults(1)
				.getSingleResult();
 
		
		return Optional.ofNullable(entity);
	}
	
	
	
	

}
