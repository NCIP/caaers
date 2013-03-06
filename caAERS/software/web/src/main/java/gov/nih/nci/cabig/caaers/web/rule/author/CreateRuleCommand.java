/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.brxml.*;
import com.semanticbits.rules.ui.DomainObject;
import com.semanticbits.rules.ui.Field;
import com.semanticbits.rules.ui.RuleUi;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
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
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Command Object holding information for Rule authoring
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class CreateRuleCommand implements RuleInputCommand {

    private static final Log logger = LogFactory.getLog(CreateRuleCommand.class);

    public static final String SPONSOR_LEVEL = RuleLevel.Sponsor.getName();

    public static final String INSTITUTIONAL_LEVEL = RuleLevel.Institution.getName();

    public static final String SPONSOR_DEFINED_STUDY_LEVEL = RuleLevel.SponsorDefinedStudy.getName();

    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = RuleLevel.InstitutionDefinedStudy.getName();
    
    public static final String CREATE_MODE = "create";
    
    public static final String EDIT_MODE = "edit";

    public static final String FIELD_LEVEL_RULES = "fieldLevelRules";

    // public static final String STUDY_LEVEL="StudyLevel";

    private CaaersRulesEngineService caaersRulesEngineService;

    private ReportDefinitionDao reportDefinitionDao;

    private OrganizationDao organizationDao;


    private RuleSet ruleSet;


    private String level;


    private String ruleSetName; // Ruleset selected by the user


    private boolean isDataChanged;

    private RuleUi ruleUi;

    private String terminology;

    private List<ReportDefinition> reportDefinitions;
    
    private String mode;
    
    
    private HashMap<String, Boolean> errorsForFields;

    
    private Study study;
    private Organization sponsor;
    private Organization institution;

    private List<CtcCategory> categories;

    private boolean ruleManager;


    public List<ReportDefinition> getReportDefinitions() {

        return reportDefinitions;
    }

    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {

        this.reportDefinitions = reportDefinitions;

    }

    public CreateRuleCommand(CaaersRulesEngineService caaersRulesEngineService,
               ReportDefinitionDao reportDefinitionDao,OrganizationDao organizationDao) {
        
        setCaaersRulesEngineService(caaersRulesEngineService);
        ruleSet = new RuleSet();
        setReportDefinitionDao(reportDefinitionDao);
        setOrganizationDao(organizationDao);
        setMode(EDIT_MODE);
    }

    /*
     * This method saves the RuleSet
     */
    public void save() {
    	try{
    		caaersRulesEngineService.saveRuleSet(ruleSet, level, ruleSetName,sponsor, institution, study);
    	}catch (Exception ex) {
            logger.error("Exception while creating Rule:", ex);
            throw new CaaersSystemException("Error while saving rule", ex);
        }
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
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


    public HashMap<String, Boolean> getErrorsForFields() {
        return errorsForFields;
    }

    public void setErrorsForFields(HashMap<String, Boolean> errorsForFields) {
        this.errorsForFields = errorsForFields;
    }

    public String getSponsorNameInitialValue(){
    	return sponsor.getFullName();
    }
    
    public void setSponsorNameInitialValue(String sponsorNameInitialValue){
    }

    public String getInstitutionNameInitialValue(){
    	return institution.getFullName();
    }
    
    public void setInstitutionNameInitialValue(String institutionNameInitialValue){
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


    /*
     * This method cpnstructs the package name based on the Command object
     * @param  level - The rule level
     */
    public String constructPackageName(String level) {


        return caaersRulesEngineService.constructPackageName(level,
                sponsor != null ? sponsor.getId().toString() : null,
                institution != null ? institution.getId().toString() : null,
                study != null ? study.getId().toString() : null,
                getRuleSetName());
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

    /**
     * Will delete the rules that are marked deleted.
     */
    public void removeDeletedRules() throws Exception{
       //find the deleted rules.
        Set<String> deletedRules = new HashSet<String>();
        if(getRuleSet() == null || getRuleSet().getRule() == null) return;
        List<Rule> rules = new ArrayList<Rule>(getRuleSet().getRule());
        for(Rule rule: rules){
            if(rule.isMarkedDelete()){
                deletedRules.add(rule.getMetaData().getName());
                getRuleSet().getRule().remove(rule);
            }
        }

        for(String ruleName : deletedRules){
            caaersRulesEngineService.deleteRule(getRuleSet().getName(), ruleName);
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

    /**
     * Will populate the ruleset from repository if it exists
     * otherwise will create an empty rule set. 
     *
     */
    public void retrieveRuleSet(){

        String packageName = constructPackageName(getLevel());
        
    	try {

            reportDefinitions = new ArrayList<ReportDefinition>();


            ruleSet = getCaaersRulesEngineService().getRuleSetByPackageName(packageName);
            if (ruleSet == null) {
                ruleSet = new RuleSet();
                ruleSet.setDescription(this.getRuleSetName());
                ruleSet.setName(packageName);
                setMode(CREATE_MODE);

                //if study specific rule, then copy the rules from sponsor or institution.
                RuleSet rs = null;
                if(StringUtils.equals(SPONSOR_DEFINED_STUDY_LEVEL, getLevel())){
                    String sponsorPackageName = constructPackageName(SPONSOR_LEVEL);
                    rs= getCaaersRulesEngineService().getRuleSetByPackageName(sponsorPackageName);

                }else if (StringUtils.equals(INSTITUTION_DEFINED_STUDY_LEVEL, getLevel())){
                    String institutionPackageName = constructPackageName(INSTITUTIONAL_LEVEL);
                    rs = getCaaersRulesEngineService().getRuleSetByPackageName(institutionPackageName);
                }

                ruleSet.setRule(rs != null ? rs.getRule() : null);
                cleanRuleset(ruleSet);
                
            }

            //add all report defintions assocated with the organization
            Organization org = getOrganization();
            reportDefinitions.addAll(fetchReportDefinitions(org));
           
            //CAAERS-4215:
            //if(org!= null && StringUtils.equals(org.getName() , "Division of Cancer Prevention"))  {

                //BJ :[comment before refactoring]  Get REport definitions of CTEP for DCP studies ,
                // because DCP uses CTEP report definitions also . TEMP fix.
              //  Organization orgCTEP = organizationDao.getByName("Cancer Therapy Evaluation Program");
               // if(orgCTEP != null) reportDefinitions.addAll(fetchReportDefinitions(orgCTEP));
            //}

        } catch (Exception e) {
            logger.error("Exception while retrieving the Rule Set", e);
            ruleSet = new RuleSet();
            ruleSet.setDescription(this.getRuleSetName());
            ruleSet.setName(packageName);
            setMode(CREATE_MODE);
        }
    }

    /**
     * Will identify the organization to be used. 
     * @return
     */
    public Organization getOrganization(){
        if(isSponsorBased()) return sponsor;
        if(isInstitutionBased()) return institution;
        return null;
    }

    /**
     * Returns true if the rule is based on sponsor otherwise false
     * @return
     */
    public boolean isSponsorBased(){
        return  StringUtils.equals(getLevel(), SPONSOR_LEVEL) ||
                StringUtils.equals(getLevel(), SPONSOR_DEFINED_STUDY_LEVEL);
    }

     /**
     * Returns true if the rule is based on institution otherwise false
     * @return
     */
    public boolean isInstitutionBased(){
        return  StringUtils.equals(getLevel(), INSTITUTIONAL_LEVEL) || 
                StringUtils.equals(getLevel(), INSTITUTION_DEFINED_STUDY_LEVEL);
    }
     /**
     * Returns true if the rule is based on study otherwise false
     * @return
     */
    public boolean isStudyBased(){
       return StringUtils.equals(getLevel(), SPONSOR_DEFINED_STUDY_LEVEL) ||
              StringUtils.equals(getLevel(), INSTITUTION_DEFINED_STUDY_LEVEL); 
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

    
    public void deployRuleSet() throws RemoteException {
        caaersRulesEngineService.deployRuleSet(ruleSet.getName());
    }
    
    public void saveAndDeploy(){
    	save();
    	try{
    		deployRuleSet();
    	}catch (RemoteException e){
			logger.warn("Error while deploying the ruleSet ", e);
		}
    }

    /**
     * Will retrieve all the ctc Categories for the study
     * @return
     */
    public List<CtcCategory> getCategories() {

        if(categories == null || categories.isEmpty()){
           categories = new ArrayList<CtcCategory>();
           if(study != null){
             Ctc ctc = study.getAeTerminology().getCtcVersion();
             for(CtcCategory c : ctc.getCategories()){
                 CtcCategory ctcCategory = new CtcCategory();
                 ctcCategory.setName(c.getName());
                 ctcCategory.setId(c.getId());
                 categories.add(ctcCategory);
             }
           }
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

        Organization org = getOrganization();
        reportDefinitions = fetchReportDefinitions(org);
        /**
         * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP
         * report definitions also . TEMP fix
         * 
         */
        // above statement not valid any more CAAERS-4215:
       // if(org != null && StringUtils.equals(org.getName(), "Division of Cancer Prevention")){
         //  org = organizationDao.getByName("Cancer Therapy Evaluation Program");
          // reportDefinitions.addAll(fetchReportDefinitions(org));
        //}


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

    public void setMode(String mode){
    	this.mode = mode;
    }
    
    public String getMode(){
    	return this.mode;
    }
    
    /**
     * Lists all the mandatory values
     * @return
     */
    public Mandatory[] getMandatoryOptions(){
        return Mandatory.values();
    }


    public Organization getInstitution() {
        return institution;
    }

    public void setInstitution(Organization institution) {
        isDataChanged = (this.institution != institution);
        this.institution = institution;
    }

    public Organization getSponsor() {
        return sponsor;
    }

    public void setSponsor(Organization sponsor) {
        isDataChanged = (this.sponsor != sponsor);
        this.sponsor = sponsor;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        isDataChanged = (this.study != study);
        this.study = study;
        setTerminology("");
        if(this.study != null){
            setTerminology(this.study.getAeTerminology().getTerm().getDisplayName());
        }
    }

    public boolean isRuleManager() {
        return ruleManager;
    }

    public void setRuleManager(boolean ruleManager) {
        this.ruleManager = ruleManager;
    }
}
