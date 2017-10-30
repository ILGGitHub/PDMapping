package es.enxenio.ilga.pdmapping.modelo.servicios;

import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;
import es.enxenio.ilga.pdmapping.web.rest.custom.JSONEnquisa;

public interface EnquisaService {

	void gardarEnquisa(JSONEnquisa enquisa)
			throws InstanceNotFoundException;

}
