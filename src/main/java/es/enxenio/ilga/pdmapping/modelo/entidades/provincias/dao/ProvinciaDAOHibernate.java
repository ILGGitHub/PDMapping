package es.enxenio.ilga.pdmapping.modelo.entidades.provincias.dao;

import org.springframework.stereotype.Repository;

import es.enxenio.ilga.pdmapping.modelo.entidades.provincias.Provincia;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;

@Repository
public class ProvinciaDAOHibernate extends
		GenericDAOHibernate<Provincia, String> implements ProvinciaDAO {

}
