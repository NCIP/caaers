package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
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
        Organization organization = new LocalOrganization();
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

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        return super.suppressValidation(request, command);
    }
}