package gov.nih.nci.ess.ae;

import java.util.List;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;

public class AdverseEventQueryImpl {

	private AdverseEventDao adverseEventDao ;
	private GridToDomainObjectConverter gridToDomainObjectConverter;
	
	public AdverseEvent[] findAdverseEvents(AdverseEvent adverseEvent) {
		gov.nih.nci.cabig.caaers.domain.AdverseEvent caaersAe = gridToDomainObjectConverter.convertAdverseEvent(adverseEvent);
		List<gov.nih.nci.cabig.caaers.domain.AdverseEvent> aes = adverseEventDao.findByExample(caaersAe);
		// convert back to grid AES and return . 
		return null;
	}
	
	public AdverseEvent getAdverseEventData(Id adverseEventIdentifier) {
		return null;
	}


	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public void setGridToDomainObjectConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainObjectConverter = gridToDomainObjectConverter;
	}
}
