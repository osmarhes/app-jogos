package br.com.loteria.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.loteria.app.repository.DezenasRepository;
import br.com.loteria.app.viewmodel.Count;

@RestController
@RequestMapping(value="/teste")
public class TesteController {

	@Inject
	private DezenasRepository repository;
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value= "/{first}/{second}/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Count> teste(@PathVariable String first,@PathVariable String second) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		List<Object[]> objects =  repository.countList(dateFormat.parse(first), dateFormat.parse(second));
		List<Count> count = new ArrayList<>();
		Long total = 0l;
		for (Object[] object : objects) {
			Count count2 = new Count();
			count2.setVezes(((Long) object[0]).intValue());
			count2.setCampo(((Integer) object[1]).toString());
			total += ((Long) object[0]).intValue();
			count.add(count2);
		}
		
		Count count2 = new Count();
		count2.setVezes(total.intValue());
		count2.setCampo("Total - " + (total / 6) + " Jogos");
		count.add(count2);
		
		return count;
	}
}
