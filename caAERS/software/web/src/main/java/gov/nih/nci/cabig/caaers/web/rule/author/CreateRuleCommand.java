package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.brxml.*;
import com.semanticbits.rules.brxml.Condition;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.impl.RulesEngineServiceImpl;
import com.semanticbits.rules.ui.DomainObject;
import com.semanticbits.rules.ui.Field;
import com.semanticbits.rules.ui.RuleUi;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionQuery;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.CaaersRuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
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

//    public static final String SPONSOR_LEVEL = RuleLevel.Sponsor.getName();
//
//    public static final String INSTITUTIONAL_LEVEL = RuleLevel.Institution.getName();
//
//    public static final String SPONSOR_DEFINED_STUDY_LEVEL = RuleLevel.SponsorDefinedStudy.getName();
//
//    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = RuleLevel.InstitutionDefinedStudy.getName();
//
//    public static final String CREATE_MODE = "create";
//
//    public static final String EDIT_MODE = "edit";

//    public static final String FIELD_LEVEL_RULES = "fieldLevelRules";

    private CaaersRulesEngineService caaersRulesEngineService;

    private ReportDefinitionDao reportDefinitionDao;
    private StudyDao studyDao;
    private OrganizationDao organizationDao;
    private RuleSet ruleSet;
    private gov.nih.nci.cabig.caaers.domain.RuleSet caaersRuleSet;

//    private boolean isDataChanged;

    private RuleUi ruleUi;
    private String terminology;
    private List<ReportDefinition> reportDefinitions;
    
