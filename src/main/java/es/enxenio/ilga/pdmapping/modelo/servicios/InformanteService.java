package es.enxenio.ilga.pdmapping.modelo.servicios;

import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Informante;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;
import es.enxenio.ilga.pdmapping.web.rest.custom.JSONInformante;

import java.util.List;

public interface InformanteService {

	List<Informante> obtenerInformantes();

	Informante gardarInformante(JSONInformante informante)
			throws InstanceNotFoundException;

}
