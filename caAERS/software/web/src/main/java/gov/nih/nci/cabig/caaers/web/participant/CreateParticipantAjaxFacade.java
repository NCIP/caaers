/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.utils.ranking.RankBasedSorterUtils;
import gov.nih.nci.cabig.caaers.utils.ranking.Serializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.BaseCommandController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class CreateParticipantAjaxFacade {

    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
    public static final String AJAX_INDEX_PARAMETER = "index";
    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    public static final String CREATE_PARTICIPANT_FORM_NAME = CreateParticipantController.class.getName() + ".FORM.command";
    public static final String EDIT_PARTICIPANT_FORM_NAME = EditParticipantController.class.getName() + ".FORM.command";
    public static final String CREATE_PARTICIPANT_REPLACED_FORM_NAME = CREATE_PARTICIPANT_FORM_NAME + ".to-replace";
    public static final String EDIT_PARTICIPANT_REPLACED_FORM_NAME = EDIT_PARTICIPANT_FORM_NAME + ".to-replace";

    private static final Log log = LogFactory.getLog(CreateParticipantAjaxFacade.class);

    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private StudyRepository studyRepository;
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;



    /*
    * Ajax Call hits this method to generate table
    */
    public List<ParticipantAjaxableDomainObject> getParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {
        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        if (type != null && text != null) {
            participants = constructExecuteParticipantQuery(type, text);
        }
        return participants;
    }


    /**
     * Builds and executes the HQL for Subject Search
     * */
    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> constructExecuteParticipantQuery(final String searchType, final String searchText) {
        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterByNameOrIdentifiers(searchText);
        try {
            participants = participantAjaxableDomainObjectRepository.findParticipants(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return participants;
        }
    }

    /**
     * Retrieves the Command of the AJAX Caller Flow
     * */
    private NewParticipantCommand getParticipantCommand(final HttpServletRequest request) {
        NewParticipantCommand newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(CREATE_PARTICIPANT_REPLACED_FORM_NAME);
        if (newParticipantCommand == null) {
            newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(CREATE_PARTICIPANT_FORM_NAME);
        }
        if (newParticipantCommand == null) {
            newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(EDIT_PARTICIPANT_REPLACED_FORM_NAME);
        }

        if (newParticipantCommand == null) {
            newParticipantCommand = (NewParticipantCommand) request.getSession().getAttribute(EDIT_PARTICIPANT_FORM_NAME);
        }

        request.setAttribute(BaseCommandController.DEFAULT_COMMAND_NAME, newParticipantCommand);
        return newParticipantCommand;
    }

    /**
     * Retrieving Organizations for the Autocompleter fields through AJAX
     * */
    public List<Organization> matchOrganization(final String text) {
        //List<Organization> orgs = organizationDao.getBySubnames(extractSubnames(text));
    	List<Organization> orgs = organizationRepository.restrictBySubnames(extractSubnames(text));
        orgs = RankBasedSorterUtils.sort(orgs , text, new Serializer<Organization>(){
            public String serialize(Organization object) {
                return object.getFullName();
            }
        });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode");
    }

    /**
     * Split the String by RegEx
     * */
    private String[] extractSubnames(final String text) {
        return text.split("\\s+");
    }

    /**
     * Add a new Identifier to the Subject through an AJAX call
     * */
    public String addIdentifier(final int index, final int type) {
        HttpServletRequest request = getHttpServletRequest();
        NewParticipantCommand newParticipantCommand = getParticipantCommand(request);

        if (type == 1) {
            newParticipantCommand.getParticipant().getIdentifiers().add(new SystemAssignedIdentifier());
        } else if (type == 2) {
            newParticipantCommand.getParticipant().getIdentifiers().add(new OrganizationAssignedIdentifier());
        }

        request.setAttribute("listEditorIndex", index);
        request.setAttribute(AJAX_INDEX_PARAMETER, newParticipantCommand.getParticipant().getIdentifiers().size() - 1);
        request.setAttribute("type", type);
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "newParticipantCommandIdentifierSection");
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");

        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);
        request.setAttribute(AJAX_INDEX_PARAMETER, index);

        return html;
    }

    /*
    * Delete an identifier from the Subject
    * */
    public boolean deleteIdentifier(final int index) {
        NewParticipantCommand newParticipantCommand = getParticipantCommand(getHttpServletRequest());
        return newParticipantCommand.getParticipant().getIdentifiers().remove(index) != null;
    }

    /**
     * Build the HTML output for the AJAX call
     * */
    private String getOutputFromJsp(final String jspResource) {
        String html = "Error in rendering...";
        try {
            html = WebContextFactory.get().forwardToString(jspResource);
        } catch (ServletException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }
        return html;
    }

    /**
     * Get the current page of the flow relative to the context
     * */
    private String getCurrentPageContextRelative(final WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();

        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }

    // //// CONFIGURATION
    @Required

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Required
	public void setParticipantAjaxableDomainObjectRepository(
			ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository) {
		this.participantAjaxableDomainObjectRepository = participantAjaxableDomainObjectRepository;
	}

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}
	
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}


}
