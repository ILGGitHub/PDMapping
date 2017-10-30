package es.enxenio.ilga.pdmapping.modelo.entidades.parroquias;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;

@Entity
public class Parroquia {
	@Id
	private String codigo;
	private String nome;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "municipio")
	private Municipio municipio;

	public String getMuni() {
		return municipio.getCodigo();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}
