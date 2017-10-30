package es.enxenio.ilga.pdmapping.modelo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.Audicion;
import es.enxenio.ilga.pdmapping.modelo.entidades.audicions.dao.AudicionDAO;
import es.enxenio.ilga.pdmapping.modelo.util.exceptions.InstanceNotFoundException;

@Service
@Transactional(readOnly = true)
public class AudicionsServiceImpl implements AudicionsService {

	@Autowired
	private AudicionDAO audicionDAO;

	@Override
	public List<Audicion> obterAudiciones(boolean visiblesSolo) {
		if (visiblesSolo) {
			return audicionDAO.findVisibles();
		} else {
			return audicionDAO.findAll();
		}
	}

	@Override
	public Audicion obterAudicion(Long id) throws InstanceNotFoundException {
		return audicionDAO.find(id);
	}

}
