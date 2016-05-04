package br.com.loteria.app.viewmodel;

import lombok.Data;

@Data
public class Premio {
	private Faixa faixa;
	private Integer quantidade;
	private Double valor; 
}
