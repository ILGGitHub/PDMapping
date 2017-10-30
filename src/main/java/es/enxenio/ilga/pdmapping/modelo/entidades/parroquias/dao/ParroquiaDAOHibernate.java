package es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.Parroquia;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;

@Repository
public class ParroquiaDAOHibernate extends
		GenericDAOHibernate<Parroquia, String> implements ParroquiaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Parroquia> findByMunicipio(String municipio) {
		String query = "from Parroquia ";
		String where = "";
		if (StringUtils.hasLength(municipio)) {
			where = " where municipio.codigo = :municipio ";
		}
		Query q = getSession().createQuery(query + where);
		if (StringUtils.hasLength(municipio)) {
			q.setParameter("municipio", municipio);
		}
		return q.list();
	}

}
