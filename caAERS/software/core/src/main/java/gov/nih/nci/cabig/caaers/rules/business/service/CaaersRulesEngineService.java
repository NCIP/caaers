/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.brxml.*;
import com.semanticbits.rules.brxml.Condition;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.exception.RuleException;
import com.semanticbits.rules.utils.BRXMLHelper;
import com.semanticbits.rules.utils.RuleUtil;
import com.semanticbits.rules.utils.XMLUtil;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.rules.common.CaaersRuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 *
 * This is the interface/facade to rules engine.
 * @author Biju Joseph
 * @author Ion C. Olaru
 *
 */
public class CaaersRulesEngineService {

    private static final Log log = LogFactory.getLog(CaaersRulesEngineService.class);


	public static final String SPONSOR_LEVEL = RuleLevel.Sponsor.getName();
    public static final String INSTITUTIONAL_LEVEL = RuleLevel.Institution.getName();
    public static final String SPONSOR_DEFINED_STUDY_LEVEL = RuleLevel.SponsorDefinedStudy.getName();
    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = RuleLevel.InstitutionDefinedStudy.getName();

	private RulesEngineService ruleEngineService;

    private RuleSetDao ruleSetDao;
    private ReportDefinitionDao reportDefinitionDao;
    private OrganizationDao organizationDao;
    private StudyDao studyDao;
    private ConfigPropertyDao configPropertyDao;

    //BJ: refactored , extracted from CreateRulesCommand.
    private String[] columnsToTrash = {"studySDO", "organizationSDO", "adverseEventEvaluationResult", "factResolver", "ruleEvaluationResult"};

    /**
     * Will return the rule-set from the rules engine, and returns it after necessary clean-up
     * @param bindURI
     * @return
     */
    public RuleSet getRuleSet(String bindURI){
        RuleSet ruleSet =  ruleEngineService.getRuleSet(bindURI);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return  ruleSet;
    }

