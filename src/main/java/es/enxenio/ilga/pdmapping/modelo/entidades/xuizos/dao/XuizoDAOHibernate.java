package es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.dao;

import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.Xuizo;
import es.enxenio.ilga.pdmapping.modelo.util.dao.GenericDAOHibernate;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

@Repository
public class XuizoDAOHibernate extends GenericDAOHibernate<Xuizo, Long>
		implements XuizoDAO {

	@Override
	public Xuizo gardar(Long informante, Long audicion, String geom)
			throws InstanceNotFoundException {
		String query = "insert into xuizo (informante, audicion, geo) "
				+ "values (:informante, :audicion, ST_TRANSFORM(ST_GeomFromGeoJSON(:geom),4258));";

		SQLQuery insertQuery = getSession().createSQLQuery(query);
		insertQuery.setParameter("informante", informante);
		insertQuery.setParameter("audicion", audicion);
		insertQuery.setParameter("geom", geom);
		insertQuery.executeUpdate();

		Long lastId = (Long) getSession()
				.createSQLQuery("select currval('id_xuizo') as id")
				.addScalar("id", LongType.INSTANCE).uniqueResult();

		return find(lastId);
	}
}
