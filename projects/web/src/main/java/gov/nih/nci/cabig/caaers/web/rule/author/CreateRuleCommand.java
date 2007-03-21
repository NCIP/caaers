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

	private static final String SPONSOR_LEVEL = "Sponsor";
	private static final String INSTITUTIONAL_LEVEL = "Institution";
	private static final String PROTOCOL_LEVEL = "Study";
	
	static {
		createCategories();
	}
	
	private RuleSet ruleSet;
	
	private String shortTitle;
	
	
	public CreateRuleCommand() {
		ruleSet = new RuleSet();
	}

	public void save() {
		try {
			try {
				//Setting package name
				ruleSet.setName("gov.nih.nci.cabig.caaers.rule.study");
				ServiceLocator.getInstance().getRemoteRuleAuthoringService().createRuleSet(ruleSet);
			} catch(Exception e) {
				//TODO check if package exists instead of catching exception
				e.printStackTrace();
			}
			List<Rule> rules = ruleSet.getRule();
			//Set the Package name and category for all rules before saving them.
			for(Rule rule : rules) {
				rule.getMetaData().getCategory().add(getStudycategory());
				rule.getMetaData().setPackageName("gov.nih.nci.cabig.caaers.rule.study");
				rule.getCondition().getColumn().add(getStudyColumn(getShortTitle()));
				rule.getMetaData().setDescription("Dummy Description");
				ServiceLocator.getInstance().getRemoteRuleAuthoringService().createRule(rule);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the category. If not found creates one.
	 * */
	private static void createCategories() {
		
		try {
		
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/");
		metaData.setName(SPONSOR_LEVEL);
		metaData.setDescription("Sponsor Level Triggers are registered under this category");
		category.setMetaData(metaData);
		ServiceLocator.getInstance().getRemoteRuleAuthoringService().createCategory(category);

		category = new Category();
		metaData = new MetaData();
		category.setPath(SPONSOR_LEVEL);
		metaData.setName(INSTITUTIONAL_LEVEL);
		metaData.setDescription("Institution Level Triggers are registered under this category");
		category.setMetaData(metaData);
		ServiceLocator.getInstance().getRemoteRuleAuthoringService().createCategory(category);

		category = new Category();
		metaData = new MetaData();
		category.setPath(SPONSOR_LEVEL + "/" +INSTITUTIONAL_LEVEL);
		metaData.setName(PROTOCOL_LEVEL);
		metaData.setDescription("Study Level Triggers are registered under this category");
		category.setMetaData(metaData);		
		ServiceLocator.getInstance().getRemoteRuleAuthoringService().createCategory(category);

		} catch (Exception e) {
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
		category.setPath(SPONSOR_LEVEL + "/" +INSTITUTIONAL_LEVEL);
		metaData.setName(PROTOCOL_LEVEL);
		metaData.setDescription("Study Level Triggers are registered under this category");
		category.setMetaData(metaData);	
		return category;
	}
	
	private Column getStudyColumn(String studyShortTitle) {
		//Only For testing purpose
		if(studyShortTitle == null) studyShortTitle = "AML/MDS 9911";
		
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

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}



}