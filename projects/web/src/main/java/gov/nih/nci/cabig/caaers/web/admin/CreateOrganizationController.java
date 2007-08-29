package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller class for adding new organizations.
 * 
 * @author Saurabh Agrawal, Created on 07/18/2007
 */
public class CreateOrganizationController extends OrganizationController<Organization> {

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
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		return createOrganization();
	}

	@Override
	protected void layoutTabs(final Flow<Organization> flow) {
		flow.addTab(new OrganizationTab());
	}

}