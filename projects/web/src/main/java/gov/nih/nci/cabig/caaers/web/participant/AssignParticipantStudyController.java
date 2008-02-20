package gov.nih.nci.cabig.caaers.web.participant;

//java imports

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Krikor Krumlian
 * @author Biju Joseph
 *         <p/>
 *         Refactored and fixed issue -8978
 */
public class AssignParticipantStudyController extends AbstractTabbedFlowFormController<AssignParticipantStudyCommand> {
    private static Log log = LogFactory.getLog(AssignParticipantStudyController.class);

    private ParticipantDao participantDao;
    protected ListValues listValues;

    @Override
    protected String getFormSessionAttributeName() {
        //the entry point to this flow is from AssignController
        return AssignController.class.getName() + ".FORM." + getCommandName();
    }

    /**
     * Validation needs to be supressed when,
     * - We go back in the flow.
     * - We the page displayed is the result of study/participant search.
     */
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);

        return (request.getParameter("studyType") != null) ||
                (request.getParameter("participantType") != null) ||
                (targetPage < curPage);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command,
                                         BindException errors) throws Exception {
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
