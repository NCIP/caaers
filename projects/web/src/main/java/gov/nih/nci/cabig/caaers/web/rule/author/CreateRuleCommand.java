package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.BRXMLHelper;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Command Object holding information for Rule authoring 
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateRuleCommand implements RuleInputCommand 
{

	private static final Log logger = LogFactory.getLog(CreateRuleCommand.class);
	
	public static final String SPONSOR_LEVEL = "Sponsor";
	public static final String INSTITUTIONAL_LEVEL = "Institution";
	public static final String STUDY_LEVEL = "Study";
	
	private RuleAuthoringService ruleAuthoringService;
	
	private RulesEngineService rulesEngineService;
	
	private NotificationDao notificationDao; 
	
	private StudyDao studyDao;
	
	private RuleSet ruleSet;
	
	private String categoryIdentifier;               // Study Short Title
	
	private String level;
	
	private String sponsorName;      
	
	private String ruleSetName;                      // Ruleset selected by the user
	
	private List<RuleSet> existingRuleSets;          // These are the rule sets retrieved based on the Sponsor or Institution or Study 
	
	private String institutionName;
	
	private boolean isDataChanged;
	
	public CreateRuleCommand(RuleAuthoringService ruleAuthoringService, StudyDao studyDao, NotificationDao notificationDao, RulesEngineService rulesEngineService) 
	{
		setRuleAuthoringService(ruleAuthoringService);
		setStudyDao(studyDao);
		setNotificationDao(notificationDao);
		setRulesEngineService(rulesEngineService);
		ruleSet = new RuleSet();
		existingRuleSets = new ArrayList<RuleSet>();
	}

	/*
	 * This method saves the RuleSet
	 */
	public void save() 
	{
		try 
		{
			List<Rule> rules = ruleSet.getRule();
			
			ruleSet.setDescription(ruleSetName);
			
			//Set the Package name and categoryIdentifier for all rules before saving them.
			for(Rule rule : rules) 
			{
				rule.getMetaData().setPackageName(constructPackageName(getLevel()));
				rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");
				
				populateCategoryBasedColumns(rule);
			}
		
	    	if (CreateRuleCommand.SPONSOR_LEVEL.equalsIgnoreCase(this.getLevel()))
	    	{
	    		rulesEngineService.saveRulesForSponsor(ruleSet, sponsorName); 
	    	}
	    	else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equalsIgnoreCase(this.getLevel()))
	    	{
	    		rulesEngineService.saveRulesForInstitution(ruleSet, institutionName);
	    	}
	    	else
	    	{
	    		rulesEngineService.saveRulesForStudy(ruleSet, categoryIdentifier, sponsorName);
	    		
	    	}

		} 
		catch (Exception ex) 
		{
			logger.error("Exception while creating Rule:", ex);
		}
	}

	
	public RuleSet getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	public String getCategoryIdentifier() {
		return categoryIdentifier;
	}

	public void setCategoryIdentifier(String categoryIdentifier) {

		if (categoryIdentifier != null && !categoryIdentifier.equalsIgnoreCase(this.categoryIdentifier))
		{
			isDataChanged = true;
		}

		this.categoryIdentifier = categoryIdentifier;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		if (level != null && !level.equalsIgnoreCase(this.level))
		{
			isDataChanged = true;
		}
		
		this.level = level;
	}

	
	private void populateCategoryBasedColumns(Rule rule) 
	{
		if(STUDY_LEVEL.equals(getLevel())) 
		{
			rule.getCondition().getColumn().add(createCriteriaForSponsor(getSponsorName()));
			rule.getCondition().getColumn().add(createCriteriaForStudy(getCategoryIdentifier()));
		} 
		else if(SPONSOR_LEVEL.equals(getLevel())) 
		{
			rule.getCondition().getColumn().add(createCriteriaForSponsor(getSponsorName()));
		}
		else
		{
			
		}
	}

	/*
	 * This method returns the attribute to be used for creating the criteria 
	 */
	private String getFieldNameBasedOnLevel(String level) 
	{
		String fieldName = "shortTitle";
		
		if(SPONSOR_LEVEL.equals(level)) 
		{
			fieldName = "primarySponsorCode";
		} 
		else if (INSTITUTIONAL_LEVEL.equals(level)) 
		{
			fieldName = "";
		}
		else
		{
			fieldName = "shortTitle";
		}
		
		return fieldName;
	}

	public RuleAuthoringService getRuleAuthoringService() {
		return ruleAuthoringService;
	}

	public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
		this.ruleAuthoringService = ruleAuthoringService;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public String getSponsorName()
	{
		return sponsorName;
	}

	public void setSponsorName(String sponsorName)
	{
		if (sponsorName != null && !sponsorName.equalsIgnoreCase(this.sponsorName))
		{
			isDataChanged = true;
		}
			
		this.sponsorName = sponsorName;
	}

	public List<RuleSet> getExistingRuleSets()
	{
		return existingRuleSets;
	}

	public void setExistingRuleSets(List<RuleSet> existingRuleSets)
	{
		this.existingRuleSets = existingRuleSets;
	}

	public String getRuleSetName()
	{
		return ruleSetName;
	}

	public void setRuleSetName(String ruleSetName)
	{
		if (ruleSetName != null && !ruleSetName.equalsIgnoreCase(this.ruleSetName))
		{
			isDataChanged = true;
		}

		this.ruleSetName = ruleSetName;
	}

	public String getInstitutionName()
	{
		return institutionName;
	}

	public void setInstitutionName(String institutionName)
	{
		if (institutionName != null && !institutionName.equalsIgnoreCase(this.institutionName))
		{
			isDataChanged = true;
		}
		
		this.institutionName = institutionName;
	}
	
	
	/*
	 * This method cpnstructs the package name based on the Command object
	 */
	public String constructPackageName(String level)
	{
    	final String SPONSOR_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.sponsor";
    	final String INSTITUTION_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.institution";
    	final String STUDY_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.study";

    	String packageName = null;
    	
    	if (SPONSOR_LEVEL.equalsIgnoreCase(level))
    	{
    		packageName = SPONSOR_BASE_PACKAGE + "." + getStringWithoutSpaces(getSponsorName()) + "." + getStringWithoutSpaces(getRuleSetName()); 
    	}
    	else if (INSTITUTIONAL_LEVEL.equalsIgnoreCase(level))
    	{
    		packageName = INSTITUTION_BASE_PACKAGE + "." + getStringWithoutSpaces(getInstitutionName()) + "." + getStringWithoutSpaces(getRuleSetName());
    	}
    	else
    	{
    		packageName = STUDY_BASE_PACKAGE + "." + getStringWithoutSpaces(getSponsorName()) + "." 
    		              + getStringWithoutSpaces(getCategoryIdentifier()) + "." + getStringWithoutSpaces(getRuleSetName());
    	}
    	
    	return packageName;

	}
	
	private String getStringWithoutSpaces(String str)
	{
		String _str= str.toLowerCase().trim();
		return _str.replace(" ", "_");
	}

	public RulesEngineService getRulesEngineService() {
		return rulesEngineService;
	}

	public void setRulesEngineService(RulesEngineService rulesEngineService) {
		this.rulesEngineService = rulesEngineService;
	}

	public boolean isDataChanged()
	{
		return isDataChanged;
	}

	public void setDataChanged(boolean isDataChanged)
	{
		this.isDataChanged = isDataChanged;
	}
	
	/*
	 * This method creates criteria column with study short title as the criteria
	 */
	private Column createCriteriaForStudy(String criteriaValue)
	{
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.domain.Study");
		column.setIdentifier("studySDO");
		column.setExpression("getShortTitle()");
		
		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();
		
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName(getFieldNameBasedOnLevel(CreateRuleCommand.STUDY_LEVEL));
		fieldConstraints.add(fieldConstraint);
		ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.getValue().add(criteriaValue);
		literalRestrictions.add(literalRestriction);
		fieldConstraint.setLiteralRestriction(literalRestrictions);

		column.setFieldConstraint(fieldConstraints);
		
		return column;
		
	}
	
	/*
	 * THis method is used to create criteria for sponsor based on the sponsor name
	 */
	private Column createCriteriaForSponsor(String criteriaValue)
	{
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.domain.Study");
		column.setIdentifier("studySDO");
		column.setExpression("getPrimarySponsorCode()");
		
		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();
		
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName(getFieldNameBasedOnLevel(CreateRuleCommand.SPONSOR_LEVEL));
		fieldConstraints.add(fieldConstraint);
		ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.getValue().add(criteriaValue);
		literalRestrictions.add(literalRestriction);
		fieldConstraint.setLiteralRestriction(literalRestrictions);

		column.setFieldConstraint(fieldConstraints);
		
		return column;
		
	}

	
}
