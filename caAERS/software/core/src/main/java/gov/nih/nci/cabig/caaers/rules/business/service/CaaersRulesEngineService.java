package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.brxml.*;
import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.rules.common.CaaersRuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.repository.PackageItem;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.impl.RuleAuthoringServiceImpl;
import com.semanticbits.rules.utils.BRXMLHelper;
import com.semanticbits.rules.utils.RuleUtil;
import com.semanticbits.rules.utils.XMLUtil;

/**
 * This is the interface/facade to rules engine. 
 * @author Biju Joseph
 */
public class CaaersRulesEngineService {

    private static final Log log = LogFactory.getLog(CaaersRulesEngineService.class);

	
	public static final String SPONSOR_LEVEL = "Sponsor";
    public static final String INSTITUTIONAL_LEVEL = "Institution";
    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";
    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
	private RulesEngineService ruleEngineService;
	private RuleAuthoringService ruleAuthoringService;
    private RepositoryService repositoryService;
    private ReportDefinitionDao reportDefinitionDao;
    private OrganizationDao organizationDao;
    private ConfigPropertyDao configPropertyDao;
    private RuleDeploymentService ruleDeploymentService;

    //BJ: refactored , extracted from CreateRulesCommand.
    private String[] columnsToTrash = {"studySDO", "organizationSDO", "adverseEventEvaluationResult", "factResolver", "ruleEvaluationResult"};

    public CaaersRulesEngineService() {
        ruleAuthoringService = new RuleAuthoringServiceImpl();
        //this.repositoryService = (RepositoryServiceImpl) RuleServiceContext.getInstance().repositoryService;
    }

    public String createRuleForInstitution(Rule rule, String ruleSetName, String institutionName,
                    String subject, String state) throws Exception {
        // TODO Auto-generated method stub

        RuleSet ruleSet = this.getRuleSetForInstitution(ruleSetName, institutionName);

        if (ruleSet == null) {
            this.createRuleSetForInstitution(ruleSetName, institutionName, subject, state);
        }

        String uuid = null;
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE
                        .getPackagePrefix(), institutionName, ruleSetName);
        Category category = CaaersRuleUtil.getInstitutionSpecificCategory(ruleAuthoringService,
                        institutionName, ruleSetName);

        if (rule.getMetaData() == null) {
            rule.setMetaData(new MetaData());
        }

        rule.getMetaData().setPackageName(packageName);
        rule.getMetaData().getCategory().clear();
        rule.getMetaData().getCategory().add(category);

        uuid = ruleAuthoringService.createRule(rule);

