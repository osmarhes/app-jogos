package br.com.loteria.app.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.loteria.app.service.TipoJogoService;
import br.com.loteria.app.viewmodel.TipoDeJogoBean;

@RestController
@RequestMapping(value="/tipojogo")
public class TipoJogoController {

	@Inject
	private TipoJogoService service;
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/", method= RequestMethod.GET)
	public List<TipoDeJogoBean> listAll() {
		return service.listAll();
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/", method= RequestMethod.POST)
	public void save(@RequestBody final TipoDeJogoBean bean) {
		service.save(bean);
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/", method= RequestMethod.PUT)
	public void update(@Valid @RequestBody final TipoDeJogoBean bean) {
		service.update(bean);
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/{nome}", method= RequestMethod.GET)
	public TipoDeJogoBean getOne(@PathVariable("nome") final String nome) {
		return service.getOneBean(nome);
	}
}
