package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
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
/**
 * This method will asses adverse event and will return one of the
 * following vlue
 * 	1. Routine AE
 *  2. SAE
 *  3. Can't be determined
 *  Calling this method again and again will not affect the rules
 *  firing adversly as nothing gets fires subsequently
 *  
 *  Only fire the rules belonging to "Asses AE Rule Set" for Sponsor
 *  
 */
public String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception{
	
	String final_result = null;
	
	String sponsor_level_evaluation = null;
	String study_level_evaluation = null;
	
	String sponsorName = study.getPrimarySponsorCode();
	String studyName = study.getShortTitle();
	String bindURI_ForSponsorLevelRules = this.getBindURI(sponsorName, studyName,"SPONSOR",RuleType.AE_ASSESMENT_RULES.getName());
	String bindURI_ForStudyLevelRules = this.getBindURI(sponsorName,studyName,"STUDY", RuleType.AE_ASSESMENT_RULES.getName());
	
	/**
	 * First asses the AE for Sponsor
	 */
	
	
	RuleSet ruleSetForSponsor = rulesEngineService.getRuleSetForSponsor(RuleType.AE_ASSESMENT_RULES.getName(), sponsorName);
	
	if(ruleSetForSponsor==null){
		throw new Exception("There are no rules configured for adverse event assesment for this sponsor!");
	}
	/*
	boolean rulesDeployedForSponsor = rulesEngineService.isDeployed(ruleSetForSponsor);
	
	if(!rulesDeployedForSponsor){
		throw new Exception("There are no rules deployd for adverse event assesment for this sponsor!");
	}
	*/
	AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
	
	try {
		evaluationForSponsor = this.getEvaluationObject(ae, study, bindURI_ForSponsorLevelRules);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
    
	System.out.println("Message: " + evaluationForSponsor.getMessage());
	/**
	 * Now fire rules for Study
	 */
	
	RuleSet ruleSetForStudy = rulesEngineService.getRuleSetForStudy(RuleType.AE_ASSESMENT_RULES.getName(), studyName, sponsorName);
	AdverseEventEvaluationResult evaluationForStudy = new AdverseEventEvaluationResult();
//	if(ruleSetForStudy!=null){
		//if(rulesEngineService.isDeployed(ruleSetForStudy)){
				try {
					evaluationForStudy = this.getEvaluationObject(ae, study, bindURI_ForStudyLevelRules);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Exception occured while executing study rules");
				}
/*			}
	}
*/	
	
	
	/**
	 * Now we can compare both the decisions 
	 * If the evaluation is same then just return that evaluation
	 * but if they are not same then Study evaluation overrides
	 */
	sponsor_level_evaluation = evaluationForSponsor.getMessage();
	study_level_evaluation = evaluationForStudy.getMessage();
	
	 if((sponsor_level_evaluation!=null)&&(study_level_evaluation!=null)){
		 final_result = sponsor_level_evaluation.equalsIgnoreCase(study_level_evaluation)?sponsor_level_evaluation:study_level_evaluation;
	 }
	 if((sponsor_level_evaluation==null)&&(study_level_evaluation==null)){
		 final_result = "CAN_NOT_DETERMINED";
	 }
	 if((sponsor_level_evaluation==null)&&(study_level_evaluation!=null)){
		 final_result = study_level_evaluation;
	 }
	 if((sponsor_level_evaluation!=null)&&(study_level_evaluation==null)){
		 final_result = sponsor_level_evaluation;
	 }
	
	return final_result;
	
}


public String assesAdverseEvent(AdverseEvent ae, Organization site) throws Exception{
	
	String final_result = null;
	
	String institution_level_evaluation = null;

	
	String institutionName = site.getName();
	String bindURI_ForInstitutionLevelRules = this.getBindURI("", institutionName,"INSTITUTION",RuleType.AE_ASSESMENT_RULES.getName());
	
	/**
	 * First asses the AE for Sponsor
	 */
	
	
	RuleSet ruleSetForInstitution = rulesEngineService.getRuleSetForInstitution(RuleType.AE_ASSESMENT_RULES.getName(), institutionName);
	
	if(ruleSetForInstitution==null){
		throw new Exception("There are no rules configured for adverse event assesment for this site!");
	}
	/*
	boolean rulesDeployedForSponsor = rulesEngineService.isDeployed(ruleSetForSponsor);
	
	if(!rulesDeployedForSponsor){
		throw new Exception("There are no rules deployd for adverse event assesment for this sponsor!");
	}
	*/
	AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
	
	try {
		evaluationForInstitution = this.getEvaluationObject(ae, site, bindURI_ForInstitutionLevelRules);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage(),e);
	}
    
	System.out.println("Message: " + evaluationForInstitution.getMessage());


	
	
	/**
	 * Now we can compare both the decisions 
	 * If the evaluation is same then just return that evaluation
	 * but if they are not same then Study evaluation overrides
	 */
	institution_level_evaluation = evaluationForInstitution.getMessage();
	

	 if((institution_level_evaluation==null)){
		 final_result = "CAN_NOT_DETERMINED";
	 }

	 if((institution_level_evaluation!=null)){
		 final_result = institution_level_evaluation;
	 }
	
	return final_result;
	
}


	//public String identifyAdverseEventType()
  /**
   * Go through all the Aes and fire the rules against them
   * and return the collection of reports
   */
	
	public String evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport) throws Exception{
		    //Report rs = aeReport.getReportSchedule();
		//aeReport.
		String institutionName = aeReport.getAssignment().getStudySite().getOrganization().getName();
		String bindURI_ForInstitutionLevelRules = this.getBindURI("", institutionName,"INSTITUTION",RuleType.REPORT_SCHEDULING_RULES.getName());
		//Study study = aeReport.getStudy();
		List<AdverseEvent> aes = aeReport.getAdverseEvents();
		AdverseEvent ae = aes.get(0);
		
		RuleSet ruleSetForInstitution = rulesEngineService.getRuleSetForInstitution(RuleType.REPORT_SCHEDULING_RULES.getName(), institutionName);
		
		if(ruleSetForInstitution==null){
			throw new Exception("There are no rules configured for adverse event assesment for this site!");
		}
		
		AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
		
		try {
			evaluationForInstitution = this.getEvaluationObject(ae, aeReport.getAssignment().getStudySite().getOrganization(), bindURI_ForInstitutionLevelRules);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage(),e);
		}
	    
		System.out.println("Message: " + evaluationForInstitution.getMessage());
		
		return evaluationForInstitution.getMessage();
		
		    
		
	}
	
	private String getBindURI(String sponsorName, String studyOrSiteName, String type, String ruleSetName){
		String bindURI = null;
		if (type.equalsIgnoreCase("SPONSOR")){
			bindURI = CategoryConfiguration.SPONSOR_BASE.getPackagePrefix() + "." +this.getStringWithoutSpaces(sponsorName)+"."+this.getStringWithoutSpaces(ruleSetName);
		}
		if(type.equalsIgnoreCase("STUDY")){
			bindURI = CategoryConfiguration.STUDY_BASE.getPackagePrefix() + "."+this.getStringWithoutSpaces(studyOrSiteName)+"."+this.getStringWithoutSpaces(sponsorName)+"."+this.getStringWithoutSpaces(ruleSetName);
		}
		if(type.equalsIgnoreCase("INSTITUTION")){
			bindURI = CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix() + "."+this.getStringWithoutSpaces(studyOrSiteName)+"."+this.getStringWithoutSpaces(ruleSetName);
		}
		return bindURI;
	}
	
	private String getStringWithoutSpaces(String str){
		
		String _str= str.trim();
		return _str.replace(" ", "_");
		
		
	}
	
	private AdverseEventEvaluationResult getEvaluationObject(AdverseEvent ae, Study study, String bindURI) throws Exception{
		
		AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
		
/*		AdverseEventSDO adverseEventSDO = new AdverseEventSDO();
		
		adverseEventSDO.setExpected(ae.getExpected().toString());
		adverseEventSDO.setGrade(new Integer(ae.getGrade().getCode()));
		adverseEventSDO.setHospitalization(ae.getHospitalization().getName());
		adverseEventSDO.setPhase(study.getPhaseCode());
		adverseEventSDO.setTerm(ae.getCtcTerm().getTerm());
		
		StudySDO studySDO = new StudySDO();
		studySDO.setPrimarySponsorCode(study.getPrimarySponsorCode());
		studySDO.setShortTitle(study.getShortTitle());
*/		
		List<Object> inputObjects = new ArrayList<Object>();
		inputObjects.add(ae);
		//inputObjects.add(ae);
		inputObjects.add(study);
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

	private AdverseEventEvaluationResult getEvaluationObject(AdverseEvent ae, Organization site, String bindURI) throws Exception{
		
		AdverseEventEvaluationResult evaluationForInstitution = new AdverseEventEvaluationResult();
		
		List<Object> inputObjects = new ArrayList<Object>();
		inputObjects.add(ae);
		inputObjects.add(site);
		
		List<Object> outputObjects = null;
		try{
		
			outputObjects = businessRulesExecutionService.fireRules(bindURI, inputObjects);
		
		}catch(Exception ex){
			/**
			 * Don't do anything, it means there are no rules for this package
			 */
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
}
