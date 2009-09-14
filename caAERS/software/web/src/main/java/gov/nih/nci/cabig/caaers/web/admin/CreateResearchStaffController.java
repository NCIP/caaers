package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Saurabh Agrawal
 */
public class CreateResearchStaffController extends ResearchStaffController<ResearchStaffCommand> {

    @Override
    protected void layoutTabs(final Flow<ResearchStaffCommand> flow) {
        flow.addTab(new ResearchStaffTab());
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        ResearchStaff rs = new LocalResearchStaff();
        rs.setAddress(new Address());
        ResearchStaffCommand command = new ResearchStaffCommand();

        command.setResearchStaff(rs);
        command.setAllRoles(configPropertyRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE));

        // create new SiteResearchStaff
        SiteResearchStaff srs = new SiteResearchStaff();
        command.getResearchStaff().addSiteResearchStaff(srs);
        srs.setResearchStaff(command.getResearchStaff());
        command.addSiteResearchStaffCommandHelper();
        
        return command;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        if (isAjaxRequest(request)) return true;
        return super.suppressValidation(request, command); 
    }
}