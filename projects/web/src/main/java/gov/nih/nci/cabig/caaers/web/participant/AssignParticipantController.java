package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;

/**
 * @author Krikor Krumlian
 */

public class AssignParticipantController extends AbstractTabbedFlowFormController<AssignParticipantStudyCommand> {

    private static Log log = LogFactory.getLog(AssignParticipantStudyController.class);
    private ParticipantDao participantDao;
    protected ListValues listValues;

    @Override
    @SuppressWarnings(value = {"unchecked"})
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        Map referenceData = super.referenceData(request, command, errors, page);
        referenceData.put("participantSearchType", listValues.getParticipantSearchType());
        referenceData.put("studySearchType", listValues.getStudySearchType());
        referenceData.put("assignType", "participant");
        return referenceData;
    }

    public AssignParticipantController() {
        setCommandClass(AssignParticipantStudyCommand.class);
        Flow<AssignParticipantStudyCommand> flow = new Flow<AssignParticipantStudyCommand>("Assign Subject to Study");
        flow.addTab(new AssignParticipantTab());
        flow.addTab(new AssignStudyTab());
        //flow.addTab(new SubjectMedHistoryTab());
        flow.addTab(new ReviewAssignmentTab());
        setFlow(flow);
    }

    @Override
    protected String getFormSessionAttributeName() {
        // the entry point to this flow is from AssignController
        return AssignController.class.getName() + ".FORM." + getCommandName();
    }

    /**
     * Validation needs to be supressed when, - We go back in the flow. - We the page displayed is
     * the result of study/participant search.
     */
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        return (request.getParameter("studyType") != null) || (request.getParameter("participantType") != null) || (targetPage < curPage);
    }


    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        Object subviewName = findInRequest(request, AbstractAdverseEventInputController.AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "par/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }

    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {
        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        log.debug("Entering Process Finish ...");
        AssignParticipantStudyCommand assignParticipantStudyCommand = (AssignParticipantStudyCommand) command;

        StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment();
        studyParticipantAssignment.setDateOfEnrollment(new Date());
        studyParticipantAssignment.setParticipant(assignParticipantStudyCommand.getParticipants().get(0));
        studyParticipantAssignment.setStudySite(assignParticipantStudyCommand.getStudySites().get(0));
        studyParticipantAssignment.setStudySubjectIdentifier(assignParticipantStudyCommand.getStudySubjectIdentifier());

        Participant participant = assignParticipantStudyCommand.getParticipants().get(0);
        participantDao.reassociateUsingLock(participant);

        participant.addAssignment(studyParticipantAssignment);
        participantDao.save(participant);

        ModelAndView modelAndView = new ModelAndView("par/par_confirm");
        modelAndView.addObject("participant", participant);
        modelAndView.addAllObjects(errors.getModel());
        response.sendRedirect("view?participantId=" + participant.getId() + "&type=confirm");

        return null;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public ListValues getListValues() {
        return listValues;
    }

    public void setListValues(ListValues listValues) {
        this.listValues = listValues;
    }

}



