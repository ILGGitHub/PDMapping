package es.enxenio.ilga.pdmapping.web.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.geotools.geojson.geom.GeometryJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public abstract class CustomGeometryDeserializer<T extends Geometry> extends
		JsonDeserializer<T> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static int SRID = 4258;

	protected static final GeometryFactory geometryFactory = new GeometryFactory(
			new PrecisionModel(PrecisionModel.FLOATING), SRID);
	private static GeometryJSON gjson = new GeometryJSON();

	protected abstract T convertGeometry(Geometry geom);

	public T deserialize(String geoJson) throws IOException {

		Reader reader = new StringReader(geoJson);
		Geometry geom = gjson.read(reader);
		geom.setSRID(SRID);
		return convertGeometry(geom);
	}

	@Override
	public T deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {

		String str = null;

		try {
			str = p.readValueAsTree().toString();
			return deserialize(str);
		} catch (Exception e) {
			logger.error("Error deserializing Polygon", e);
			logger.error("Polygon readed {}", str);
			throw e;
		}
	}
}
