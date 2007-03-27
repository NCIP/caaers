package gov.nih.nci.cabig.caaers.rules.runtime.action;

import gov.nih.nci.cabig.caaers.rules.brxml.Action;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class ActionContext {

	private Action action;

	private String actionId;
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}


}