package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

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
        return new ListAdverseEventsCommand(assignmentDao, studyDao, participantDao,
                        evaluationService);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
                    throws Exception {
        ControllerTools.registerGridDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "assignment", assignmentDao);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
        Set<String> paramNames = request.getParameterMap().keySet();
        boolean hasParticipant = paramNames.contains("participant") || paramNames.contains("mrn")
                        || paramNames.contains("assignment");
        boolean hasStudy = paramNames.contains("study") || paramNames.contains("nciIdentifier")
                        || paramNames.contains("assignment");
        boolean hasStudySubjectGridId = paramNames.contains("studySubjectGridId");
        return (hasParticipant && hasStudy) || hasStudySubjectGridId;
    }

    @Override
    protected void onBind(HttpServletRequest request, Object command, BindException errors)
                    throws Exception {
        super.onBind(request, command, errors);
        ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        String assignmentGridId = request.getParameter("studySubjectGridId");
        // forwarded from external system (eg : Labviewer)
        if (assignmentGridId != null) {
            StudyParticipantAssignment assignment = assignmentDao.getByGridId(assignmentGridId);
            // able to load the assignment?
            if (assignment != null) {
                listAECmd.setAssignment(assignment);
                listAECmd.setParticipant(listAECmd.getAssignment().getParticipant());
                listAECmd.setStudy(listAECmd.getAssignment().getStudySite().getStudy());
            }
        }
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command,
                    BindException errors) throws Exception {
        super.onBindAndValidate(request, command, errors);
        ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        boolean noStudy = listAECmd.getStudy() == null;
        boolean noParticipant = listAECmd.getParticipant() == null;
        if (noStudy) errors.rejectValue("study", "REQUIRED", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "REQUIRED", "Missing subject");
        if (!(noStudy || noParticipant) && listAECmd.getAssignment() == null) {
            errors.reject("REQUIRED", "The subject is not assigned to the provided study");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors)
                    throws Exception {
    	ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("pageTitle", "Manage AEs: Select Subject & Study");
        refdata.put("bodyTitle", "Manage AEs: Select Subject & Study");
        refdata.put("instructions",
                        "Select a subject and study to see all the AEs for that combination.");
        
        return refdata;
    }

    // //// CONFIGURATION

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
