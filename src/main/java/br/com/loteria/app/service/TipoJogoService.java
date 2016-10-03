package br.com.loteria.app.service;

import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import br.com.loteria.app.entity.TipoJogo;
import br.com.loteria.app.infra.DozerHelper;
import br.com.loteria.app.repository.TipoJogoRepository;
import br.com.loteria.app.viewmodel.TipoDeJogoBean;

@Service
public class TipoJogoService {
	@Inject
	private TipoJogoRepository repository;

	@Inject
	private Mapper beanMapper;

	public TipoJogo getTipoJogoById(Integer id) {
		return repository.findOne(id);
	}

	public List<TipoDeJogoBean> listAll() {
		return DozerHelper.map(beanMapper, repository.findAll(), TipoDeJogoBean.class);
	}

	public void save(final TipoDeJogoBean tipoDeJogoBean) {
		TipoJogo tipoJogo = getOne(tipoDeJogoBean.getNome());
		if(tipoJogo != null) {
			//TODO Colocar um throw de app aqui
			throw new IllegalArgumentException("Existe um registro com esse nome");
		}
		tipoJogo = beanMapper.map(tipoDeJogoBean, TipoJogo.class);
		repository.saveAndFlush(tipoJogo);
	}
	
	public void update(final TipoDeJogoBean tipoDeJogoBean) {
		TipoJogo tipoJogo = getOne(tipoDeJogoBean.getNome());
		if(tipoJogo == null) {
			//TODO Colocar um throw de app aqui
			throw new IllegalArgumentException("Não existe um registro com esse nome");
		}
		beanMapper.map(tipoDeJogoBean, tipoJogo);
		repository.saveAndFlush(tipoJogo);
	}

	public TipoJogo getOne(final String nome) {
		return repository.getOneByNome(nome);
	}
	
	public TipoDeJogoBean getOneBean(final String nome) {
		return beanMapper.map(getOne(nome), TipoDeJogoBean.class);
	}

}
