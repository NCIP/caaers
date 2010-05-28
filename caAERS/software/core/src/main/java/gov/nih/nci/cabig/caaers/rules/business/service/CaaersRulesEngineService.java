package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.brxml.*;
import com.semanticbits.rules.brxml.Condition;
import com.semanticbits.rules.impl.RuleAuthoringServiceImpl;
import com.semanticbits.rules.utils.BRXMLHelper;
import com.semanticbits.rules.utils.RuleUtil;
import com.semanticbits.rules.utils.XMLUtil;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.repository.PackageItem;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private RuleAuthoringService ruleAuthoringService;
    private RepositoryService repositoryService;
    private ReportDefinitionDao reportDefinitionDao;
    private OrganizationDao organizationDao;
    private StudyDao studyDao;
    private ConfigPropertyDao configPropertyDao;
    private RuleDeploymentService ruleDeploymentService;

    //BJ: refactored , extracted from CreateRulesCommand.
    private String[] columnsToTrash = {"studySDO", "organizationSDO", "adverseEventEvaluationResult", "factResolver", "ruleEvaluationResult"};

    public CaaersRulesEngineService() {
        ruleAuthoringService = new RuleAuthoringServiceImpl();
    }

    /**
     * Will create and register the ruleset with authoring service.
     * @param packageName   - The package name of ruleset
     * @param ruleSetName   - The rule name.
     * @param subject       - Subject to be set in the rule metadata
     * @param coverage         - The coverage of the rule.
     * @return              - A ruleset
     */
    public RuleSet createRuleset(String packageName, String ruleSetName, String subject, String coverage){
        //create
       if(log.isDebugEnabled()) log.debug("Ruleset dosent exist, so going to create it");

       RuleSet ruleSet = new RuleSet();
       ruleSet.setName(packageName);
       ruleSet.setStatus("Draft");
       ruleSet.setDescription(ruleSetName);
       ruleSet.setSubject(subject);
       ruleSet.setCoverage(coverage);

       if (ruleSet.getImport().size() == 0) {
           ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
       }

       ruleAuthoringService.createRuleSet(ruleSet);
       return ruleSet;
    }



    /**
     * This method will create a rule set  in the system.
     * Will take care of creating the category and ruleset if they do not exist
     * @param ruleSet          - The rule to create
     * @param packageName   - The package name
     * @param path          - The path to register the rule in authoring system.
     * @param ruleSetName   - The name of ruleset
     * @param subject       - The subject for the ruleset
     * @param state         - The coverage of the ruleset
     * @return              - The ID of the rule.
     * @throws Exception
     */
    public void createOrUpdateRuleSet(RuleSet ruleSet, String packageName, String path, String ruleSetName, String subject, String state) throws Exception{

        //find the category
        Category category = CaaersRuleUtil.createCategory(ruleAuthoringService , path);

        //find the ruleset, if it dosent exist create.
        RuleSet existingRuleSet = getRuleSetByPackageName(packageName, true);

        //if there is no ruleset create it.
        if(existingRuleSet == null) createRuleset(packageName, ruleSetName, subject, state);

        for(Rule rule : ruleSet.getRule()){


            if(rule.getId() == null){
             //create
                //add the category in the rule.
                if(rule.getMetaData() == null) rule.setMetaData(new MetaData());
                rule.getMetaData().getCategory().clear();
                rule.getMetaData().getCategory().add(category);
                rule.getMetaData().setPackageName(packageName);

                String uuid = ruleAuthoringService.createRule(rule);
                log.info("UUID of rule :" + uuid);
            }else{
              //update
                ruleEngineService.updateRule(rule);
            }
        }

    }

    /**
     * Will delete the rule from the ruleset. 
     * @param ruleSetName
     * @param ruleName
     */
    public void deleteRule(String ruleSetName, String ruleName) throws Exception{
        ruleEngineService.deleteRule(ruleSetName, ruleName);
    }

    /**
     * This method will import the rules XML.
     * CAAERS-2325 - requires the use of NCI-code and Sponsor-ID of study to identify the study and organization. 
     *
     * @param fileName - The file to import.
     * @return - A list of ReportDefinition  names
     * @throws Exception
     */
    public List<String> importRules(String fileName) throws Exception {

        File f = new File(fileName);
        if(!f.isFile() || !f.exists()){
             throw new Exception("This is not a valid file name or this is a directory");
        }

        String xml = FileUtils.readFileToString(f);

        RuleSet ruleSet = (RuleSet) XMLUtil.unmarshal(xml);
        List<Rule> rules = ruleSet.getRule();
        
        if (rules.size() == 0) {
            throw new Exception("There is nothing to import !");
        }

        log.info("Importing ruleSet :" + ruleSet.getName() );
        
        //--------- Modify the package names ----------------------
        reconcileRuleSet(ruleSet);
        String strOrgId = parseOrganizationId(ruleSet.getName());
        Organization org = organizationDao.getById(Integer.parseInt(strOrgId));
        
        if(log.isInfoEnabled()){
            log.info("Rule set name:" + ruleSet.getName());
            log.info("Rule set id:" + ruleSet.getId());
            log.info("Rule set desc:" + ruleSet.getDescription());
        }

        // delete rule set if exists
        try {
        	ruleEngineService.deleteRuleSet(ruleSet.getName());
        } catch (Exception e) {
            // not able to delete which is fine...
            log.debug("May not be an issue", e);
        }

        //throw away the ID associated to each rule, so that it wiil issue a create rule.
        for(Rule rule : rules){
            rule.setId(null);
        }

        //Find the report definitions required to be created. 
        Set<String> reportDefinitionNames = new HashSet<String>();
        List<String> reportDefinitionsCreated = new ArrayList<String>();

        boolean isAssociatedToDCP = org.getNciInstituteCode().equals("DCP");
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
                    ReportDefinition reportDefinition = reportDefinitionDao.getByName(rd, org.getId());
                    if (reportDefinition == null && !rd.equals("IGNORE")) {
                        //if(log.isInfoEnabled()) log.info("need to create .." + rd);
                        ReportDefinition newRd = new ReportDefinition();
                        newRd.setEnabled(true);
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

        //find the path to deploy from category
        Category cat = ruleSet.getRule().get(0).getMetaData().getCategory().get(0);
        String catPath = cat.getPath();

        //create the rule set
        createOrUpdateRuleSet(ruleSet, ruleSet.getName(), catPath,ruleSet.getDescription(), ruleSet.getSubject(), ruleSet.getCoverage());

        //deploy the ruleset
        ruleEngineService.deployRuleSet(ruleSet);


        return reportDefinitionsCreated;
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

        if(StringUtils.isEmpty(ruleSet.getSubject())) return;

        String[] subjectParts = StringUtils.split(ruleSet.getSubject(), "||");



        if(subjectParts.length > 4){
             //length = 5 - new pattern

            String level = subjectParts[1];
            if(StringUtils.isNotEmpty(level)){
                //need to modify package.
                String sponsorNCICode = subjectParts[2].trim();
                String institutionNCICode = subjectParts[3].trim();
                String studyIdentifierValue = subjectParts[4].trim();

                String nciCode = null;
                if(level.equals(SPONSOR_DEFINED_STUDY_LEVEL) || level.equals(SPONSOR_LEVEL)){
                   nciCode = sponsorNCICode;
                }
                if(level.equals(INSTITUTION_DEFINED_STUDY_LEVEL) || level.equals(INSTITUTIONAL_LEVEL)){
                   nciCode = institutionNCICode;
                }

                Organization org = organizationDao.getByNCIcode(nciCode);
                Study study = null;
                
                if(org == null) throw new CaaersSystemException("RUL_011", "Missing sponsor/institution : "
                        + nciCode );

                if(StringUtils.isNotEmpty(studyIdentifierValue)){
                  OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
                    id.setValue(studyIdentifierValue);
                    id.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
                    study = studyDao.getByIdentifier(id);

                }
                if(level.equals(SPONSOR_DEFINED_STUDY_LEVEL) || level.equals(INSTITUTION_DEFINED_STUDY_LEVEL)){
                    if(study == null){
                        throw new CaaersSystemException("RUL_021", "Could not find the Study identified by sponsor identifier : "
                                + studyIdentifierValue);
                    }
                }

                //generate new package name.
                String newPackageName = constructPackageName(level, String.valueOf(org.getId()),
                        String.valueOf(org.getId()), String.valueOf(study.getId()), ruleSet.getDescription());
                ruleSet.setName(newPackageName);


        }

            

        }else{
            //length < 5 - old pattern.
            String level = null;
            String orgName = null;
            String studyShortTitle = null;
            String path = CategoryConfiguration.CAAERS_BASE.getPath();

            Organization org = null;
            Study study = null;
            String strOrgId = null;
            String strStudyId = null;

            //need to change package name & Path.
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

            //validate orgname
            if(orgName == null) throw new CaaersSystemException("RUL_011", "Missing sponsor/institution" );

            org = organizationDao.getByName(orgName);

            if(org == null) throw new CaaersSystemException("RUL_011", "Missing sponsor/institution : "
                        + orgName );
            strOrgId = org.getId().toString();

            if(StringUtils.equals(level, SPONSOR_DEFINED_STUDY_LEVEL) || StringUtils.equals(level, INSTITUTION_DEFINED_STUDY_LEVEL)){
                if(StringUtils.isEmpty(studyShortTitle))throw new CaaersSystemException("RUL_021", "Could not find the Study");
                study = studyDao.getByShortTitle(studyShortTitle);
                if(study == null) throw new CaaersSystemException("RUL_021", "Could not find the Study by short title : "
                                + studyShortTitle);
                strStudyId = study.getId().toString();
            }

            //generate new package name.
            String newPackageName = constructPackageName(level, strOrgId,strOrgId, strStudyId, ruleSet.getDescription());
            ruleSet.setName(newPackageName);

            //update the subject
            StringBuilder subject = new StringBuilder(ruleSet.getDescription()).append("||");
                subject.append(StringUtils.isEmpty(level)? " " : level);
                subject.append("||");
                subject.append(org.getNciInstituteCode() );
                subject.append("||");
                subject.append(org.getNciInstituteCode());
                subject.append("||");
                subject.append(study != null ? study.getPrimaryIdentifierValue() : " ");

                ruleSet.setSubject(subject.toString());

            //update the path.
            path = generatePath(level, null, org, org, study);
            for(Rule rule : ruleSet.getRule()){
                rule.getMetaData().getCategory().get(0).setPath(path);
                rule.getMetaData().getCategory().get(0).getMetaData().setName(RuleUtil.getStringWithoutSpaces(ruleSet.getDescription()));
            }
        }


    }


    /**
     * This method is used to unDeploy a ruleSet
     * 
     * @param  ruleSetName - The bind URI
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
     * @param  ruleSetName - The bind URI
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
     * This method will save a rule set.
     *
     * Note: The subject of the ruleset will be framed using the login
     *  RuleSetName || level || Sponsor-NCI-Code || Institution-NCI-Code || Study Primary ID
     *
     * @param ruleSet - The ruleset to save
     * @param level    - The level
     * @param ruleSetName  - The ruleset name eg: SAE Reporting Rules
     * @param sponsor     - The sponsor of the study
     * @param institution - The site, where the subject belongs
     * @param study       - The study on which adverse event occured
     * @throws Exception
     */
    public void saveRuleSet(RuleSet ruleSet,String level,String ruleSetName,Organization  sponsor,
                            Organization institution,Study study) throws Exception{
    	try {

                String sponsorId = (sponsor != null)? sponsor.getId().toString() : null;
                String studyId = (study != null) ? study.getId().toString() : null;
                String institutionId = (institution != null) ? institution.getId().toString() : null;

                String sponsorName = (sponsor != null) ? sponsor.getName() : null;
                String institutionName = (institution != null) ? institution.getName() : null;
                String studyShortTitle = (study != null) ?  study.getShortTitle() : null;
                String pathToDeploy = generatePath(level, ruleSetName, sponsor, institution, study);
                String packageName = constructPackageName(level, sponsorId,institutionId, studyId, ruleSetName);


                //set additional attributes in ruleset
                ruleSet.setDescription(ruleSetName);
                ruleSet.setCoverage("Not Enabled");
                StringBuilder subject = new StringBuilder(ruleSetName).append("||");
                subject.append(StringUtils.isEmpty(level)? " " : level);
                subject.append("||");
                subject.append(sponsor != null? sponsor.getNciInstituteCode() : " ");
                subject.append("||");
                subject.append(institution != null ? institution.getNciInstituteCode() : " ");
                subject.append("||");
                subject.append(study != null ? study.getPrimaryIdentifierValue() : " ");
               
                ruleSet.setSubject(subject.toString());

                List<Rule> rules = ruleSet.getRule();

                // delete columns which are marked as delete .
                for (Rule rule : rules) {
                    boolean termSelected = false;

                    List<Column> colsToDelete = new ArrayList<Column>();
                    for (Column col : rule.getCondition().getColumn()) {
                        if (col.isMarkedDelete()) {
                            colsToDelete.add(col);
                        }
                        
                    }

                    for (Column col : colsToDelete) {
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

                    if(rule.getMetaData() == null) rule.setMetaData(new MetaData());
                    rule.getMetaData().setPackageName(packageName);
                    rule.getMetaData().setDescription("Setting Description since its mandatory by JBoss Repository config");
                    populateCategoryBasedColumns(rule, level, sponsorName, institutionName, studyShortTitle);

                }
            

                if(log.isDebugEnabled())log.debug("Generated Path : " + pathToDeploy );

                createOrUpdateRuleSet(ruleSet, packageName, pathToDeploy, ruleSetName, subject.toString(), ruleSet.getCoverage());


    	}catch (Exception e){
    		e.printStackTrace();
            throw new Exception("Error saving a  ruleset", e);
    	}
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
    
    /*
     * This method constructs the package name based on the Command object
     */
    public String constructPackageName(String level, String sponsorName, String institutionName, String studyShortTitle, String ruleSetName) {

        StringBuilder sb = new StringBuilder();
        
        if(StringUtils.equals(level, SPONSOR_LEVEL)){
            sb.append(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix())
                    .append(".").append(modifyOrganizationName(sponsorName));
        }else if(StringUtils.equals(level, SPONSOR_DEFINED_STUDY_LEVEL)){
            sb.append(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix())
                    .append(".").append(modifyOrganizationName(sponsorName))
                    .append(".").append(modifyStudyName(studyShortTitle));
        }else if(StringUtils.equals(level, INSTITUTIONAL_LEVEL)){
            sb.append(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix())
                     .append(".").append(modifyOrganizationName(institutionName));
        }else if(StringUtils.equals(level, INSTITUTION_DEFINED_STUDY_LEVEL)){
            sb.append(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix())
                    .append(".").append(modifyOrganizationName(institutionName))
                    .append(".").append(modifyStudyName(studyShortTitle));
        }else if (RuleType.FIELD_LEVEL_RULES.getName().equals(level)){
            sb.append(CategoryConfiguration.CAAERS_BASE.getPackagePrefix());
        }

        sb.append(".").append(RuleUtil.getStringWithoutSpaces(ruleSetName));

        if(log.isDebugEnabled()){
            log.debug("level : " + level);
            log.debug(" sponsorName : " + sponsorName);
            log.debug("institutionName:" + institutionName);
            log.debug("studyShortTitle:" + studyShortTitle);
            log.debug("ruleSetName : " + ruleSetName);
            log.debug("Package name : " + sb.toString());
        }

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
     * Will parse and return the level, given a package name. 
     * @param packageName
     * @return
     */
    public String parseRuleLevel(String packageName){

      String prefix = StringUtils.substringBefore(packageName , ".ORG_");
        
      if(StringUtils.equals(prefix, CategoryConfiguration.SPONSOR_BASE.getPackagePrefix())){
         return SPONSOR_LEVEL;
      }

      if(StringUtils.equals(prefix, CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix())){
         return SPONSOR_DEFINED_STUDY_LEVEL;
      }

      if(StringUtils.equals(prefix, CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix())){
         return INSTITUTIONAL_LEVEL;
      }
        
      if(StringUtils.equals(prefix, CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix())){
         return INSTITUTION_DEFINED_STUDY_LEVEL;
      }
        
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

        RuleSet r1;

        // lookup the Sponsor Study level Rules
        String packageName = constructPackageName(SPONSOR_DEFINED_STUDY_LEVEL, aeReport.getStudy().getPrimaryFundingSponsorOrganization().getId().toString(),
                null, aeReport.getStudy().getId().toString(), RuleType.REPORT_SCHEDULING_RULES.getName());
        r1 = getRuleSetByPackageName(packageName);

        // if there is no Sponsor Study level RuleSet, check Sponsor level Rules
        if (r1 == null) {
            packageName = constructPackageName(SPONSOR_LEVEL, aeReport.getStudy().getPrimaryFundingSponsorOrganization().getId().toString(),
                null, null, RuleType.REPORT_SCHEDULING_RULES.getName());
            r1 = getRuleSetByPackageName(packageName);
        }
        if (r1 != null) rs.add(r1);


        //find institution based or institution based study rule sets.

        StudySite assignmentStudySite = aeReport.getReportingPeriod().getAssignment().getStudySite();
        packageName = constructPackageName(INSTITUTION_DEFINED_STUDY_LEVEL, null,
                assignmentStudySite.getOrganization().getId().toString(), aeReport.getStudy().getId().toString(), RuleType.REPORT_SCHEDULING_RULES.getName());
        r1 = getRuleSetByPackageName(packageName);

        // if there is no Institution Study level RuleSet, check Institution level Rules
        if(r1 == null){
            packageName = constructPackageName(INSTITUTIONAL_LEVEL, null,
                assignmentStudySite.getOrganization().getId().toString(), null, RuleType.REPORT_SCHEDULING_RULES.getName());
            r1 = getRuleSetByPackageName(packageName);
        }
        if (r1 != null) rs.add(r1);
        
        return rs;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }



    /**
     * This method will retrive the ruleset identified by package name.
     * @param packageName
     * @return
     */
    public RuleSet getRuleSetByPackageName(String packageName){
        return getRuleSetByPackageName(packageName, false) ;
    }


    /**
     * This method will retrive the ruleset identified by package name.
     * @param packageName
     * @param cached - If true will return from cache
     * @return
     */
    public RuleSet getRuleSetByPackageName(String packageName, boolean cached){
        RuleSet ruleSet = ruleAuthoringService.getRuleSet(packageName, cached);
        cleanRuleSet(ruleSet);
        makeRuleSetReadable(ruleSet);
        return ruleSet;
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
     * @param fromCache - if true will be retrieved from cache.
     * @return
     */
    public RuleSet getFieldRuleSet(String rulesetName, boolean fromCache){
        String packageName = RuleUtil.getPackageName(CategoryConfiguration.CAAERS_BASE.getPackagePrefix(), null, rulesetName);
        return getRuleSetByPackageName(packageName, fromCache);
    }

    /**
     * Will retrieve a ruleset from the repository based on the unique ID of the ruleset.
     * @param ruleSetId
     * @return
     */
    public RuleSet getRuleSetById(String ruleSetId){

      //the only way is to get all the rulesets, then return the matching one.
      List<RuleSet> ruleSets = ruleAuthoringService.getAllRuleSets();
      RuleSet rs = null;
      for(RuleSet ruleSet : ruleSets){
          if(ruleSet.getId().equals(ruleSetId)){
            rs = ruleSet;
            break;
          }
      }

      //set the meta-data in the rule set.
      if(rs != null){
          String packageName = rs.getName();
          rs.setLevel(parseRuleLevel(packageName));
          rs.setOrganization(parseOrganizationId(packageName));
          rs.setStudy(parseStudyId(packageName));

      }

      return rs;

    }

    /**
     * Will retrieve all the rule sets.
     * Note: The default ruleset created by rules engine is removed from the list, as it is not requrired for the caAERS.  
     * @return
     */
    public List<RuleSet> getAllRuleSets(){
       List<RuleSet> ruleSets = ruleAuthoringService.getAllRuleSets();
       List<RuleSet> allRuleSets = new ArrayList<RuleSet>();

       for(RuleSet ruleSet : ruleSets){

           if (ruleSet.getDescription().equals("The default rule package")) continue;

           allRuleSets.add(ruleSet);

           //populate the other attributes like Organization, level, Study etc. 
           String[] subjectParts = StringUtils.split(ruleSet.getSubject(), "||");
           if(subjectParts.length < 4) continue;

           String levelCode = subjectParts[1].trim();
           String orgNCICode = subjectParts[2].trim();
           String studyPrimaryIDValue = subjectParts[4].trim();
           ruleSet.setOrganization(orgNCICode);
           ruleSet.setStudy(studyPrimaryIDValue);

           for(RuleLevel rl : RuleLevel.values()){
              if(StringUtils.equals(rl.getName(), levelCode)){
                   ruleSet.setLevel(rl.getDescription());
              }
           }
           
       }
       return allRuleSets;
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

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }
}
