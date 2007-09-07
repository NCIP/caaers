package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.rmi.RemoteException;

public class ImportRuleCommand implements RuleInputCommand {

	private String folder;
	private String message;
	private boolean updated = false ;

	public boolean isUpdated() {
		return updated;
	}


	public void setUpdated(boolean updated) {
		this.updated = updated;
	}


	public ImportRuleCommand() throws RemoteException {
		//ruleSets = ruleAuthoringService.getAllRuleSets();
		//ruleSets.remove(0);//removing the default
	}


	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
