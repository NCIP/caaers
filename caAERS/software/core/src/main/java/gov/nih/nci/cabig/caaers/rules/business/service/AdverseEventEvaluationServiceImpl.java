/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.api.BusinessRulesExecutionService;
import com.semanticbits.rules.exception.RuleException;
import com.semanticbits.rules.impl.RuleEvaluationResult;
import com.semanticbits.rules.objectgraph.FactResolver;
import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.SafetyRuleEvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.common.CaaersRuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.spi.AgendaFilter;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;

/**
 * This class is used by EvaluationService, to evaluate adverse events.
 * @author Sujith Vellat Thayyilthodi
 * @ahthor Visu Patlola
 * @author Vinay Kumar
 * @author Srini Akkala
 * @author Biju Joseph (Refactored)
 */
public class AdverseEventEvaluationServiceImpl implements AdverseEventEvaluationService {

    // Replace with spring injection
    private BusinessRulesExecutionService businessRulesExecutionService;

    private CaaersRulesEngineService caaersRulesEngineService;


    public static final String SERIOUS_ADVERSE_EVENT = CaaersRuleUtil.SERIOUS_ADVERSE_EVENT;

    private static final Log log = LogFactory.getLog(AdverseEventEvaluationServiceImpl.class);

    private RuleSet findRuleSet(List<RuleSet> ruleSets, RuleLevel level, Integer orgId, Integer studyId){
        if(ruleSets == null || ruleSets.isEmpty()) return null;
        for(RuleSet rs : ruleSets){
            if(!rs.getOrganization().getId().equals(orgId)) continue;
            if(level != rs.getRuleLevel()) continue;
            if(level == RuleLevel.Sponsor || level == RuleLevel.Institution) return rs;
            if(rs.getStudy() == null) continue;
            if(rs.getStudy().getId().equals(studyId)) return rs;
        }
        return  null;
    }

