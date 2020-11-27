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

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repositories.CozinhaRepository;
import com.restaurante.api.domain.services.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;

	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping
	public ResponseEntity<List<Cozinha>> findAll() {

		List<Cozinha> list = repository.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findyById(@PathVariable Long id) {

		Cozinha cozinha = repository.find(id);

		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Cozinha> save(@RequestBody Cozinha cozinha) {

		cozinha = cozinhaService.save(cozinha);
		return ResponseEntity.ok().body(cozinha);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@RequestBody Cozinha cozinha, @PathVariable Long id) {

		Cozinha obj = repository.find(id);

		if (obj != null) {

			BeanUtils.copyProperties(cozinha, obj, "id");
			obj = repository.save(obj);

			return ResponseEntity.ok().body(obj);

		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remove(@PathVariable Long id) {

		try {

			cozinhaService.remove(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}

	}

}
