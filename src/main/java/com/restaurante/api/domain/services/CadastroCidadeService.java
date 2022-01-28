 package com.restaurante.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.restaurante.api.domain.exceptions.EntidadeEmUsoException;
import com.restaurante.api.domain.exceptions.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cidade;
import com.restaurante.api.domain.model.Estado;
import com.restaurante.api.domain.repositories.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_EM_USO
		= "A cidade com o código %d não pode ser removida pois está em uso no sistema";

	private static final String MSG_CIDADE_NAO_ENCONTRADA
		= "A cidade com o código %d não foi encontrada!";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired CadastroEstadoService cadastroEstado;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstado.buscarOufalhar(estadoId);
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
		
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id));
		}
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
	
}
