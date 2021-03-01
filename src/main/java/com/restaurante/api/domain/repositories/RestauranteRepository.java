package com.restaurante.api.domain.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurante.api.domain.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends CustomJPARepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante>{
	
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findByTaxaFreteBetweenAndCozinhaNomeContains(BigDecimal taxaInicial, BigDecimal taxaFinal, String nome);
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :cozinha")
	List<Restaurante> consultaPorNome(String nome, Long cozinha);
	
//	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	
}
