package com.restaurante.api.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.fasterxml.jackson.databind.ObjectMapper;
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
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@RequestBody Restaurante restaurante, @PathVariable Long restauranteId) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOufalhar(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		try {
			return cadastroRestaurante.salvar(restauranteAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOufalhar(restauranteId);

		merge(campos, restauranteAtual);

		try {
			return atualizar(restauranteAtual, restauranteId);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

//	@PostMapping
//	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
//
//		try {
//			Restaurante restauranteSalvo = cadastroRestaurante.salvar(restaurante);
//			return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//
//	}

//	@PutMapping("/{id}")
//	public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
//
//		try {
//			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
//
//			if (restauranteAtual.isPresent()) {
//
//				Restaurante restauranteSalvo = restauranteAtual.get();
//				BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "formasPagamento", "endereco",
//						"dataCadastro", "produtos");
//				restauranteSalvo = cadastroRestaurante.salvar(restauranteSalvo);
//
//				return ResponseEntity.ok(restauranteSalvo);
//			}
//
//			return ResponseEntity.notFound().build();
//
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//
//	}

//	@PatchMapping("/{id}")
//	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
//
//		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
//
//		if (restauranteAtual.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//
//		Restaurante restauranteSalvo = restauranteAtual.get();
//
//		merge(campos, restauranteSalvo);
//		return atualizar(restauranteSalvo, id);
//	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restaurante = objectMapper.convertValue(camposOrigem, Restaurante.class);

		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {

			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restaurante);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);

			System.out.println(nomePropriedade + " = " + " " + valorPropriedade + " = " + novoValor);
		});
	}

}
