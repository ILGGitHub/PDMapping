package es.enxenio.ilga.pdmapping.web.util;

import java.io.IOException;
import java.io.StringWriter;

import org.geotools.geojson.geom.GeometryJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Geometry;

public class CustomGeometrySerializer extends JsonSerializer<Geometry> {
	private static Logger logger = LoggerFactory
			.getLogger(CustomGeometrySerializer.class);

	private static GeometryJSON gjson = new GeometryJSON();

	public String serialize(Geometry value) {

		StringWriter writer = new StringWriter();
		try {
			gjson.write(value, writer);
		} catch (IOException e) {
			logger.debug(e.getMessage(), e);
		}
		return writer.toString();
	}

	@Override
	public void serialize(Geometry value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {

		StringWriter writer = new StringWriter();
		gjson.write(value, writer);
		gen.writeRawValue(writer.toString());
	}
}