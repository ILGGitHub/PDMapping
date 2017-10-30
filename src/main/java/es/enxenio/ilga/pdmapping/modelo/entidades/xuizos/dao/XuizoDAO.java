package es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.dao;

import es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.Xuizo;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAO;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

public interface XuizoDAO extends GenericDAO<Xuizo, Long> {

	Xuizo gardar(Long id, Long id2, String geom)
			throws InstanceNotFoundException;

}
