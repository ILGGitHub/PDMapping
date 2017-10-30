package es.enxenio.ilga.pdmapping.modelo.entidades.lugares.dao;

import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAO;

public interface LugarDAO extends GenericDAO<Lugar, String> {

	List<Lugar> findByMunicipioAndParroquia(String municipio, String parroquia);

}
