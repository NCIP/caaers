package gov.nih.nci.cabig.caaers.web.rule.author;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
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
	
	private StudyDao studyDao;
	
	private RuleSet ruleSet;
	
	private String categoryIdentifier;
	
	private String level;
	
	public CreateRuleCommand(RuleAuthoringService ruleAuthoringService, StudyDao studyDao) {
		setRuleAuthoringService(ruleAuthoringService);
		setStudyDao(studyDao);
		
		createCategories();
		ruleSet = new RuleSet();
	}

	public void save() {
		try {
			
			//Create Package if it does not exist
			createPackage();

			List<Rule> rules = ruleSet.getRule();
			//Set the Package name and categoryIdentifier for all rules before saving them.
			for(Rule rule : rules) {
				rule.getMetaData().getCategory().addAll(getAllCategories());
				rule.getMetaData().setPackageName(getPackageName());
				//rule.getCondition().getColumn().get(0).setIdentifier("adverseEventSDO");
				populateCategoryBasedColumns(rule);
				rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");
				if(rule.getId() == null)
					ruleAuthoringService.createRule(rule);
				else
					ruleAuthoringService.updateRule(rule);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void createPackage() throws RemoteException {
		try {
			ruleSet.setName(getPackageName());
			ruleAuthoringService.createRuleSet(ruleSet);
		} catch(Exception exception) {
			//Ignore this error .. Package already exists 
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

	private String getPackageName() {
		String packageName = "gov.nih.nci.cabig.caaers.rule.study";
		if(INSTITUTIONAL_LEVEL.equals(getLevel())) {
			packageName = "gov.nih.nci.cabig.caaers.rule.institution";
		} else if(SPONSOR_LEVEL.equals(getLevel())) {
			packageName = "gov.nih.nci.cabig.caaers.rule.sponsor";
		}
		return packageName;
	}
	
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
	private Category createCategory(String path, String name, String description) {
		Category category = null;
		try {
			category = ruleAuthoringService.getCategory(path + "/" + name);
		} catch(RemoteException e) {
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
		try {
			ruleAuthoringService.createCategory(category);
		} catch(RemoteException remoteException) {
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
	
	private void populateCategoryBasedColumns(Rule rule) {
		if(STUDY_LEVEL.equals(getLevel())) {
			rule.getCondition().getColumn().add(getCategoryBasedColumn(getCategoryIdentifier()));
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

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

}