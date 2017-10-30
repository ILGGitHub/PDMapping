package es.enxenio.ilga.pdmapping.modelo.entidades.provincias;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;

@Entity
public class Provincia {

	@Id
	private String codigo;
	private String nome;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "capital")
	private Lugar capital;

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

	public Lugar getCapital() {
		return capital;
	}

	public void setCapital(Lugar capital) {
		this.capital = capital;
	}

}
