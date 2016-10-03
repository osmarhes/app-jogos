package br.com.loteria.app.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.loteria.app.entity.Concursos;
import br.com.loteria.app.infra.DozerHelper;
import br.com.loteria.app.service.ConcursosService;
import br.com.loteria.app.validator.ConcursosValidator;
import br.com.loteria.app.viewmodel.ResultadoBean;

@Transactional
@RequestMapping("/concursos")
public class ConcursosController {

	@Inject
	private Mapper beanMapper;

	@Inject
	private ConcursosService service;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ConcursosValidator());
	}

	@RequestMapping("/form")
	public ModelAndView form() {
		return new ModelAndView("concursos/form");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("bean")  ResultadoBean bean, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form();
		}
		Concursos concursos = beanMapper.map(bean, Concursos.class);
		service.save(concursos);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:concursos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("concursos/list");
		List<ResultadoBean> concursos = DozerHelper.map(beanMapper, service.lista(), ResultadoBean.class);
		view.addObject("concursos", concursos);
		return view;
	}
}
