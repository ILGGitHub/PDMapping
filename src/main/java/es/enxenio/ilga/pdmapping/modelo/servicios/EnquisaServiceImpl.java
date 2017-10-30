package es.enxenio.ilga.pdmapping.modelo.servicios;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.dao.AudicionDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Informante;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.dao.InformanteDAO;
import es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.Xuizo;
import es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.dao.XuizoDAO;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;
import es.enxenio.ilga.pdmapping.web.rest.custom.JSONEnquisa;
import es.enxenio.ilga.pdmapping.web.rest.custom.JSONXuizo;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnquisaServiceImpl implements EnquisaService {
	private Logger logger = LoggerFactory.getLogger(EnquisaServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private InformanteService informanteService;

	@Autowired
	private InformanteDAO informanteDAO;

	@Autowired
	private XuizoDAO xuizoDAO;

	@Autowired
	private AudicionDAO audicionDAO;

	@Transactional(readOnly = false, rollbackFor = { InstanceNotFoundException.class })
	private Informante gardarXuizos(Informante informante,
			List<JSONXuizo> xuizos) throws InstanceNotFoundException {

		Xuizo xuizo = null;
		Audicion audicion = null;

		// Guardando xuizos
		for (JSONXuizo x : xuizos) {
			audicion = audicionDAO.find(x.getId());
			xuizo = new Xuizo(informante, audicion, x.getGeom(), x.getParecido());
			xuizoDAO.save(xuizo);
			informante.getXuizos().add(xuizo);
		}

		informanteDAO.save(informante);
		return informante;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { InstanceNotFoundException.class })
	public void gardarEnquisa(JSONEnquisa enquisa)
			throws InstanceNotFoundException {

		Informante informante = informanteService.gardarInformante(enquisa
				.getInformante());
		gardarXuizos(informante, enquisa.getXuizos());
	}
}
