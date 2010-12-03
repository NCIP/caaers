package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.ess.ae.service.query.common.QueryI;

import java.util.ArrayList;
import java.util.List;

public class AdverseEventQueryImpl implements QueryI { 

	private AdverseEventDao adverseEventDao ;
	private GridToDomainObjectConverter gridToDomainObjectConverter;
	private DomainToGridObjectConverter domainToGridObjectConverter;
	
	public AdverseEvent[] findAdverseEvents(AdverseEvent adverseEvent) {
		gov.nih.nci.cabig.caaers.domain.AdverseEvent caaersAe = gridToDomainObjectConverter.convertAdverseEvent(adverseEvent);
		List<gov.nih.nci.cabig.caaers.domain.AdverseEvent> aes = adverseEventDao.findByExample(caaersAe);
		// convert back to grid AES and return . 
		List<AdverseEvent> aesToReturnList = new ArrayList<AdverseEvent>();
		for (gov.nih.nci.cabig.caaers.domain.AdverseEvent ae:aes){
			AdverseEvent gridAe = domainToGridObjectConverter.convertAdverseEvent(ae);
			aesToReturnList.add(gridAe);
		}
		return (AdverseEvent[]) aesToReturnList.toArray();
	}
	
	public AdverseEvent getAdverseEventData(Id adverseEventIdentifier) {
		if (adverseEventIdentifier != null) {
			Integer id = Integer.parseInt(adverseEventIdentifier.getExtension());
			gov.nih.nci.cabig.caaers.domain.AdverseEvent ae = adverseEventDao.getById(id);
			AdverseEvent gridAe = domainToGridObjectConverter.convertAdverseEvent(ae);
			return gridAe;
		}
		return null;
	}


	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public void setGridToDomainObjectConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainObjectConverter = gridToDomainObjectConverter;
	}

	public void setDomainToGridObjectConverter(
			DomainToGridObjectConverter domainToGridObjectConverter) {
		this.domainToGridObjectConverter = domainToGridObjectConverter;
	}
}
