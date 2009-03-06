package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a Investigator
 * Design This uses AbstractTabbedFlowFormController to implement tabbed workflow
 * 
 * @author Saurabh
 */
public abstract class InvestigatorController<C extends Investigator> extends
	AutomaticSaveAjaxableFormController<C, Investigator, InvestigatorDao> {

    private static final Log log = LogFactory.getLog(InvestigatorController.class);

    protected InvestigatorDao investigatorDao;
    protected InvestigatorRepository investigatorRepository;

    protected OrganizationDao organizationDao;

    protected ConfigProperty configurationProperty;
    private String authenticationMode;

    public InvestigatorController() {
        setCommandClass(Investigator.class);
        Flow<C> flow = new Flow<C>("Create Investigator");
        layoutTabs(flow);
        setFlow(flow);
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
    }

    @Override
    protected void initBinder(final HttpServletRequest request,
                    final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
        binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));
    }

    /**
     * Template method to let the subclass decide the order of tab
     */
    protected abstract void layoutTabs(Flow<C> flow);

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors, final int page) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, command, errors, page);
        refdata.put("authenticationMode", getAuthenticationMode());
        return refdata;

    }

    /**
     * Override this in sub controller if summary is needed
     * 
     * @return
     */
    protected boolean isSummaryEnabled() {
        return false;
    }

    @Override
    protected boolean suppressValidation(final HttpServletRequest request) {

        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null) {
            return true;
        }
        String action = (String) findInRequest(request, "_action");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
            return true;
        }
        return super.suppressValidation(request);
    }

    @Override
    protected String getViewName(final HttpServletRequest request, final Object command,
                    final int page) {
        Object subviewName = findInRequest(request, "_subview");
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
    protected ModelAndView processFinish(final HttpServletRequest request,
                    final HttpServletResponse response, final Object command,
                    final BindException errors) throws Exception {

        int selected = Integer.parseInt(request.getParameter("_selected"));
        String action = request.getParameter("_action");
        Investigator investigator = (Investigator) command;
        boolean sentEmail = true;
        if ("addSiteInvestigator".equals(action)) {
        } else if ("removeInvestigator".equals(action)) {
        } else {
            try {
				investigatorRepository.save(investigator, ResetPasswordController.getURL(request
				        .getScheme(), request.getServerName(), request.getServerPort(), request
				        .getContextPath()));
            } catch (MailException e) {
                logger.error("Could not send email to user.", e);
                sentEmail = false;
            }
            request.setAttribute("statusMessage", "Successfully saved the investigator"
            		+ ((sentEmail) ? "." : ". But we could not send email to user."));
            ModelAndView modelAndView = new ModelAndView("admin/investigator_review");
            modelAndView.addAllObjects(errors.getModel());
            modelAndView.addObject("investigator", investigator);
            return modelAndView;
        }
        return new ModelAndView(new RedirectView("createInvestigator"));

    }

    @Override
    protected Investigator getPrimaryDomainObject(final C command) {
        return command;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public InvestigatorDao getInvestigatorDao() {
        return investigatorDao;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(final ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    public void setInvestigatorDao(final InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }

    @Override
    protected InvestigatorDao getDao() {
        return investigatorDao;
    }

	public String getAuthenticationMode() {
		return authenticationMode;
	}

	public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}
    
    public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}
    
    public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

}
