package com.restaurante.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.api.model.Cozinha;

@Repository
public interface CozinhaRespository extends JpaRepository<Cozinha, Long> {

}
