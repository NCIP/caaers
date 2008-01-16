package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
/**
 * @author Krikor Krumlian
 * @author Biju Joseph 
 * 
 * Refactored and fixed issue -8978
 */
public class AssignStudyController extends AssignParticipantStudyController{
	
	
	@Override
	@SuppressWarnings(value={"unchecked"})
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		Map referenceData =  super.referenceData(request, command, errors, page);
		referenceData.put("participantSearchType", listValues.getParticipantSearchType());
		referenceData.put("studySearchType", listValues.getStudySearchType());
		referenceData.put("assignType", "study");
		return referenceData;
	}
	
	public AssignStudyController() {
        setCommandClass(AssignParticipantStudyCommand.class);
        Flow<AssignParticipantStudyCommand> flow = new Flow<AssignParticipantStudyCommand>("Assign Subject to Study");
        flow.addTab(new AssignStudyTab());
        flow.addTab(new AssignParticipantTab());
        flow.addTab(new ReviewAssignmentTab());
        setFlow(flow);
       
    }
	

}