    /**
     * Will load the rule-set identified by the ID and exports it.
     * @param bindURI - The binding URI
     */
    @Transactional(readOnly = true)
   public String exportRules(String bindURI) {
      try{
          String xml = ruleEngineService.exportRuleSetXML(bindURI);
          return xml;
      }catch (RuleException ruleEx){
        log.error("Error while retrieving the rules for bindURI : " + bindURI, ruleEx);
        throw new CaaersSystemException("Error pulling the rules xml [" + ruleEx.getMessage() + "]", ruleEx);
      }
   }
    
    
    /**
     * This method will import the rules XML.
     * CAAERS-2325 - requires the use of NCI-code and Sponsor-ID of study to identify the study and organization.
     *
     * @param fileName - The file to import.
     * @return - A list of ReportDefinition  names
     */
    @Transactional
    public List<String> importRules(String fileName) throws CaaersSystemException {

        File f = new File(fileName);
        if(!f.isFile() || !f.exists()){
             throw new CaaersSystemException("The file :" + fileName + "do not exist");
        }
        
        String xml = null;
        try{

            xml = FileUtils.readFileToString(f);
            
        }catch (IOException ioe){
            throw  new CaaersSystemException("Unable to import rule file : " + ioe.getMessage(), ioe);
        }

        RuleSet ruleSet = (RuleSet) XMLUtil.unmarshal(xml);
        List<Rule> rules = ruleSet.getRule();

        if (rules.size() == 0) {
            throw new CaaersSystemException("The provided rule file has nothing to import");
        }

        log.info("Importing ruleSet :" + ruleSet.getName() );

        //--------- Modify the package names ----------------------
        reconcileRuleSet(ruleSet);

        RuleType ruleType = parseRuleType(ruleSet.getName());
        RuleLevel ruleLevel = parseRuleLevel(ruleSet.getName());
        String strOrgId = parseOrganizationId(ruleSet.getName());
        String strStudyId = parseStudyId(ruleSet.getName());

        
        if(log.isInfoEnabled()){
            log.info("RuleSet : name (package name) : " + ruleSet.getName());
            log.info("Rule Set Type : " + String.valueOf(ruleType));
            log.info("Rule Set Level : " + String.valueOf(ruleLevel));
            log.info("Rule Set orgId : " + String.valueOf(strOrgId));
            log.info("Rule Set studyId : " + String.valueOf(strStudyId));
        }
        
        Organization organization = null;
        if(StringUtils.isNotBlank(strOrgId)){
            organization = organizationDao.getById(Integer.parseInt(strOrgId));    
        }
        
        Study study = null;
        if(StringUtils.isNotBlank(strStudyId)){
            study = studyDao.getById(Integer.parseInt(strStudyId));
        }


        //Find the report definitions required to be created.
        HashSet<String> reportDefinitionNames = new HashSet<String>();
        List<String> reportDefinitionsCreated = new ArrayList<String>();

        boolean isAssociatedToDCP = organization == null ? false : organization.getNciInstituteCode().equals("DCP");
        boolean isSAERule =  ruleSet.getDescription().equals(RuleType.REPORT_SCHEDULING_RULES.getName());
        if ( isSAERule && !isAssociatedToDCP ) {

            for (Rule rule : rules) {
                for (String action : rule.getAction()) {
                    reportDefinitionNames.add(action);
                }
            }


            //find the "Expedited" - ConfigProperty
            ConfigProperty expeditedConfigProperty = configPropertyDao.getByTypeAndCode(ConfigPropertyType.REPORT_GROUP, "RT_AdEERS");
            // check report definitions for this org
            for (String rd : reportDefinitionNames) {
                if(StringUtils.equals("IGNORE", rd)) continue;
                ReportDefinition reportDefinition = reportDefinitionDao.getByName(rd, organization.getId());
                if(reportDefinition != null) continue;

                //create a new report definition.
                ReportDefinition newRd = new ReportDefinition();
                newRd.setEnabled(false);
                newRd.setName(rd);
                newRd.setLabel(rd);
                newRd.setOrganization(organization);
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
        

        gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet = createOrFindRuleSet( ruleType, ruleLevel, organization, study, true);

        saveOrUpdateRuleSet(domainRuleSet, ruleSet);
        deployRuleSet(domainRuleSet.getRuleBindURI());

        reportDefinitionsCreated.add(0, domainRuleSet.getRuleBindURI());

        return reportDefinitionsCreated;
    }

    @Transactional
    public void saveOrUpdateRuleSet(gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet, RuleSet ruleSet) {

         saveOrUpdateRuleSet(domainRuleSet);

        //add imports if necessary
        if(CollectionUtils.isEmpty(ruleSet.getImport())){
            ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
        }

        //correct package names
        RuleType ruleType = domainRuleSet.getRuleType();
        RuleLevel ruleLevel = domainRuleSet.getRuleLevel();
        Integer orgId = domainRuleSet.getOrganization() == null ? null : domainRuleSet.getOrganization().getId();
        Integer studyId = domainRuleSet.getStudy() == null ? null : domainRuleSet.getStudy().getId();
        String packageName = constructPackageName(domainRuleSet.getRuleType(), domainRuleSet.getRuleLevel(), orgId, studyId);
        ruleSet.setName(packageName);

        //correct subject
        String nciCode = domainRuleSet.getOrganization() == null ? "" : domainRuleSet.getOrganization().getNciInstituteCode();
        String studyPrimaryId = domainRuleSet.getStudy() == null ? "" : domainRuleSet.getStudy().getPrimaryIdentifierValue();
        String newSubject = constructSubject(ruleType, ruleLevel, nciCode, studyPrimaryId);
        ruleSet.setSubject(newSubject);

        //correct description
        ruleSet.setDescription(ruleType.getName());



        List<Rule> rules = ruleSet.getRule();

        // delete columns which are marked as delete .
        for(Rule rule : rules){
            List<Column> colsToDelete = new ArrayList<Column>();
            for(Column col : rule.getCondition().getColumn()){
                if(col.isMarkedDelete()) colsToDelete.add(col);
            }
            if(!colsToDelete.isEmpty())  rule.getCondition().getColumn().removeAll(colsToDelete);
        }
        
        for (Rule rule : rules) {

            //add rule-id if it is empty
            if(rule.getId() == null) rule.setId("r-" + UUID.randomUUID().toString());

            boolean termSelected = false;

            for (Column col : rule.getCondition().getColumn()) {
                if(col.getFieldConstraint() == null || col.getFieldConstraint().isEmpty()) continue;
                if(col.getFieldConstraint().get(0).getFieldName() == null) continue;
                if (col.getFieldConstraint().get(0).getFieldName().equals("term")) {
                    termSelected = true;
                }
            }

            // modify category if term selecetd
            for (Column col : rule.getCondition().getColumn()) {
                if(col.getFieldConstraint() == null || col.getFieldConstraint().isEmpty()) continue;
                if(col.getFieldConstraint().get(0).getFieldName() == null) continue;
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

            replaceCommaSeperatedStringToList(rule.getCondition());
 

            if(!hasFactResolverColumn(rule.getCondition())) rule.getCondition().getColumn().add(createCriteriaForFactResolver());

            if(rule.getMetaData() == null) rule.setMetaData(new MetaData());
            rule.getMetaData().setPackageName(packageName);
            rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");
            if(ruleLevel != null){
                
                String organizationName = ( domainRuleSet.getOrganization() != null) ? domainRuleSet.getOrganization().getName() : null;
                String sponsorName =  null;
                String institutionName = null;

                if(ruleLevel.isInstitutionBased()) institutionName = organizationName;
                if(ruleLevel.isSponsorBased()) sponsorName = organizationName;

                String studyShortTitle = (domainRuleSet.getStudy() != null) ?  domainRuleSet.getStudy().getShortTitle() : null;

                populateCategoryBasedColumns(rule, ruleLevel.getName(), sponsorName, institutionName, studyShortTitle);   
            }
            

        }

        //save the rules in staging area.
        ruleEngineService.saveOrUpdateRuleSet(domainRuleSet.getRuleBindURI(), ruleSet);
    }

    private gov.nih.nci.cabig.caaers.domain.RuleSet saveOrUpdateRuleSet(gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet){
        if(log.isDebugEnabled()) log.debug("Before saving RuleSet : " + String.valueOf(domainRuleSet));
        ValidationErrors errors = domainRuleSet.validate();

        if(errors.hasErrors()) throw new CaaersSystemException("Unable to save domain RuleSet : " + String.valueOf(errors));

        ruleSetDao.save(domainRuleSet);
        if(log.isDebugEnabled()) log.debug("After saving RuleSet : " + String.valueOf(domainRuleSet));
        return domainRuleSet;
    }

    @Transactional(readOnly = true)
    public gov.nih.nci.cabig.caaers.domain.RuleSet createOrFindRuleSet(RuleType ruleType, RuleLevel ruleLevel, Organization organization, Study study, boolean enabled){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleType(ruleType);
        if(ruleLevel != null) ruleSetQuery.filterByRuleLevel(ruleLevel);
        if(organization != null) ruleSetQuery.filterByOrganizationId(organization.getId());
        if(study != null) ruleSetQuery.filterByStudyId(study.getId());
        
        
        List<gov.nih.nci.cabig.caaers.domain.RuleSet> existingRuleSets = (List<gov.nih.nci.cabig.caaers.domain.RuleSet>) ruleSetDao.search(ruleSetQuery);
        gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet;
        if(!existingRuleSets.isEmpty()){
            domainRuleSet = existingRuleSets.get(0);
            if(log.isDebugEnabled()) log.debug("Found Ruleset in the database [ruleType : " + String.valueOf(ruleType) + ", ruleLevel : " + String.valueOf(ruleLevel) + "Organization " + String.valueOf(organization) + ", study " + String.valueOf(study) + "]:" + String.valueOf(domainRuleSet));
        }else {
            domainRuleSet = new gov.nih.nci.cabig.caaers.domain.RuleSet();
            domainRuleSet.setRuleBindURI(CaaersRuleUtil.getRandomBindURI());
            domainRuleSet.setRuleLevel(ruleLevel);
            domainRuleSet.setRuleType(ruleType);
            domainRuleSet.setStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
            domainRuleSet.setOrganization(organization);
            domainRuleSet.setStudy(study);
            if(log.isDebugEnabled()) log.debug("Created new Ruleset [ruleType : " + String.valueOf(ruleType) + ", ruleLevel : " + String.valueOf(ruleLevel) + "Organization " + String.valueOf(organization) + ", study " + String.valueOf(study) + "]:" + String.valueOf(domainRuleSet));

        }

        domainRuleSet.setStatus(enabled ? gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED : gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_DISABLED);

        return domainRuleSet;
    }



    /**
     * This method will take care of reconciling the ruleset,by making sure that
     * 1. The ruleSet, has the correct package name
     * 2. The path-to-deploy is correct.
     *
     * Note: Backward compatiable with the old ruleset naming convention used.
      * @param ruleSet
     */
    public void reconcileRuleSet(RuleSet ruleSet){

        if(StringUtils.isBlank(ruleSet.getSubject())) return;

        String level = null;

        Organization org = null;
        Study study = null;

        String strOrgId = null;
        String strStudyId = null;

        String[] subjectParts = StringUtils.split(ruleSet.getSubject(), "||");
        if(subjectParts.length >= 4){
            //new pattern
            level = subjectParts[1];
            String nciCode = null;
            String studyPrimaryId = null;
            if(level.equals(SPONSOR_DEFINED_STUDY_LEVEL) || level.equals(SPONSOR_LEVEL)){
                nciCode = subjectParts[2].trim();
            }
            if(level.equals(INSTITUTION_DEFINED_STUDY_LEVEL) || level.equals(INSTITUTIONAL_LEVEL)){
                nciCode = subjectParts[3].trim();
            }

            studyPrimaryId = subjectParts.length > 4 ? StringUtils.trimToNull(subjectParts[4]) : null;

            if(StringUtils.isNotBlank(nciCode)) org = organizationDao.getByNCIcode(nciCode);
            if(StringUtils.isNotBlank(studyPrimaryId)){
              OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
              id.setValue(studyPrimaryId);
              id.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
              study = studyDao.getByIdentifier(id);
            }

        }else{
            //old pattern
            String orgName = null;
            String studyShortTitle = null;
            if(StringUtils.equals(subjectParts[0],CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getDescription())){
                level = SPONSOR_DEFINED_STUDY_LEVEL;
                orgName =  subjectParts[1].trim();
                studyShortTitle = subjectParts[2].trim();
            }else if(StringUtils.equals(subjectParts[0], CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getDescription())){
                level = INSTITUTION_DEFINED_STUDY_LEVEL;
                orgName =  subjectParts[1].trim();
                studyShortTitle = subjectParts[2].trim();
            }else if(StringUtils.equals(subjectParts[0], CategoryConfiguration.SPONSOR_BASE.getDescription())){
                level = SPONSOR_LEVEL;
                orgName =  subjectParts[1].trim();
            }else if(StringUtils.equals(subjectParts[0], CategoryConfiguration.INSTITUTION_BASE.getDescription())){
                level = INSTITUTIONAL_LEVEL;
                orgName =  subjectParts[1].trim();
            }

            if(StringUtils.isNotBlank(orgName)) org = organizationDao.getByName(orgName);
            if(StringUtils.isNotBlank(studyShortTitle)) study = studyDao.getByShortTitle(studyShortTitle);
        }

        //do validations - on organization
        if(StringUtils.equals(level, SPONSOR_LEVEL) ||
           StringUtils.equals(level, INSTITUTIONAL_LEVEL) ||
           StringUtils.equals(level, INSTITUTION_DEFINED_STUDY_LEVEL) ||
           StringUtils.equals(level, SPONSOR_DEFINED_STUDY_LEVEL)){
          if(org == null) throw new CaaersSystemException("RUL_011", "Unable to figureout the sponsor/institution");
        }

        //do validation - study
        if(StringUtils.equals(level, INSTITUTION_DEFINED_STUDY_LEVEL) ||
           StringUtils.equals(level, SPONSOR_DEFINED_STUDY_LEVEL)){
           if(study == null) throw new CaaersSystemException("RUL_021", "Could not figure out the study");
        }

        if(org != null) strOrgId = String.valueOf(org.getId());
        if(study != null) strStudyId = String.valueOf(study.getId());

        //update the package name on the ruleset
        RuleLevel ruleLevel = (StringUtils.isBlank(level) ? null : RuleLevel.getByName(level));
        RuleType ruleType = RuleType.getByName(ruleSet.getDescription());     
        Integer orgId = (org != null) ? org.getId() : null;
        Integer studyId = study != null ? study.getId() : null;
        
        String newPackageName = constructPackageName(ruleType, ruleLevel, orgId, studyId);
        ruleSet.setName(newPackageName);


        //update the subject  \

        String nciCode = org == null ? "" : org.getNciInstituteCode();
        String studyPrimaryId = study == null ? "" : study.getPrimaryIdentifierValue();
        String newSubject = constructSubject(ruleType, ruleLevel, nciCode, studyPrimaryId);
        ruleSet.setSubject(newSubject);


        //update the path.
        String path = generatePath(level, ruleSet.getDescription(), org, org, study);
        for(Rule rule : ruleSet.getRule()){
            List<Category> categories = rule.getMetaData().getCategory();
            if(categories == null){
                categories = new ArrayList<Category>();
                rule.getMetaData().setCategory(categories);
            }
            if(categories.isEmpty()) {
              Category category = new Category();
              category.setMetaData(new MetaData());
              categories.add(category);
            }
            if(categories.get(0).getMetaData() == null){
                categories.get(0).setMetaData(new MetaData());
            }

            rule.getMetaData().getCategory().get(0).setPath(path);
            rule.getMetaData().getCategory().get(0).getMetaData().setName(RuleUtil.getStringWithoutSpaces(ruleSet.getDescription()));
        }

        if(log.isDebugEnabled()){
            log.debug("New Package Name :" + newPackageName);
            log.debug("New Subject :" + newSubject);
            log.debug("New Path :" + path);
        }
    }
    
    public String constructSubject(RuleType ruleType, RuleLevel ruleLevel, String nciCode, String studyPrimaryId){
        StringBuilder sb = new StringBuilder(ruleType.getName()).append("||")
                .append(ruleLevel == null ? " " : ruleLevel.getName()).append("||")
                .append(StringUtils.isEmpty(nciCode) ? " " : nciCode).append("||")
                .append(StringUtils.isEmpty(nciCode) ? " " : nciCode).append("||")
                .append(StringUtils.isEmpty(studyPrimaryId)? " " : studyPrimaryId);
        return sb.toString();
    }


    /**
     * This method is used to unDeploy a ruleSet
     *
     * @param  bindURI - The bind URI
     * @exception RemoteException
     */
    @Transactional
    public void unDeployRuleSet(String bindURI) throws RemoteException {
        gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet = ruleSetDao.getByBindURI(bindURI);
        if(domainRuleSet == null)
            throw new CaaersSystemException("Unable to find RuleSet having rulesBindURI:" + bindURI);

        ruleEngineService.undeployRuleSet(bindURI);
        domainRuleSet.setStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_DISABLED);
        ruleSetDao.save(domainRuleSet);
    }

    /**
     * Will remove the rule-set
     * @param bindURI
     */
    @Transactional
    public void deleteRuleSet(String bindURI){
        ruleEngineService.deleteRuleSet(bindURI);
        ruleSetDao.deleteRuleSet(bindURI);
    }

    /**
     * This method is used to deploy a ruleSet
     *
     * @param  bindURI - The bind URI
     * @exception RemoteException
     */
    @Transactional
    public void deployRuleSet(String bindURI) {
        gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet = ruleSetDao.getByBindURI(bindURI);
        if(domainRuleSet == null)
            throw new CaaersSystemException("Unable to find RuleSet having rulesBindURI:" + bindURI);

        ruleEngineService.deployRuleSet(bindURI);
        domainRuleSet.setStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
        ruleSetDao.save(domainRuleSet);
    }

    @Transactional(readOnly = true)
    public List<gov.nih.nci.cabig.caaers.domain.RuleSet> searchRuleSets(RuleSetQuery query){
        return (List<gov.nih.nci.cabig.caaers.domain.RuleSet>) ruleSetDao.search(query);
    }

    /**
     * Will generate the path to which the rule should be deployed.
     * @param level - The level of the rule.
     * @param ruleSetName - The name of the rule.
     * @param sponsor  - The sponsor organization
     * @param institution  - The institution organization.
     * @param study  - The study.
     * @return
     */
    public String generatePath(String level,String ruleSetName, Organization sponsor, Organization institution, Study study){

        StringBuilder path = new StringBuilder("/").append(CategoryConfiguration.CAAERS_BASE.getName());

        if(StringUtils.equals(level, SPONSOR_LEVEL)){
            path.append("/")
                .append(CategoryConfiguration.SPONSOR_BASE.getName())
                .append("/")
                .append(modifyOrganizationName(sponsor.getId().toString()));

        }
        if(StringUtils.equals(level, INSTITUTIONAL_LEVEL)){
             path.append("/")
                 .append(CategoryConfiguration.INSTITUTION_BASE.getName())
                 .append("/")
                 .append(modifyOrganizationName(institution.getId().toString())) ;
        }
        if(StringUtils.equals(level, SPONSOR_DEFINED_STUDY_LEVEL)){
            path.append("/")
                .append(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getName())
                .append("/")
                .append(modifyOrganizationName(sponsor.getId().toString()))
                .append("/").append(modifyStudyName(study.getId().toString()));
        }
        if(StringUtils.equals(level, INSTITUTION_DEFINED_STUDY_LEVEL)){
            path.append("/")
                .append(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getName())
                .append("/")
                .append(modifyOrganizationName(institution.getId().toString()))
                .append("/").append(modifyStudyName(study.getId().toString()));
        }
        if(StringUtils.isNotEmpty(ruleSetName)){
            path.append("/").append(RuleUtil.getStringWithoutSpaces(ruleSetName));
        }
        return path.toString();

    }

    private Column createCriteriaForFactResolver() {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType("com.semanticbits.rules.objectgraph.FactResolver");
        column.setIdentifier("factResolver");

        return column;

    }
    
    
    private boolean hasFactResolverColumn(Condition condition){
        for(Column c : condition.getColumn()){
            if(StringUtils.equals(c.getIdentifier(), "factResolver")) return true;
        }
        return false;
    }
    
    private void replaceCommaSeperatedStringToList(Condition condition){
        for (Column col : condition.getColumn()) {
            if(CollectionUtils.isEmpty(col.getFieldConstraint())) continue;
            FieldConstraint fc = col.getFieldConstraint().get(0);
            if(CollectionUtils.isEmpty(fc.getLiteralRestriction())) continue;
            LiteralRestriction lr = fc.getLiteralRestriction().get(0);
            if( CollectionUtils.isEmpty(lr.getValue()) ) continue;
            String value = lr.getValue().get(0);
            if (StringUtils.contains(value, ",")) {
                List<String> values = CaaersRuleUtil.charSeparatedStringToStringList(value, ",");
                col.getFieldConstraint().get(0).getLiteralRestriction().get(0).setValue(values);
            }

        }
    }

    /**
     * Populates the category based columns in the rule.
     * @param rule
     * @param level
     * @param sponsorName
     * @param institutionName
     * @param studyShortTitle
     */
    public void populateCategoryBasedColumns(Rule rule, String level, String sponsorName, String institutionName, String studyShortTitle) {
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

    public String constructPackageName(RuleType ruleType, RuleLevel ruleLevel, Integer orgId, Integer studyId){
        StringBuilder sb = new StringBuilder(ruleType.getPackageName());
        if(ruleLevel != null )sb.append(".").append(ruleLevel.getPackageName());
        if(orgId != null) sb.append(".ORG_").append(orgId);
        if(studyId != null)sb.append(".STU_").append(studyId);
        sb.append(".").append(CaaersRuleUtil.getStringWithoutSpaces(ruleType.getName()));
        if(log.isDebugEnabled()) log.debug("New Package : " + sb.toString());
        return sb.toString();
    }


    /**
     * This method will prefix "ORG_" to the organization name.
     * @param orgName
     * @return
     */
    private String modifyOrganizationName(String orgName){
        return "ORG_" + orgName;
    }

    /**
     * This method will prefix "STU_" to the study name.
     * @param studyName
     * @return
     */
    private String modifyStudyName(String studyName){
       return "STU_" + studyName;
    }

    /**
     * Will return the RuleType from package name.
     * @param packageName
     * @return
     */
    public RuleType parseRuleType(String packageName){

        if(StringUtils.contains(packageName, "sae_reporting_rules")) return RuleType.REPORT_SCHEDULING_RULES;
        if(StringUtils.contains(packageName, "field_rules")) return RuleType.FIELD_LEVEL_RULES;
        if(StringUtils.contains(packageName, "mandatory_sections_rules")) return RuleType.MANDATORY_SECTIONS_RULES;
        if(StringUtils.contains(packageName, "safety_signalling_rules")) return RuleType.SAFETY_SIGNALLING_RULES;

        return null;
    }
    
    /**
     * Will parse and return the level, given a package name.
     * @param packageName
     * @return
     */
    public RuleLevel parseRuleLevel(String packageName){

        String prefix = StringUtils.substringBefore(packageName , ".ORG_");

        if(StringUtils.equals(prefix, "gov.nih.nci.cabig.caaers.rules.sponsor.study")) return RuleLevel.SponsorDefinedStudy;
        if(StringUtils.equals(prefix, "gov.nih.nci.cabig.caaers.rules.sponsor")) return RuleLevel.Sponsor;
        if(StringUtils.equals(prefix, "gov.nih.nci.cabig.caaers.rules.institution.study")) return RuleLevel.InstitutionDefinedStudy;
        if(StringUtils.equals(prefix, "gov.nih.nci.cabig.caaers.rules.institution")) return RuleLevel.Institution;

        return null;
    }

    /**
     * Will return the organization Id (Sponsor/Institution) id
     * @param packageName
     * @return
     */
    public String parseOrganizationId(String packageName){

     String s = StringUtils.substringAfter(packageName, ".ORG_");
     String orgId = StringUtils.substringBefore(s, ".");
     return orgId;

    }


    /**
     * Will return the study Id
     * @param packageName
     * @return
     */
    public String parseStudyId(String packageName){

     String s = StringUtils.substringAfter(packageName, ".STU_");
     String studyId = StringUtils.substringBefore(s, ".");
     return studyId;

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

   /*
    *
    * @author Ion C. Olaru
    * Get the fields involved in this particular ruleSet
    * @param rs - the RuleSet to be evaluated
    * @param objectIdentifier - object type to consider, ex: adverseEvent
    * @return - List of String containin the field involved in this ruleSet
    *
    */
    private List<String> getRuleableFields(RuleSet rs, String objectIdentifier) {

        List<String> fields = new ArrayList<String>();
        if (rs == null) return fields;

        log.debug(String.format("Get the ruleable fields from RuleSet: %s", rs.getName()));
        // if(log.isInfoEnabled()) log.info(String.format("Get the ruleable fields from RuleSet: %s", rs.getName()));

        for (Rule r : rs.getRule()) {
            Condition condition = r.getCondition();
            for (Column c : condition.getColumn()) {
                if (c.getIdentifier().equals(objectIdentifier)) {
                    for (FieldConstraint fc : c.getFieldConstraint()) {
                        fields.add(fc.getFieldName());
                    }
                }
            }
        }
        return fields;
    }

    /*
     * Lists attributes of AdverseEvent used in SAE rules.
     *
     * @param r - the aeReport to be evaluated
     * @return - List of String containin the field involved in the ruleSets
     *
     */
    @Transactional(readOnly = true)
    public List<String> getFieldsUsedInSAERules(ExpeditedAdverseEventReport r) throws Exception {

        List<String> fields = new ArrayList<String>();
        Study s = r.getStudy();

        for (RuleSet rs : getRuleSetsByExpeditedReport(r)) {
            fields.addAll(getRuleableFields(rs, "adverseEvent"));
        }

        return fields;
    }


    /*
     *
     * @author Ion C. Olaru
     * Get the List of rulSets applicable for an AEReport
     * The result should contain Sponsor level + Institution level RuleSets
     * Sponsor level RuleSet contains either Sponsor Study specific rules if available, otherwise it takes Sponsor rules
     * Institution level RuleSet contains either Institution Study specific rules if available, otherwise it takes Institution rules
     * @param r - the aeReport to be evaluated
     * @return - List of RuleSets
     *
     */
    private List<RuleSet> getRuleSetsByExpeditedReport(ExpeditedAdverseEventReport aeReport) throws Exception {
        List<RuleSet> rs = new ArrayList<RuleSet>();
        RuleSet r1 = null;
        //checking for sponsor + study
        RuleSetQuery sponsorStudyQuery = new RuleSetQuery();
        sponsorStudyQuery.filterByOrganizationId(aeReport.getStudy().getPrimaryFundingSponsorOrganization().getId());
        sponsorStudyQuery.filterByStudyId(aeReport.getStudy().getId());
        sponsorStudyQuery.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
        sponsorStudyQuery.filterByRuleLevel(RuleLevel.SponsorDefinedStudy);
        sponsorStudyQuery.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
        List<gov.nih.nci.cabig.caaers.domain.RuleSet> sponsorStudyruleSets = searchRuleSets(sponsorStudyQuery);
        if(!sponsorStudyruleSets.isEmpty()){
           r1 = getRuleSet(sponsorStudyruleSets.get(0).getRuleBindURI()); 
        }
        
        //checking sponsor
        if(r1 == null){
            RuleSetQuery sponsorQuery = new RuleSetQuery();
            sponsorQuery.filterByOrganizationId(aeReport.getStudy().getPrimaryFundingSponsorOrganization().getId());
            sponsorQuery.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
            sponsorQuery.filterByRuleLevel(RuleLevel.Sponsor);
            sponsorQuery.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
            List<gov.nih.nci.cabig.caaers.domain.RuleSet> sponsorRuleSets = searchRuleSets(sponsorQuery);
            if(!sponsorRuleSets.isEmpty()){
                r1 = getRuleSet(sponsorRuleSets.get(0).getRuleBindURI());
            } 
        }

        if(r1 != null) rs.add(r1);

        r1 = null;

        //find institution based or institution based study rule sets.
        RuleSetQuery institutionStudyQuery = new RuleSetQuery();
        institutionStudyQuery.filterByOrganizationId(aeReport.getStudySite().getOrganization().getId());
        institutionStudyQuery.filterByStudyId(aeReport.getStudy().getId());
        institutionStudyQuery.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
        institutionStudyQuery.filterByRuleLevel(RuleLevel.InstitutionDefinedStudy);
        institutionStudyQuery.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
        List<gov.nih.nci.cabig.caaers.domain.RuleSet> institutionStudyruleSets = searchRuleSets(institutionStudyQuery);
        if(!institutionStudyruleSets.isEmpty()){
            r1 = getRuleSet(institutionStudyruleSets.get(0).getRuleBindURI());
        }

        //checking sponsor
        if(r1 == null){
            RuleSetQuery institutionQuery = new RuleSetQuery();
            institutionQuery.filterByOrganizationId(aeReport.getStudySite().getOrganization().getId());
            institutionQuery.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
            institutionQuery.filterByRuleLevel(RuleLevel.Institution);
            institutionQuery.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
            List<gov.nih.nci.cabig.caaers.domain.RuleSet> institutionRuleSets = searchRuleSets(institutionQuery);
            if(!institutionRuleSets.isEmpty()){
                r1 = getRuleSet(institutionRuleSets.get(0).getRuleBindURI());
            }
        }

        if(r1 != null) rs.add(r1);


        return rs;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }




    /**
     * Retrieves the ruleset configured for Input fields
     * @return
     */
    @Transactional(readOnly = true)
    public RuleSet getFieldRuleSet(){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleType(RuleType.FIELD_LEVEL_RULES);
        List<gov.nih.nci.cabig.caaers.domain.RuleSet> ruleSets = searchRuleSets(ruleSetQuery);
        if(!ruleSets.isEmpty()) {
            return getRuleSet(ruleSets.get(0).getRuleBindURI());
        }
        return null;
    }



    /**
     * Will return all the rule-set objects availbalbe in caAERS
     */
    @Transactional(readOnly = true)
    public List<gov.nih.nci.cabig.caaers.domain.RuleSet> getAllRuleSets(){
        return (List<gov.nih.nci.cabig.caaers.domain.RuleSet>) ruleSetDao.search(new RuleSetQuery());
    }
    
//
//    /**
//     * Will retrieve all the rule sets.
//     * Note: The default ruleset created by rules engine is removed from the list, as it is not requrired for the caAERS.
//     * @return
//     */
//    public List<RuleSet> getAllRuleSets(){
//       List<RuleSet> ruleSets = ruleAuthoringService.getAllRuleSets();
//       List<RuleSet> allRuleSets = new ArrayList<RuleSet>();
//
//       for(RuleSet ruleSet : ruleSets){
//
//           if (ruleSet.getDescription().equals("The default rule package")) continue;
//
//           allRuleSets.add(ruleSet);
//
//           //populate the other attributes like Organization, level, Study etc.
//           String[] subjectParts = StringUtils.split(ruleSet.getSubject(), "||");
//           if(subjectParts.length < 4) continue;
//
//           String levelCode = subjectParts[1].trim();
//
//           if(StringUtils.isBlank(levelCode)) continue;
//
//           String orgNCICode = subjectParts[2].trim();
//           if(StringUtils.isEmpty(orgNCICode)){
//               orgNCICode = subjectParts[3].trim();
//           }
//           String studyPrimaryIDValue = subjectParts[4].trim();
//           ruleSet.setOrganization(orgNCICode);
//           ruleSet.setStudy(studyPrimaryIDValue);
//
//           for(RuleLevel rl : RuleLevel.values()){
//              if(StringUtils.equals(rl.getName(), levelCode)){
//                   ruleSet.setLevel(rl.getDescription());
//              }
//           }
//
//       }
//       return allRuleSets;
//    }

	public RulesEngineService getRuleEngineService() {
		return ruleEngineService;
	}

	public void setRuleEngineService(RulesEngineService ruleEngineService) {
		this.ruleEngineService = ruleEngineService;
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


    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public RuleSetDao getRuleSetDao() {
        return ruleSetDao;
    }

    public void setRuleSetDao(RuleSetDao ruleSetDao) {
        this.ruleSetDao = ruleSetDao;
    }
}
