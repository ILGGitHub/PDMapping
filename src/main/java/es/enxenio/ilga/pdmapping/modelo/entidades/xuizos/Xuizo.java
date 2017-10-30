package es.enxenio.ilga.pdmapping.modelo.entidades.xuizos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Polygon;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Informante;
import es.enxenio.ilga.pdmapping.web.util.CustomGeometrySerializer;
import es.enxenio.ilga.pdmapping.web.util.CustomPolygonDeserializer;

@Entity
public class Xuizo {
	@Id
	@SequenceGenerator(name = "XuizoIdGenerator", sequenceName = "id_xuizo")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "XuizoIdGenerator")
	private Long id;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "informante")
	private Informante informante;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "audicion")
	private Audicion audicion;

	private Integer parecido;

	@JsonIgnore
	@Type(type = "org.hibernate.spatial.GeometryType")
	@JsonSerialize(using = CustomGeometrySerializer.class)
	@JsonDeserialize(using = CustomPolygonDeserializer.class)
	private Polygon geo;

	public Xuizo(Informante informante2, Audicion audicion2, Polygon geom, Integer parecido) {
		this.informante = informante2;
		this.audicion = audicion2;
		this.geo = geom;
		this.parecido = parecido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Informante getInformante() {
		return informante;
	}

	public void setInformante(Informante informante) {
		this.informante = informante;
	}

	public Audicion getAudicion() {
		return audicion;
	}

	public void setAudicion(Audicion audicion) {
		this.audicion = audicion;
	}

	public Polygon getGeo() {
		return geo;
	}

	public void setGeo(Polygon geo) {
		this.geo = geo;
	}

	public Integer getParecido() {
		return parecido;
	}

	public void setParecido(Integer parecido) {
		this.parecido = parecido;
	}
}
