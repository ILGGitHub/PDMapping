package es.enxenio.ilga.pdmapping.modelo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.dao.LugarDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.dao.MunicipioDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.Parroquia;
import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.dao.ParroquiaDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.provincias.Provincia;
import es.enxenio.ilga.pdmapping.modelo.entidades.provincias.dao.ProvinciaDAO;

@Service
@Transactional(readOnly = true)
public class LocalizacionServiceImpl implements LocalizacionService {

	@Autowired
	private MunicipioDAO municipioDAO;

	@Autowired
	private ProvinciaDAO provinciaDAO;

	@Autowired
	private ParroquiaDAO parroquiaDAO;

	@Autowired
	private LugarDAO lugarDAO;

	@Override
	public List<Provincia> obtenerProvincias() {
		return provinciaDAO.findAll();
	}

	@Override
	public List<Municipio> obtenerMunicipios(String provincia) {
		return municipioDAO.findByProvincia(provincia);
	}

	@Override
	public List<Parroquia> obtenerParroquias(String municipio) {
		return parroquiaDAO.findByMunicipio(municipio);
	}

	@Override
	public List<Lugar> obtenerLugares(String municipio, String parroquia) {
		return lugarDAO.findByMunicipioAndParroquia(municipio, parroquia);
	}

}
