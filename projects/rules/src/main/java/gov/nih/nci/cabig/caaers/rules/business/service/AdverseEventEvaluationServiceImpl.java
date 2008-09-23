package gov.nih.nci.cabig.caaers.rules.business.service;

import static gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration.INSTITUTION_BASE;
import static gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE;
import static gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration.SPONSOR_BASE;
import static gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.rules.exception.RuleException;
import gov.nih.nci.cabig.caaers.rules.exception.RuleSetNotFoundException;
import gov.nih.nci.cabig.caaers.rules.objectgraph.FactResolver;
import gov.nih.nci.cabig.caaers.rules.runtime.BusinessRulesExecutionService;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class AdverseEventEvaluationServiceImpl implements AdverseEventEvaluationService {

    // Replace with spring injection
    private BusinessRulesExecutionService businessRulesExecutionService;

    private RulesEngineService rulesEngineService;

    public static final String CAN_NOT_DETERMINED = "CAN_NOT_DETERMINED";

    public static final String SERIOUS_ADVERSE_EVENT = "SERIOUS_ADVERSE_EVENT";

    private static final Log log = LogFactory.getLog(AdverseEventEvaluationServiceImpl.class);

    /**
     * This method will asses adverse event and will return one of the following vlue 1. Routine AE
     * 2. SAE 3. Can't be determined Calling this method again and again will not affect the rules
     * firing adversly as nothing gets fires subsequently
     * 
     * fire the rules at sponsor defined defined study level.. if not rules specified , then fire
     * sponsor level rules.
     * 
     */
    public String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception {

        ExpeditedAdverseEventReport aer = ae.getReport();

        String message = evaluateSponsorTarget(ae, study, null, RuleType.REPORT_SCHEDULING_RULES
                        .getName(), aer);

        if (!message.equals(CAN_NOT_DETERMINED)) {
            if (message.indexOf("IGNORE") < 0) {
                return SERIOUS_ADVERSE_EVENT;
            }
        }

        for (StudyOrganization so : study.getStudyOrganizations()) {
            message = evaluateInstitutionTarget(ae, study, so.getOrganization(), null,
                            RuleType.REPORT_SCHEDULING_RULES.getName(), aer);
            if (!message.equals(CAN_NOT_DETERMINED)) {
                if (message.indexOf("IGNORE") < 0) {
                    return SERIOUS_ADVERSE_EVENT;
                }
            }
        }
        return CAN_NOT_DETERMINED;
    }

    public Map<String, List<String>> evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport, List<AdverseEvent> aes,
    		Study study) throws Exception {

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        //List<AdverseEvent> aes = aeReport.getAdverseEvents();
        List<String> reportDefinitionsForSponsor = new ArrayList<String>();

        // boolean ignore = false ;

        for (AdverseEvent ae : aes) {
            String message = evaluateSponsorTarget(ae, study, null,
                            RuleType.REPORT_SCHEDULING_RULES.getName(), aeReport);

            System.out.println("message is " + message);

            if (!message.equals(CAN_NOT_DETERMINED)) {

                if (message.indexOf("IGNORE") < 0) {
                    // add the report definitions to the map
                    String[] messages = RuleUtil
                                    .charSeparatedStringToStringArray(message, "\\|\\|");
                    for (int i = 0; i < messages.length; i++) {
                        reportDefinitionsForSponsor.add(messages[i]);
                    }
                }

            }
        }

        map.put(study.getPrimaryFundingSponsorOrganization().getName(),
                        reportDefinitionsForSponsor);

        //Study study = aeReport.getStudy();

        // TO-DO get orgs like FDA, CALGB and add to this list

        for (StudyOrganization so : study.getStudyOrganizations()) {
            List<String> reportDefinitionsForInstitution = new ArrayList<String>();

            for (AdverseEvent ae : aes) {
                String message = evaluateInstitutionTarget(ae, study, so.getOrganization(), null,
                                RuleType.REPORT_SCHEDULING_RULES.getName(), aeReport);

                if (!message.equals(CAN_NOT_DETERMINED)) {

                    if (message.indexOf("IGNORE") < 0) {
                        // add the report definitions to the map
                        String[] messages = RuleUtil.charSeparatedStringToStringArray(message,
                                        "\\|\\|");
                        for (int i = 0; i < messages.length; i++) {
                            reportDefinitionsForInstitution.add(messages[i]);
                        }
                    }

                }
            }

            // chek for key
            List<String> existingList = map.get(so.getOrganization().getName());
            if (existingList != null) {
                reportDefinitionsForInstitution.addAll(existingList);
            }

            map.put(so.getOrganization().getName(), reportDefinitionsForInstitution);
        }

        return map;
    }

    public Collection<ExpeditedReportSection> mandatorySectionsForReport(ExpeditedAdverseEventReport aeReport, ReportDefinition reportDefinition)
                    throws Exception {
        List<AdverseEvent> adverseEvents = aeReport.getAdverseEvents();
        Study study = aeReport.getStudy();
        Set<String> sections = new HashSet<String>();
        String strSections = null;
        String[] sectionNames = null;

        // for every AE evaluate, sponsor & organization level rules
        for (AdverseEvent ae : adverseEvents) {

            // evaluate sponsor level rules
            strSections = evaluateSponsorTarget(ae, study, reportDefinition,
                            RuleType.MANDATORY_SECTIONS_RULES.getName(), aeReport);
            if (!strSections.equals(CAN_NOT_DETERMINED)) {
                sectionNames = RuleUtil.charSeparatedStringToStringArray(strSections, "\\|\\|");
                sections.addAll(Arrays.asList(sectionNames));
            }

            // evaluate organization level rules
            for (StudyOrganization so : study.getStudyOrganizations()) {
                strSections = evaluateInstitutionTarget(ae, study, so.getOrganization(),
                                reportDefinition, RuleType.MANDATORY_SECTIONS_RULES.getName(),
                                aeReport);
                if (!strSections.equals(CAN_NOT_DETERMINED)) {
                    sectionNames = RuleUtil.charSeparatedStringToStringArray(strSections, "\\|\\|");
                    sections.addAll(Arrays.asList(sectionNames));
                }
            }

        }

        if (log.isDebugEnabled()) {
            log.debug("Determined " + sections + " are mandatory for " + reportDefinition.getName());
        }

        return sectionNamesToSections(sections);
    }

    public Collection<ExpeditedReportSection> mandatorySections(ExpeditedAdverseEventReport aeReport, ReportDefinition... reportDefinitions) throws Exception {
        Set<ExpeditedReportSection> sections = new LinkedHashSet<ExpeditedReportSection>();
        if(reportDefinitions != null && reportDefinitions.length > 0){
        	for(ReportDefinition reportDefinition : reportDefinitions){
        		sections.addAll(mandatorySectionsForReport(aeReport, reportDefinition));
        	}
        }else{
        	for (Report report : aeReport.getReports()) {
                sections.addAll( mandatorySectionsForReport(aeReport, report.getReportDefinition()));
            }	
        }

        return sections;
    }

    private Collection<ExpeditedReportSection> sectionNamesToSections(
                    Collection<String> sectionNames) {
        List<ExpeditedReportSection> sections = new LinkedList<ExpeditedReportSection>();
        for (String sectionName : sectionNames) {
            ExpeditedReportSection section = ExpeditedReportSection.valueOf(sectionName);
            if (section == null) throw new CaaersSystemException(
                            "There is no ExpeditedReportSection named '"
                                            + sectionName
                                            + "'.  This probably indicates your mandatory section rules are out of sync with the software.");
            sections.add(section);
        }
        return sections;
    }

    /**
     * fire the rules at sponsor defined defined study level.. if not rules specified , then fire
     * sponsor level rules.
     * 
     */
    private String evaluateSponsorTarget(AdverseEvent ae, Study study,
                    ReportDefinition reportDefinition, String ruleTypeName,
                    ExpeditedAdverseEventReport aer) throws Exception {

        String sponsor_define_study_level_evaluation = null;
        String sponsor_level_evaluation = null;
        String final_result = null;

        /**
         * get and fire study level rules
         */
        sponsor_define_study_level_evaluation = sponsorDefinedStudyLevelRules(ae, study,
                        reportDefinition, ruleTypeName, aer);

        // if study level rule exist and null message...
        if (sponsor_define_study_level_evaluation == null) {
            return CAN_NOT_DETERMINED;

            // if study level rules not found , then get to sponsor rules..
        } else if (sponsor_define_study_level_evaluation.equals("no_rules_found")) {
            sponsor_level_evaluation = sponsorLevelRules(ae, study, reportDefinition, ruleTypeName,
                            aer);
            final_result = sponsor_level_evaluation;

            // if study level rules exist and returned a message..
        } else {
            final_result = sponsor_define_study_level_evaluation;
        }

        if (final_result == null || "no_rules_found".endsWith(final_result)) {
            final_result = CAN_NOT_DETERMINED;
        }

        return final_result;

    }

    private String evaluateInstitutionTarget(AdverseEvent ae, Study study,
                    Organization organization, ReportDefinition reportDefinition,
                    String ruleTypeName, ExpeditedAdverseEventReport aer) throws Exception {
        String institution_define_study_level_evaluation = null;
        String institution_level_evaluation = null;
        String final_result = null;

        /**
         * get and fire study level rules
         */
        institution_define_study_level_evaluation = institutionDefinedStudyLevelRules(ae, study,
                        organization, reportDefinition, ruleTypeName, aer);

        // if study level rule exist and null message...
        if (institution_define_study_level_evaluation == null) {
            return CAN_NOT_DETERMINED;

            // if study level rules not found , then get to sponsor rules..
        } else if (institution_define_study_level_evaluation.equals("no_rules_found")) {
            institution_level_evaluation = institutionLevelRules(ae, study, organization,
                            reportDefinition, ruleTypeName, aer);
            final_result = institution_level_evaluation;

            // if study level rules exist and returned a message..
        } else {
            final_result = institution_define_study_level_evaluation;
        }

        if (final_result == null || "no_rules_found".endsWith(final_result)) {
            final_result = CAN_NOT_DETERMINED;
        }

        return final_result;

    }

    /**
     * This method will evaluate the rules that are bound in the below URI. URI naming convention
     * :"gov.nih.nci.cabig.caaers.rules." + + <section_name>"
     */
    @SuppressWarnings(value = "unchecked")
    public ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,ExpeditedReportSection... sections) {

        // create the input objects
        ValidationErrors errors = new ValidationErrors();
        Study study = aeReport.getStudy();

        // 1. fire the rules
        List<Object> input = new ArrayList<Object>();
        input.add(aeReport);
        input.add(study);
        input.add(errors);
        
        for(ExpeditedReportSection section : sections){
	        // 2. fetch the bindUri
	        String bindURI = getBindURI(null, null, CategoryConfiguration.CAAERS_BASE, "reporting_" + section.name());
	
	        try {
	            List<Object> output = fireRules(input, bindURI);
	            if (output != null) {
	                for (Iterator it = output.iterator(); it.hasNext();) {
	                    Object o = it.next();
	                    if (o instanceof ValidationErrors) {
	                        errors = (ValidationErrors) o;
	                        break;
	                    }
	                }
	            }
	        } catch (RuleSetNotFoundException e) {
	            log.debug("There exist no reporting validation business rules for this AE, ignoring exception",e);
	        }
        }
        // 3. fetch the errors from
        return errors;
    }

    // RULE METHODS

    private String sponsorLevelRules(AdverseEvent ae, Study study,
                    ReportDefinition reportDefinition, String ruleTypeName,
                    ExpeditedAdverseEventReport aer) throws Exception {
        String message = null;
        String bindURI = getBindURI(study.getPrimaryFundingSponsorOrganization().getName(), "",
                        CategoryConfiguration.SPONSOR_BASE, ruleTypeName);

        RuleSet ruleSetForSponsor = rulesEngineService.getRuleSetForSponsor(ruleTypeName, study
                        .getPrimaryFundingSponsorOrganization().getName());

        if (ruleSetForSponsor == null) {
            return "no_rules_found";
            // throw new Exception("There are no rules configured for adverse event scheduling for
            // this sponsor!");
        }

        AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();

        try {
            evaluationForSponsor = this
                            .getEvaluationObject(ae, study, study
                                            .getPrimaryFundingSponsorOrganization(),
                                            reportDefinition, bindURI, aer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception(e.getMessage(), e);
        }

        message = evaluationForSponsor.getMessage();

        return message;

    }

    private String sponsorDefinedStudyLevelRules(AdverseEvent ae, Study study,
                    ReportDefinition reportDefinition, String ruleTypeName,
                    ExpeditedAdverseEventReport aer) throws Exception {
        String message = null;
        String bindURI = getBindURI(study.getPrimaryFundingSponsorOrganization().getName(), study
                        .getShortTitle(), CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE,
                        ruleTypeName);

        RuleSet ruleSetForSponsorDefinedStudy = rulesEngineService
                        .getRuleSetForSponsorDefinedStudy(ruleTypeName, study.getShortTitle(),
                                        study.getPrimaryFundingSponsorOrganization().getName());
        if (ruleSetForSponsorDefinedStudy == null) {
            return "no_rules_found";
            // throw new Exception("There are no rules configured for adverse event assesment for
            // this sponsor defined study!");
        }

        AdverseEventEvaluationResult evaluationForSponsorDefinedStudy = new AdverseEventEvaluationResult();

        try {
            evaluationForSponsorDefinedStudy = this
                            .getEvaluationObject(ae, study, study
                                            .getPrimaryFundingSponsorOrganization(),
                                            reportDefinition, bindURI, aer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception(e.getMessage(), e);
        }

        message = evaluationForSponsorDefinedStudy.getMessage();

        return message;

    }

    private String institutionDefinedStudyLevelRules(AdverseEvent ae, Study study,
                    Organization organization, ReportDefinition reportDefinition,
                    String ruleTypeName, ExpeditedAdverseEventReport aer) throws Exception {
        String message = null;

        String studyShortTitle = study.getShortTitle();
        String organizationName = organization.getName();

        String bindURI = getBindURI(organizationName, studyShortTitle,
                        CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE, ruleTypeName);

        RuleSet ruleSetForInstitutionDefinedStudy = rulesEngineService
                        .getRuleSetForInstitutionDefinedStudy(ruleTypeName, studyShortTitle,
                                        organizationName);
        if (ruleSetForInstitutionDefinedStudy == null) {
            return "no_rules_found";
            // throw new Exception("There are no rules configured for adverse event assesment for
            // this sponsor defined study!");
        }

        AdverseEventEvaluationResult evaluationForInstitutionDefinedStudy = new AdverseEventEvaluationResult();

        try {
            evaluationForInstitutionDefinedStudy = this.getEvaluationObject(ae, study,
                            organization, reportDefinition, bindURI, aer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception(e.getMessage(), e);
        }

        message = evaluationForInstitutionDefinedStudy.getMessage();

        return message;

    }

    private String institutionLevelRules(AdverseEvent ae, Study study, Organization organization,
                    ReportDefinition reportDefinition, String ruleTypeName,
                    ExpeditedAdverseEventReport aer) throws Exception {
        String message = null;

        String organizationName = organization.getName();
        String bindURI = getBindURI(organizationName, "", CategoryConfiguration.INSTITUTION_BASE,
                        ruleTypeName);
        RuleSet ruleSetForInstiution = rulesEngineService.getRuleSetForInstitution(ruleTypeName,
                        organizationName);

        if (ruleSetForInstiution == null) {
            return "no_rules_found";
            // throw new Exception("There are no rules configured for adverse event scheduling for
            // this sponsor!");
        }

        AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();

        try {
            evaluationForInstitution = this.getEvaluationObject(ae, study, organization,
                            reportDefinition, bindURI, aer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception(e.getMessage(), e);
        }

        message = evaluationForInstitution.getMessage();

        return message;
    }

    private String getBindURI(String sponsorOrInstitutionName, String studyName,
                    final CategoryConfiguration type, String ruleSetName) {
        // include the package name
        StringBuffer sb = new StringBuffer(type.getPackagePrefix());

        // include study name
        if (SPONSOR_DEFINED_STUDY_BASE.equals(type) || INSTITUTION_DEFINED_STUDY_BASE.equals(type)) {
            sb.append(".").append(RuleUtil.getStringWithoutSpaces(studyName));
        }

        // include sponsor/institution name
        if (SPONSOR_BASE.equals(type) || INSTITUTION_BASE.equals(type)
                        || SPONSOR_DEFINED_STUDY_BASE.equals(type)
                        || INSTITUTION_DEFINED_STUDY_BASE.equals(type)) {
            sb.append(".").append(RuleUtil.getStringWithoutSpaces(sponsorOrInstitutionName));
        }

        // always include the ruleSetName
        return sb.append(".").append(RuleUtil.getStringWithoutSpaces(ruleSetName)).toString();

    }

    private AdverseEventEvaluationResult getEvaluationObject(AdverseEvent ae, Study study,
                    Organization organization, ReportDefinition reportDefinition, String bindURI,
                    ExpeditedAdverseEventReport aer) throws Exception {
        // holder for the returned object
        AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();

        // add AE, Study, Organization, ReportDefinition, TreatmentInformation to the input
        List<Object> inputObjects = new ArrayList<Object>();
        inputObjects.add(ae);

        // add expedited report
        if (aer != null) {
            inputObjects.add(aer);
        }

        FactResolver f = new FactResolver();
        inputObjects.add(f);

        if (study != null) inputObjects.add(study);
        if (organization != null) inputObjects.add(organization);
        if (reportDefinition != null) inputObjects.add(reportDefinition);
        if (ae.getReport() != null && ae.getReport().getTreatmentInformation() != null) inputObjects
                        .add(ae.getReport().getTreatmentInformation());

        // fire the rules and AdverseEventEvaluationResult from the output.
        List<Object> outputObjects = fireRules(inputObjects, bindURI);
        if (outputObjects == null) {
            // no_rules_found
            evaluationForSponsor.setMessage("no_rules_found");
            return evaluationForSponsor;
        }
        for (Iterator<Object> it = outputObjects.iterator(); it.hasNext();) {
            Object o = it.next();
            if (o instanceof AdverseEventEvaluationResult) return (AdverseEventEvaluationResult) o;

        }

        // return the empty AdverseEventEvaluationResult.
        return evaluationForSponsor;

    }

    /**
     * Will fire the rules bound on the bindURI.
     * 
     * @param inputObjects
     * @param bindURI
     * @return
     * @throws Exception
     */
    private List<Object> fireRules(List<Object> inputObjects, String bindURI) {

        List<Object> outputObjects = null;
        try {
            outputObjects = businessRulesExecutionService.fireRules(bindURI, inputObjects);
        } catch (RuleException e) {
            throw e;
        } catch (Exception ex) {
            log.error("Unable to fire the rule : " + bindURI);
            log.error("Rule might have been be un deployed  , please look at the exception . ", ex);
            if (ex.getMessage().indexOf("local class incompatible") != -1) {
                throw new RuleException(ex.getMessage(), ex);
            }

        }

        return outputObjects;
    }

    // /Object Methods
    public BusinessRulesExecutionService getBusinessRulesExecutionService() {
        return businessRulesExecutionService;
    }

    @Required
    public void setBusinessRulesExecutionService(
                    BusinessRulesExecutionService businessRulesExecutionService) {
        this.businessRulesExecutionService = businessRulesExecutionService;
    }

    public RulesEngineService getRulesEngineService() {
        return rulesEngineService;
    }

    @Required
    public void setRulesEngineService(RulesEngineService rulesEngineService) {
        this.rulesEngineService = rulesEngineService;
    }

}
