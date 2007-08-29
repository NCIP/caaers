package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a ResearchStaff Design This uses
 * AbstractTabbedFlowFormController to implement tabbed workflow
 * @author Saurabh
 */
public abstract class ResearchStaffController<C extends ResearchStaff> extends
		AutomaticSaveFlowFormController<C, ResearchStaff, ResearchStaffDao> {

	private static final Log log = LogFactory.getLog(ResearchStaffController.class);

	protected ResearchStaffDao researchStaffDao;

	private OrganizationDao organizationDao;

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
	public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	@Override
	protected ResearchStaffDao getDao() {
		return researchStaffDao;
	}

	protected abstract void layoutTabs(Flow<C> flow);

	@Override
	protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));

	}

	@Override
	@SuppressWarnings("unchecked")
	protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors,
			final int page) throws Exception {
		Map<String, Object> refdata = super.referenceData(request, command, errors, page);
		return refdata;
	}

	/**
	 * Override this in sub controller if summary is needed
	 * @return
	 */
	protected boolean isSummaryEnabled() {
		return false;
	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {

		ResearchStaff staff = (ResearchStaff) command;
		researchStaffDao.save(staff);
		return new ModelAndView(new RedirectView("createResearchStaff"));

	}

}
