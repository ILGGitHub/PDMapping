package es.enxenio.ilga.pdmapping.web.rest;

import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Estudo;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Grao;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Informante;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Lingua;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Ocupacion;
import es.enxenio.ilga.pdmapping.modelo.servicios.InformanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InformanteResource extends MainResource {

	@Autowired
	private InformanteService informanteService;

	@RequestMapping(value = "/informantes", method = RequestMethod.GET)
	public List<Informante> obtenerInformantes() {
		return informanteService.obtenerInformantes();
	}

	@RequestMapping(value = "/estudos", method = RequestMethod.GET)
	public Estudo[] obtenerEstudos() {
		return Estudo.values();
	}

	@RequestMapping(value = "/ocupacions", method = RequestMethod.GET)
	public Ocupacion[] obtenerOcupacions() {
		return Ocupacion.values();
	}

	@RequestMapping(value = "/linguas", method = RequestMethod.GET)
	public Lingua[] obtenerLinguas() {
		return Lingua.values();
	}

	@RequestMapping(value = "/graos", method = RequestMethod.GET)
	public Grao[] obtenerGraos() {
		return Grao.values();
	}
}
