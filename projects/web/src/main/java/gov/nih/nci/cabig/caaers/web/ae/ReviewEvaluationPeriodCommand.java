package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;

/**
 *
 * @author Sameer Sawant
 */
public class ReviewEvaluationPeriodCommand{
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	private IndexFixedList<AdverseEvent> adverseEvents;
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		return adverseEventReportingPeriod;
	}
	
	public void setAdverseEventReportingPeriod(AdverseEventReportingPeriod adverseEventReportingPeriod){
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}
	
	public IndexFixedList<AdverseEvent> getAdverseEvents() {
		return adverseEvents;
	}
    public void setAdverseEvents(IndexFixedList<AdverseEvent> adverseEvents) {
		this.adverseEvents = adverseEvents;
	}
}