package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a Organization
 * Design This uses AbstractTabbedFlowFormController to implement tabbed workflow
 * 
 * @author Saurabh
 */
// TODO: this "flow" only has one tab in all its forms. It shouldn't use the complexity of a flow
// controller
public abstract class OrganizationController<C extends Organization> extends
                AutomaticSaveAjaxableFormController<C, Organization, OrganizationDao> {

    private static final Log log = LogFactory.getLog(OrganizationController.class);

    protected OrganizationDao organizationDao;

    protected OrganizationRepository organizationRepository;

    protected WebControllerValidator webControllerValidator;

    public OrganizationController() {
        setCommandClass(Organization.class);
        Flow<C> flow = new Flow<C>("Create Organization");
        layoutTabs(flow);
        setFlow(flow);
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
    }

    // /LOGIC
    @Override
    protected Organization getPrimaryDomainObject(final C command) {
        return command;
    }

    @Required
    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    protected OrganizationDao getDao() {
        return organizationDao;
    }

    /**
     * Template method to let the subclass decide the order of tab
     */
    protected abstract void layoutTabs(Flow<C> flow);

    @Override
    protected void initBinder(final HttpServletRequest request,
                    final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

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
    protected ModelAndView processFinish(final HttpServletRequest request,
                    final HttpServletResponse response, final Object command,
                    final BindException errors) throws Exception {

        Organization organization = (Organization) command;
        organizationRepository.createOrUpdate(organization);
        ModelAndView modelAndView = new ModelAndView("admin/organization_confirmation");
        modelAndView.addAllObjects(errors.getModel());
        return modelAndView;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command,
                    BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        webControllerValidator.validate(request, command, errors);
    }

    @Required
    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Required
    public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
        this.webControllerValidator = webControllerValidator;
    }
}
