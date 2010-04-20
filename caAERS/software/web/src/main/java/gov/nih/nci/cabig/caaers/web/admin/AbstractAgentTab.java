package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ion C. Olaru
 *
 */
public abstract class AbstractAgentTab extends TabWithFields<AgentCommand> {

    protected static final Log log = LogFactory.getLog(AbstractAgentTab.class);
    protected Map<String, String> methodNameMap = new HashMap<String, String>();

    public AbstractAgentTab(String lName, String sName, String vName) {
        super(lName, sName, vName);
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, AgentCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        return refdata;
    }

    @Override
    public void onBind(HttpServletRequest request, AgentCommand command, Errors errors) {
        super.onBind(request, command, errors);
    }

    @Override
    protected void validate(final AgentCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
    }

    @Override
    protected boolean methodInvocationRequest(HttpServletRequest request) {
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }

    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
        System.out.println(methodNameMap.get(task + currentItem));
    	return methodNameMap.get(task + currentItem);
    }

    //
    public ModelAndView addSiteResearchStaff(HttpServletRequest request, Object object, Errors errors) {
        ResearchStaffCommand  command = (ResearchStaffCommand)object;
        SiteResearchStaff srs = new SiteResearchStaff();
        command.getResearchStaff().addSiteResearchStaff(srs);
        srs.setResearchStaff(command.getResearchStaff());
        command.addSiteResearchStaffCommandHelper();

        ModelAndView modelAndView = new ModelAndView("admin/ajax/researchStaffFormSection");
        // modelAndView.getModel().put("objects", command.getResearchStaff().getSiteResearchStaffs());
        modelAndView.getModel().put("indexes", new Integer[]{command.getResearchStaff().getSiteResearchStaffs().size() - 1});
        return modelAndView;
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AgentCommand command) {
        return new InputFieldGroupMap();
    }
}