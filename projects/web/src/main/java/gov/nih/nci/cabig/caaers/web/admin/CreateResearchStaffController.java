package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
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

        ResearchStaff researchStaff = new LocalResearchStaff();
        return researchStaff;
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