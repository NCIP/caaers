package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurbah Agrawal
 */
public class EditOrganizationController extends OrganizationController<Organization> {

	private static final Log log = LogFactory.getLog(EditOrganizationController.class);

	public EditOrganizationController() {
		setBindOnNewForm(true);
	}

	// /LOGIC

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
		Organization organization = organizationDao.getById(Integer.parseInt(request.getParameter("organizationId")));

		if (log.isDebugEnabled()) {
			log.debug("Retrieved Organization :" + String.valueOf(organization));
		}

		return organization;
	}

	@Override
	protected Organization save(final Organization organization, final Errors errors) {
		if (errors.hasErrors()) {
			return organization;
		}
		organizationService.createOrUpdate(organization);
		return organization;
	}

	@Override
	protected boolean isSummaryEnabled() {
		return true;
	}

	@Override
	protected void layoutTabs(final Flow<Organization> flow) {
		flow.addTab(new OrganizationTab());
	}

    @Override
    @SuppressWarnings({ "unchecked" })
    protected ModelAndView processFinish(
        final HttpServletRequest request, final HttpServletResponse response,
        final Object command, final BindException errors
    ) throws Exception {
        ModelAndView mv = super.processFinish(request, response, command, errors);
        mv.getModel().put("flashMessage", "Successfully updated the Organization");
        return mv;
    }
}