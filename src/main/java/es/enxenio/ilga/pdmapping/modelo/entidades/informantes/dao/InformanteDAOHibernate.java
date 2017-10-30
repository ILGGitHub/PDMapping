package es.enxenio.ilga.pdmapping.modelo.entidades.informantes.dao;

import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Informante;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;
import org.springframework.stereotype.Repository;

@Repository
public class InformanteDAOHibernate extends
		GenericDAOHibernate<Informante, Long> implements InformanteDAO {

}
