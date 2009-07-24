package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Date;

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
        ResearchStaffCommand command = new ResearchStaffCommand();
        command.setResearchStaff(rs);
        command.setAllRoles(configPropertyRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE));
        return command;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        System.out.println("--- suppressValidation");
        return super.suppressValidation(request, command); 
    }
}