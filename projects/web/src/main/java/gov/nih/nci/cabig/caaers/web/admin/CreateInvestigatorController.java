package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Priyatam
 */
public class CreateInvestigatorController extends SimpleFormController {

	private InvestigatorDao investigatorDao;

	private OrganizationDao organizationDao;

	private ConfigProperty configurationProperty;

	public CreateInvestigatorController() {
		setCommandClass(Investigator.class);
		setFormView("admin/investigator_details");
		setSuccessView("admin/investigator_details");
	}

	@Override
	protected Map<String, Object> referenceData(final HttpServletRequest request) throws Exception {
		Map<String, Object> refdata = new HashMap<String, Object>();
		return referenceData(refdata);
	}

	private Map<String, Object> referenceData(final Map<String, Object> refdata) {
		Map<String, List<Lov>> configMap = configurationProperty.getMap();

		refdata.put("sitesRefData", getOrganizations());
		refdata.put("studySiteStatusRefData", configMap.get("studySiteStatusRefData"));
		refdata.put("studySiteRoleCodeRefData", configMap.get("studySiteRoleCodeRefData"));
		return refdata;
	}

	public void validate(final Investigator command, final Errors errors) {
		boolean firstName = command.getFirstName() == null || command.getFirstName().equals("");
		boolean lastName = command.getLastName() == null || command.getLastName().equals("");
		boolean emailAddress = command.getEmailAddress() == null || command.getEmailAddress().equals("");
		boolean phoneNumber = command.getPhoneNumber() == null || command.getPhoneNumber().equals("");
		if (firstName) {
			errors.rejectValue("firstName", "REQUIRED", "Missing First Name");
		}
		if (lastName) {
			errors.rejectValue("lastName", "REQUIRED", "Missing Last Name");
		}
		if (emailAddress) {
			errors.rejectValue("emailAddress", "REQUIRED", "Missing Email Address");
		}
		if (phoneNumber) {
			errors.rejectValue("phoneNumber", "REQUIRED", "Missing Phone Number");
		}

	}

	@Override
	protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(true));
		binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));
	}

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		return createInvestigatorWithDesign();
	}

	@Override
	protected ModelAndView onSubmit(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {

		int selected = Integer.parseInt(request.getParameter("_selected"));
		String action = request.getParameter("_action");
		Investigator investigator = (Investigator) command;

		if ("addSite".equals(action)) {
			SiteInvestigator siteInvestigator = new SiteInvestigator();
			investigator.addSiteInvestigator(siteInvestigator);
		}
		else if ("removeSite".equals(action)) {
			investigator.getSiteInvestigators().remove(selected);
		}
		else {
			Investigator inv = (Investigator) command;
			investigatorDao.save(inv);

			return new ModelAndView(new RedirectView("createInvestigator"));
		}

		Map map = errors.getModel();
		referenceData(map);
		ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);

		// needed for saving session state
		request.getSession().setAttribute(getFormSessionAttributeName(), command);

		return modelAndView;
	}

	private void handleSiteInvestigatorAction(final Investigator investigator, final String action,
			final String selected) {
		if ("addSite".equals(action)) {
			SiteInvestigator siteInvestigator = new SiteInvestigator();
			investigator.addSiteInvestigator(siteInvestigator);
		}
		else if ("removeSite".equals(action)) {
			investigator.getSiteInvestigators().remove(Integer.parseInt(selected));
		}
	}

	private Investigator createInvestigatorWithDesign() {
		Investigator investigator = new Investigator();
		SiteInvestigator siteInvestigator = new SiteInvestigator();
		investigator.addSiteInvestigator(siteInvestigator);

		return investigator;
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	private List<Organization> getOrganizations() {
		return organizationDao.getAll();
	}

	public InvestigatorDao getInvestigatorDao() {
		return investigatorDao;
	}

	public void setInvestigatorDao(final InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}

	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(final ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
}