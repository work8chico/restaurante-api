package com.restaurante.api.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repositories.CozinhaRepository;
import com.restaurante.api.domain.services.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@GetMapping
	public ResponseEntity<List<Cozinha>> buscarTodos() {

		List<Cozinha> list = cozinhaRepository.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {

		return cozinhaService.buscarOufalhar(cozinhaId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
		cozinha = cozinhaService.salvar(cozinha);
		return ResponseEntity.ok().body(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@RequestBody Cozinha cozinha, @PathVariable Long cozinhaId) {

		Cozinha cozinhaAtual = cozinhaService.buscarOufalhar(cozinhaId);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			return cozinhaService.salvar(cozinhaAtual);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
			cozinhaService.remover(id);
	}

}
