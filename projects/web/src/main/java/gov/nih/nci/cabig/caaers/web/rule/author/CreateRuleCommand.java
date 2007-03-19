package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.BRXMLHelper;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.rule.ServiceLocator;

import java.rmi.RemoteException;
import java.util.List;
/**
 * Command Object holding information for Rule authoring 
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateRuleCommand implements RuleInputCommand {

	private RuleSet ruleSet;
	
	public CreateRuleCommand(){
		ruleSet = new RuleSet();
	}
		
	public void save() {
		try {
			//Setting package name
			ruleSet.setName("gov.nih.nci.cabig.caaers.rule.study");
			ServiceLocator.getInstance().getRemoteRuleAuthoringService().createRuleSet(ruleSet);
			List<Rule> rules = ruleSet.getRule();
			//Set the Package name and category for all rules before saving them.
			for(Rule rule : rules) {
				rule.getMetaData().getCategory().add(getStudycategory());
				rule.getMetaData().setPackageName("gov.nih.nci.cabig.caaers.rule.study");
				rule.getCondition().getColumn().add(getStudyColumn("AML/MDS 9911"));
				ServiceLocator.getInstance().getRemoteRuleAuthoringService().createRule(rule);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public RuleSet getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	private Category getStudycategory() {
        Category category = new Category();
        MetaData metaData = new MetaData();
		category.setPath("Sponsor");
		metaData.setName("Study");
		metaData.setDescription("Study Level Triggers are registered under this category");
		category.setMetaData(metaData);
		return category;
	}
	
	private Column getStudyColumn(String studyShortTitle) {
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.StudySDO");
		column.setIdentifier("studySDO");
		FieldConstraint fieldConstraint = column.getFieldConstraint().get(0);
		fieldConstraint.setFieldName("shortTitle");
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue(studyShortTitle);
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		column.getFieldConstraint().add(fieldConstraint);
		return column;
	}

}