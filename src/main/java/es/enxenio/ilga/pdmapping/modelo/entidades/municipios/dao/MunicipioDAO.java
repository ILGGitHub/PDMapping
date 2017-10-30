package es.enxenio.ilga.pdmapping.modelo.entidades.municipios.dao;

import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAO;

public interface MunicipioDAO extends GenericDAO<Municipio, String> {

	List<Municipio> findByProvincia(String provincia);

}
