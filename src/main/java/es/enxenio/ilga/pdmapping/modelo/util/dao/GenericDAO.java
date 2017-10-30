package es.enxenio.ilga.pdmapping.modelo.util.dao;

import java.io.Serializable;
import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

public interface GenericDAO<E, PK extends Serializable> {

	void save(E entity);

	void delete(PK id) throws InstanceNotFoundException;

	boolean exists(PK id);

	E find(PK id) throws InstanceNotFoundException;

	List<E> findAll();

}
