package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurbah Agrawal
 */
public class EditParticipantController extends ParticipantController<NewParticipantCommand> {

	private static final Log log = LogFactory.getLog(EditParticipantController.class);

	public EditParticipantController() {
		setBindOnNewForm(true);
	}

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {

		request.getSession().removeAttribute(CreateParticipantAjaxFacade.CREATE_PARTICIPANT_FORM_NAME);
		request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
		Participant participant = participantDao.getParticipantById(Integer.parseInt(request
				.getParameter("participantId")));

		if (log.isDebugEnabled()) {
			log.debug("Retrieved Participant :" + String.valueOf(participant));
		}

		NewParticipantCommand participantCommand = new NewParticipantCommand(participant);

		List<StudyParticipantAssignment> assignments = participant.getAssignments();
		List<StudySite> studySites = new ArrayList<StudySite>();
		for (StudyParticipantAssignment studyParticipantAssignment : assignments) {
			studySites.add(studyParticipantAssignment.getStudySite());
		}
		participantCommand.setStudySites(studySites);
		participantCommand.setOrganization(participant.getAssignments().get(0).getStudySite().getOrganization());
		return participantCommand;

	}

	@Override
	protected NewParticipantCommand save(final NewParticipantCommand newParticipantCommand, final Errors errors) {

		if (errors.hasErrors()) {
			return newParticipantCommand;
		}
		Participant participant = newParticipantCommand.getParticipant();
		Participant mergedParticipant = getDao().merge(participant);
		getDao().initialize(mergedParticipant);
		getDao().save(mergedParticipant);
		newParticipantCommand.setParticipant(mergedParticipant);
		return newParticipantCommand;
	}

	@Override
	protected boolean isSummaryEnabled() {
		return true;
	}

	@Override
	protected void layoutTabs(final Flow<NewParticipantCommand> flow) {
		flow.addTab(new ReviewParticipantTab());
		flow.addTab(new CreateParticipantTab());

	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {

		NewParticipantCommand participantCommand = (NewParticipantCommand) command;
		Participant participant = participantCommand.getParticipant();
		participantDao.merge(participant);
		request.setAttribute("flashMessage", "Successfully updated the Participant");
		ModelAndView modelAndView = new ModelAndView("par/par_confirm");
		return modelAndView;
	}

	@Override
	protected boolean shouldSave(final HttpServletRequest request, final NewParticipantCommand command,
			final Tab<NewParticipantCommand> tab) {
		// supress for ajax and delete requests
		Object isAjax = findInRequest(request, "_isAjax");
		if (isAjax != null) {
			return false;
		}

		String action = (String) super.findInRequest(request, "_action");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
			return false;
		}
		return super.shouldSave(request, command, tab) && tab.getNumber() != 0; // dont save if it is overview page

	}

}