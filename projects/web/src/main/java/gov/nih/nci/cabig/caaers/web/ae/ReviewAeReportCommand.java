package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * This is the command class for ReviewAeReportController
 * @author Sameer Sawant
 */

public class ReviewAeReportCommand extends EditExpeditedAdverseEventCommand{
	
	public ReviewAeReportCommand(ExpeditedAdverseEventReportDao reportDao){
		super(reportDao);
	}
	
	@Override
	public void reassociate(){
		if(this.aeReport != null && this.aeReport.getId() != null){
			reportDao.reassociate(this.aeReport);
		}
	}
}