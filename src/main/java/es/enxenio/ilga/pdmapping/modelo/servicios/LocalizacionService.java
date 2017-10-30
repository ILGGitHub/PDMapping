package es.enxenio.ilga.pdmapping.modelo.servicios;

import java.util.List;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.entidades.parroquias.Parroquia;
import es.enxenio.ilga.pdmapping.modelo.entidades.provincias.Provincia;

public interface LocalizacionService {

	List<Provincia> obtenerProvincias();

	List<Municipio> obtenerMunicipios(String provincia);

	List<Parroquia> obtenerParroquias(String municipio);

	List<Lugar> obtenerLugares(String municipio, String parroquia);

}
