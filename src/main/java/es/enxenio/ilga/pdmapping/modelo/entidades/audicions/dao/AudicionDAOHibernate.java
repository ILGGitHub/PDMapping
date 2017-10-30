package es.enxenio.ilga.pdmapping.modelo.entidades.audicions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

@Repository
public class AudicionDAOHibernate extends GenericDAOHibernate<Audicion, Long>
		implements AudicionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Audicion> findVisibles() {
		return getSession().createQuery("from Audicion where visible = :true")
				.setParameter("true", true).list();
	}

	public Audicion find(Long id) throws InstanceNotFoundException {
		Audicion a = (Audicion) getSession()
				.createQuery("from Audicion where id = :id")
				.setParameter("id", id).uniqueResult();
		if (a == null) {
			throw new InstanceNotFoundException(id, Audicion.class.getName());
		}
		return a;
	}

}
