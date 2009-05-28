package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.BaseCommandController;

/**
 * @author Saurabh Agrawal
 */
public class InvestigatorAjaxFacade {

    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";

    public static final String AJAX_INDEX_PARAMETER = "index";

    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";

    public static final String CREATE_INVESTIGATOR_FORM_NAME = CreateInvestigatorController.class
                    .getName()
                    + ".FORM.command";

    public static final String EDIT_INVESTIGATOR_FORM_NAME = EditInvestigatorController.class
                    .getName()
                    + ".FORM.command";

    public static final String CREATE_INVESTIGATOR_REPLACED_FORM_NAME = CREATE_INVESTIGATOR_FORM_NAME
                    + ".to-replace";

    public static final String EDIT_INVESTIGATOR_REPLACED_FORM_NAME = EDIT_INVESTIGATOR_FORM_NAME
                    + ".to-replace";

    private static final Log log = LogFactory.getLog(InvestigatorAjaxFacade.class);

    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;

    private Investigator getInvestigatorCommand(final HttpServletRequest request) {
        Investigator investigator = (Investigator) request.getSession().getAttribute(
                        CREATE_INVESTIGATOR_REPLACED_FORM_NAME);
        if (investigator == null) {
            investigator = (Investigator) request.getSession().getAttribute(
                            CREATE_INVESTIGATOR_FORM_NAME);
        }
        if (investigator == null) {
            investigator = (Investigator) request.getSession().getAttribute(
                            EDIT_INVESTIGATOR_REPLACED_FORM_NAME);
        }

        if (investigator == null) {
            investigator = (Investigator) request.getSession().getAttribute(
                            EDIT_INVESTIGATOR_FORM_NAME);
        }

        request.setAttribute(BaseCommandController.DEFAULT_COMMAND_NAME, investigator);
        return investigator;
    }

    public String addSiteInvestigator(final int index) {
        HttpServletRequest request = getHttpServletRequest();
        Investigator investigator = getInvestigatorCommand(request);

        SiteInvestigator siteInvestigator = new SiteInvestigator();
        investigator.addSiteInvestigator(siteInvestigator);

        request.setAttribute("listEditorIndex", index);
        request.setAttribute(AJAX_INDEX_PARAMETER, investigator.getSiteInvestigators().size() - 1);
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "siteInvestigatorSection");
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");

        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);
        request.setAttribute(AJAX_INDEX_PARAMETER, index);

        return html;
    }

    public boolean deleteInvestigator(final int index) {
        Investigator investigator = getInvestigatorCommand(getHttpServletRequest());
        return investigator.getSiteInvestigators().remove(index) != null;
    }

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

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
    
    
    /*
     * added this method to call this wherever any security filtering on organization is required
     */
    public List<Organization> restrictOrganization(String text) {
        List<Organization> orgs = organizationRepository.restrictBySubnames(new String[] { text });
        return ObjectTools.reduceAll(orgs, "id", "name", "nciInstituteCode","externalId");
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
