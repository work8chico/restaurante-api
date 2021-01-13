package com.restaurante.api.infraestructure.repositories;

import static com.restaurante.api.infraestructure.repositories.spec.RestauranteSpec.comFreteGratis;
import static com.restaurante.api.infraestructure.repositories.spec.RestauranteSpec.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repositories.RestauranteRepository;
import com.restaurante.api.domain.repositories.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
//	@Override
//	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
//
//		var jpql = new StringBuilder();
//		jpql.append("from Restaurante where 0 = 0 ");
//
//		var parametros = new HashMap<String, Object>();
//
//		if (StringUtils.hasLength(nome)) {
//			jpql.append(" and nome like :nome ");
//			parametros.put("nome", "%" + nome + "%");
//		}
//
//		if (taxaInicial != null) {
//			jpql.append(" and taxaFrete >= :taxaInicial ");
//			parametros.put("taxaInicial", taxaInicial);
//		}
//
//		if (taxaFinal != null) {
//			jpql.append(" and taxaFrete <= :taxaFinal ");
//			parametros.put("taxaFinal", taxaFinal);
//		}
//
//		TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
//
//		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//
//		return query.getResultList();
//	}

	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if(taxaInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));			
		}
		
		if(taxaFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
		return query.getResultList();
				
		
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

}