        return uuid;
    }

    public String createRuleForSponsor(Rule rule, String ruleSetName, String sponsorName,
                    String subject, String state) throws Exception {
        // TODO Auto-generated method stub

        RuleSet ruleSet = this.getRuleSetForSponsor(ruleSetName, sponsorName);

        if (ruleSet == null) {
            this.createRuleSetForSponsor(ruleSetName, sponsorName, subject, state);
        }

        String uuid = null;
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE
                        .getPackagePrefix(), sponsorName, ruleSetName);
        Category category = CaaersRuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName,
                        ruleSetName);

        System.out.println("Path of category For Sponsor Rules:" + category.getPath());
        System.out
                        .println("Name of category For Sponsor Rules:"
                                        + category.getMetaData().getName());

        System.out.println("PackageName:" + packageName);
        if (rule.getMetaData() == null) {
            rule.setMetaData(new MetaData());
        }

        rule.getMetaData().setPackageName(packageName);
        rule.getMetaData().getCategory().clear();
        rule.getMetaData().getCategory().add(category);

        uuid = ruleAuthoringService.createRule(rule);

        return uuid;
    }

    public String createRuleForSponsorDefinedStudy(Rule rule, String ruleSetName,
                    String studyShortTitle, String sponsorName, String subject, String state)
                    throws Exception {
        // TODO Auto-generated method stub

        RuleSet ruleSet = this.getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle,
                        sponsorName);

        if (ruleSet == null) {
            this.createRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName,
                            subject, state);
        }

        String uuid = null;
        String packageName = CaaersRuleUtil.getStudySponsorSpecificPackageName(
                        CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(),
                        studyShortTitle, sponsorName, ruleSetName);
        Category category = CaaersRuleUtil.getStudySponsorSpecificCategory(ruleAuthoringService,
                        sponsorName, studyShortTitle, ruleSetName);

        if (rule.getMetaData() == null) {
            rule.setMetaData(new MetaData());
        }

        rule.getMetaData().setPackageName(packageName);
        rule.getMetaData().getCategory().clear();
        rule.getMetaData().getCategory().add(category);

        uuid = ruleAuthoringService.createRule(rule);

        return uuid;

    }

    public String createRuleForInstitutionDefinedStudy(Rule rule, String ruleSetName,
                    String studyShortTitle, String institutionName, String subject, String state)
                    throws Exception {
        // TODO Auto-generated method stub

        RuleSet ruleSet = this.getRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle,
                        institutionName);

        if (ruleSet == null) {
            this.createRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle,
                            institutionName, subject, state);
        }

        String uuid = null;
        String packageName = CaaersRuleUtil.getStudySponsorSpecificPackageName(
                        CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(),
                        studyShortTitle, institutionName, ruleSetName);
        Category category = CaaersRuleUtil.getStudyInstitutionSpecificCategory(ruleAuthoringService,
                        institutionName, studyShortTitle, ruleSetName);

        if (rule.getMetaData() == null) {
            rule.setMetaData(new MetaData());
        }

        rule.getMetaData().setPackageName(packageName);
        rule.getMetaData().getCategory().clear();
        rule.getMetaData().getCategory().add(category);

        uuid = ruleAuthoringService.createRule(rule);

        return uuid;

    }

    public RuleSet createRuleSetForInstitution(String ruleSetName, String institutionName,
                    String subject, String state) throws Exception {
        // TODO Auto-generated method stub

        Category cat = CaaersRuleUtil.getInstitutionSpecificCategory(ruleAuthoringService,
                        institutionName, ruleSetName);

        RuleSet ruleSet = new RuleSet();
        // This name should be unique
        // String packageName =
        // "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

        String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE
                        .getPackagePrefix(), institutionName, ruleSetName);
        // System.out.println(packageName);
        ruleSet.setName(packageName);
        ruleSet.setStatus("Draft");
        ruleSet.setDescription(ruleSetName);
        ruleSet.setSubject(subject);
        ruleSet.setCoverage(state);

        // ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
        if (ruleSet.getImport().size() == 0) {
            ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
        }
        // List<String> _imports = new ArrayList<String>();
        // _imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
        // _imports.add("gov.nih.nci.cabig.caaers.domain.*");
        // ruleSet.setImport(_imports);

        ruleAuthoringService.createRuleSet(ruleSet);

        return ruleSet;

    }

    /**
     * 
     */

    public RuleSet createRuleSetForSponsor(String ruleSetName, String sponsorName, String subject,
                    String state) throws Exception {

        //retrieve or create category (or path) for rules
        Category cat = CaaersRuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName,ruleSetName);

        RuleSet ruleSet = new RuleSet();

        String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
        log.info("PackageName:" + packageName);
        ruleSet.setName(packageName);
        ruleSet.setStatus("Draft");
        ruleSet.setDescription(ruleSetName);
        ruleSet.setSubject(subject);
        ruleSet.setCoverage(state);

        if (ruleSet.getImport().size() == 0) {
            ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
        }

        ruleAuthoringService.createRuleSet(ruleSet);

        return ruleSet;
    }

    public RuleSet createRuleSetForSponsorDefinedStudy(String ruleSetName, String studyShortTitle,
                    String sponsorName, String subject, String state) throws Exception {
       //retrieve or create category (or path) for rules
        Category cat = CaaersRuleUtil.getStudySponsorSpecificCategory(ruleAuthoringService, sponsorName,studyShortTitle, ruleSetName);
        RuleSet ruleSet = new RuleSet();

        String packageName = CaaersRuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(),
                        studyShortTitle, sponsorName, ruleSetName);
        log.info("PackageName :" + packageName);
        ruleSet.setName(packageName);
        ruleSet.setStatus("Draft");
        ruleSet.setDescription(ruleSetName);
        ruleSet.setSubject(subject);
        ruleSet.setCoverage(state);

        if (ruleSet.getImport().size() == 0) {
            ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
        }

        ruleAuthoringService.createRuleSet(ruleSet);

        return ruleSet;
    }

    public RuleSet createRuleSetForInstitutionDefinedStudy(String ruleSetName,
                    String studyShortTitle, String institutionName, String subject, String state)
                    throws Exception {
       //retrieve or create category (or path) for rules
        Category cat = CaaersRuleUtil.getStudyInstitutionSpecificCategory(ruleAuthoringService,institutionName, studyShortTitle, ruleSetName);
        RuleSet ruleSet = new RuleSet();
        String packageName = CaaersRuleUtil.getStudyInstitutionSpecificPackageName(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(),
                        studyShortTitle, institutionName, ruleSetName);
        log.info("PackageName:" + packageName);
        ruleSet.setName(packageName);
        ruleSet.setStatus("Draft");
        ruleSet.setDescription(ruleSetName);
        ruleSet.setSubject(subject);
        ruleSet.setCoverage(state);

        if (ruleSet.getImport().size() == 0) {
            ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
        }

        ruleAuthoringService.createRuleSet(ruleSet);

        return ruleSet;
    }

    public RuleSet getRuleSetForInstitution(String ruleSetName, String institutionName,
                    boolean cached) throws Exception {
        // TODO Auto-generated method stub
        RuleSet ruleSet = null;
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE
                        .getPackagePrefix(), institutionName, ruleSetName);

        ruleSet = ruleAuthoringService.getRuleSet(packageName, cached);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return ruleSet;
    }

    /**
     * I really have to investigate into this We may have to have handle to repositoyr service and
     * do something from there
     * 
     * @throws Exception
     */

    public List<RuleSet> getAllRuleSetsForInstitution(String institutionName) throws Exception {
        List<RuleSet> ruleSets = new ArrayList<RuleSet>();
        String institutionSpecificCategoryPath = CaaersRuleUtil
                        .getInstitutionSpecificPath(institutionName);
        /**
         * First check if there is a entry for this sponsor in the repository If there is no entry
         * then return empty list implying that there are no rules for this sponsor
         */
        boolean exist = RuleUtil.categoryExist(ruleAuthoringService,
                        institutionSpecificCategoryPath);
        if (!exist) {
            return ruleSets;
        }

        /**
         * Now for this catgeory we can go ahead and pull all children
         */
        List<String> ruleSetNames = this.repositoryService
                        .getAllImmediateChildren(institutionSpecificCategoryPath);
        if (ruleSetNames.size() < 1) {
            return ruleSets;
        }

        for (String ruleSetName : ruleSetNames) {

            RuleSet ruleSet = this.getRuleSetForInstitution(ruleSetName, institutionName);
            ruleSets.add(ruleSet);
        }

        return ruleSets;
    }

    public RuleSet getRuleSetForSponsor(String ruleSetName, String sponsorName, boolean cached)
                    throws Exception {
        RuleSet ruleSet = null;
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
        ruleSet = ruleAuthoringService.getRuleSet(packageName, cached);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return ruleSet;
    }

    /**
     * I really have to investigate into this We may have to have handle to repositoyr service and
     * do something from there
     * 
     * @throws Exception
     */

    public List<RuleSet> getAllRuleSetsForSponsor(String sponsorName) throws Exception {
        List<RuleSet> ruleSets = new ArrayList<RuleSet>();
        String sponsorSpecificCategoryPath = CaaersRuleUtil.getSponsorSpecificPath(sponsorName);
        /**
         * First check if there is a entry for this sponsor in the repository If there is no entry
         * then return empty list implying that there are no rules for this sponsor
         */
        boolean exist = RuleUtil.categoryExist(ruleAuthoringService, sponsorSpecificCategoryPath);
        if (!exist) {
            return ruleSets;
        }

        /**
         * Now for this catgeory we can go ahead and pull all children
         */
        List<String> ruleSetNames = this.repositoryService
                        .getAllImmediateChildren(sponsorSpecificCategoryPath);
        if (ruleSetNames.size() < 1) {
            return ruleSets;
        }

        for (String ruleSetName : ruleSetNames) {

            RuleSet ruleSet = this.getRuleSetForSponsor(ruleSetName, sponsorName);
            System.out.println(ruleSet.getName());
            ruleSets.add(ruleSet);
        }

        return ruleSets;
    }

    public RuleSet getRuleSetForSponsorDefinedStudy(String ruleSetName, String studyShortTitle,
                    String sponsorName, boolean cached) throws Exception {
        // TODO Auto-generated method stub
        RuleSet ruleSet = null;
        String packageName = CaaersRuleUtil.getStudySponsorSpecificPackageName(
                        CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(),
                        studyShortTitle, sponsorName, ruleSetName);

        ruleSet = ruleAuthoringService.getRuleSet(packageName, cached);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return ruleSet;

    }

    public RuleSet getRuleSetForInstitutionDefinedStudy(String ruleSetName, String studyShortTitle,
                    String institutionName, boolean cached) throws Exception {
        // TODO Auto-generated method stub
        RuleSet ruleSet = null;
        String packageName = CaaersRuleUtil.getStudyInstitutionSpecificPackageName(
                        CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(),
                        studyShortTitle, institutionName, ruleSetName);

        ruleSet = ruleAuthoringService.getRuleSet(packageName, cached);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return ruleSet;

    }

    /**
     * I really have to investigate into this We may have to have handle to repositoyr service and
     * do something from there
     */

    public List<RuleSet> getAllRuleSetsForSponsorDefinedStudy(String studyShortTitle,
                    String sponsorName) throws Exception {
        List<RuleSet> ruleSets = new ArrayList<RuleSet>();
        String studySponsorSpecificCategoryPath = CaaersRuleUtil.getStudySponsorSpecificPath(
                        studyShortTitle, sponsorName);
        /**
         * First check if there is a entry for this sponsor in the repository If there is no entry
         * then return empty list implying that there are no rules for this sponsor
         */
        boolean exist = RuleUtil.categoryExist(ruleAuthoringService,
                        studySponsorSpecificCategoryPath);
        if (!exist) {
            return ruleSets;
        }

        /**
         * Now for this catgeory we can go ahead and pull all children
         */
        List<String> ruleSetNames = this.repositoryService
                        .getAllImmediateChildren(studySponsorSpecificCategoryPath);
        if (ruleSetNames.size() < 1) {
            return ruleSets;
        }

        for (String ruleSetName : ruleSetNames) {

            RuleSet ruleSet = this.getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle,
                            sponsorName);
            ruleSets.add(ruleSet);
        }

        return ruleSets;
    }

    public List<RuleSet> getAllRuleSetsForInstitutionDefinedStudy(String studyShortTitle,
                    String institutionName) throws Exception {
        List<RuleSet> ruleSets = new ArrayList<RuleSet>();
        String studyInstitutionSpecificCategoryPath = CaaersRuleUtil.getStudyInstitutionSpecificPath(
                        studyShortTitle, institutionName);
        /**
         * First check if there is a entry for this sponsor in the repository If there is no entry
         * then return empty list implying that there are no rules for this sponsor
         */
        boolean exist = RuleUtil.categoryExist(ruleAuthoringService,
                        studyInstitutionSpecificCategoryPath);
        if (!exist) {
            return ruleSets;
        }

        /**
         * Now for this catgeory we can go ahead and pull all children
         */
        List<String> ruleSetNames = this.repositoryService
                        .getAllImmediateChildren(studyInstitutionSpecificCategoryPath);
        if (ruleSetNames.size() < 1) {
            return ruleSets;
        }

        for (String ruleSetName : ruleSetNames) {

            RuleSet ruleSet = this.getRuleSetForInstitutionDefinedStudy(ruleSetName,
                            studyShortTitle, institutionName);
            ruleSets.add(ruleSet);
        }

        return ruleSets;
    }

    /**
     * Retrieves the ruleset configured for Input fields
     * @return
     */
    public RuleSet getFieldRuleSet(String rulesetName){
        return getFieldRuleSet(rulesetName,true);
    }

    /**
     * Retrieves the ruleset configured for input fields
     * @param fromCache - if true will be reteieved from cache.
     * @return
     */
    public RuleSet getFieldRuleSet(String rulesetName, boolean fromCache){
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.CAAERS_BASE.getPackagePrefix(), null, rulesetName);
        RuleSet ruleSet = ruleAuthoringService.getRuleSet(packageName, fromCache);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return ruleSet;
    }

    public List<Rule> getRulesByCategory(String categoryPath) throws Exception {
        // TODO Auto-generated method stub
        List<Rule> rules = null;

        rules = ruleAuthoringService.getRulesByCategory(categoryPath);

        return rules;
    }

    public void saveRulesForInstitution(RuleSet ruleSet, String institutionName) throws Exception {
        // TODO Auto-generated method stub
        String ruleSetName = ruleSet.getDescription();
        String subject = ruleSet.getSubject();
        String state = ruleSet.getCoverage();

        if (ruleSetName == null) {
            throw new Exception("Rule name should be set to some  valid value");
        }
        RuleSet rs = this.getRuleSetForInstitution(ruleSetName, institutionName);
        if (rs == null) {
            // create the rule set
            RuleSet ruleSetTemp = this.createRuleSetForInstitution(ruleSetName, institutionName,
                            subject, state);
        }
        List<Rule> rules = ruleSet.getRule();
        for (Rule rule : rules) {
            if (rule.getId() == null) {
                this.createRuleForInstitution(rule, ruleSetName, institutionName, subject, state);
            } else {
            	ruleEngineService.updateRule(rule);
            }
        }

    }
    public void saveFieldRules(RuleSet ruleSet) throws Exception {
        //retrieve or create category (or path) for rules
        Category cat = CaaersRuleUtil.getFieldRuleSpecificCategory(ruleAuthoringService,ruleSet.getDescription());
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.CAAERS_BASE.getPackagePrefix(), null, ruleSet.getDescription());
        log.info("PackageName:" + packageName);

        RuleSet rs = getFieldRuleSet(ruleSet.getDescription());
        if(rs == null){
            //create ruleset
            RuleSet newRuleSet = new RuleSet();
            newRuleSet.setName(packageName);
            newRuleSet.setStatus("Draft");
            newRuleSet.setDescription(ruleSet.getDescription());
            newRuleSet.setSubject(ruleSet.getSubject());
            newRuleSet.setCoverage(ruleSet.getCoverage());

            if (newRuleSet.getImport().size() == 0) {
                newRuleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
            }

            ruleAuthoringService.createRuleSet(newRuleSet);

        }
        
        for(Rule rule : ruleSet.getRule()){
            if(rule.getId() == null){
                //create rule
                if(rule.getMetaData() == null) rule.setMetaData(new MetaData());
                rule.getMetaData().getCategory().clear();
                rule.getMetaData().getCategory().add(cat);
                rule.getMetaData().setPackageName(packageName);
                ruleAuthoringService.createRule(rule);
            }else{
                ruleEngineService.updateRule(rule);
            }
        }
    }

    public void saveRulesForSponsor(RuleSet ruleSet, String sponsorName) throws Exception {

        String ruleSetName = ruleSet.getDescription();
        String subject = ruleSet.getSubject();
        String state = ruleSet.getCoverage();

        if (ruleSetName == null) {
            throw new Exception("Rule name should be set to some  valid value");
        }
        RuleSet rs = this.getRuleSetForSponsor(ruleSetName, sponsorName);

        if (rs == null) {
            // create the rule set
            RuleSet ruleSetTemp = this.createRuleSetForSponsor(ruleSetName, sponsorName, subject,
                            state);
        }
        List<Rule> rules = ruleSet.getRule();
        for (Rule rule : rules) {
            if (rule.getId() == null) {
                this.createRuleForSponsor(rule, ruleSetName, sponsorName, subject, state);
            } else {
            	ruleEngineService.updateRule(rule);
            }
        }

    }

    public void saveRulesForSponsorDefinedStudy(RuleSet ruleSet, String studyShortTitle,
                    String sponsorName) throws Exception {
        // TODO Auto-generated method stub
        String ruleSetName = ruleSet.getDescription();
        String subject = ruleSet.getSubject();
        String state = ruleSet.getCoverage();

        if (ruleSetName == null) {
            throw new Exception("Rule name should be set to some  valid value");
        }
        RuleSet rs = this.getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle,
                        sponsorName);
        if (rs == null) {
            // create the rule set
            RuleSet ruleSetTemp = this.createRuleSetForSponsorDefinedStudy(ruleSetName,
                            studyShortTitle, sponsorName, subject, state);
        }
        List<Rule> rules = ruleSet.getRule();
        for (Rule rule : rules) {
            if (rule.getId() == null) {
                this.createRuleForSponsorDefinedStudy(rule, ruleSetName, studyShortTitle,
                                sponsorName, subject, state);
            } else {
            	ruleEngineService.updateRule(rule);
            }
        }

    }

    public void saveRulesForInstitutionDefinedStudy(RuleSet ruleSet, String studyShortTitle,
                    String institutionName) throws Exception {
        // TODO Auto-generated method stub
        String ruleSetName = ruleSet.getDescription();
        String subject = ruleSet.getSubject();
        String state = ruleSet.getCoverage();

        // System.out.println("package name before savinf : " +
        // ruleSet.getMetaData().getPackageName());
        if (ruleSetName == null) {
            throw new Exception("Rule name should be set to some  valid value");
        }
        RuleSet rs = this.getRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle,
                        institutionName);
        if (rs == null) {
            // create the rule set
            RuleSet ruleSetTemp = this.createRuleSetForInstitutionDefinedStudy(ruleSetName,
                            studyShortTitle, institutionName, subject, state);
        }
        List<Rule> rules = ruleSet.getRule();
        for (Rule rule : rules) {
            if (rule.getId() == null) {
                this.createRuleForInstitutionDefinedStudy(rule, ruleSetName, studyShortTitle,
                                institutionName, subject, state);
            } else {
            	ruleEngineService.updateRule(rule);
            }
        }

    }
    
    public List<String> importRules(String fileName) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = null;
        File f = new File(fileName);
        if (!f.exists() || f.isDirectory()) {
            throw new Exception("This is not a valid file name or this is a directory");
        }
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(f);
        br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            // System.out.println(line);
            sb.append(line);
        }
        String xml = sb.toString();
        RuleSet ruleSet = (RuleSet) XMLUtil.unmarshal(xml);

        System.out.println("Rule set name:" + ruleSet.getName());
        System.out.println("Rule set id:" + ruleSet.getId());
        System.out.println("Rule set desc:" + ruleSet.getDescription());

        // delete rule set if exists
        try {
        	ruleEngineService.deleteRuleSet(ruleSet.getName());
        } catch (Exception e) {
            // not able to delete which is fine...
            // e.printStackTrace();
        }

        List<Rule> rules = ruleSet.getRule();
        if (rules.size() == 0) {
            throw new Exception("There is nothing to import !");
        }
        Rule r = rules.get(0);
        Category cat = r.getMetaData().getCategory().get(0);
        System.out.println("Category Path:" + cat.getPath());

        RuleSet rs_ = ruleEngineService.getRuleSet(ruleSet.getName());
        List<String> reportDefinitionsCreated = new ArrayList<String>();
        if (rs_ == null) {

            reportDefinitionsCreated = importRuleSet(ruleSet);
            ruleEngineService.deployRuleSet(ruleSet);
        }

        return reportDefinitionsCreated;
    }
    
    private List<String> importRuleSet(RuleSet ruleSet) throws Exception {

        List<Rule> rules = ruleSet.getRule();

        Rule r = rules.get(0);
        Category cat = r.getMetaData().getCategory().get(0);
        //System.out.println("Category Path:" + cat.getPath());
        String catPath = cat.getPath();
        int i = 0;
        String ruleSetName = ruleSet.getDescription();
        Iterator<Rule> it = rules.iterator();
        String subject = ruleSet.getSubject();
        String state = ruleSet.getCoverage();
        String desc = ruleSet.getDescription();
        List<String> reportDefinitions = new ArrayList<String>();

        if (desc.equals(RuleType.REPORT_SCHEDULING_RULES.getName())) {

            for (Rule rule : rules) {
                for (String action : rule.getAction()) {
                    if (!reportDefinitions.contains(action)) {
                        reportDefinitions.add(action);
                    }
                }
            }
        }

        if ((catPath.indexOf(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getName()) == -1)
                        && (catPath.indexOf(CategoryConfiguration.SPONSOR_BASE.getName()) != -1)) {
            // this is sponsor level rules
            i = catPath.lastIndexOf("/");
            String sponsorName = catPath.substring(i + 1, catPath.length());

            // this.saveRulesForSponsor(ruleSet, sponsorName);

            while (it.hasNext()) {
                Rule rule = it.next();
                rule.setId("");
                createRuleForSponsor(rule, ruleSetName, sponsorName, subject, state);

            }

        }

        if (catPath.indexOf(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getName()) != -1) {
            // this is sponsor defined study level rules
            i = catPath.lastIndexOf("/");
            String sponsorName = catPath.substring(i + 1, catPath.length());
            String stringMinusSponsorName = catPath.substring(0, i);
            i = stringMinusSponsorName.lastIndexOf("/");
            String studyShortTitle = stringMinusSponsorName.substring(i + 1, stringMinusSponsorName
                            .length());

            while (it.hasNext()) {
                Rule rule = it.next();
                rule.setId("");
                createRuleForSponsorDefinedStudy(rule, ruleSetName, studyShortTitle, sponsorName,
                                subject, state);
            }
        }
        if ((catPath.indexOf(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getName()) == -1)
                        && (catPath.indexOf(CategoryConfiguration.INSTITUTION_BASE.getName()) != -1)) {
            // this is INSTITUTION defined level rules
            i = catPath.lastIndexOf("/");
            String institutionName = catPath.substring(i + 1, catPath.length());

            while (it.hasNext()) {
                Rule rule = it.next();
                rule.setId("");
                createRuleForInstitution(rule, ruleSetName, institutionName, subject, state);
            }
        }
        if (catPath.indexOf(CategoryConfiguration.INSTITUTION_BASE.getName()) != -1) {
            // this is sponsor defined study level rules
            i = catPath.lastIndexOf("/");
            String institutionName = catPath.substring(i + 1, catPath.length());
            String stringMinusInstitutionName = catPath.substring(0, i);
            i = stringMinusInstitutionName.lastIndexOf("/");
            String studyShortTitle = stringMinusInstitutionName.substring(i + 1,
                            stringMinusInstitutionName.length());

            while (it.hasNext()) {
                Rule rule = it.next();
                rule.setId("");
                createRuleForInstitutionDefinedStudy(rule, ruleSetName, studyShortTitle,
                                institutionName, subject, state);
            }
        }

        String orgName = subject.split("\\|\\|")[1];
        // System.out.println(orgName);
        Organization org = organizationDao.getByName(orgName);
        System.out.println(org.getId() + "," + org.getName());
        List<String> reportDefinitionsCreated = new ArrayList<String>();
        
        /**
         * dont create report definitions if the rule set is DCP 
         * DCP uses CTEP report definitions, this is a temp fix
         */
        if (desc.equals(RuleType.REPORT_SCHEDULING_RULES.getName()) && !orgName.equals("Division of Cancer Prevention")) {
        	
        	//find the "Expedited" - ConfigProperty 
        	ConfigProperty expeditedConfigProperty = configPropertyDao.getByTypeAndCode(ConfigPropertyType.REPORT_GROUP, "RT_AdEERS");
            // check report definitions for this org
            for (String rd : reportDefinitions) {
                ReportDefinition reportDefinition = reportDefinitionDao.getByName(rd, org.getId());
                if (reportDefinition == null && !rd.equals("IGNORE")) {
                    //System.out.println("need to create .." + rd);
                    ReportDefinition newRd = new ReportDefinition();
                    newRd.setName(rd);
                    newRd.setLabel(rd);
                    newRd.setOrganization(org);
                    newRd.setAmendable(true);
                    newRd.setTimeScaleUnitType(TimeScaleUnit.DAY);
                    newRd.setDuration(2);
                    newRd.setReportFormatType(ReportFormatType.ADEERSPDF);
                    newRd.setGroup(expeditedConfigProperty);
                    newRd.setPhysicianSignOff(false);
                    reportDefinitionDao.save(newRd);
                    reportDefinitionsCreated.add(rd);
                }

            }
        }
        return reportDefinitionsCreated;

        // check report definitions in db , make a list of report definitions not found .

    }

    /**
     * This method is used to unDeploy a ruleSet
     * 
     * @param  ruleSetName
     * @exception RemoteException
     */
    public void unDeployRuleSet(String ruleSetName) throws RemoteException {
        String bindUri = ruleSetName;

        try {
            ruleDeploymentService.registerRuleSet(bindUri, ruleSetName);
        } catch (Exception e) {
            // A hack... for the first time this exception will be there...ignore...

        }
        ruleDeploymentService.deregisterRuleSet(bindUri);
        PackageItem item = repositoryService.getRulesRepository().loadPackage(bindUri);
        item.updateCoverage("Not Enabled");
        repositoryService.getRulesRepository().save();
    }
    
    /**
     * This method is used to deploy a ruleSet
     * 
     * @param  ruleSetName
     * @exception RemoteException
     */
    public void deployRuleSet(String ruleSetName) throws RemoteException {
        String bindUri = ruleSetName;

        try {
            ruleDeploymentService.deregisterRuleSet(bindUri);
        } catch (Exception e) {
            // A hack... for the first time this exception will be there...ignore...
        }

        try {
            ruleDeploymentService.registerRuleSet(bindUri, ruleSetName);
            PackageItem item = repositoryService.getRulesRepository().loadPackage(bindUri);
            item.updateCoverage("Enabled");
            repositoryService.getRulesRepository().save();

            // getRuleDeploymentService().registerRuleSet(bindUri, ruleSetName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error deploying ruleset", e);
        }
    }

    /**
     * This method will save the ruleset. 
     * @param ruleSet
     * @param level
     * @param sponsorName
     * @param institutionName
     * @param studyShortTitle
     * @param ruleSetName
     * @throws Exception
     */
    public void saveRuleSet(RuleSet ruleSet, String level, String sponsorName, String institutionName, String studyShortTitle, String ruleSetName ) throws Exception{
    	try {
    			
                List<Rule> rules = ruleSet.getRule();
                // delete columns which are marked as delete .
                for (Rule rule : rules) {
                    boolean termSelected = false;

                    List<Column> cols = new ArrayList<Column>();
                    for (Column col : rule.getCondition().getColumn()) {
                        if (col.isMarkedDelete()) {
                            cols.add(col);
                        }
                        /*
                         * if (col.getFieldConstraint().get(0).getFieldName().equals("term")) {
                         * termSelected = true; }
                         */
                    }

                    for (Column col : cols) {
                        rule.getCondition().getColumn().remove(col);
                    }

                    for (Column col : rule.getCondition().getColumn()) {
                        if (col.getFieldConstraint().get(0).getFieldName().equals("term")) {
                            termSelected = true;
                        }
                    }

                    // modify category if term selecetd
                    for (Column col : rule.getCondition().getColumn()) {
                        if (col.getFieldConstraint().get(0).getFieldName().equals("category")) {
                            if (termSelected) {
                                if (col.getExpression().equals("factResolver.assertFact(adverseEvent,'gov.nih.nci.cabig.caaers.domain.CtcCategory','id','0','>')")) {
                                    String expr = col.getExpression();
                                    String eval = col.getFieldConstraint().get(0)
                                                    .getLiteralRestriction().get(0).getEvaluator();
                                    String value = col.getFieldConstraint().get(0)
                                                    .getLiteralRestriction().get(0).getValue().get(0);
                                    expr = expr.replaceAll("'0'", "'" + value + "'");
                                    expr = expr.replaceAll("'>'", "'" + eval + "'");
                                    col.setExpression(expr);
                                } else {
                                    col.setExpression("factResolver.assertFact(adverseEvent,'gov.nih.nci.cabig.caaers.domain.CtcCategory','id','0','>')");
                                }
                            } else {
                                if (col.getExpression().equals("factResolver.assertFact(adverseEvent,'gov.nih.nci.cabig.caaers.domain.CtcCategory','id','0','>')")) {
                                    String expr = col.getExpression();
                                    String eval = col.getFieldConstraint().get(0)
                                                    .getLiteralRestriction().get(0).getEvaluator();
                                    String value = col.getFieldConstraint().get(0)
                                                    .getLiteralRestriction().get(0).getValue().get(0);
                                    expr = expr.replaceAll("'0'", "'" + value + "'");
                                    expr = expr.replaceAll("'>'", "'" + eval + "'");
                                    col.setExpression(expr);
                                }
                            }
                        }
                    }

                    // get comma seperated values ....
                    for (Column col : rule.getCondition().getColumn()) {
                        String value = col.getFieldConstraint().get(0).getLiteralRestriction().get(0)
                                        .getValue().get(0);
                        if (value.contains(",")) {
                            List<String> values = RuleUtil.charSeparatedStringToStringList(value, ",");
                            col.getFieldConstraint().get(0).getLiteralRestriction().get(0).setValue(
                                            values);
                        }

                    }
                    rule.getCondition().getColumn().add(createCriteriaForFactResolver());
                }
                ruleSet.setCoverage("Not Enabled");
                String subject = "";
                System.out.println("------- LEVEL ----- " + level);
                if (SPONSOR_DEFINED_STUDY_LEVEL.equals(level)) {
                    subject = "Sponsor defined rules for a study||" + sponsorName + "||"
                                    + studyShortTitle;
                } else if (SPONSOR_LEVEL.equals(level)) {
                    subject = "Sponsor rules||" + sponsorName;
                } else if (INSTITUTIONAL_LEVEL.equals(level)) {
                    subject = "Institution rules||" + institutionName;
                } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equals(level)) {

                    subject = "Institution defined rules for a study||" + institutionName + "||"
                                    + studyShortTitle;
                }

                ruleSet.setDescription(ruleSetName);
                ruleSet.setSubject(subject);

                // Set the Package name and categoryIdentifier for all rules before saving them.
                for (Rule rule : rules) {
                    rule.getMetaData().setPackageName(constructPackageName(level, sponsorName, institutionName, studyShortTitle, ruleSetName));
                    rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");

                    populateCategoryBasedColumns(rule, level, sponsorName, institutionName, studyShortTitle);
                }

                if(StringUtils.equals(ruleSetName, RuleType.FIELD_LEVEL_RULES.getName())){

                    saveFieldRules(ruleSet);

                }else{
                   if (SPONSOR_LEVEL.equalsIgnoreCase(level)) {
                       saveRulesForSponsor(ruleSet, sponsorName);
                   } else if (INSTITUTIONAL_LEVEL.equalsIgnoreCase(level)) {
                       saveRulesForInstitution(ruleSet, institutionName);
                   } else if (SPONSOR_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
                       saveRulesForSponsorDefinedStudy(ruleSet, studyShortTitle, sponsorName);
                   } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
                       saveRulesForInstitutionDefinedStudy(ruleSet, studyShortTitle, institutionName);
                   }

                }


    	}catch (Exception e){
    		e.printStackTrace();
            throw new Exception("Error saving a  ruleset", e);
    	}
    }
    
    private Column createCriteriaForFactResolver() {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType("com.semanticbits.rules.objectgraph.FactResolver");
        column.setIdentifier("factResolver");

        return column;

    }
    
    private void populateCategoryBasedColumns(Rule rule, String level, String sponsorName, String institutionName, String studyShortTitle) {
        if (SPONSOR_DEFINED_STUDY_LEVEL.equals(level)) {
            rule.getCondition().getColumn().add(createCriteriaForSponsor(sponsorName));
            rule.getCondition().getColumn().add(
                            createCriteriaForStudy(studyShortTitle,
                                            SPONSOR_DEFINED_STUDY_LEVEL));
        } else if (SPONSOR_LEVEL.equals(level)) {
            rule.getCondition().getColumn().add(createCriteriaForSponsor(sponsorName));
        } else if (INSTITUTIONAL_LEVEL.equals(level)) {
            rule.getCondition().getColumn().add(createCriteriaForInstitute(institutionName));
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equals(level)) {
            rule.getCondition().getColumn().add(createCriteriaForInstitute(institutionName));
            rule.getCondition().getColumn().add(
                            createCriteriaForStudy(studyShortTitle,
                                            INSTITUTION_DEFINED_STUDY_LEVEL));
        }
    }
    
    /*
     * THis method is used to create criteria for sponsor based on the sponsor name
     */
    private Column createCriteriaForSponsor(String criteriaValue) {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType(gov.nih.nci.cabig.caaers.domain.Study.class.getName());
        column.setIdentifier("studySDO");
        String expression = "factResolver.assertFact(studySDO,"
                        + "\"gov.nih.nci.cabig.caaers.domain.Organization" + "\"," + "\"name"
                        + "\"," + "\"" + criteriaValue + "\",\"==\"" + ")";

        column.setExpression(expression);

        List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

        FieldConstraint fieldConstraint = new FieldConstraint();
        fieldConstraint.setFieldName(getFieldNameBasedOnLevel(SPONSOR_LEVEL));
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
     * This method creates criteria column with study short title as the criteria
     */
    private Column createCriteriaForStudy(String criteriaValue, String level) {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType(gov.nih.nci.cabig.caaers.domain.Study.class.getName());
        column.setIdentifier("studySDO");

        String expression = "factResolver.assertFact(studySDO,null," + "\"shortTitle" + "\","
                        + "\"" + criteriaValue + "\",\"==\"" + ")";

        column.setExpression(expression);

        List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

        FieldConstraint fieldConstraint = new FieldConstraint();
        fieldConstraint.setFieldName(getFieldNameBasedOnLevel(level));
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
     * This method creates criteria column with institute name as the criteria
     */
    private Column createCriteriaForInstitute(String criteriaValue) {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType(gov.nih.nci.cabig.caaers.domain.Organization.class.getName());
        column.setIdentifier("organizationSDO");
        String expression = "factResolver.assertFact(organizationSDO,null," + "\"name" + "\","
                        + "\"" + criteriaValue + "\",\"==\"" + ")";

        column.setExpression(expression);

        List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

        FieldConstraint fieldConstraint = new FieldConstraint();
        fieldConstraint
                        .setFieldName(getFieldNameBasedOnLevel(INSTITUTIONAL_LEVEL));
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
     * This method cpnstructs the package name based on the Command object
     */
    public String constructPackageName(String level, String sponsorName, String institutionName, String studyShortTitle, String ruleSetName) {
        final String SPONSOR_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.sponsor";
        final String INSTITUTION_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.institution";

        String packageName = null;

        if (SPONSOR_LEVEL.equalsIgnoreCase(level)) {
            packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
        } else if (INSTITUTIONAL_LEVEL.equalsIgnoreCase(level)) {
            packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
        } else if (SPONSOR_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
            packageName = CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix() + "."
                            + RuleUtil.getStringWithoutSpaces(sponsorName) + "."
                            + RuleUtil.getStringWithoutSpaces(studyShortTitle) + "."
                            + RuleUtil.getStringWithoutSpaces(ruleSetName);
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
            packageName = CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE + "." 
                            + RuleUtil.getStringWithoutSpaces(institutionName) + "."
                            + RuleUtil.getStringWithoutSpaces(studyShortTitle) + "."
                            + RuleUtil.getStringWithoutSpaces(ruleSetName);
        }

        return packageName;

    }

    /**
     * Will clean the ruleset retrieved from authoring service, by removing elements like
     *  a. StudySDO
     *  b. OrganizationSDO
     *  c. FactResolver
     *  d. EvaluationResult.
     * @param ruleSet
     */
    public void cleanRuleSet(RuleSet ruleSet){

        if(ruleSet == null || ruleSet.getRule() == null || ruleSet.getRule().size() <= 0)  return;

        for(Rule rule : ruleSet.getRule()){
          List<Column> toRemove = new ArrayList<Column>();
          for(Column c : rule.getCondition().getColumn()){
            if(ArrayUtils.contains(columnsToTrash, c.getIdentifier())) toRemove.add(c);
          }
          rule.getCondition().getColumn().removeAll(toRemove);
        }

    }

    /**
     * Populates the ReadableRule and Readable actions in the given ruleset.
     * @param ruleSet - A cleaned ruleset
     */
    //BJ : refactored, extracted from ReviewTab.referenceData():Map
    public void makeRuleSetReadable(RuleSet ruleSet){
        
        if(ruleSet == null || ruleSet.getRule() == null || ruleSet.getRule().size() <= 0)  return;
        int i = 1;
        for(Rule rule : ruleSet.getRule()){
            if(rule.isMarkedDelete()) continue;

            //add the readable rules (LHS)
            ReadableRule readableRule = new ReadableRule();
            List<String> readableRuleLine = new ArrayList<String>();
            List<String> readableActions = new ArrayList<String>();

            readableRuleLine.add("If");

            if(rule.getCondition() != null && rule.getCondition().getColumn() != null){
                int cCount = 0;
                for(Column column : rule.getCondition().getColumn()){
                    if(StringUtils.equals(column.getExpression(), "getPrimaryFundingSponsorOrganization().getName()") ||
                       StringUtils.equals(column.getObjectType(), "gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult") ||
                       StringUtils.equals(column.getObjectType(), "com.semanticbits.rules.impl.RuleEvaluationResult") ||
                       BooleanUtils.isTrue( column.isMarkedDelete()))  continue;

                    if(cCount > 0) readableRuleLine.add("And");
                    readableRuleLine.add("	" + RuleUtil.readableColumn(column));
                    cCount++;

                }
            }

            //add the readable actions (RHS)
            if(ruleSet.getDescription().equals(RuleType.MANDATORY_SECTIONS_RULES.getName())){
               for(String action: rule.getAction()) readableActions.add(ExpeditedReportSection.valueOf(action).getDisplayName());
            }else if(ruleSet.getDescription().equals(RuleType.FIELD_LEVEL_RULES.getName())){
               for(String action: rule.getAction()) readableActions.add(Mandatory.valueOf(action).getDisplayName());
            }else{
                readableActions.addAll(rule.getAction());
            }
            rule.getMetaData().setName("Rule-" + i);
            readableRule.setLine(readableRuleLine);
            rule.setReadableRule(readableRule);
            rule.setReadableAction(readableActions);

           i++;
        }

    }
    
    /*
     * This method returns the attribute to be used for creating the criteria
     */
    private String getFieldNameBasedOnLevel(String level) {
        String fieldName = "shortTitle";

        if (SPONSOR_LEVEL.equals(level)) {
            fieldName = "primarySponsorCode";
        } else if (INSTITUTIONAL_LEVEL.equals(level)) {
            fieldName = "name";
        } else if (SPONSOR_DEFINED_STUDY_LEVEL.equals(level)) {
            fieldName = "shortTitle";
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equals(level)) {
            fieldName = "shortTitle";
        }

        return fieldName;
    }
    

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public RuleSet getRuleSetForSponsor(String ruleSetName, String sponsorName) throws Exception {
        return getRuleSetForSponsor(ruleSetName, sponsorName, true);
    }

    public RuleSet getRuleSetForInstitution(String ruleSetName, String institutionName)
                    throws Exception {
        return getRuleSetForInstitution(ruleSetName, institutionName, true);
    }

    public RuleSet getRuleSetForSponsorDefinedStudy(String ruleSetName, String studyShortTitle,
                    String sponsorName) throws Exception {
        return getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName, true);
    }

    public RuleSet getRuleSetForInstitutionDefinedStudy(String ruleSetName, String studyShortTitle,
                    String institutionName) throws Exception {
        return getRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle, institutionName,
                        true);
    }

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public RulesEngineService getRuleEngineService() {
		return ruleEngineService;
	}

	public void setRuleEngineService(RulesEngineService ruleEngineService) {
		this.ruleEngineService = ruleEngineService;
	}

	public RuleAuthoringService getRuleAuthoringService() {
		return ruleAuthoringService;
	}

	public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
		this.ruleAuthoringService = ruleAuthoringService;
	}

	public ReportDefinitionDao getReportDefinitionDao() {
		return reportDefinitionDao;
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}
	
	public ConfigPropertyDao getConfigPropertyDao() {
		return configPropertyDao;
	}
	public void setConfigPropertyDao(ConfigPropertyDao configPropertyDao) {
		this.configPropertyDao = configPropertyDao;
	}
    
	public RuleDeploymentService getRuleDeploymentService() {
        return ruleDeploymentService;
    }

    public void setRuleDeploymentService(RuleDeploymentService ruleDeploymentService) {
        this.ruleDeploymentService = ruleDeploymentService;
    }
}
