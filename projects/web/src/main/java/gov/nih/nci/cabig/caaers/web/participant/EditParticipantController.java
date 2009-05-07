package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.Identifier;
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class EditParticipantController<T extends ParticipantInputCommand> extends ParticipantController<T> {

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
    	
    	/**
    	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
    	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
    	 */
    	
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
        try {
			ParticipantInputCommand cmd = (ParticipantInputCommand) oCommand;

			String p = request.getParameter("_asyncMethodName");
			if (p == null || !(p.equals("addOrganizationIdentifier") || p.equals("removeOrganizationIdentifier") || p.equals("addSystemIdentifier") || p.equals("removeSystemIdentifier"))) {
			    participantDao.reassociate(cmd.getParticipant());
			}
		}catch (HibernateOptimisticLockingFailureException  e) {
			log.warn("Optimistic locking error, while reassociating the report", e);
			request.setAttribute("OPTIMISTIC_LOCKING_ERROR", e);
		}

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

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object cmd) {
        ParticipantInputCommand command = (ParticipantInputCommand) cmd;

        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        
        // supress for ajax and delete requests
        if (isAjaxRequest(request) && !StringUtils.equals("save", command.getTask())) return true;
        return super.suppressValidation(request, command);
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        ParticipantInputCommand cmd = (ParticipantInputCommand) command;

        List<Identifier> sitePrimaryIdentifiers = participantDao.getSitePrimaryIdentifiers(cmd.getOrganization().getId().intValue());

        for (int i=0; i<sitePrimaryIdentifiers.size(); i++) {
            Identifier sID = sitePrimaryIdentifiers.get(i);
            for (int j=0; j<cmd.getParticipant().getIdentifiers().size(); j++) {
                Identifier pID = cmd.getParticipant().getIdentifiers().get(j);

                if (sID.getValue().equals(pID.getValue()) && (sID.getId() == null || sID.getId().intValue() != pID.getId().intValue())) {
                    errors.reject("ERR_DUPLICATE_SITE_PRIMARY_IDENTIFIER", new Object[] {cmd.getOrganization().getName(), pID.getValue()}, "Duplicate identifiers for the same site.");
                }
            }
        }

        // if the target tab is not the next to the cirrent one
        if (getTargetPage(request, command, errors, page) - page > 1) {

            // if the assisgnment object needed by SubjectMedHistoryTab is not in the command
            if (cmd.assignment == null || cmd.assignment.getId() == null)
                if (getTab((T) command, getTargetPage(request, command, errors, page)).getClass() == SubjectMedHistoryTab.class)
                    errors.reject("ERR_SELECT_STUDY_FROM_DETAILS", "Please select a study from the \"Details\" tab");
        }

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