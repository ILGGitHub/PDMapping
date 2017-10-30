package es.enxenio.ilga.pdmapping.web.rest.custom;

import java.util.List;

/**
 * @author acortinas
 *
 */
public class JSONEnquisa {
	private JSONInformante informante;
	private List<JSONRespuesta> cuestionario;
	private List<JSONXuizo> xuizos;

	public JSONInformante getInformante() {
		return informante;
	}

	public void setInformante(JSONInformante informante) {
		this.informante = informante;
	}

	public List<JSONRespuesta> getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(List<JSONRespuesta> cuestionario) {
		this.cuestionario = cuestionario;
	}

	public List<JSONXuizo> getXuizos() {
		return xuizos;
	}

	public void setXuizos(List<JSONXuizo> xuizos) {
		this.xuizos = xuizos;
	}

	@Override
	public String toString() {
		return "JSONEnquisa [informante=" + informante + ", cuestionario="
				+ cuestionario + ", xuizos=" + xuizos + "]";
	}

}
