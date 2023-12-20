package com.restaurante.api.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.api.domain.exceptions.CozinhaNaoEncontradaException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.exceptions.NegocioException;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repositories.RestauranteRepository;
import com.restaurante.api.domain.services.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> buscar() {

		return restauranteRepository.findAll();

	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscarPorId(@PathVariable Long restauranteId) {

		return cadastroRestaurante.buscarOufalhar(restauranteId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {

		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@RequestBody Restaurante restaurante, @PathVariable Long restauranteId) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOufalhar(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		try {
			return cadastroRestaurante.salvar(restauranteAtual);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOufalhar(restauranteId);

		merge(campos, restauranteAtual, request);

		try {
			return atualizar(restauranteAtual, restauranteId);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);			
			
			Restaurante restaurante = objectMapper.convertValue(camposOrigem, Restaurante.class);
	
			camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
	
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
	
				Object novoValor = ReflectionUtils.getField(field, restaurante);
	
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
	
				System.out.println(nomePropriedade + " = " + " " + valorPropriedade + " = " + novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}

}
