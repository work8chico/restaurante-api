package com.restaurante.api.controllers;

import static com.restaurante.api.infraestructure.repositories.spec.RestauranteSpec.comFreteGratis;
import static com.restaurante.api.infraestructure.repositories.spec.RestauranteSpec.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repositories.CozinhaRepository;
import com.restaurante.api.domain.repositories.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/busca-por-nome")
	public List<Cozinha> buscarPorNome(@RequestParam String nome){
		return cozinhaRepository.findTodasByNome(nome);
	}
	
	@GetMapping("/busca-unica-por-nome")
	public Optional<Cozinha> buscarUnicaCozinhaPorNome(@RequestParam String nome){
		return cozinhaRepository.findCozinhaByNome(nome);
	}
	
	@GetMapping("/busca-cozinhas-por-nome")
	public Optional<List<Cozinha>> buscarCozinhasPorNome(@RequestParam String nome){
		return cozinhaRepository.findByNomeContains(nome);
	}
	
	@GetMapping("/busca-restaurante-por-frete")
	public List<Restaurante> buscarRestaurantesPorFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/busca-restaurante-por-frete-nome")
	public List<Restaurante> buscarRestaurantesPorFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping("/busca-restaurante-first-por-nome")
	public Optional<Restaurante> buscarRestaurantesPrimeiroPorNome(String nome){
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/busca-restaurante-top2-por-nome")
	public List<Restaurante> buscarRestaurantesTop2PorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/busca-restaurante-nome")
	public List<Restaurante> buscarPorNome(String nome, Long cozinha){
		return restauranteRepository.consultaPorNome(nome, cozinha);
	}
	
	@GetMapping("/busca-restaurante-com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
		
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/busca-restaurante-primeiro")
	public Optional<Restaurante> buscarPrimeiro(){
		return restauranteRepository.buscarPrimeiro();
	}

}
