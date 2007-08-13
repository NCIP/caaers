package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.servlet.mvc.BaseCommandController;

/**
 * @author Saurabh Agrawal
 */
public class CreateParticipantAjaxFacade {

	public static final String AJAX_REQUEST_PARAMETER = "_isAjax";

	public static final String AJAX_INDEX_PARAMETER = "index";

	public static final String AJAX_SUBVIEW_PARAMETER = "_subview";

	public static final String CREATE_PARTICIPANT_FORM_NAME = CreateParticipantController.class.getName()
			+ ".FORM.command";

	// public static final String EDIT_PARTICIPANT_FORM_NAME = E.class.getName() + ".FORM.command";

	private static final Log log = LogFactory.getLog(CreateParticipantAjaxFacade.class);

	private OrganizationDao organizationDao;

	private NewParticipantCommand getParticipantCommand(final HttpServletRequest request) {
		NewParticipantCommand newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(
				CREATE_PARTICIPANT_FORM_NAME);
		if (newParticipantCommand == null) {
			// newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(
			// EDIT_PARTICIPANT_FORM_NAME);
		}
		request.setAttribute(BaseCommandController.DEFAULT_COMMAND_NAME, newParticipantCommand);
		return newParticipantCommand;
	}

	public List<Organization> matchOrganization(final String text) {
		List<Organization> orgs = organizationDao.getBySubnames(extractSubnames(text));
		return ObjectTools.reduceAll(orgs, "id", "name");
	}

	private String[] extractSubnames(final String text) {
		return text.split("\\s+");
	}

	public String addIdentifier(final int index, final int type) {
		HttpServletRequest request = getHttpServletRequest();
		NewParticipantCommand newParticipantCommand = getParticipantCommand(request);

		if (type == 1) {
			newParticipantCommand.getIdentifiers().add(new SystemAssignedIdentifier());
		}
		else if (type == 2) {
			newParticipantCommand.getIdentifiers().add(new OrganizationAssignedIdentifier());
		}
		request.setAttribute(AJAX_INDEX_PARAMETER, newParticipantCommand.getIdentifiers().size() - 1);
		request.setAttribute("type", type);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "newParticipantCommandIdentifierSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");

		String url = getCurrentPageContextRelative(WebContextFactory.get());
		String html = getOutputFromJsp(url);
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		return html;
	}

	public boolean deleteIdentifier(final int index) {
		NewParticipantCommand newParticipantCommand = getParticipantCommand(getHttpServletRequest());
		return newParticipantCommand.getIdentifiers().remove(index) != null;
	}

	private String getOutputFromJsp(final String jspResource) {
		String html = "Error in rendering...";
		try {
			html = WebContextFactory.get().forwardToString(jspResource);
		}
		catch (ServletException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
		catch (IOException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
		return html;
	}

	private String getCurrentPageContextRelative(final WebContext webContext) {
		String contextPath = webContext.getHttpServletRequest().getContextPath();
		String page = webContext.getCurrentPage();
		if (contextPath == null) {
			log.debug("context path not set");
			return page;
		}
		else if (!page.startsWith(contextPath)) {
			log.debug(page + " does not start with context path " + contextPath);
			return page;
		}
		else {
			return page.substring(contextPath.length());
		}
	}

	private HttpServletRequest getHttpServletRequest() {
		return WebContextFactory.get().getHttpServletRequest();
	}

	// //// CONFIGURATION

	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

}
