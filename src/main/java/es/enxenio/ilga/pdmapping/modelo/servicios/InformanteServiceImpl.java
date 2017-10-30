package es.enxenio.ilga.pdmapping.modelo.servicios;

import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Informante;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.dao.InformanteDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.dao.LugarDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.dao.MunicipioDAO;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;
import es.enxenio.ilga.pdmapping.web.rest.custom.JSONInformante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InformanteServiceImpl implements InformanteService {

	@Autowired
	private InformanteDAO informanteDAO;

	@Autowired
	private LugarDAO lugarDAO;

	@Autowired
	private MunicipioDAO municipioDAO;

	@Override
	public List<Informante> obtenerInformantes() {
		return informanteDAO.findAll();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { InstanceNotFoundException.class })
	public Informante gardarInformante(JSONInformante informante)
			throws InstanceNotFoundException {
		Informante informante2 = new Informante(informante.getId(),
				informante.getAnoNacemento(), informante.getSexo(),
				informante.getEstudos(), informante.getOcupacion(), informante.getOutroLugarResidencia(),
				informante.getOutroLugarNacemento(), informante.getLugarNacementoPaisCoincide(),
				informante.getViviuForaDeGalicia(), informante.getLinguaNativa(),
				informante.getLinguaFalada(), informante.getLugaresDesprazamento(),
				informante.getGraoDiferenzaFala(), informante.getMellorGalego(), informante.getPeorGalego(),
				informante.getDiferenteGalego());

		// Guardando informante
		if (StringUtils.hasText(informante.getLugarResidencia())) {
			informante2.setLugarResidencia(lugarDAO.find(informante
					.getLugarResidencia()));
		}

		if (StringUtils.hasText(informante.getLugarNacemento())) {
			informante2.setLugarNacemento(lugarDAO.find(informante
					.getLugarNacemento()));
		}

		for (String outraResidencia : informante.getOutrosLugaresDeResidencia()) {
			informante2.getOutrosLugaresResidencia().add(municipioDAO.find(outraResidencia));
		}

		informanteDAO.save(informante2);
		return informante2;
	}
}
