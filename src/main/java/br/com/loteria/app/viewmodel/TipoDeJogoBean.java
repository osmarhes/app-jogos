package br.com.loteria.app.viewmodel;

import lombok.Data;

@Data
public class TipoDeJogoBean {
	private Integer totalDezena;
	private Integer maxDezena;
	private String nome;
	private String descricao;
}
