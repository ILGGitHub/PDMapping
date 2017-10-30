package es.enxenio.ilga.pdmapping.web.rest.custom;

public class JSONRespuesta {
	private Long id;
	private String respuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "JSONRespuesta [id=" + id + ", respuesta=" + respuesta + "]";
	}

}
