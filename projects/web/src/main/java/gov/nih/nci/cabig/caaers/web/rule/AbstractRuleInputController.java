package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.web.rule.author.ReviewTab;
import gov.nih.nci.cabig.caaers.web.rule.author.RuleTab;
import gov.nih.nci.cabig.caaers.web.rule.author.SelectRueTypeTab;
import gov.nih.nci.cabig.caaers.web.rule.author.TriggerTab;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;

/**
 *
 * @author Sujith Vellat Thayyilthodi
 * */
public abstract class AbstractRuleInputController <C extends RuleInputCommand> extends AbstractTabbedFlowFormController<RuleInputCommand> {

	
    protected AbstractRuleInputController() {
        initFlow();
        
    }

    protected void initFlow() {
        setFlow(new Flow<RuleInputCommand>(getFlowName()));
    }
    
    protected abstract String getFlowName();

}
