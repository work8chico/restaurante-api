package com.restaurante.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repositories.CozinhaRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@GetMapping("/consulta/por-nome")
	public List<Cozinha> consultaPorNome(@RequestParam String nome){
		
		return cozinhaRepository.consultaPorNome(nome);
		
	}

}
