package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Set;

/**
 * @author Rhett Sutphin
 */
public class ListAdverseEventsController extends SimpleFormController {
    private StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    protected EvaluationService evaluationService;

    public ListAdverseEventsController() {
        setCommandClass(ListAdverseEventsCommand.class);
        setBindOnNewForm(true);
        setFormView("ae/selectAssignment");
        setSuccessView("ae/list");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new ListAdverseEventsCommand(assignmentDao, studyDao, participantDao, evaluationService);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerGridDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "assignment", assignmentDao);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
        Set<String> paramNames = request.getParameterMap().keySet();
        boolean hasParticipant
            =  paramNames.contains("participant")
            || paramNames.contains("mrn")
            || paramNames.contains("assignment");
        boolean hasStudy
            =  paramNames.contains("study") 
            || paramNames.contains("nciIdentifier")
            || paramNames.contains("assignment");
        return hasParticipant && hasStudy;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(
        HttpServletRequest request, Object command, Errors errors
    ) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("pageTitle", "List AEs: Select Participant & Study");
        refdata.put("bodyTitle", "List AEs: Select Participant & Study");
        refdata.put("instructions", "Select a participant and study to see all the AEs for that combination.");
        return refdata;
    }

    ////// CONFIGURATION

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

	public EvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}
    
    
    
}
