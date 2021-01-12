package com.restaurante.api.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.api.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

	List<Cozinha> findTodasByNome(String nome);
	Optional<Cozinha> findCozinhaByNome(String nome);
	Optional<List<Cozinha>> findByNomeContains(String nome);
}
