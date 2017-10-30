package es.enxenio.ilga.pdmapping.web.rest.custom;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Polygon;

import es.enxenio.ilga.pdmapping.web.util.CustomGeometrySerializer;
import es.enxenio.ilga.pdmapping.web.util.CustomPolygonDeserializer;

public class JSONXuizo {

	private Long id;

	@Type(type = "org.hibernate.spatial.GeometryType")
	@JsonSerialize(using = CustomGeometrySerializer.class)
	@JsonDeserialize(using = CustomPolygonDeserializer.class)
	private Polygon geom;

	private Integer parecido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Polygon getGeom() {
		return geom;
	}

	public void setGeom(Polygon geom) {
		this.geom = geom;
	}

	public Integer getParecido() {
		return parecido;
	}

	public void setParecido(Integer parecido) {
		this.parecido = parecido;
	}

	@Override
	public String toString() {
		return "JSONXuizo [id=" + id + ", geom=" + geom + "]";
	}

}
