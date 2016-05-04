package br.com.loteria.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude="dezenas")
@Entity(name="concursos")
public class Concursos {
	@Id
	@Column(name="id_concursos")
	private Integer id;
	@Column(name="numero")
	private Integer numero;
	@Column(name="data_sorteio")
	private Date dataSorteio;
	@Column(name="arrecadacao_total")
	private Double arrecadacaoTotal;
	@Column(name="ganhadores_sena")
	private Integer ganhadoresSena;
	@Column(name="cidade")
	private String cidade;
	@Column(name="estado")
	private String estado;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name = "id_tipo_jogo")
	private TipoJogo tipoJogo;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="concurso", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Dezenas> dezenas;
	
	public void setListaDezenas(List<Integer> numeros){
		dezenas = new ArrayList<>();
		for (Integer numero : numeros) {
			Dezenas dezena = new Dezenas();
			dezena.setConcurso(this);
			dezena.setNumero(numero);
			
			dezenas.add(dezena);
		}
	}
	
	public List<Integer> getListaDezenas(){
		List<Integer> numeros = new ArrayList<>();
		if(dezenas == null){
			return numeros;
		}
		for (Dezenas dezena : dezenas) {
			numeros.add(dezena.getNumero());
		}
		return numeros;
	}
}
