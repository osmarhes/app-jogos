package br.com.loteria.app.infra.loader;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public class ConcursoMapping extends BeanMappingBuilder{

	@Override
	protected void configure() {
		mapping(br.com.loteria.app.entity.Concursos.class,
				br.com.loteria.app.viewmodel.ResultadoBean.class, 
				TypeMappingOptions.dateFormat("dd/MM/yyyy"))
				.fields("id", "concurso")
				.fields("dataSorteio", "dataSorteio")
				.fields("tipoJogo.id", "idTipoJogo")
				.fields("listaDezenas", "dezenas");
	}
}
