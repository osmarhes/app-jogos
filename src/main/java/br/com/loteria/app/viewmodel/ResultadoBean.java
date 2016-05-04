package br.com.loteria.app.viewmodel;

import java.util.List;

import lombok.Data;

@Data
public class ResultadoBean {
	// @NotNull
	private Integer concurso;
	// @Date
	private String dataSorteio;
	// @NotNull
	private String localSorteio;
	private boolean acumulou;
	// @NotNull
	private Integer idTipoJogo;
	private List<Integer> dezenas;
	private Double estimativaPremio;
	private Double valorAcumulado;
	private String descAcumuladoOutro;
	private Double valorAcumuladoOutro;
	private Double valorMegaVirada;
	private List<Premio> premios;
	private Double arrecadacaoTotal;
}