    /**
     * Evaluate the Rules defined in the context of the report and for the specified adverse events.
     *
     * @param aeReport  - The ExpeditedAdverseEventReport  in context
     * @param aes    - The list of AdverseEvent  to evaluate
     * @param study     - The Study in context
     * @return
     * @throws Exception
     */
    public Map<AdverseEvent, List<AdverseEventEvaluationResult>> evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport, List<AdverseEvent> aes, Study study) throws Exception {

        Map<AdverseEvent, List<AdverseEventEvaluationResult>> map = new HashMap<AdverseEvent, List<AdverseEventEvaluationResult>>();

        List<AdverseEventEvaluationResult> adverseEventEvaluationResults;

        Organization sponsorOrg = study.getPrimaryFundingSponsor().getOrganization();
        Organization coordinatingOrg = study.getStudyCoordinatingCenter().getOrganization();
        Organization siteOrg = study.findStudyOrganizationById(aes.get(0).getReportingPeriod().getStudySite().getId()).getOrganization();
        Set<Integer> orgIdSet = new HashSet<Integer>();
        orgIdSet.add(sponsorOrg.getId());
        orgIdSet.add(coordinatingOrg.getId());
        orgIdSet.add(siteOrg.getId());

        RuleSetQuery query = new RuleSetQuery();
        query.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
        query.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
        query.filterByOrganizationId(orgIdSet);


        List<gov.nih.nci.cabig.caaers.domain.RuleSet> ruleSets = caaersRulesEngineService.searchRuleSets(query);
        RuleSet sponsorStudyLevelRuleSet = findRuleSet(ruleSets, RuleLevel.SponsorDefinedStudy, sponsorOrg.getId(), study.getId());
        RuleSet sponsorLevelRuleSet = findRuleSet(ruleSets, RuleLevel.Sponsor, sponsorOrg.getId(), study.getId());
        RuleSet coordinatingOrgStudyLevelRuleSet = findRuleSet(ruleSets, RuleLevel.InstitutionDefinedStudy, coordinatingOrg.getId(), study.getId());
        RuleSet coordinatingOrgLevelRuleSet = findRuleSet(ruleSets, RuleLevel.Institution, coordinatingOrg.getId(), study.getId());
        RuleSet siteOrgStudyLevelRuleSet = findRuleSet(ruleSets, RuleLevel.InstitutionDefinedStudy, siteOrg.getId(), study.getId());
        RuleSet siteOrgLevelRuleSet = findRuleSet(ruleSets, RuleLevel.Institution, siteOrg.getId(), study.getId());

        for (AdverseEvent ae : aes) {
        	
        	//try to get the existing list of report def names
        	adverseEventEvaluationResults = map.get(ae);
        	if(adverseEventEvaluationResults == null){
        		adverseEventEvaluationResults = new ArrayList<AdverseEventEvaluationResult>();
        		map.put(ae, adverseEventEvaluationResults);
        	}
        	
        	//evaluate sponsor rules
            AdverseEventEvaluationResult message = evaluateSponsorTarget(ae, study, null, aeReport, sponsorLevelRuleSet, sponsorStudyLevelRuleSet);
            adverseEventEvaluationResults.add(message);

            if(coordinatingOrgLevelRuleSet != null || coordinatingOrgStudyLevelRuleSet != null){
                message = evaluateInstitutionTarget(ae, study, coordinatingOrg, null,  aeReport, coordinatingOrgLevelRuleSet,coordinatingOrgStudyLevelRuleSet );
                adverseEventEvaluationResults.add(message);
            }

            if(siteOrgLevelRuleSet != null || siteOrgStudyLevelRuleSet != null){
                message = evaluateInstitutionTarget(ae, study, siteOrg, null,  aeReport, siteOrgLevelRuleSet,siteOrgStudyLevelRuleSet );
                adverseEventEvaluationResults.add(message);
            }


        }


        return map;
    }
    
    /**
     * The mandatory sections associated with the report.
     * @param aeReport  - The ExpeditedAdverseEventReport in context
     * @param reportDefinition  - The ReportDefinitions whose mandatory sections to be considered.
     * @return
     * @throws Exception
     */
    public Collection<ExpeditedReportSection> mandatorySectionsForReport(ExpeditedAdverseEventReport aeReport, ReportDefinition reportDefinition) throws Exception {
        List<AdverseEvent> adverseEvents = aeReport.getAdverseEvents();
        Study study = aeReport.getStudy();
        Set<String> sections = new HashSet<String>();
        AdverseEventEvaluationResult adverseEventEvaluationResult = null;

        Organization reportOwnerOrg = reportDefinition.getOrganization();
        RuleSetQuery query = new RuleSetQuery();
        query.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
        query.filterByRuleType(RuleType.MANDATORY_SECTIONS_RULES);
        query.filterByOrganizationId(reportOwnerOrg.getId());


        List<gov.nih.nci.cabig.caaers.domain.RuleSet> ruleSets = caaersRulesEngineService.searchRuleSets(query);
        RuleSet sponsorStudyLevelRuleSet = findRuleSet(ruleSets, RuleLevel.SponsorDefinedStudy, reportOwnerOrg.getId(), study.getId());
        RuleSet sponsorLevelRuleSet = findRuleSet(ruleSets, RuleLevel.Sponsor, reportOwnerOrg.getId(), study.getId());
        if(sponsorStudyLevelRuleSet != null){
            if(log.isInfoEnabled()) log.info("Evaluating mandatory section rules @ sponsor study level [bindURI:" + sponsorStudyLevelRuleSet.getRuleBindURI() + "]");
            adverseEventEvaluationResult = getEvaluationObject(study, reportOwnerOrg, reportDefinition, sponsorStudyLevelRuleSet.getRuleBindURI(), aeReport, adverseEvents.toArray(new AdverseEvent[0]));
            if (!adverseEventEvaluationResult.isCannotDetermine()) {
                sections.addAll(adverseEventEvaluationResult.getRuleEvaluationResult().getResponses());
            }
        }else if(sponsorLevelRuleSet != null){
            if(log.isInfoEnabled()) log.info("Evaluating mandatory section rules @ sponsor level [bindURI:" + sponsorLevelRuleSet.getRuleBindURI() + "]");
            adverseEventEvaluationResult = getEvaluationObject(study, reportOwnerOrg, reportDefinition, sponsorLevelRuleSet.getRuleBindURI(), aeReport, adverseEvents.toArray(new AdverseEvent[0]));
            if (!adverseEventEvaluationResult.isCannotDetermine()) {
                sections.addAll(adverseEventEvaluationResult.getRuleEvaluationResult().getResponses());
            }
        }


        RuleSet siteOrgStudyLevelRuleSet = findRuleSet(ruleSets, RuleLevel.InstitutionDefinedStudy, reportOwnerOrg.getId(), study.getId());
        RuleSet siteOrgLevelRuleSet = findRuleSet(ruleSets, RuleLevel.Institution, reportOwnerOrg.getId(), study.getId());
        if(siteOrgStudyLevelRuleSet != null){
            if(log.isInfoEnabled()) log.info("Evaluating mandatory section rules @ institution study level [bindURI:" + siteOrgStudyLevelRuleSet.getRuleBindURI() + "]");
            adverseEventEvaluationResult = getEvaluationObject(study, reportOwnerOrg, reportDefinition, siteOrgStudyLevelRuleSet.getRuleBindURI(), aeReport, adverseEvents.toArray(new AdverseEvent[0]));
            if (!adverseEventEvaluationResult.isCannotDetermine()) {
                sections.addAll(adverseEventEvaluationResult.getRuleEvaluationResult().getResponses());
            }
        }else if(siteOrgLevelRuleSet != null){
            if(log.isInfoEnabled()) log.info("Evaluating mandatory section rules @ institution level [bindURI:" + siteOrgLevelRuleSet.getRuleBindURI() + "]");
            adverseEventEvaluationResult = getEvaluationObject(study, reportOwnerOrg, reportDefinition, siteOrgLevelRuleSet.getRuleBindURI(), aeReport, adverseEvents.toArray(new AdverseEvent[0]));
            if (!adverseEventEvaluationResult.isCannotDetermine()) {
                sections.addAll(adverseEventEvaluationResult.getRuleEvaluationResult().getResponses());
            }
        }

        if (log.isInfoEnabled()) {
            log.info("Determined " + sections + " are mandatory for " + reportDefinition.getName());
        }

        return sectionNamesToSections(sections);
    }
    
    /**
     * This method will find the mandatory sections defined, in the mandatory-section-rules, for a given report definition. 
     * Case 1:  If report definitions is empy, the {@link ExpeditedAdverseEventReport} is scanned for active {@link Report}s, the {@link ReportDefinition} associated
     * to each such report is considered. 
     * Case 2: If {@link ReportDefinition}s are passed explicitly, then will work against the report definitions instead. 
     */
    public Collection<ExpeditedReportSection> mandatorySections(ExpeditedAdverseEventReport aeReport, ReportDefinition... reportDefinitions) throws Exception {
        Set<ExpeditedReportSection> sections = new LinkedHashSet<ExpeditedReportSection>();
        if(reportDefinitions != null && reportDefinitions.length > 0){
        	for(ReportDefinition reportDefinition : reportDefinitions){
        		sections.addAll(mandatorySectionsForReport(aeReport, reportDefinition));
        	}
        }else{
        	for (Report report : aeReport.getReports()) {
        		if(report.isActive()) sections.addAll( mandatorySectionsForReport(aeReport, report.getReportDefinition()));
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
     * fire the rules at sponsor defined defined study level.. if no rules specified , then fire
     * sponsor level rules.
     * 
     */
    private AdverseEventEvaluationResult evaluateSponsorTarget(AdverseEvent ae, Study study, ReportDefinition reportDefinition, ExpeditedAdverseEventReport aer, RuleSet sponsorLevelRuleSet, RuleSet sponsorStudyLevelRuleSet) throws Exception {


        AdverseEventEvaluationResult sponsor_define_study_level_evaluation = null;
        AdverseEventEvaluationResult sponsor_level_evaluation = null;
        AdverseEventEvaluationResult final_result = null;

        /**
         * get and fire study level rules
         */
        sponsor_define_study_level_evaluation = sponsorDefinedStudyLevelRules(ae, study, reportDefinition, aer, sponsorStudyLevelRuleSet);

        // if study level rule exist and null message...
        if (sponsor_define_study_level_evaluation == null) {
            return AdverseEventEvaluationResult.cannotDetermine(null);

            // if study level rules not found , then get to sponsor rules..
        } else if (sponsor_define_study_level_evaluation.isNoRulesFound()) {
            sponsor_level_evaluation = sponsorLevelRules(ae, study, reportDefinition, aer, sponsorLevelRuleSet);
            final_result = sponsor_level_evaluation;
            // if study level rules exist and returned a message..
        } else {
            final_result = sponsor_define_study_level_evaluation;
        }

        if (final_result == null) {
            final_result = AdverseEventEvaluationResult.cannotDetermine(null);
        } else if(final_result.isNoRulesFound()) {
            String ruleMetadata = final_result.getRuleMetadata();
            final_result = AdverseEventEvaluationResult.cannotDetermine(null);
            final_result.setRuleMetadata(ruleMetadata);
        }

        return final_result;

    }

    private AdverseEventEvaluationResult evaluateInstitutionTarget(AdverseEvent ae, Study study,
                                                                   Organization organization, ReportDefinition reportDefinition,
                                                                   ExpeditedAdverseEventReport aer, RuleSet institutionLevelRuleSet, RuleSet institutionStudyLevelRuleSet) throws Exception {
        AdverseEventEvaluationResult institution_define_study_level_evaluation = null;
        AdverseEventEvaluationResult institution_level_evaluation = null;
        AdverseEventEvaluationResult final_result = null;

        /**
         * get and fire study level rules
         */
        institution_define_study_level_evaluation = institutionDefinedStudyLevelRules(ae, study, organization, reportDefinition, aer, institutionStudyLevelRuleSet);

        // if study level rule exist and null message...
        if (institution_define_study_level_evaluation == null) {
            return AdverseEventEvaluationResult.cannotDetermine(null);

            // if study level rules not found , then get to sponsor rules..
        } else if (institution_define_study_level_evaluation.isNoRulesFound()) {
            institution_level_evaluation = institutionLevelRules(ae, study, organization, reportDefinition, aer, institutionLevelRuleSet);
            final_result = institution_level_evaluation;
            // if study level rules exist and returned a message..
        } else {
            final_result = institution_define_study_level_evaluation;
        }

        if (final_result == null) {
            final_result = AdverseEventEvaluationResult.cannotDetermine(null);
        } else if(final_result.isNoRulesFound()){
            String ruleMetadata = final_result.getRuleMetadata();
            final_result = AdverseEventEvaluationResult.cannotDetermine(null);
            final_result.setRuleMetadata(ruleMetadata);
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
	        String bindURI = "gov.nih.nci.cabig.caaers.rules.reporting_" + CaaersRuleUtil.getStringWithoutSpaces(section.name());
	
	        try {
	            List<Object> output = businessRulesExecutionService.fireRules(bindURI, input);
	            if (output != null) {
	                for (Iterator it = output.iterator(); it.hasNext();) {
	                    Object o = it.next();
	                    if (o instanceof ValidationErrors) {
	                        errors = (ValidationErrors) o;
	                        break;
	                    }
	                }
	            }
	        } catch (RuleException e) {
	            log.debug("There exist no reporting validation business rules for this AE, ignoring exception",e);
	        }
        }
        // 3. fetch the errors from
        return errors;
    }


    /**
     * Will evaluate the rules defined at Sponsor level. 
     * @param ae - AdverseEvent to evaluate
     * @param study - Study on which the AdverseEvent occured
     * @param reportDefinition - The Reoprt in context
     * @param aer - The ExpeditedAdverseEventReport in context
     * @param sponsorLevelRuleSet - The ruleset defined by sponsor
     * @return  - the evaluation result.
     * @throws Exception
     */
    private AdverseEventEvaluationResult sponsorLevelRules(AdverseEvent ae, Study study,
                    ReportDefinition reportDefinition, 
                    ExpeditedAdverseEventReport aer, RuleSet sponsorLevelRuleSet) throws Exception {
        String bindURI = sponsorLevelRuleSet != null ? sponsorLevelRuleSet.getRuleBindURI() : null;

        if (bindURI == null) {
            return AdverseEventEvaluationResult.noRulesFound(bindURI).populateRuleMetaData(RuleLevel.Sponsor, study.getPrimaryFundingSponsorOrganization(), null);
        }


        try {
            return  this.getEvaluationObject(study, study.getPrimaryFundingSponsorOrganization(), reportDefinition, bindURI, aer, ae)
                    .populateRuleMetaData(RuleLevel.Sponsor, study.getPrimaryFundingSponsorOrganization(), null);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }


    }

    /**
     * Will evaluate the Sponsor defined Study level rules.
     * @param ae - AdverseEvent to evaluate
     * @param study - Study on which the AdverseEvent occured
     * @param reportDefinition - The Reoprt in context
     * @param aer - The ExpeditedAdverseEventReport in context
     * @param sponsorStudyLevelRuleSet - The ruleset by sponsor at study level.
     * @return  - the evaluation result. 
     * @throws Exception
     */
    private AdverseEventEvaluationResult sponsorDefinedStudyLevelRules(AdverseEvent ae, Study study, ReportDefinition reportDefinition,ExpeditedAdverseEventReport aer, RuleSet sponsorStudyLevelRuleSet) throws Exception {


        //check if sponsor-study has rules ?
        String bindURI = sponsorStudyLevelRuleSet != null ? sponsorStudyLevelRuleSet.getRuleBindURI() : null;

        if(bindURI == null)  return AdverseEventEvaluationResult.noRulesFound(bindURI).populateRuleMetaData(RuleLevel.SponsorDefinedStudy, study.getPrimaryFundingSponsorOrganization(), study);

        //evaluate the rules. 

        try {
            return this.getEvaluationObject( study, study.getPrimaryFundingSponsorOrganization(), reportDefinition, bindURI, aer, ae)
                    .populateRuleMetaData(RuleLevel.SponsorDefinedStudy, study.getPrimaryFundingSponsorOrganization(), study);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }

    }

    /**
     * Will evalate the institution defined study level rules. 
     * @param ae - AdverseEvent to evaluate
     * @param study - Study on which the AdverseEvent occured
     * @param organization - The site on which AE occured. 
     * @param reportDefinition - The Reoprt in context
     * @param aer - The ExpeditedAdverseEventReport in context
     * @param institutionStudyLevelRuleSet - The ruleset
     * @return  - the evaluation result.
     * @throws Exception
     */
    private AdverseEventEvaluationResult institutionDefinedStudyLevelRules(AdverseEvent ae, Study study,
                    Organization organization, ReportDefinition reportDefinition,
                     ExpeditedAdverseEventReport aer, RuleSet institutionStudyLevelRuleSet) throws Exception {


        String bindURI = institutionStudyLevelRuleSet != null ? institutionStudyLevelRuleSet.getRuleBindURI() : null;
        if (bindURI == null) {
            return AdverseEventEvaluationResult.noRulesFound(bindURI).populateRuleMetaData(RuleLevel.InstitutionDefinedStudy, organization, study);
        }


        try {
            return this.getEvaluationObject( study, organization, reportDefinition, bindURI, aer, ae).populateRuleMetaData(RuleLevel.InstitutionDefinedStudy, organization, study);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }


    }

    /**
     * Will evaluate the institution level rules. 
     * @param ae - AdverseEvent to evaluate
     * @param study - Study on which the AdverseEvent occured
     * @param organization - The site on which AE occured.
     * @param reportDefinition - The Reoprt in context
     * @param aer - The ExpeditedAdverseEventReport in context
     * @param institutionLevelRuleSet - The rule set
     * @return  - the evaluation result.
     * @throws Exception
     */
    private AdverseEventEvaluationResult institutionLevelRules(AdverseEvent ae, Study study, Organization organization,
                    ReportDefinition reportDefinition,
                    ExpeditedAdverseEventReport aer, RuleSet institutionLevelRuleSet) throws Exception {

        String bindURI = institutionLevelRuleSet != null ? institutionLevelRuleSet.getRuleBindURI() : null;
        if(bindURI == null) return AdverseEventEvaluationResult.noRulesFound(bindURI).populateRuleMetaData(RuleLevel.Institution, organization, null);


        try {
            return this.getEvaluationObject(study, organization,  reportDefinition, bindURI, aer, ae).populateRuleMetaData(RuleLevel.Institution, organization, null);
        } catch (Exception e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }

    }


    /*
     * Generate the input objects for the rules engine. 
     */
    private List<Object> generateInput(ExpeditedAdverseEventReport aeReport, ReportDefinition reportDefinition, Study study, Organization org, AdverseEvent... aes){
        List<Object> inputObjects = new ArrayList<Object>();

        if(aeReport != null) inputObjects.add(aeReport);
        if(reportDefinition != null) inputObjects.add(reportDefinition);
        if(aes != null) {
            for(AdverseEvent ae : aes) inputObjects.add(ae);
        }
        if(study != null) inputObjects.add(study);
        if(org != null) inputObjects.add(org);

        //add treatment information
        ExpeditedAdverseEventReport theAeReport = aeReport != null ? aeReport : (  (aes != null && aes.length > 0) ? aes[0].getReport()  : null);
        if(theAeReport != null){
            if(theAeReport.getTreatmentInformation() != null) inputObjects.add(theAeReport.getTreatmentInformation());
        }

        //add a fact resolver by default
        FactResolver f = new FactResolver();
        inputObjects.add(f);

        return inputObjects;
    }

    /**
     * Will fire the rules and get the evaluation object
     * @param aes
     * @param study
     * @param organization
     * @param reportDefinition
     * @param bindURI
     * @param aer
     * @return
     * @throws Exception
     */
    private AdverseEventEvaluationResult getEvaluationObject(Study study, Organization organization, ReportDefinition reportDefinition, String bindURI,
                    ExpeditedAdverseEventReport aer, AdverseEvent... aes) throws Exception {
        // holder for the returned object
        AdverseEventEvaluationResult eventEvaluationResult = new AdverseEventEvaluationResult();


        // fire the rules and AdverseEventEvaluationResult from the output.
        RuleEvaluationResult ruleEvaluationResult = fireRules(generateInput(aer, reportDefinition, study, organization, aes), bindURI);
        eventEvaluationResult.setRuleEvaluationResult(ruleEvaluationResult);

        return eventEvaluationResult;

    }

    /**
     * Will fire the rules bound on the bindURI.
     * 
     * @param inputObjects
     * @param bindURI
     * @return
     * @throws Exception
     */
    private RuleEvaluationResult fireRules(List<Object> inputObjects, String bindURI , AgendaFilter... agendaFilter) {

        List<Object> outputObjects = null;
        try {

            outputObjects = businessRulesExecutionService.fireRules(bindURI, inputObjects, agendaFilter);

        } catch (RuleException e){
        	log.debug("No rule registered under :" + bindURI, e);
            throw e;
        } catch (Exception ex) {
            log.error("Unable to fire the rule : " + bindURI);
            log.error("Rule might have been be un deployed  , please look at the exception . ", ex);
            throw new CaaersSystemException(ex.getMessage(), ex);
        }

        for(Object o : outputObjects) {
            if (o instanceof RuleEvaluationResult){
               return (RuleEvaluationResult) o;
            }
        }

        return null;
    }



    /**
     * Will evaluate the field level rule.
     * @param bindURL  - The url where the field level rule is registered
     * @param ruleNames  - The rule name
     * @param inputObjects - The input objects to evaluate.
     * @return
     */
    public String evaluateFieldLevelRules(String bindURL, String ruleNames, List<Object> inputObjects) {

        StringBuilder sb = new StringBuilder();
        List<Object> inputList = new ArrayList(inputObjects);
        try{

           if(StringUtils.isNotEmpty(bindURL) && StringUtils.isNotEmpty(ruleNames)){
                String result = null;
                //add the rule name agenda & fact resolver.
                String[] ruleNamesArray = StringUtils.split(ruleNames, ',');
                inputList.add(new FactResolver());
                result = fireRules( inputList, bindURL , RuleUtil.createRuleNameEqualsAgendaFilter(ruleNamesArray)).getMessage();

                if(result != null && result.length() > 0){
                    if(sb.length() > 0) sb.append("||");
                    sb.append(result);
                }
           }
           
        }catch(Exception e){
            log.warn("Error while evaluating field rules", e);
            log.warn("Due to error evaluating fields rules setting the return value as  : OPTIONAL"); 
        }

        if(sb.length() > 0) return sb.toString();

        return "OPTIONAL";
    }
    
    public String fetchBindURI(RuleType ruleType, RuleLevel level, Integer orgId, Integer studyId){
        RuleSetQuery query = new RuleSetQuery();
        query.filterByStatus(gov.nih.nci.cabig.caaers.domain.RuleSet.STATUS_ENABLED);
        query.filterByRuleType(ruleType);
        if(level != null){
            query.filterByRuleLevel(level);
            if(orgId != null) query.filterByOrganizationId(orgId);
            if(studyId != null && level.isStudyBased()) query.filterByStudyId(studyId);
        }
        if(ruleType == RuleType.SAFETY_SIGNALLING_RULES) {
            query.filterByStudyId(studyId);
        }

        
        List<gov.nih.nci.cabig.caaers.domain.RuleSet> ruleSets = caaersRulesEngineService.searchRuleSets(query);
        if(ruleSets == null || ruleSets.isEmpty()) return null;
        return ruleSets.get(0).getRuleBindURI();
        
    }

    public SafetyRuleEvaluationResultDTO evaluateSafetySignallingRules(ObservedAdverseEventProfile observedAEProfile) {
        SafetyRuleEvaluationResultDTO result = new SafetyRuleEvaluationResultDTO();
        result.setNotificationStatus(NotificationStatus.IGNORE);

        String bindURI = fetchBindURI(RuleType.SAFETY_SIGNALLING_RULES, null, null, observedAEProfile.getTreatmentAssignment().getStudy().getId());
        if(bindURI != null){
            ArrayList<Object> inputObjects = new ArrayList<Object>();
            inputObjects.add(observedAEProfile);
            RuleEvaluationResult ruleEvaluationResult = fireRules( inputObjects, bindURI);
            
            result.setRulesMatched(ruleEvaluationResult.getRulesMatched());
            if(ruleEvaluationResult.getResponses().contains(NotificationStatus.DO_NOT_NOTIFY.name())){
                result.setNotificationStatus(NotificationStatus.DO_NOT_NOTIFY);
            }else if(ruleEvaluationResult.getResponses().contains(NotificationStatus.NOTIFY.name())){
                result.setNotificationStatus(NotificationStatus.NOTIFY);
            }
            
        }

        return result;
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

    @Required
	public CaaersRulesEngineService getCaaersRulesEngineService() {
		return caaersRulesEngineService;
	}

	public void setCaaersRulesEngineService(
			CaaersRulesEngineService caaersRulesEngineService) {
		this.caaersRulesEngineService = caaersRulesEngineService;
	}

}
