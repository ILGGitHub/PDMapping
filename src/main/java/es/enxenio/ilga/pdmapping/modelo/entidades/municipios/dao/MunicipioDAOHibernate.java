package es.enxenio.ilga.pdmapping.modelo.entidades.municipios.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;

@Repository
public class MunicipioDAOHibernate extends
		GenericDAOHibernate<Municipio, String> implements MunicipioDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> findByProvincia(String provincia) {
		String query = "from Municipio ";
		String where = "";
		if (StringUtils.hasLength(provincia)) {
			where = " where provincia.codigo = :provincia ";
		}
		Query q = getSession().createQuery(query + where);
		if (StringUtils.hasLength(provincia)) {
			q.setParameter("provincia", provincia);
		}
		return q.list();
	}
}
