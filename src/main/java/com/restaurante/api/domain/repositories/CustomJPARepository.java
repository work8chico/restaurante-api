package com.restaurante.api.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJPARepository<T, ID> extends JpaRepository<T, ID>{
	
	Optional<T> buscarPrimeiro();

}
