package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
		return new ResearchStaff();
	}

}