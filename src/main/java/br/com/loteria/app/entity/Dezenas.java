package br.com.loteria.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude={"concurso"})
@EqualsAndHashCode(exclude={"concurso"})
@Entity
@Table(name="dezenas")
public class Dezenas {	
	@Id
	@GeneratedValue
	@Column(name="id_dezenas")
	private Integer id;
	@Column(name="numero")
	private Integer numero;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(updatable=false, name = "id_concursos")
	private Concursos concurso;
}
