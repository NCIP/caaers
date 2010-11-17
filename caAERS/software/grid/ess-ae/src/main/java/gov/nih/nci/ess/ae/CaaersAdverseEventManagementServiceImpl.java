package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;

import java.util.List;

public class CaaersAdverseEventManagementServiceImpl {
	
	private AdverseEventDao adverseEventDao ;
	private GridToDomainObjectConverter gridToDomainObjectConverter;
	
	public List<AdverseEvent> getAdverseEvents(AdverseEvent gridAe) {
		gov.nih.nci.cabig.caaers.domain.AdverseEvent caaersAe = gridToDomainObjectConverter.convertAdverseEvent(gridAe);
		List<gov.nih.nci.cabig.caaers.domain.AdverseEvent> aes = adverseEventDao.findByExample(caaersAe);
		// convert back to grid AES and return . 
		return null;
	}

	public AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public void setGridToDomainObjectConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainObjectConverter = gridToDomainObjectConverter;
	}
}
