package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Saurbah Agrawal
 */
public class EditResearchStaffController extends ResearchStaffController<ResearchStaff> {

    private static final Log log = LogFactory.getLog(EditResearchStaffController.class);

    public EditResearchStaffController() {
        setBindOnNewForm(true);
    }

    // /LOGIC

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        ResearchStaff researchStaff = researchStaffRepository.getById(Integer.parseInt(request.getParameter("researchStaffId")));

        if (log.isDebugEnabled()) {
            log.debug("Retrieved ResearchStaff :" + String.valueOf(researchStaff));
        }

        return researchStaff;
    }

    @Override
    protected void layoutTabs(final Flow<ResearchStaff> flow) {
        flow.addTab(new ResearchStaffTab());
    }

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final ResearchStaff command, final Tab<ResearchStaff> tab) {
        // supress for ajax and delete requests
        return super.shouldSave(request, command, tab);
    }

}