package com.restaurante.api.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.PermissaoNaoEncontradaException;
import com.restaurante.api.domain.model.Permissao;
import com.restaurante.api.domain.repositories.PermissaoRepository;
import com.restaurante.api.domain.services.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private PermissaoService permissaoService;

	@GetMapping
	public ResponseEntity<List<Permissao>> buscar() {
		List<Permissao> permissoes = permissaoRepository.findAll();

		return ResponseEntity.ok(permissoes);
	}

	@GetMapping("/{permissaoId}")
	public ResponseEntity<Permissao> buscarPorId(@PathVariable Long permissaoId) {
		Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);

		if (permissao.isPresent()) {
			return ResponseEntity.ok(permissao.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Permissao> inserir(@RequestBody Permissao permissao) {
		Permissao permissaoSalva = permissaoService.salvar(permissao);
		return ResponseEntity.status(HttpStatus.CREATED).body(permissaoSalva);
	}

	@PutMapping("/{permissaoId}")
	public ResponseEntity<Permissao> atualizar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {

		Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);

		if (permissaoAtual.isPresent()) {
			Permissao permissaoSalva = permissaoAtual.get();
			BeanUtils.copyProperties(permissao, permissaoSalva, "id");
			permissaoSalva = permissaoService.salvar(permissaoSalva);

			return ResponseEntity.ok(permissaoSalva);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<?> remover(@PathVariable Long permissaoId) {
		try {
			permissaoService.remover(permissaoId);
			return ResponseEntity.noContent().build();

		} catch (PermissaoNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
