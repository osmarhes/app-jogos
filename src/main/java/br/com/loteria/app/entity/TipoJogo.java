package br.com.loteria.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="tipo_jogo")
public class TipoJogo {
	@Id
	@GenericGenerator(name = "incrementGenerator", strategy = "org.hibernate.id.IncrementGenerator")
	@GeneratedValue(generator="incrementGenerator", strategy=GenerationType.TABLE)
	@Column(name="id")
	private Integer id;
	@Column(name="total_dezena")
	private Integer totalDezena;
	@Column(name="max_dezena")
	private Integer maxDezena;
	@Column(name="nome", unique=true, nullable=false)
	private String nome;
	@Column(name="descricao")
	private String descricao;
}
