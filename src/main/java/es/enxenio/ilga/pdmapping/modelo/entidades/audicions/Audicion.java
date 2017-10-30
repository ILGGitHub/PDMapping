package es.enxenio.ilga.pdmapping.modelo.entidades.audicions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;

@Entity
public class Audicion {
	@Id
	@Column(name = "id_audicion")
	@SequenceGenerator(name = "AudicionIdGenerator", sequenceName = "id_audicion")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AudicionIdGenerator")
	private Long id;
	private String titulo;
	@JsonIgnore
	private String path;
	private Boolean visible;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "lugarAudicion")
	@JsonIgnore
	private Lugar lugarAudicion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Lugar getLugarAudicion() {
		return lugarAudicion;
	}

	public void setLugarAudicion(Lugar lugarAudicion) {
		this.lugarAudicion = lugarAudicion;
	}

}
