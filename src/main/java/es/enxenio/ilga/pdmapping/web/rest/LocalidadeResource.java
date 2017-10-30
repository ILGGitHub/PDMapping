package es.enxenio.ilga.pdmapping.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.Parroquia;
import es.enxenio.ilga.pdmapping.modelo.entidades.provincias.Provincia;
import es.enxenio.ilga.pdmapping.modelo.servicios.LocalizacionService;

@RestController
@RequestMapping("/api")
public class LocalidadeResource extends MainResource {
	private final Logger log = LoggerFactory
			.getLogger(LocalidadeResource.class);

	@Autowired
	private LocalizacionService localizacionServicio;

	@RequestMapping(value = "/provincias", method = RequestMethod.GET)
	public List<Provincia> obtenerProvincias() {
		return localizacionServicio.obtenerProvincias();
	}

	@RequestMapping(value = "/municipios", method = RequestMethod.GET)
	public List<Municipio> obtenerMunicipios(
			@RequestParam(required = false) String provincia) {
		return localizacionServicio.obtenerMunicipios(provincia);
	}

	@RequestMapping(value = "/parroquias", method = RequestMethod.GET)
	public List<Parroquia> obtenerParroquias(
			@RequestParam(required = false) String municipio) {
		return localizacionServicio.obtenerParroquias(municipio);
	}

	@RequestMapping(value = "/lugares", method = RequestMethod.GET)
	public List<Lugar> obtenerLugares(
			@RequestParam(required = false) String municipio,
			@RequestParam(required = false) String parroquia) {
		return localizacionServicio.obtenerLugares(municipio, parroquia);

	}

}