//    private String mode;
    
    
    private HashMap<String, Boolean> errorsForFields;
    private List<CtcCategory> categories;
    private boolean ruleManager;


    public CreateRuleCommand(CaaersRulesEngineService caaersRulesEngineService,
               ReportDefinitionDao reportDefinitionDao,OrganizationDao organizationDao, StudyDao studyDao) {
        
        setCaaersRulesEngineService(caaersRulesEngineService);
        ruleSet = new RuleSet();
        caaersRuleSet = new gov.nih.nci.cabig.caaers.domain.RuleSet();
        caaersRuleSet.setStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_DISABLED);
        caaersRuleSet.setRuleBindURI(CaaersRuleUtil.getRandomBindURI());
        setReportDefinitionDao(reportDefinitionDao);
        setOrganizationDao(organizationDao);
        setStudyDao(studyDao);
    }


    public void deployRuleSet() {
        caaersRulesEngineService.deployRuleSet(caaersRuleSet.getRuleBindURI());
    }

    public void saveAndDeploy(){
        save();
        deployRuleSet();
    }

    /*
     * This method saves the RuleSet
     */
    public void save() {
    	try{
            //add the rule-set to stage area
            caaersRulesEngineService.saveOrUpdateRuleSet(caaersRuleSet, ruleSet);

    	}catch (Exception ex) {
            logger.error("Exception while creating Rule:", ex);
            throw new CaaersSystemException("Error while saving rule", ex);
        }
    }

    /**
     * Will delete the rules that are marked deleted.
     */
    public void removeDeletedRules(){
        //find the deleted rules.
        if(getRuleSet() == null || getRuleSet().getRule() == null) return;
        List<Rule> rules = new ArrayList<Rule>(getRuleSet().getRule());
        for(Rule rule: rules){
            if(rule.isMarkedDelete()){
                getRuleSet().getRule().remove(rule);
            }
        }

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
        RuleSet ruleSet =  this.getRuleSet();
        if(!ruleSet.getRule().isEmpty()) return;

        Rule newRule = new Rule();
        MetaData metaData = new MetaData();
        newRule.setMetaData(metaData);

        Condition condition = newCondition();
        newRule.setCondition(condition);

        // Action action = new Action();
        List<String> action = new ArrayList<String>();
        newRule.setAction(action);

        ruleSet.getRule().add(newRule);



        // Set the name as the name field has been removed from UI.
        Integer ruleCount = ruleSet.getRule().size() - 1;
        Integer ruleNumber = ++ruleCount;
        newRule.getMetaData().setName("Rule-" + ruleNumber);
        // Done setting the rule name.

    }

    public RuleUi getRuleUi() {
        return ruleUi;
    }

    public void setRuleUi(RuleUi ruleUi) {
        this.ruleUi = ruleUi;
    }
    
    public void retrieveReportDefinitions(){
        if(caaersRuleSet.getOrganization() != null){
            reportDefinitions = fetchReportDefinitions(caaersRuleSet.getOrganization());
        }
    }

    public Map<String, String> getSummary(){

        Map<String, String> summary = new LinkedHashMap<String, String>();
        if(caaersRuleSet.getRuleType() !=  null) summary.put("Rule set name", caaersRuleSet.getRuleType().getName());

        RuleLevel level = caaersRuleSet.getRuleLevel();
        if(caaersRuleSet.getRuleType() == RuleType.SAFETY_SIGNALLING_RULES){
            if(caaersRuleSet.getStudy() !=  null) summary.put("Study", caaersRuleSet.getStudy().getDisplayName());

        }
        if(level !=  null) {
            summary.put("Rule level", level.getDescription());
            if(level.isSponsorBased() && caaersRuleSet.getSponsor() !=  null) summary.put("Sponsor", caaersRuleSet.getOrganization().getFullName());
            if(level.isInstitutionBased() && caaersRuleSet.getSponsor() !=  null) summary.put("Institution", caaersRuleSet.getOrganization().getFullName());
            if(level.isStudyBased() && caaersRuleSet.getStudy() !=  null) summary.put("Study", caaersRuleSet.getStudy().getDisplayName());
        }

        return summary;
    }


    public boolean isCreateMode(){
        return caaersRuleSet.getId() == null;
    }

    public boolean isFieldLevelRule(){
        return caaersRuleSet.getRuleType() == RuleType.FIELD_LEVEL_RULES;
    }
    
    public boolean isSafetySignallingRule(){
        return caaersRuleSet.getRuleType() == RuleType.SAFETY_SIGNALLING_RULES;
    }


    public ExpeditedReportSection[] getReportSectionNames() {
        return  ExpeditedReportSection.values();
    }

    /**
     * Lists all the mandatory values
     * @return
     */
    public Mandatory[] getMandatoryOptions(){
        return Mandatory.values();
    }

    public NotificationStatus[] getNotificationOptions(){
        return NotificationStatus.statuesForRules();
    }

    /**
     * Will populate the ruleset from repository if it exists
     * otherwise will create an empty rule set. 
     *
     */
    public void retrieveParentRuleSet(){
       if(this.ruleSet.getRule().isEmpty()){

           RuleSetQuery query = new RuleSetQuery();
           query.filterByOrganizationId(caaersRuleSet.getOrganization().getId());
           query.filterByRuleType(caaersRuleSet.getRuleType());
           query.filterByRuleLevel(caaersRuleSet.getRuleLevel().isInstitutionBased() ? RuleLevel.Institution : RuleLevel.Sponsor);

           List<gov.nih.nci.cabig.caaers.domain.RuleSet> ruleSets = caaersRulesEngineService.searchRuleSets(query);
           if(!ruleSets.isEmpty()){
               gov.nih.nci.cabig.caaers.domain.RuleSet parentRuleSet = ruleSets.get(0);
               RuleSet parent = caaersRulesEngineService.getRuleSet(parentRuleSet.getRuleBindURI());
               this.ruleSet = parent;
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
    
    
    public void cleanRuleSetAndMakeReadable(){
        if(ruleSet != null){

            caaersRulesEngineService.cleanRuleSet(ruleSet);
            
            if(caaersRuleSet != null){
                Integer orgId = caaersRuleSet.getOrganization() != null ? caaersRuleSet.getOrganization().getId() : null;
                Integer studyId = caaersRuleSet.getStudy() != null ? caaersRuleSet.getStudy().getId() : null;
                String orgNCICode =  caaersRuleSet.getOrganization() != null ? caaersRuleSet.getOrganization().getNciInstituteCode() : "";
                String studyPrimaryId = caaersRuleSet.getStudy() != null ? caaersRuleSet.getStudy().getPrimaryIdentifierValue() : "";
                ruleSet.setName(caaersRulesEngineService.constructPackageName(caaersRuleSet.getRuleType(), caaersRuleSet.getRuleLevel(), orgId, studyId));
                ruleSet.setDescription(caaersRulesEngineService.constructSubject(caaersRuleSet.getRuleType(), caaersRuleSet.getRuleLevel(), orgNCICode, studyPrimaryId ));
            }
            
            caaersRulesEngineService.makeRuleSetReadable(ruleSet);
        }
    }


    public CaaersRulesEngineService getCaaersRulesEngineService() {
        return caaersRulesEngineService;
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
    }

    public List<ReportDefinition> getReportDefinitions() {

        return reportDefinitions;
    }

    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {

        this.reportDefinitions = reportDefinitions;

    }
    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public HashMap<String, Boolean> getErrorsForFields() {
        return errorsForFields;
    }

    public void setErrorsForFields(HashMap<String, Boolean> errorsForFields) {
        this.errorsForFields = errorsForFields;
    }
    /**
     * Will retrieve all the ctc Categories for the study
     * @return
     */
    public List<CtcCategory> getCategories() {

        if(categories == null || categories.isEmpty()){
           categories = new ArrayList<CtcCategory>();
           if(caaersRuleSet.getStudy() != null){
             Ctc ctc = caaersRuleSet.getStudy().getAeTerminology().getCtcVersion();
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

    public ReportDefinitionDao getReportDefinitionDao() {
        return reportDefinitionDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
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

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public boolean isRuleManager() {
        return ruleManager;
    }

    public void setRuleManager(boolean ruleManager) {
        this.ruleManager = ruleManager;
    }

    public gov.nih.nci.cabig.caaers.domain.RuleSet getCaaersRuleSet() {
        return caaersRuleSet;
    }

    public void setCaaersRuleSet(gov.nih.nci.cabig.caaers.domain.RuleSet caaersRuleSet) {
        this.caaersRuleSet = caaersRuleSet;
    }
    
    public Map<Object, Object> getRuleTypeOptions(){
        return WebUtils.collectOptions(Arrays.asList(RuleType.values()) , "name", "name", "Please select");
    }

    public Map<Object, Object> getRuleLevelOptions(){
        return WebUtils.collectOptions(Arrays.asList(RuleLevel.values()) , "name", "description", "Please select");
    }

}
