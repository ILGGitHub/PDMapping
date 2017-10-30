package es.enxenio.ilga.pdmapping.modelo.entidades.audicions.dao;

import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAO;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

public interface AudicionDAO extends GenericDAO<Audicion, Long> {

	List<Audicion> findVisibles();

	Audicion find(Long id) throws InstanceNotFoundException;

}
