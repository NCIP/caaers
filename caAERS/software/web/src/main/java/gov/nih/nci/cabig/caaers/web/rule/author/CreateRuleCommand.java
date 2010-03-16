package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.Condition;
import com.semanticbits.rules.brxml.FieldConstraint;
import com.semanticbits.rules.brxml.LiteralRestriction;
import com.semanticbits.rules.brxml.MetaData;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.ui.DomainObject;
import com.semanticbits.rules.ui.Field;
import com.semanticbits.rules.ui.RuleUi;
import com.semanticbits.rules.utils.RuleUtil;

/**
 * Command Object holding information for Rule authoring
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class CreateRuleCommand implements RuleInputCommand {

    private static final Log logger = LogFactory.getLog(CreateRuleCommand.class);

    public static final String SPONSOR_LEVEL = "Sponsor";

    public static final String INSTITUTIONAL_LEVEL = "Institution";

    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";

    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
    
    public static final String CREATE_MODE = "create";
    
    public static final String EDIT_MODE = "edit";

    public static final String FIELD_LEVEL_RULES = "fieldLevelRules";

    // public static final String STUDY_LEVEL="StudyLevel";

    private RuleAuthoringService ruleAuthoringService;

    private CaaersRulesEngineService caaersRulesEngineService;

    private NotificationDao notificationDao;

    private ReportDefinitionDao reportDefinitionDao;

    private OrganizationDao organizationDao;

    private CtcDao ctcDao;

    private StudyDao studyDao;

    private RuleSet ruleSet;

    private String categoryIdentifier; // Study Short Title

    private String level;

    private String sponsorName;
    
    private String sponsorNameInitialValue;

    private String ruleSetName; // Ruleset selected by the user

    private List<RuleSet> existingRuleSets; // These are the rule sets retrieved based on the
                                            // Sponsor or Institution or Study

    private String institutionName;
    
    private String institutionNameInitialValue;

    private String organizationName;

    private boolean isDataChanged;

    private RuleUi ruleUi;

    private String terminology;

    private List<ReportDefinition> reportDefinitions;
    
    private String mode;
    
    private RuleDeploymentService ruleDeploymentService;
    
    private RepositoryService repositoryService;
    
    private HashMap<String, Boolean> errorsForFields;

    public List<ReportDefinition> getReportDefinitions() {

        return reportDefinitions;
    }

    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {

        this.reportDefinitions = reportDefinitions;

    }

    public CreateRuleCommand(RuleAuthoringService ruleAuthoringService, StudyDao studyDao,
                    NotificationDao notificationDao, CaaersRulesEngineService caaersRulesEngineService,
                    ReportDefinitionDao reportDefinitionDao, OrganizationDao organizationDao,
                    CtcDao ctcDao, RuleDeploymentService ruleDeploymentService, RepositoryService repositoryService) {
        setRuleAuthoringService(ruleAuthoringService);
        setStudyDao(studyDao);
        setNotificationDao(notificationDao);
        setCaaersRulesEngineService(caaersRulesEngineService);
        ruleSet = new RuleSet();
        existingRuleSets = new ArrayList<RuleSet>();
        setReportDefinitionDao(reportDefinitionDao);
        setOrganizationDao(organizationDao);
        this.setCtcDao(ctcDao);
        setMode(EDIT_MODE);
        // reportDefinitions = reportDefinitionDao.getAll();
    }

    /*
     * This method saves the RuleSet
     */
    public void save() {
    	try{
    		caaersRulesEngineService.saveRuleSet(ruleSet, level, sponsorName, institutionName, categoryIdentifier, ruleSetName);
    	}catch (Exception ex) {
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

        if (categoryIdentifier != null
                        && !categoryIdentifier.equalsIgnoreCase(this.categoryIdentifier)) {
            isDataChanged = true;
        }

        this.categoryIdentifier = categoryIdentifier;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        if (level != null && !level.equalsIgnoreCase(this.level)) {
            isDataChanged = true;
        }

        this.level = level;
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
    
    public HashMap<String, Boolean> getErrorsForFields() {
        return errorsForFields;
    }

    public void setErrorsForFields(HashMap<String, Boolean> errorsForFields) {
        this.errorsForFields = errorsForFields;
    }

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        if (sponsorName != null && !sponsorName.equalsIgnoreCase(this.sponsorName)) {
            isDataChanged = true;
        }

        this.sponsorName = sponsorName;
    }
    
    public String getSponsorNameInitialValue(){
    	return sponsorNameInitialValue;
    }
    
    public void setSponsorNameInitialValue(String sponsorNameInitialValue){
    	this.sponsorNameInitialValue = sponsorNameInitialValue;
    }
    
    public String getInstitutionNameInitialValue(){
    	return institutionNameInitialValue;
    }
    
    public void setInstitutionNameInitialValue(String institutionNameInitialValue){
    	this.institutionNameInitialValue = institutionNameInitialValue;
    }

    public List<RuleSet> getExistingRuleSets() {
        return existingRuleSets;
    }

    public void setExistingRuleSets(List<RuleSet> existingRuleSets) {
        this.existingRuleSets = existingRuleSets;
    }

    public String getRuleSetName() {
        return ruleSetName;
    }

    public void setRuleSetName(String ruleSetName) {
        if (ruleSetName != null && !ruleSetName.equalsIgnoreCase(this.ruleSetName)) {
            isDataChanged = true;
        }

        this.ruleSetName = ruleSetName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        if (institutionName != null && !institutionName.equalsIgnoreCase(this.institutionName)) {
            isDataChanged = true;
        }

        this.institutionName = institutionName;
    }

    /*
     * This method cpnstructs the package name based on the Command object
     */
    public String constructPackageName(String level) {
        final String SPONSOR_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.sponsor";
        final String INSTITUTION_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.institution";

        String packageName = null;

        if (SPONSOR_LEVEL.equalsIgnoreCase(level)) {
            packageName = SPONSOR_BASE_PACKAGE + "."
                            + RuleUtil.getStringWithoutSpaces(getSponsorName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        } else if (INSTITUTIONAL_LEVEL.equalsIgnoreCase(level)) {
            packageName = INSTITUTION_BASE_PACKAGE + "."
                            + RuleUtil.getStringWithoutSpaces(getInstitutionName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        } else if (SPONSOR_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
            packageName = SPONSOR_BASE_PACKAGE + ".study."
                            + RuleUtil.getStringWithoutSpaces(getSponsorName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getCategoryIdentifier()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
            packageName = INSTITUTION_BASE_PACKAGE + ".study."
                            + RuleUtil.getStringWithoutSpaces(getInstitutionName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getCategoryIdentifier()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        }

        // System.out.println("Package name is : " + packageName);
        return packageName;

    }

    public CaaersRulesEngineService getCaaersRulesEngineService() {
        return caaersRulesEngineService;
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
    }

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void setDataChanged(boolean isDataChanged) {
        this.isDataChanged = isDataChanged;
    }


    public void setRuleUi(String terminology) {

        // System.out.println("termonilogy is " + terminology);
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("rules-ui.xml");

        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance("com.semanticbits.rules.ui")
                            .createUnmarshaller();
            ruleUi = (RuleUi) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            logger.error("Error in reading rules-ui xml file ", e);
            throw new CaaersSystemException(e);
        }

        // ServletContext servletContext = WebContextFactory.get().getServletContext();

        // ruleUi = (RuleUi) servletContext.getAttribute("ruleUi");

        // System.out.println("ui is " + ruleUi.getCondition().size());

        for (DomainObject domainObject : ruleUi.getCondition().get(0).getDomainObject()) {
            List<Field> fields = new ArrayList<Field>();
            List<Field> fields2 = new ArrayList<Field>();
            for (Field field : domainObject.getField()) {
                if (field.getFilter().equals("") || field.getFilter().equals(terminology)) {
                    fields.add(field);
                } else {
                    fields2.add(field);
                }
            }
            fields.addAll(fields2);

            domainObject.setField(fields);
        }

    }

    //pre processes the ruleset to remove unwanted stuff.
    private void cleanRuleset(RuleSet ruleSet){

        if(ruleSet == null || ruleSet.getRule().size() <= 0) return;

        for(Rule rule : ruleSet.getRule()){
              rule.setId(null);
              if(rule.getMetaData() != null) rule.getMetaData().setCategory(null);
        }
    }

    
    public void retrieveRuleSet(){
    	try {


            CaaersRulesEngineService rulesEngineService = this.getCaaersRulesEngineService();

            if(StringUtils.equals(RuleType.FIELD_LEVEL_RULES.getName() , ruleSetName)){
                
                ruleSet = rulesEngineService.getFieldRuleSet(RuleType.FIELD_LEVEL_RULES.getName(), false);
                reportDefinitions = fetchReportDefinitions(null);

            } else {

                if (CreateRuleCommand.SPONSOR_LEVEL.equals(this.getLevel())) {

                    ruleSet = rulesEngineService.getRuleSetForSponsor(this.getRuleSetName(), this.getSponsorName(), false);
                    this.setOrganizationName(this.getSponsorName());

                } else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equals(this.getLevel())) {
                    this.setOrganizationName(this.getSponsorName());
                    String packageName = this.constructPackageName(this.getLevel());
                    ruleSet = rulesEngineService.getRuleSetForSponsorDefinedStudy(this.getRuleSetName(), this.getCategoryIdentifier(), this.getSponsorName(), false);

                    // Check whether ruleset exists? Otherwise retrieve sponsor ruleset
                    if (ruleSet == null) {
                        RuleSet rs = rulesEngineService.getRuleSetForSponsor(this.getRuleSetName(), this.getSponsorName(), false);
                        ruleSet = new RuleSet();
                        ruleSet.setDescription(this.getRuleSetName());
                        ruleSet.setRule(rs == null ? null : rs.getRule());
                        setMode(CREATE_MODE);
                        ruleSet.setName(packageName);
                        cleanRuleset(ruleSet);
                    }

                } else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equals(this.getLevel())) {
                    this.setOrganizationName(this.getInstitutionName());
                    ruleSet = rulesEngineService.getRuleSetForInstitution(this.getRuleSetName(), this.getInstitutionName(), false);
                } else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equals(this.getLevel())) {
                    String packageName = this.constructPackageName(this.getLevel());
                    this.setOrganizationName(this.getInstitutionName());
                    ruleSet = rulesEngineService.getRuleSetForInstitutionDefinedStudy(this.getRuleSetName(), this.getCategoryIdentifier(),this.getInstitutionName(), false);

                    // Check whether ruleset exists? Otherwise retrieve inst ruleset
                    if (ruleSet == null) {
                        RuleSet rs = rulesEngineService.getRuleSetForInstitution(this.getRuleSetName(), this.getInstitutionName(),false);
                        ruleSet = new RuleSet();
                        ruleSet.setDescription(this.getRuleSetName());
                        ruleSet.setRule(rs == null ? null : rs.getRule());
                        setMode(CREATE_MODE);
                        ruleSet.setName(packageName);
                        cleanRuleset(ruleSet);
                    }
                }

                //populate the report defnitions
                reportDefinitions = new ArrayList<ReportDefinition>();

                //find the organizations
                Organization org = organizationDao.getByName(organizationName);
                if(org != null) reportDefinitions.addAll(fetchReportDefinitions(org));

                //BJ :[comment before refactoring]  Get REport definitions of CTEP for DCP studies ,
                // because DCP uses CTEP report definitions also . TEMP fix.
                if(StringUtils.equals(organizationName , "Division of Cancer Prevention")){
                    org = organizationDao.getByName("Cancer Therapy Evaluation Program");
                }
                if(org != null) reportDefinitions.addAll(fetchReportDefinitions(org));

            }


        } catch (Exception e) {
            logger.error("Exception while retrieving the Rule Set", e);
            
        }finally{
           if (ruleSet == null) {
                ruleSet = new RuleSet();
                ruleSet.setDescription(this.getRuleSetName());
                setMode(CREATE_MODE);
            }
        }
    }

    /**
     * Will return Report definitions associated to a given organization
     * or all report definitions incase where organization is empty.
     *
     * @param org - An organization, whose report defnitions to be fetched.
     * @return  ReportDefinitions of the given organization or all report definitions in the system
     */
    //BJ: protected for the sake of unit testcases
    protected List<ReportDefinition> fetchReportDefinitions(Organization org) {

        ReportDefinitionQuery rdQuery = new ReportDefinitionQuery(true);
        if(org != null) rdQuery.filterByOrganizationId(org.getId());

        Object result = reportDefinitionDao.search(rdQuery);
        if(result == null) return new ArrayList<ReportDefinition>();
        return (List<ReportDefinition>) result;
    }

    /**
     * Populates the readable rule and action in the ruleset. 
     */
    public void makeRuleSetReadable(){
        if(ruleSet != null) caaersRulesEngineService.makeRuleSetReadable(ruleSet);
    }
    
    public void unDeployRuleSet() throws RemoteException {
        caaersRulesEngineService.unDeployRuleSet(ruleSet.getName());
    }
    
    public void deployRuleSet() throws RemoteException {
        caaersRulesEngineService.deployRuleSet(ruleSet.getName());
    }
    
    public void saveAndDeploy(){
    	save();
    	try{
    		deployRuleSet();
    	}catch (RemoteException e){
			logger.warn("Error while deployting the ruleSet ", e);
		}
    }

    
    public List<CtcCategory> getCategories() {
    	
    	List<CtcCategory> categories;
    	
    	if (!getCategoryIdentifier().equals("") ) {
    		Study s = getStudyDao().getByShortTitle(getCategoryIdentifier());
        	Ctc ctc = s.getAeTerminology().getCtcVersion();
        	categories = ctc.getCategories();
    	} else {
    		//return an emply list
    		categories = new ArrayList<CtcCategory>();
    	}
    	
         // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }
    
    private Condition newCondition() {
        Condition condition = new Condition();
        Column column = newColumn();
        condition.getColumn().add(column);
        return condition;
    }
    
    private Column newColumn() {
        Column column = new Column();
        FieldConstraint fieldConstraint = newFieldConstraint();
        column.getFieldConstraint().add(fieldConstraint);
        return column;
    }

    private FieldConstraint newFieldConstraint() {
        FieldConstraint fieldConstraint = new FieldConstraint();
        LiteralRestriction literalRestriction = new LiteralRestriction();
        fieldConstraint.getLiteralRestriction().add(literalRestriction);
        return fieldConstraint;
    }
    
    /**
     * This method adds a default rule if the author is creating a fresh ruleSet.
     */
    public void addDefaultRule(){
    	RuleSet ruleSet = (RuleSet) this.getRuleSet();
        Rule newRule = new Rule();
        MetaData metaData = new MetaData();
        newRule.setMetaData(metaData);

        Condition condition = newCondition();
        newRule.setCondition(condition);

        // Action action = new Action();
        List<String> action = new ArrayList<String>();
        newRule.setAction(action);

        ruleSet.getRule().add(newRule);

        Organization org = null;
        if(ruleSetName.equals(RuleType.FIELD_LEVEL_RULES.getName())){
            reportDefinitions = fetchReportDefinitions(null);
        }else if(StringUtils.isNotEmpty(organizationName)){

            org = organizationDao.getByName(organizationName);
            reportDefinitions = fetchReportDefinitions(org);
            /**
             * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP
             * report definitions also . TEMP fix
             */
            if(StringUtils.equals(organizationName, "Division of Cancer Prevention")){
               org = organizationDao.getByName("Cancer Therapy Evaluation Program");
               reportDefinitions.addAll(fetchReportDefinitions(org));
            }
        }

        // Set the name as the name field has been removed from UI.
        Integer ruleCount = ruleSet.getRule().size() - 1;
        Integer ruleNumber = ++ruleCount;
        newRule.getMetaData().setName("Rule-" + ruleNumber);
        // Done setting the rule name.

    }
    
    public ExpeditedReportSection[] getReportSectionNames() {
        ExpeditedReportSection[] expeditedReportSections = ExpeditedReportSection.values();
        /*
         * String[] sectionNames = new String[expeditedReportSections.length]; for (int i=0;i<expeditedReportSections.length;i++ ){
         * sectionNames[i] = expeditedReportSections[i].name(); }
         */
        return expeditedReportSections;
    }

    public RuleUi getRuleUi() {
        return ruleUi;
    }

    public ReportDefinitionDao getReportDefinitionDao() {
        return reportDefinitionDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public String getLevelDescription() {
        return RuleLevel.valueOf(level).getDescription();

    }

    public String getTerminology() {
        return terminology;
    }

    public void setTerminology(String terminology) {
        this.terminology = terminology;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
    
    public void setMode(String mode){
    	this.mode = mode;
    }
    
    public String getMode(){
    	return this.mode;
    }
    
    public RuleDeploymentService getRuleDeploymentService() {
        return ruleDeploymentService;
    }

    public void setRuleDeploymentService(RuleDeploymentService ruleDeploymentService) {
        this.ruleDeploymentService = ruleDeploymentService;
    }
    
    public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

    /**
     * Lists all the mandatory values
     * @return
     */
    public Mandatory[] getMandatoryOptions(){
        return Mandatory.values();
    }

}
