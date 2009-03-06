package gov.nih.nci.cabig.caaers.web.rule.deploy;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class DeployRuleController extends AbstractTabbedFlowFormController<RuleInputCommand> {

    public DeployRuleController() {
        initFlow();
        setCommandClass(DeployRuleCommand.class);
    }

    protected void initFlow() {
        setFlow(new Flow<RuleInputCommand>(getFlowName()));
        getFlow().addTab(new DeployTab());
    }

    protected String getFlowName() {
        return "Deploy Trigger";
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1,
                    Object arg2, BindException arg3) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}