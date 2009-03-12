package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * This is the command class for ReviewAeReportController
 * @author Sameer Sawant
 */

public class ReviewAeReportCommand extends EditExpeditedAdverseEventCommand{
	
	public ReviewAeReportCommand(StudyParticipantAssignmentDao assignmentDao){
		super(assignmentDao);
	}
}