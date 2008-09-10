package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class EditParticipantController <T extends ParticipantInputCommand> extends ParticipantController<T> {

    private static final Log log = LogFactory.getLog(EditParticipantController.class);
    private Task task;
    private ConfigProperty configurationProperty;

    public EditParticipantController() {
        setBindOnNewForm(true);
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {

        request.getSession().removeAttribute(CreateParticipantAjaxFacade.CREATE_PARTICIPANT_FORM_NAME);
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        
        Participant participant = participantRepository.getParticipantById(Integer.parseInt(request.getParameter("participantId")));

        EditParticipantCommand cmd = new EditParticipantCommand(participant);
        List<StudyParticipantAssignment> assignments = participant.getAssignments();

        // store StudySites from Participant object to Command object
        List<StudySite> studySites = new ArrayList<StudySite>();
        for (StudyParticipantAssignment studyParticipantAssignment : assignments) {
            studySites.add(studyParticipantAssignment.getStudySite());
        }
        cmd.setStudySites(studySites);
        
        if (participant.getAssignments().size() > 0)
            cmd.setOrganization(participant.getAssignments().get(0).getStudySite().getOrganization());
        
        return cmd;

    }

    @Override
    protected boolean isSummaryEnabled() {
        return true;
    }

    @Override
    protected void layoutTabs(final Flow<T> flow) {
        flow.addTab(new EditParticipantReviewParticipantTab());
        flow.addTab(new EditParticipantTab());
        flow.addTab(new SubjectMedHistoryTab());
    }

    @Override
    protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response, final Object command, final BindException errors) throws Exception {
        ParticipantInputCommand participantCommand = (ParticipantInputCommand) command;
        Participant participant = participantCommand.getParticipant();
        participantDao.merge(participant);

/*
        request.setAttribute("flashMessage", "Successfully updated the Participant");
        ModelAndView modelAndView = new ModelAndView("par/par_confirm");
*/

        response.sendRedirect("view?participantId=" + participant.getId() + "&type=edit");

        return null;
    }

    @Override
    protected boolean shouldSave(HttpServletRequest request, T command, Tab<T> tTab) {
        if (isAjaxRequest(request)) return false;
        return super.shouldSave(request, command, tTab);
    }

    protected Object currentFormObject(HttpServletRequest request, Object oCommand) throws Exception {
        ParticipantInputCommand cmd = (ParticipantInputCommand)oCommand;
        participantDao.reassociateUsingLock(cmd.getParticipant());
        return super.currentFormObject(request, oCommand);
    }

    protected T save(T command, Errors errors) {
        ParticipantInputCommand participantCommand = (ParticipantInputCommand) command;
        participantDao.merge(participantCommand.getParticipant());

        return command;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors, final int page) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, command, errors, page);
        refdata.put("currentTask", task);

        if (getFlow().getTabCount() == page + 1) {
            refdata.put("_finish", true);
        }
        return refdata;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }
}