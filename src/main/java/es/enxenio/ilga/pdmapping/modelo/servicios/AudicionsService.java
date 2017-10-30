package es.enxenio.ilga.pdmapping.modelo.servicios;

import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

public interface AudicionsService {
	List<Audicion> obterAudiciones(boolean visiblesSolo);

	Audicion obterAudicion(Long id) throws InstanceNotFoundException;
}
