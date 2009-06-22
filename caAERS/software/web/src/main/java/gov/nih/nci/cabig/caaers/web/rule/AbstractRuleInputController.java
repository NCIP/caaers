package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public abstract class AbstractRuleInputController<C extends RuleInputCommand> extends
                AbstractTabbedFlowFormController<RuleInputCommand> {

    protected AbstractRuleInputController() {
        initFlow();

    }

    protected void initFlow() {
        setFlow(new Flow<RuleInputCommand>(getFlowName()));
    }

    protected abstract String getFlowName();

}
