package gov.nih.nci.cabig.caaers.rules.runtime.action;

import gov.nih.nci.cabig.caaers.rules.runtime.RuleContext;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public interface NotificationHandler {

    public void performNotify(ActionContext actionContext, RuleContext ruleContext);

}