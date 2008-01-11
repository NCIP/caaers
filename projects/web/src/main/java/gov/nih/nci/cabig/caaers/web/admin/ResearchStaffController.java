package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.service.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
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
 * Base Controller class to handle the basic work flow in the Creation / Updation of a ResearchStaff Design. This uses
 * AbstractTabbedFlowFormController to implement tabbed workflow
 * @author Saurabh
 */
public abstract class ResearchStaffController<C extends ResearchStaff> extends
		AutomaticSaveFlowFormController<C, ResearchStaff, ResearchStaffDao> {

	private static final Log log = LogFactory.getLog(ResearchStaffController.class);

	protected ResearchStaffRepository researchStaffRepository;

	private OrganizationDao organizationDao;

    protected WebControllerValidator webControllerValidator;


	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public ResearchStaffController() {
		setCommandClass(ResearchStaff.class);
		Flow<C> flow = new Flow<C>("Create Research Staff");
		layoutTabs(flow);
		setFlow(flow);
		setAllowDirtyBack(false);
		setAllowDirtyForward(false);
	}

	// /LOGIC
	@Override
	protected ResearchStaff getPrimaryDomainObject(final C command) {
		return command;
	}

	@Required
	public void setResearchStaffRepository(final ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	@Override
	protected ResearchStaffDao getDao() {
		return null;
	}

	protected abstract void layoutTabs(Flow<C> flow);

	@Override
	protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));

	}
	
	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {
		
		ResearchStaff researchStaff = (ResearchStaff) command;
		researchStaffRepository.save(researchStaff);
		if(!errors.hasErrors()){
			request.setAttribute("statusMessage", "Research staff saved successfully.");
		}

		ModelAndView modelAndView = new ModelAndView("admin/research_staff_review");
		modelAndView.addAllObjects(errors.getModel());
		modelAndView.addObject("researchStaff", researchStaff);
		return modelAndView;

	}


    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        webControllerValidator.validate(request, command, errors);
    }
    
    @Required
    public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
        this.webControllerValidator = webControllerValidator;
    }
}
