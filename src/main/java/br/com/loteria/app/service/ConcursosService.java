package br.com.loteria.app.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.loteria.app.entity.Concursos;
import br.com.loteria.app.repository.ConcursosRepository;

@Service
public class ConcursosService {
	
	@Inject
	private ConcursosRepository repository;
	
	public void save(Concursos concursos) {
		repository.saveAndFlush(concursos);
	}

	public List<Concursos> lista() {
		return repository.lista();
	}

}
