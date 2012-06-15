package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;

/**
 *
 * @author Sameer Sawant
 */
public class ReviewEvaluationPeriodCommand extends CaptureAdverseEventInputCommand{
	
	public ReviewEvaluationPeriodCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	

	
}