package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.runtime.BusinessRulesExecutionService;
import gov.nih.nci.cabig.caaers.rules.runtime.BusinessRulesExecutionServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdverseEventEvaluationServiceImpl implements AdverseEventEvaluationService {

	//Replace with spring injection
	private BusinessRulesExecutionService businessRulesExecutionService = new BusinessRulesExecutionServiceImpl();
	private RulesEngineService rulesEngineService= new RulesEngineServiceImpl();
	//private ReportDefinitionDao reportDefinitionDao;
	//private ReportServiceImpl reportService;
	
	public static final String CAN_NOT_DETERMINED = "CAN_NOT_DETERMINED";
	


/**
 * This method will asses adverse event and will return one of the
 * following vlue
 * 	1. Routine AE
 *  2. SAE
 *  3. Can't be determined
 *  Calling this method again and again will not affect the rules
 *  firing adversly as nothing gets fires subsequently
 *  
 *  fire the rules at sponsor defined defined study level..
 *  if not rules specified , then fire sponsor level rules.
 *  
 */
public String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception{
	
	String sponsor_define_study_level_evaluation = null;
	String sponsor_level_evaluation = null;
	String final_result = null;
	
	/**
	 * get and fire study lelev rules
	 */
	sponsor_define_study_level_evaluation = sponsorDefinedStudyLevelAssesmentRules(ae, study);

	// if study level rule exist and null message...
	if (sponsor_define_study_level_evaluation == null) {
		return CAN_NOT_DETERMINED;
	
	// if study level rules not found , then get to sponsor rules..
	} else 	if (sponsor_define_study_level_evaluation.equals("no_rules_found")) {
		sponsor_level_evaluation = sponsorLevelAssesmentRules(ae, study);
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

public List evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport) throws Exception {
	
	List reportDefinitions = new ArrayList();
	List<AdverseEvent> aes = aeReport.getAdverseEvents();
	
	for(AdverseEvent ae : aes )
	{
		String message = evaluateSponsorReportSchedule(ae,aeReport.getStudy());
		if (!message.equals(CAN_NOT_DETERMINED)) {
			reportDefinitions.add(message);
			//break;
		}

	}
	
	Study study = aeReport.getStudy();
	
	//TO-DO get orgs like FDA, CALGB and add to this list
	
	for(StudyOrganization so : study.getStudyOrganizations() )
	{
		for(AdverseEvent ae : aes ) {
			String message = evaluateInstitutionReportSchedule(ae, study, so.getOrganization());
			if (!message.equals(CAN_NOT_DETERMINED)) {
				reportDefinitions.add(message);
				//break;
			}
		}
	}
	
	
	return reportDefinitions;
}
/**
 *  fire the rules at sponsor defined defined study level..
 *  if not rules specified , then fire sponsor level rules.
 *  
  */
private String evaluateSponsorReportSchedule(AdverseEvent ae, Study study) throws Exception{
	
	String sponsor_define_study_level_evaluation = null;
	String sponsor_level_evaluation = null;
	String final_result = null;
	
	/**
	 * get and fire study level rules
	 */
	sponsor_define_study_level_evaluation = sponsorDefinedStudyLevelSchedulingRules(ae, study);

	// if study level rule exist and null message...
	if (sponsor_define_study_level_evaluation == null) {
		return CAN_NOT_DETERMINED;
	
	// if study level rules not found , then get to sponsor rules..
	} else 	if (sponsor_define_study_level_evaluation.equals("no_rules_found")) {
		sponsor_level_evaluation = sponsorLevelSchedulingRules(ae, study);
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

/**
 *  fire the rules at institution defined defined study level..
 *  if not rules specified , then fire institution level rules.
 *  
  */

private String evaluateInstitutionReportSchedule(AdverseEvent ae, Study study , Organization organization) throws Exception {
	String institution_define_study_level_evaluation = null;
	String institution_level_evaluation = null;
	String final_result = null;

	/**
	 * get and fire study level rules
	 */
	institution_define_study_level_evaluation = institutionDefinedStudyLevelSchedulingRules(ae, study, organization);

	// if study level rule exist and null message...
	if (institution_define_study_level_evaluation == null) {
		return CAN_NOT_DETERMINED;
	
	// if study level rules not found , then get to sponsor rules..
	} else 	if (institution_define_study_level_evaluation.equals("no_rules_found")) {
		institution_level_evaluation = institutionLevelSchedulingRules(ae, organization);
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



private String sponsorLevelAssesmentRules(AdverseEvent ae, Study study) throws Exception{
	String message = null;
	String bindURI = getBindURI(study.getPrimarySponsorCode(), "","SPONSOR",RuleType.AE_ASSESMENT_RULES.getName());
	
	RuleSet ruleSetForSponsor = rulesEngineService.getRuleSetForSponsor(RuleType.AE_ASSESMENT_RULES.getName(), study.getPrimarySponsorCode());
	if(ruleSetForSponsor==null){
		return "no_rules_found";
		//throw new Exception("There are no rules configured for adverse event assesment for this sponsor!");
	}
	
	AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
	
	try {
		evaluationForSponsor = this.getEvaluationObject(ae, study, null,bindURI);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
	
	message = evaluationForSponsor.getMessage();
	
	return message;
	
}


private String sponsorDefinedStudyLevelAssesmentRules(AdverseEvent ae, Study study) throws Exception{
	String message = null;
	String bindURI = getBindURI(study.getPrimarySponsorCode(), study.getShortTitle(),"SPONSOR_DEFINED_STUDY",RuleType.AE_ASSESMENT_RULES.getName());
	
	RuleSet ruleSetForSponsorDefinedStudy = rulesEngineService.getRuleSetForSponsorDefinedStudy(RuleType.AE_ASSESMENT_RULES.getName(), study.getShortTitle(), study.getPrimarySponsorCode());
	if(ruleSetForSponsorDefinedStudy==null){
		return "no_rules_found";
		//throw new Exception("There are no rules configured for adverse event assesment for this sponsor defined study!");
	}
	
	AdverseEventEvaluationResult evaluationForSponsorDefinedStudy = new AdverseEventEvaluationResult();
	
	try {
		evaluationForSponsorDefinedStudy = this.getEvaluationObject(ae, study, null, bindURI);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
	
	message = evaluationForSponsorDefinedStudy.getMessage();
	
	return message;
	
}


private String sponsorLevelSchedulingRules(AdverseEvent ae, Study study) throws Exception{
	String message = null;
	String bindURI = getBindURI(study.getPrimarySponsorCode(), "","SPONSOR",RuleType.REPORT_SCHEDULING_RULES.getName());
	
	RuleSet ruleSetForSponsor = rulesEngineService.getRuleSetForSponsor(RuleType.REPORT_SCHEDULING_RULES.getName(), study.getPrimarySponsorCode());
	
	if(ruleSetForSponsor==null){
		return "no_rules_found";
		//throw new Exception("There are no rules configured for adverse event scheduling for this sponsor!");
	}
	
	AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
	
	try {
		evaluationForSponsor = this.getEvaluationObject(ae, study, null,bindURI);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
	
	message = evaluationForSponsor.getMessage();
	
	return message;
	
}

private String sponsorDefinedStudyLevelSchedulingRules(AdverseEvent ae, Study study) throws Exception{
	String message = null;
	String bindURI = getBindURI(study.getPrimarySponsorCode(), study.getShortTitle(),"SPONSOR_DEFINED_STUDY",RuleType.REPORT_SCHEDULING_RULES.getName());
	
	RuleSet ruleSetForSponsorDefinedStudy = rulesEngineService.getRuleSetForSponsorDefinedStudy(RuleType.REPORT_SCHEDULING_RULES.getName(), study.getShortTitle(), study.getPrimarySponsorCode());
	if(ruleSetForSponsorDefinedStudy==null){
		return "no_rules_found";
		//throw new Exception("There are no rules configured for adverse event assesment for this sponsor defined study!");
	}
	
	AdverseEventEvaluationResult evaluationForSponsorDefinedStudy = new AdverseEventEvaluationResult();
	
	try {
		evaluationForSponsorDefinedStudy = this.getEvaluationObject(ae, study, null, bindURI);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
	
	message = evaluationForSponsorDefinedStudy.getMessage();
	
	return message;
	
}

private String institutionDefinedStudyLevelSchedulingRules(AdverseEvent ae, Study study , Organization organization) throws Exception{
	String message = null;
	
	String studyShortTitle = study.getShortTitle();
	String organizationName = organization.getName();
	
	String bindURI = getBindURI(organizationName, studyShortTitle,"INSTITUTION_DEFINED_STUDY",RuleType.REPORT_SCHEDULING_RULES.getName());
	
	RuleSet ruleSetForInstitutionDefinedStudy = rulesEngineService.getRuleSetForInstitutionDefinedStudy(RuleType.REPORT_SCHEDULING_RULES.getName(), studyShortTitle, organizationName);
	if(ruleSetForInstitutionDefinedStudy==null){
		return "no_rules_found";
		//throw new Exception("There are no rules configured for adverse event assesment for this sponsor defined study!");
	}
	
	AdverseEventEvaluationResult evaluationForInstitutionDefinedStudy = new AdverseEventEvaluationResult();
	
	try {
		evaluationForInstitutionDefinedStudy = this.getEvaluationObject(ae, study, organization, bindURI);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
	
	message = evaluationForInstitutionDefinedStudy.getMessage();
	
	return message;
	
}

private String institutionLevelSchedulingRules(AdverseEvent ae,  Organization organization) throws Exception{
	String message = null;
	
	String organizationName = organization.getName();
	
	String bindURI = getBindURI(organizationName, "","INSTITUTION",RuleType.REPORT_SCHEDULING_RULES.getName());
	
	RuleSet ruleSetForInstiution = rulesEngineService.getRuleSetForInstitution(RuleType.REPORT_SCHEDULING_RULES.getName(), organizationName);
	
	if(ruleSetForInstiution==null){
		return "no_rules_found";
		//throw new Exception("There are no rules configured for adverse event scheduling for this sponsor!");
	}
	
	AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
	
	try {
		evaluationForInstitution = this.getEvaluationObject(ae, null, organization,bindURI);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
	
	message = evaluationForInstitution.getMessage();
	
	return message;
}	

private String getBindURI(String sponsorOrInstitutionName, String studyName, String type, String ruleSetName){
		String bindURI = null;
		if (type.equalsIgnoreCase("SPONSOR")){
			bindURI = CategoryConfiguration.SPONSOR_BASE.getPackagePrefix() + "." +RuleUtil.getStringWithoutSpaces(sponsorOrInstitutionName)+"."+RuleUtil.getStringWithoutSpaces(ruleSetName);
		}
		
		if(type.equalsIgnoreCase("INSTITUTION")){
			bindURI = CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix() + "."+RuleUtil.getStringWithoutSpaces(sponsorOrInstitutionName)+"."+RuleUtil.getStringWithoutSpaces(ruleSetName);
		}
		
		if(type.equalsIgnoreCase("SPONSOR_DEFINED_STUDY")){
			bindURI = CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix() + "."+RuleUtil.getStringWithoutSpaces(studyName)+"."+RuleUtil.getStringWithoutSpaces(sponsorOrInstitutionName)+"."+RuleUtil.getStringWithoutSpaces(ruleSetName);
		}
		
		
		if(type.equalsIgnoreCase("INSTITUTION_DEFINED_STUDY")){
			bindURI = CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix() + "."+RuleUtil.getStringWithoutSpaces(studyName)+"."+RuleUtil.getStringWithoutSpaces(sponsorOrInstitutionName)+"."+RuleUtil.getStringWithoutSpaces(ruleSetName);
		}
		
		
		return bindURI;
	}
	

	
	private AdverseEventEvaluationResult getEvaluationObject(AdverseEvent ae, Study study, Organization organization, String bindURI) throws Exception{
		
		AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
		List<Object> inputObjects = new ArrayList<Object>();
		inputObjects.add(ae);
		if (study != null ) {
			inputObjects.add(study);
		}
		if (organization != null) {
			inputObjects.add(organization);
		}
		//inputObjects.add(new AdverseEventEvaluationResult());
		
		List<Object> outputObjects = null;
		try{
		
			outputObjects = businessRulesExecutionService.fireRules(bindURI, inputObjects);
		
		}catch(Exception ex){
			/**
			 * Don't do anything, it means there are no rules for this package
			 */
			throw new RuleException("There are no rule configured for this sponsor",ex);
			//return evaluationForSponsor;
		}
		
		
		
		Iterator<Object> it = outputObjects.iterator();
		
		while(it.hasNext()){
			Object obj = it.next();
			
			if(obj instanceof AdverseEventEvaluationResult) {
				evaluationForSponsor = (AdverseEventEvaluationResult)obj;
				break;
			}
			
			
		}
		
		return evaluationForSponsor;
	}
	
/*
		
		public String evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport) throws Exception{
			    //Report rs = aeReport.getReportSchedule();
			System.out.println("firing ... : :");
			String institutionName = aeReport.getAssignment().getStudySite().getOrganization().getName();
			String bindURI_ForInstitutionLevelRules = this.getBindURI(institutionName, "","INSTITUTION",RuleType.REPORT_SCHEDULING_RULES.getName());
			//Study study = aeReport.getStudy();
			
			System.out.println(bindURI_ForInstitutionLevelRules);
			List<AdverseEvent> aes = aeReport.getAdverseEvents();
			AdverseEvent ae = aes.get(0);
			
			RuleSet ruleSetForInstitution = rulesEngineService.getRuleSetForInstitution(RuleType.REPORT_SCHEDULING_RULES.getName(), institutionName);
			
			if(ruleSetForInstitution==null){
				throw new Exception("There are no rules configured for adverse event assesment for this site!");
			}
			
			AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
			
			try {
				evaluationForInstitution = this.getEvaluationObject(ae,  null, aeReport.getAssignment().getStudySite().getOrganization(), bindURI_ForInstitutionLevelRules);
		
				
				
				//reportService = this.getReportService();
				
				//System.out.println(reportServiceImpl.toString());
				//Report r = reportService.createReport(reportDefinition, aeReport);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage(),e);
			}
		    
			System.out.println("Message: :" + evaluationForInstitution.getMessage());
			

			
			/*
			ApplicationContext ac = AdverseEventEvaluationServiceImpl.getDeployedApplicationContext();
			
			ReportDefinitionDao reportDefinitionDao = (ReportDefinitionDao)ac.getBean("reportDefinitionDao");
			ReportDefinition reportDefinition = reportDefinitionDao.getByName(evaluationForInstitution.getMessage());
			reportDefinitionDao.initialize(reportDefinition);
			System.out.println(reportDefinition.getDescription());
			*/
			//reportServiceImpl = this.getReportServiceImpl();
			
			//System.out.println(reportServiceImpl.toString());
			//Report r = reportServiceImpl.createReport(reportDefinition, aeReport);
			
			// report .. aer and rd 
			
			//return evaluationForInstitution.getMessage();
		//}
/*
	private AdverseEventEvaluationResult getEvaluationObject(AdverseEvent ae, Organization site, String bindURI) throws Exception{
		
		AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
		
		List<Object> inputObjects = new ArrayList<Object>();
		inputObjects.add(ae);
		inputObjects.add(site);
		
		List<Object> outputObjects = null;
		try{
		
			outputObjects = businessRulesExecutionService.fireRules(bindURI, inputObjects);
		
		}catch(Exception ex){
		
			throw new RuleException("There are no rule configured for this institution",ex);
			//return evaluationForSponsor;
		}
		
		
		
		Iterator<Object> it = outputObjects.iterator();
		
		while(it.hasNext()){
			Object obj = it.next();
			
			if(obj instanceof AdverseEventEvaluationResult) {
				evaluationForInstitution = (AdverseEventEvaluationResult)obj;
				break;
			}
			
			
		}
		
		return evaluationForInstitution;
	}
*/
	/*
	public static String[] getConfigLocations() {
        return new String[] {
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-db.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml"
        		
        };
    }
	/*
	public synchronized static ApplicationContext getDeployedApplicationContext() {
        if (acLoadFailure == null && applicationContext == null) {
            // This might not be the right place for this
            try {
                SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            } catch (NamingException e) {
                throw new RuntimeException("", e);
            }

            try {
            	log.debug("Initializing test version of deployed application context");
                applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
            } catch (RuntimeException e) {
                acLoadFailure = e;
                throw e;
            }
        } else if (acLoadFailure != null) {
            throw new CaaersSystemException(
                "Application context loading already failed.  Will not retry.  " +
                    "Original cause attached.", acLoadFailure);
        }
        return applicationContext;
    }
	*/


	


/*
	public ReportServiceImpl getReportService() {
		return reportService;
	}


	public void setReportService(ReportServiceImpl reportService) {
		this.reportService = reportService;
	}

	/*
	private String institutionLevelRules(AdverseEvent ae, Study study) throws Exception{
		String message = null;
		// TO DO get from ORG object 
		String institutionName = ""; 
		String bindURI = this.getBindURI(institutionName,"","INSTITUTION",RuleType.AE_ASSESMENT_RULES.getName());
		
		RuleSet ruleSetForInstitution = rulesEngineService.getRuleSetForInstitution(RuleType.AE_ASSESMENT_RULES.getName(), institutionName);
		
		if(ruleSetForInstitution==null){
			throw new Exception("There are no rules configured for adverse event assesment for this institution!");
		}
		
		AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
		
		try {
			// TO DO GET ORG ...
			evaluationForInstitution = this.getEvaluationObject(ae, null, new Organization(), bindURI);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage(),e);
		}
		
		message = evaluationForInstitution.getMessage();
		
		return message;
		
	}
	*/
	
	/*
	private String institutionDefinedStudyLevelRules(AdverseEvent ae, Study study) throws Exception{
		String message = null;
		// TO DO get from ORG object 
		String institutionName = ""; 
		
		String bindURI = getBindURI(institutionName, study.getShortTitle(),"INSTITUTION_DEFINED_STUDY",RuleType.AE_ASSESMENT_RULES.getName());
		
		RuleSet ruleSetForInstitutionDefinedStudy = rulesEngineService.getRuleSetForInstitutionDefinedStudy(RuleType.AE_ASSESMENT_RULES.getName(), study.getShortTitle(), institutionName);
		
		if(ruleSetForInstitutionDefinedStudy==null){
			throw new Exception("There are no rules configured for adverse event assesment for this institution defined study!");
		}
		
		AdverseEventEvaluationResult evaluationForInstitutionDefinedStudy = new AdverseEventEvaluationResult();
		
		try {
			// TO DO get ORG...
			evaluationForInstitutionDefinedStudy = this.getEvaluationObject(ae, study, new Organization(), bindURI);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage(),e);
		}
		
		message = evaluationForInstitutionDefinedStudy.getMessage();
		
		return message;
		
	}
	*/
	
}
