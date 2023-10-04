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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.FormaPagamento;
import com.restaurante.api.domain.repositories.FormaPagamentoRepository;
import com.restaurante.api.domain.services.FormaPagamentoService;

@RestController
@RequestMapping("/formas-de-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@GetMapping
	public ResponseEntity<List<FormaPagamento>> buscar() {
		List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
		return ResponseEntity.ok(formasPagamento);
	}

	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Long formaPagamentoId) {
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);

		if (formaPagamento.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(formaPagamento.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<FormaPagamento> salvar(@RequestBody FormaPagamento formaPagamento) {

		FormaPagamento formaPagamentoSalva = formaPagamentoService.salvar(formaPagamento);
		return ResponseEntity.ok(formaPagamentoSalva);

	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<?> atualizar(@RequestBody FormaPagamento formaPagamento, @PathVariable Long formaPagamentoId){
		
		Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);
		
		if(formaPagamentoAtual.isPresent()) {
			FormaPagamento formaPagamentoSalva = formaPagamentoAtual.get();
			BeanUtils.copyProperties(formaPagamento, formaPagamentoSalva, "id");
			formaPagamentoSalva = formaPagamentoService.salvar(formaPagamentoSalva);
			return ResponseEntity.ok(formaPagamentoSalva);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<?> remover(@PathVariable Long formaPagamentoId){
		
		try {
			formaPagamentoService.remover(formaPagamentoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}

}
