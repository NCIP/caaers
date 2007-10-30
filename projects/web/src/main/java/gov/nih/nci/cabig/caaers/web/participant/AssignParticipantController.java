package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
/**
 * @author Krikor Krumlian
 */
public class AssignParticipantController extends AssignParticipantStudyController{
	
	@Override
	@SuppressWarnings(value={"unchecked"})
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		Map referenceData =  super.referenceData(request, command, errors, page);
		referenceData.put("participantSearchType", listValues.getParticipantSearchType());
		referenceData.put("studySearchType", listValues.getStudySearchType());
		referenceData.put("assignType", "participant");
		return referenceData;
	}
	
	public AssignParticipantController() {
        setCommandClass(AssignParticipantStudyCommand.class);
        Flow<AssignParticipantStudyCommand> flow = new Flow<AssignParticipantStudyCommand>("Assign Participant to Study");
        flow.addTab(new AssignParticipantTab());
        flow.addTab(new AssignStudyTab());
        flow.addTab(new ReviewAssignmentTab());
        setFlow(flow);
    }

}
