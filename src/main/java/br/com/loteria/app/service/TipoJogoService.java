package br.com.loteria.app.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.loteria.app.entity.TipoJogo;
import br.com.loteria.app.repository.TipoJogoRepository;

@Service
public class TipoJogoService {
	@Inject
	private TipoJogoRepository repository;
	
	public TipoJogo getTipoJogoById(Integer id){
		return repository.findOne(id);
	}
}
