package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class EditResearchStaffController extends ResearchStaffController<ResearchStaffCommand> {

    private static final Log log = LogFactory.getLog(EditResearchStaffController.class);

    public EditResearchStaffController() {
        setBindOnNewForm(true);
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        ResearchStaff researchStaff = researchStaffRepository.getById(Integer.parseInt(request.getParameter("researchStaffId")));

        if (log.isDebugEnabled()) {
            log.debug("Retrieved ResearchStaff :" + String.valueOf(researchStaff));
        }
        ResearchStaffCommand command = new ResearchStaffCommand();
        command.setResearchStaff(researchStaff);
        command.setAllRoles(configPropertyRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE));
        command.buildResearchStaffCommandHelpers();
        return command;
    }
    
    @Override
    protected void layoutTabs(final Flow<ResearchStaffCommand> flow) {
        flow.addTab(new ResearchStaffTab());
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object o) {
        System.out.println("--- suppressValidation");
        if (isAjaxRequest(request)) return true;
        return super.suppressValidation(request, o);
    }
}