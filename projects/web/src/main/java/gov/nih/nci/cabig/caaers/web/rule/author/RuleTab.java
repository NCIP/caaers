package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;


/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleTab extends DefaultTab {

	
	public RuleTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public RuleTab() {
		super("Add Rules","Rules","rule/author/authorRules");
	}


}