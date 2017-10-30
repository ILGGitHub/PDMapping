package es.enxenio.ilga.pdmapping.web.rest;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.servicios.AudicionsService;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;
import es.enxenio.ilga.pdmapping.web.util.RepositorioFicheiros;

@RestController
@RequestMapping("/api")
public class AudicionsResource extends MainResource {
	private final Logger log = LoggerFactory
			.getLogger(LocalidadeResource.class);

	@Autowired
	private RepositorioFicheiros repositorioFicheiros;

	@Autowired
	private AudicionsService audicionsServicio;

	@RequestMapping(value = "/audicions", method = RequestMethod.GET)
	public List<Audicion> obterAudiciones() {
		return audicionsServicio.obterAudiciones(true);
	}

	@RequestMapping(value = "/audicions/{id}", method = RequestMethod.GET)
	public void obterAudio(@PathVariable Long id,
			HttpServletResponse response) {
		Audicion audicion;
		try {
			audicion = audicionsServicio.obterAudicion(id);
			if (audicion.getPath() != null) {
				File audio = repositorioFicheiros.getFicheiro(audicion
						.getPath());
				if (audio.exists()) {
					repositorioFicheiros.servirFicheiro(audio, response,
							"audio/mpeg");
					return;
				}
			}
		} catch (InstanceNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		response.setStatus(HttpStatus.NOT_FOUND.value());
	}
}
