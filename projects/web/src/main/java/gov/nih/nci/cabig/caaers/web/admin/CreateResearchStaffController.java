package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurabh Agrawal
 */
public class CreateResearchStaffController extends ResearchStaffController<ResearchStaff> {

	@Override
	protected void layoutTabs(final Flow<ResearchStaff> flow) {
		flow.addTab(new ResearchStaffTab());
	}

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {

		ResearchStaff researchStaff = new ResearchStaff();
		return researchStaff;
	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {

		return super.processFinish(request, response, command, errors);
	}

}