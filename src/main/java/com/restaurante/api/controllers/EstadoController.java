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

import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.EstadoRepository;
import com.restaurante.api.domain.services.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@GetMapping
	public ResponseEntity<List<Estado>> buscar() {

		List<Estado> estados = estadoRepository.findAll();
		return ResponseEntity.ok().body(estados);
	}

	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable Long estadoId) {

		return cadastroEstado.buscarOufalhar(estadoId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
	}

	@PutMapping("/{estadoId}")
	public Estado atualizar(@RequestBody Estado estado, @PathVariable Long estadoId) {

		Estado estadoAtual = cadastroEstado.buscarOufalhar(estadoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");

		return cadastroEstado.salvar(estadoAtual);

	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.remover(estadoId);
	}

}
