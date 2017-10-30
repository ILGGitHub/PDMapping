package es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.dao;

import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.Parroquia;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAO;

public interface ParroquiaDAO extends GenericDAO<Parroquia, String> {

	List<Parroquia> findByMunicipio(String municipio);

}
