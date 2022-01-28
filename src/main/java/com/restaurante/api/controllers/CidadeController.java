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

import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.domain.repositories.CidadeRepository;
import com.restaurante.api.domain.services.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public List<Cidade> buscar() {
		
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		
		return cadastroCidade.buscarOuFalhar(cidadeId);
		
	}

//	@PostMapping
//	public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
//
//		try {
//			Cidade cidadeSalva = cadastroCidade.salvar(cidade);
//			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
//
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		} catch (IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());			
//		}
//
//	}

	@PostMapping
	public Cidade salvar(@RequestBody Cidade cidade) {		
			return cadastroCidade.salvar(cidade);
	}
	
	
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);	
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return cadastroCidade.salvar(cidadeAtual);
				
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			cadastroCidade.remover(id);		
	}
}
