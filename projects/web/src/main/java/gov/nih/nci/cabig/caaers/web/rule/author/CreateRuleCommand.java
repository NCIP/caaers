package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.BRXMLHelper;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Command Object holding information for Rule authoring 
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateRuleCommand implements RuleInputCommand {

	public static final String SPONSOR_LEVEL = "Sponsor";
	public static final String INSTITUTIONAL_LEVEL = "Institution";
	public static final String STUDY_LEVEL = "Study";
	
	private RuleAuthoringService ruleAuthoringService;
	
	private RuleSet ruleSet;
	
	private String category;
	
	private String level;
	
	public CreateRuleCommand(RuleAuthoringService ruleAuthoringService) {
		this.ruleAuthoringService = ruleAuthoringService;
		createCategories();
		ruleSet = new RuleSet();
	}

	public void save() {
		try {
			
			//Create Package if it does not exist
			createPackage();

			List<Rule> rules = ruleSet.getRule();
			//Set the Package name and category for all rules before saving them.
			for(Rule rule : rules) {
				rule.getMetaData().getCategory().addAll(getAllCategories());
				rule.getMetaData().setPackageName(getPackageName());
				//rule.getCondition().getColumn().get(0).setIdentifier("adverseEventSDO");
				populateCategoryBasedColumns(rule);
				rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");
				ruleAuthoringService.createRule(rule);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void createPackage() throws RemoteException {
		try {
			RuleSet studyRuleSet = ruleAuthoringService.getRuleSet(getPackageName());
		} catch(Exception exception) {
			ruleSet.setName(getPackageName());
			ruleAuthoringService.createRuleSet(ruleSet);
		}		
	}

	/**
	 * Loads the category. If not found creates one.
	 * */
	private void createCategories() {
		
		try {

			createCategory("/", SPONSOR_LEVEL, "Sponsor Level Rules are registered under this category");
	
			createCategory(SPONSOR_LEVEL, INSTITUTIONAL_LEVEL, "Institution Level Rules are registered under this category");

			createCategory(SPONSOR_LEVEL + "/" +INSTITUTIONAL_LEVEL, STUDY_LEVEL, "Study Level Rules are registered under this category");

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
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	private String getPackageName() {
		String packageName = "gov.nih.nci.cabig.caaers.rule.study";
		if(INSTITUTIONAL_LEVEL.equals(getLevel())) {
			packageName = "gov.nih.nci.cabig.caaers.rule.institution";
		} else if(SPONSOR_LEVEL.equals(getLevel())) {
			packageName = "gov.nih.nci.cabig.caaers.rule.sponsor";
		}
		return packageName;
	}
	
	
	private List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		if(STUDY_LEVEL.equals(getLevel())) {
			String study = getCategory();
			categories.add(getStudycategory());
			Category category = createCategory(SPONSOR_LEVEL + "/" +INSTITUTIONAL_LEVEL + "/" + STUDY_LEVEL, study, "Sponsor Level Rules are registered under this category");
			categories.add(category);
		} else if(INSTITUTIONAL_LEVEL.equals(getLevel())) {
			String institution = getCategory();
			Category category = createCategory(SPONSOR_LEVEL + "/" +INSTITUTIONAL_LEVEL, institution, "Institution Level Rules are registered under this category");
			categories.add(category);
		} else if(SPONSOR_LEVEL.equals(getLevel())) {
			String sponsor = getCategory();
			categories.add(getSponsorCategory());
			Category category = createCategory(SPONSOR_LEVEL, sponsor, "Institution Level Rules are registered under this category");
			categories.add(category);
		}
		return categories;
	}

	/**
	 * Create a category if it does not exist in the DB
	 * */
	private Category createCategory(String path, String name, String description) {
		Category category = null;
		try {
			category = ruleAuthoringService.getCategory(path + "/" + name);
		} catch(RemoteException e) {
			//Forget this exception now
			e.printStackTrace();
		}
		category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(path);
		metaData.setName(name);
		metaData.setDescription(description);
		category.setMetaData(metaData);		
		try {
			ruleAuthoringService.createCategory(category);
		} catch(RemoteException remoteException) {
			throw new CaaersSystemException(remoteException.getMessage(), remoteException);
		}
		return category;
	}
	

	
	public Category getStudycategory() {
        Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(SPONSOR_LEVEL + "/" +INSTITUTIONAL_LEVEL);
		metaData.setName(STUDY_LEVEL);
		metaData.setDescription("Study Level Triggers are registered under this category");
		category.setMetaData(metaData);	
		return category;
	}

	private Category getSponsorCategory() {
        Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/");
		metaData.setName(SPONSOR_LEVEL);
		metaData.setDescription("Sponsor Level Triggers are registered under this category");
		category.setMetaData(metaData);
		return category;
	}
	
	private void populateCategoryBasedColumns(Rule rule) {
		if(STUDY_LEVEL.equals(getLevel())) {
			rule.getCondition().getColumn().add(getCategoryBasedColumn(getCategory()));
		} else if(SPONSOR_LEVEL.equals(getLevel())) {
			
		}
	}

	private Column getCategoryBasedColumn(String categoryValue) {
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.StudySDO");
		column.setIdentifier("studySDO");
		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName(getFieldNameBasedOnLevel());
		fieldConstraints.add(fieldConstraint);
		ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue(categoryValue);
		literalRestrictions.add(literalRestriction);
		fieldConstraint.setLiteralRestriction(literalRestrictions);
		column.setFieldConstraint(fieldConstraints);
		return column;
	}
	
	
	private String getFieldNameBasedOnLevel() {
		String fieldName = "shortTitle";
		if(SPONSOR_LEVEL.equals(getLevel())) {
			fieldName = "primarySponsorCode";
		} else if (INSTITUTIONAL_LEVEL.equals(getLevel())) {
			fieldName = "site";
		}
		return fieldName;
	}

	public RuleAuthoringService getRuleAuthoringService() {
		return ruleAuthoringService;
	}

	public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
		this.ruleAuthoringService = ruleAuthoringService;
	}

}