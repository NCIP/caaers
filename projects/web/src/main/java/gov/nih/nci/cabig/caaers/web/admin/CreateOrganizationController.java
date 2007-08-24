package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Controller class for adding new organizations.
 * 
 * @author Saurabh Agrawal, Created on 07/18/2007
 */
public class CreateOrganizationController extends SimpleFormController {

	/**
	 * Validator class for Organization
	 */
	private class OrganizationValidator implements Validator {
		public boolean supports(Class clazz) {
			return clazz.equals(Organization.class);
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
		 */
		public void validate(Object object, Errors errors) {
			Organization command = (Organization) object;
			boolean name = command.getName() == null || command.getName().equals("");
			boolean descriptionText = command.getDescriptionText() == null || command.getDescriptionText().equals("");
			boolean nciInstituteCode = command.getNciInstituteCode() == null
					|| command.getNciInstituteCode().equals("");

			if (name) {
				errors.rejectValue("name", "REQUIRED", "Missing name");
			}
			// if (descriptionText) {
			// errors.rejectValue("descriptionText", "REQUIRED", "Missing Description Text");
			// }
			if (nciInstituteCode) {
				errors.rejectValue("nciInstituteCode", "REQUIRED", "Missing NCI Institute Code");
			}

		}
	}

	/** The organization dao. */
	private OrganizationDao organizationDao;

	/** The configuration property. */
	private ConfigProperty configurationProperty;

	/**
	 * Instantiates a new create organization controller.
	 */
	public CreateOrganizationController() {
		setCommandClass(Organization.class);
		setValidator(new OrganizationValidator());
		setFormView("admin/organization_details");
		setSuccessView("admin/organization_details");
	}

	/**
	 * Creates a new organization that is not yet persisted to DB.
	 * 
	 * @return the organization
	 */
	private Organization createOrganization() {
		Organization organization = new Organization();
		return organization;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return createOrganization();
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		Organization organization = (Organization) command;
		organizationDao.save(organization);
		ModelAndView modelAndView = new ModelAndView("admin/organization_confirmation");
		modelAndView.addAllObjects(errors.getModel());
		return modelAndView;

	}

	/**
	 * Sets the configuration property.
	 * 
	 * @param configurationProperty the configuration property
	 */
	@Required
	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

	/**
	 * Sets the organization dao.
	 * 
	 * @param organizationDao the organization dao
	 */
	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

}
