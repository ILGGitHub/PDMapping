package es.enxenio.ilga.pdmapping.web.util;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;

public class CustomPolygonDeserializer extends
		CustomGeometryDeserializer<Polygon> {

	@Override
	protected Polygon convertGeometry(Geometry geom) {
		logger.debug("Geometry type: {} to Polygon", geom.getGeometryType());

		if (geom.getGeometryType() != "Polygon") {
			logger.error("Geometry is not polygon: {}", geom.toString());
			return null;
		}

		return (Polygon) geom;
	}
}