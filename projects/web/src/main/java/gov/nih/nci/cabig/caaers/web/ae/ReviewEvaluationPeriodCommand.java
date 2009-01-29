package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;

/**
 *
 * @author Sameer Sawant
 */
public class ReviewEvaluationPeriodCommand{
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		return adverseEventReportingPeriod;
	}
	
	public void setAdverseEventReportingPeriod(AdverseEventReportingPeriod adverseEventReportingPeriod){
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}
}