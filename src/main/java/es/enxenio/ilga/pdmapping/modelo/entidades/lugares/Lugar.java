package es.enxenio.ilga.pdmapping.modelo.entidades.lugares;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.Parroquia;

@Entity
public class Lugar {

	@Id
	private String codigo;
	private String nome;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "municipio")
	@JsonIgnore
	private Municipio municipio;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "parroquia")
	@JsonIgnore
	private Parroquia parroquia;
	private Integer poblacion;

	public String getMuni() {
		return municipio.getCodigo();
	}

	public String getParr() {
		if (parroquia == null) {
			return null;
		}
		return parroquia.getCodigo();
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

	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	public Integer getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}

}
