package gov.nih.nci.cabig.caaers.web.rule.author;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
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
	
	private NotificationDao notificationDao; 
	
	private StudyDao studyDao;
	
	private RuleSet ruleSet;
	
	private String categoryIdentifier;               // Study Short Title
	
	private String level;
	
	private String sponsorName;      
	
	private String ruleSetName;                      // Ruleset selected by the user
	
	private List<RuleSet> existingRuleSets;          // These are the rule sets retrieved based on the Sponsor or Institution or Study 
	
	private String institutionName;
	
	public CreateRuleCommand(RuleAuthoringService ruleAuthoringService, StudyDao studyDao, NotificationDao notificationDao) 
	{
		setRuleAuthoringService(ruleAuthoringService);
		setStudyDao(studyDao);
		setNotificationDao(notificationDao);
		createCategories();
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
			//Create Package if it does not exist
			createPackage();

			List<Rule> rules = ruleSet.getRule();
			
			//Set the Package name and categoryIdentifier for all rules before saving them.
			for(Rule rule : rules) 
			{
				//rule.getMetaData().getCategory().addAll(getAllCategories());
				//rule.getMetaData().setPackageName(getPackageName());
				
				// Create category
				//srule.getMetaData().getCategory().add(ruleSet.getMetaData().getCategory().get(0));
				rule.getMetaData().setPackageName(constructPackageName(getLevel()));
				rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");
				
				//rule.getCondition().getColumn().get(0).setIdentifier("adverseEventSDO");
				populateCategoryBasedColumns(rule);
				createAdverseEventEvaluatorCondition(rule);
				
				if(rule.getId() == null)
				{
					ruleAuthoringService.createRule(rule);
				}
				else
				{
					ruleAuthoringService.updateRule(rule);
				}
			}
		} 
		catch (RemoteException e) 
		{
			logger.error("Exception while creating Rule:", e);
			//e.printStackTrace();
		}
	}
	
	private void createPackage() throws RemoteException 
	{
		try 
		{
			//ruleSet.setName(getPackageName());
			//ruleSet.setDescription("The package for deploying study level rules");
			
			String packageName = constructPackageName(getLevel()); 
			ruleSet.setName(packageName);
			
			if (ruleAuthoringService.containsRuleSet(packageName))
			{
				return;
			}

			ruleSet.setDescription(ruleSetName); // This contains the actual ruleset name

			// REVISIT! This is not need as the domain objects are referenced with the full package path
			
			if (ruleSet.getImport().size() == 0)
			{	
				ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
			}


			// Category is need to tag the package so that it can be useful in searching the Package
			Category category = new Category();
			MetaData categoryMetaData = new MetaData();
			
			String categoryPath = this.getCategoryPath(this);
			
			category.setPath(categoryPath);
			categoryMetaData.setName(categoryPath);
			categoryMetaData.setDescription(categoryPath);
			category.setMetaData(categoryMetaData);		

			MetaData ruleSetMetaData = new MetaData();
			ruleSetMetaData.setName(ruleSetName);
			ruleSetMetaData.setDescription(ruleSetName);
			ruleSetMetaData.getCategory().add(category);
			
			ruleSet.setMetaData(ruleSetMetaData);
			
			ruleAuthoringService.createRuleSet(ruleSet);
		} 
		catch(Exception exception) 
		{
			//Ignore this error .. Package already exists 
			exception.printStackTrace();
		}		
	}

	/**
	 * Loads the categoryIdentifier. If not found creates one. 
	 * //TODO : This needs to be called only once.
	 * */
	private void createCategories() {
		
		try {

			createCategory("", SPONSOR_LEVEL, "Sponsor Level Rules are always linked with this categoryIdentifier");

			createCategory("", INSTITUTIONAL_LEVEL, "Institution Level Rules are always linked with this categoryIdentifier");

			createCategory("", STUDY_LEVEL, "Study Level Rules are always linked with this categoryIdentifier");

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
	
	public String getCategoryIdentifier() {
		return categoryIdentifier;
	}

	public void setCategoryIdentifier(String categoryIdentifier) {
		this.categoryIdentifier = categoryIdentifier;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	// REVISIT: This uses a static package structure. Going fwd we should use dynamic package constrution. That is done in the other method.
/*	private String getPackageName() 
	{
		// REVISIT! Changed all the package names
		String packageName = "gov.nih.nci.cabig.caaers.rule.study.testlevel";
		
		if(INSTITUTIONAL_LEVEL.equals(getLevel())) 
		{
			packageName = "gov.nih.nci.cabig.caaers.rule.institution.testlevel";
		} 
		else if(SPONSOR_LEVEL.equals(getLevel())) 
		{
			packageName = "gov.nih.nci.cabig.caaers.rule.sponsor.testlevel";
		}
		
		return packageName;
	}
*/	
	/**
	 * Here we decide, to which all categories the Rule needs to be linked.
	 * If its a study level rule. 
	 * 1. Its linked to global STUDY category.
	 * 2. Linked to the category named with Study Short Title
	 * 3. Linked to the category named with Study SponsorCode   
	 * */
	private List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		if(STUDY_LEVEL.equals(getLevel())) {
			String shortTitle = getCategoryIdentifier(); //short title of study
			categories.add(getCategory(STUDY_LEVEL));
			Study template = new Study();
			template.setShortTitle(shortTitle);
			Study study = CollectionUtils.firstElement(studyDao.searchByExample(template, false));
			//Create Sponsor Category
			categories.add(createCategory("",study.getPrimarySponsorCode(), "Primary Sponsor Code for Study " + study.getShortTitle()));
			//Create Institution Categories
			List<StudySite> studySites = study.getStudySites();
			for(StudySite studySite : studySites) {
				categories.add(createCategory(study.getPrimarySponsorCode(), studySite.getSite().getName(), "This is a study Site with primary Sponsor being " + study.getPrimarySponsorCode()));
				//Create Study Categories
				categories.add(createCategory(study.getPrimarySponsorCode() + "/" + studySite.getSite().getName(), study.getShortTitle(), ""));
			}
		} else if(INSTITUTIONAL_LEVEL.equals(getLevel())) {
			String institution = getCategoryIdentifier();
			categories.add(getCategory(INSTITUTIONAL_LEVEL));
			Category category = createCategory(INSTITUTIONAL_LEVEL, institution, "Institution Level Rules are registered under this categoryIdentifier");
			categories.add(category);
		} else if(SPONSOR_LEVEL.equals(getLevel())) {
			String sponsorCode = getCategoryIdentifier();
			categories.add(getCategory(SPONSOR_LEVEL));
			Study template = new Study();
			template.setPrimarySponsorCode(sponsorCode);
			List<Study> studies = studyDao.searchByExample(template, false);
			for(Study study : studies) {
				categories.add(createCategory("", sponsorCode, "Institution Level Rules are registered under this categoryIdentifier"));
			}
		}
		return categories;
	}

	/**
	 * Create a categoryIdentifier if it does not exist in the DB
	 * */
	private Category createCategory(String path, String name, String description) 
	{
		Category category = null;
	
		try 
		{
			category = ruleAuthoringService.getCategory(path + "/" + name);
		} 
		catch(RemoteException e) 
		{
			//Forget this exception now
			e.printStackTrace();
		}
		
		if(category != null) return category;

		category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(path);
		metaData.setName(name);
		metaData.setDescription(description);
		category.setMetaData(metaData);		
		
		try 
		{
			ruleAuthoringService.createCategory(category);
		} 
		catch(RemoteException remoteException) 
		{
			throw new CaaersSystemException(remoteException.getMessage(), remoteException);
		}
		
		return category;
	}
	
	public Category getCategory(String categoryPath) {
		try {
			return ruleAuthoringService.getCategory(categoryPath);
		}catch(RemoteException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
	}
	
	private void populateCategoryBasedColumns(Rule rule) 
	{
		if(STUDY_LEVEL.equals(getLevel())) 
		{
			rule.getCondition().getColumn().add(createCriteria(STUDY_LEVEL, getCategoryIdentifier()));
		} 
		else if(SPONSOR_LEVEL.equals(getLevel())) 
		{
			rule.getCondition().getColumn().add(createCriteria(SPONSOR_LEVEL, sponsorName));
		}
		else
		{
			rule.getCondition().getColumn().add(createCriteria(INSTITUTIONAL_LEVEL, institutionName));
		}
	}

	private Column createCriteria(String level, String criteriaValue) 
	{
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.StudySDO");
		column.setIdentifier("studySDO");
	
		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();
		
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName(getFieldNameBasedOnLevel(level));
		fieldConstraints.add(fieldConstraint);
		ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue(criteriaValue);
		literalRestrictions.add(literalRestriction);
		fieldConstraint.setLiteralRestriction(literalRestrictions);
		
		if (STUDY_LEVEL.equals(level))
		{
			FieldConstraint sponsorFieldConstraint = new FieldConstraint();
			fieldConstraint.setFieldName(getFieldNameBasedOnLevel(SPONSOR_LEVEL));
			fieldConstraints.add(sponsorFieldConstraint);
			ArrayList<LiteralRestriction> sponsorLiteralRestrictions = new ArrayList<LiteralRestriction>();
			LiteralRestriction sponsorLiteralRestriction = new LiteralRestriction();
			sponsorLiteralRestriction.setEvaluator("==");
			sponsorLiteralRestriction.setValue(criteriaValue);
			sponsorLiteralRestrictions.add(sponsorLiteralRestriction);
			fieldConstraint.setLiteralRestriction(sponsorLiteralRestrictions);
		}

		column.setFieldConstraint(fieldConstraints);
		
		return column;
	}

	
	private String getFieldNameBasedOnLevel(String level) 
	{
		String fieldName = "shortTitle";
		
		if(SPONSOR_LEVEL.equals(level)) 
		{
			fieldName = "primarySponsorCode";
		} 
		else if (INSTITUTIONAL_LEVEL.equals(level)) 
		{
			fieldName = "site";
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
		this.ruleSetName = ruleSetName;
	}

	public String getInstitutionName()
	{
		return institutionName;
	}

	public void setInstitutionName(String institutionName)
	{
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


    /*
     * This method construts the category path based on the user selections. It needs Sponsor name, Institution Name or Study Name along with RuleSet Name
     */
    public String getCategoryPath(CreateRuleCommand command) 
    {
    	final String SPONSOR_BASE_PATH = "/Sponsor";
    	final String INSTITUTION_BASE_PATH = "/Institution";
    	final String STUDY_BASE_PATH = "/Study";
    	
    	String categoryPath = null;
    	
    	if (CreateRuleCommand.SPONSOR_LEVEL.equalsIgnoreCase(command.getLevel()))
    	{
    		categoryPath = SPONSOR_BASE_PATH + "/" + command.getSponsorName() + "/" + command.getRuleSetName(); 
    	}
    	else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equalsIgnoreCase(command.getLevel()))
    	{
    		categoryPath = INSTITUTION_BASE_PATH + "/" + command.getInstitutionName() + "/" + command.getRuleSetName();
    	}
    	else
    	{
    		categoryPath = STUDY_BASE_PATH + "/" + command.getSponsorName() + "/" + command.getCategoryIdentifier() + "/" + command.getRuleSetName();
    	}
    	
    	return categoryPath;
    }
    
    private void createAdverseEventEvaluatorCondition(Rule rule)
    {
    	Condition condition = rule.getCondition();
    	
		Column column_fixed = new Column();
		column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		column_fixed.setIdentifier("adverseEventEvaluationResult");
	
		condition.getColumn().add(column_fixed);
    }
}