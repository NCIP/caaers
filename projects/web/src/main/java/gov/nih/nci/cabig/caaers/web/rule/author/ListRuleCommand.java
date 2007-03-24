package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class ListRuleCommand implements RuleInputCommand {

	private List<RuleSet> ruleSets;
	
	
	public ListRuleCommand(RuleAuthoringService ruleAuthoringService) throws RemoteException {
		ruleSets = ruleAuthoringService.getAllRuleSets();
		//ruleSets.remove(0);//removing the default
	}

	public List<RuleSet> getRuleSets() {
		return ruleSets;
	}

	public void setRuleSets(List<RuleSet> ruleSets) {
		this.ruleSets = ruleSets;
	}
}
