package es.enxenio.ilga.pdmapping.modelo.entidades.lugares.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;

@Repository
public class LugarDAOHibernate extends GenericDAOHibernate<Lugar, String>
		implements LugarDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lugar> findByMunicipioAndParroquia(String municipio,
			String parroquia) {
		String query = "from Lugar ";
		String where = " where 1=1 ";
		if (StringUtils.hasLength(municipio)) {
			where += " and municipio.codigo = :municipio ";
		}
		if (StringUtils.hasLength(parroquia)) {
			where += " and parroquia.codigo = :parroquia ";
		}
		Query q = getSession().createQuery(query + where);
		if (StringUtils.hasLength(municipio)) {
			q.setParameter("municipio", municipio);
		}
		if (StringUtils.hasLength(parroquia)) {
			q.setParameter("parroquia", parroquia);
		}
		return q.list();
	}

}
