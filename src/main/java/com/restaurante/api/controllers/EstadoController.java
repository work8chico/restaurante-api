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
import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.EstadoRepository;
import com.restaurante.api.domain.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public ResponseEntity<List<Estado>> findAll() {

		List<Estado> estados = estadoRepository.findAll();
		return ResponseEntity.ok().body(estados);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> find(@PathVariable Long id) {

		Estado estado = estadoRepository.find(id);

		if (estado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Estado> save(@RequestBody Estado estado) {
		estado = estadoService.save(estado);
		return ResponseEntity.ok(estado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@RequestBody Estado estado, @PathVariable Long id) {

		Estado estadoAtual = estadoRepository.find(id);

		if (estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			estadoAtual = estadoService.save(estadoAtual);

			return ResponseEntity.ok(estadoAtual);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {

		try {

			estadoService.remove(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
