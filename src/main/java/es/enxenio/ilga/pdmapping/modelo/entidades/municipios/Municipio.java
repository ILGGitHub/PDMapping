package es.enxenio.ilga.pdmapping.modelo.entidades.municipios;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.entidades.provincias.Provincia;

@Entity
public class Municipio {

	@Id
	private String codigo;
	private String nome;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "provincia")
	@JsonIgnore
	private Provincia provincia;
	private Integer poblacion;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "capital")
	private Lugar capital;

	public String getProv() {
		return provincia.getCodigo();
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Integer getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}

	public Lugar getCapital() {
		return capital;
	}

	public void setCapital(Lugar capital) {
		this.capital = capital;
	}

}
