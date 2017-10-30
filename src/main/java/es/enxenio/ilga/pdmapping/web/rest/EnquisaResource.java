package es.enxenio.ilga.pdmapping.web.rest;

import es.enxenio.ilga.pdmapping.modelo.servicios.EnquisaService;
import es.enxenio.ilga.pdmapping.modelo.servicios.InformanteService;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;
import es.enxenio.ilga.pdmapping.web.rest.custom.JSONEnquisa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class EnquisaResource extends MainResource {

	private Logger logger = LoggerFactory.getLogger(EnquisaResource.class);

	@Autowired
	private InformanteService informanteService;

	@Autowired
	private EnquisaService enquisaService;

	@RequestMapping(value = "/enquisas", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void gardarEnquisa(
			@RequestBody(required = false) JSONEnquisa enquisa,
			HttpServletRequest request) throws InstanceNotFoundException {

		try {
			enquisaService.gardarEnquisa(enquisa);
		} catch (Exception e) {
			logger.error("Error al guardar enquisa: ", e);
			logger.error("JSONEnquisa = {}", enquisa.toString());
			throw e;
		}
	}

}
