package br.com.loteria.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="tipo_jogo")
public class TipoJogo {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="total_dezena")
	private Integer totalDezena;
	@Column(name="max_dezena")
	private Integer maxDezena;
	@Column(name="nome")
	private String nome;
	@Column(name="descricao")
	private String descricao;
}
