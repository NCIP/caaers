package gov.nih.nci.cabig.caaers.rules.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.runtime.BusinessRulesExecutionService;
import gov.nih.nci.cabig.caaers.rules.runtime.BusinessRulesExecutionServiceImpl;
import gov.nih.nci.cabig.caaers.rules.runtime.Global;

public class AdverseEventEvaluationServiceImpl implements AdverseEventEvaluationService {

	//Replace with spring injection
	private BusinessRulesExecutionService businessRulesExecutionService = new BusinessRulesExecutionServiceImpl();
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
public String assesAdverseEvent(AdverseEvent ae, Study study){
	
	String final_result = null;
	
	String sponsor_level_evaluation = null;
	String study_level_evaluation = null;
	
	String sponsorName = study.getPrimarySponsorCode();
	String studyName = study.getShortTitle();
	String bindURI_ForSponsorLevelRules = this.getBindURI(sponsorName, "SPONSOR", "Asses AE Rule");
	String bindURI_ForStudyLevelRules = this.getBindURI(studyName,"STUDY", "Asses AE Rule");
	
	/**
	 * First asses the AE for Sponsor
	 */
	
	AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
	try {
		evaluationForSponsor = this.getEvaluationObject(ae, study, bindURI_ForSponsorLevelRules);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	System.out.println(evaluationForSponsor.getMessage());
	/**
	 * Now fire rules for Study
	 */
	
	AdverseEventEvaluationResult evaluationForStudy = new AdverseEventEvaluationResult();
	try {
		evaluationForStudy = this.getEvaluationObject(ae, study, bindURI_ForStudyLevelRules);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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
	
	//public String identifyAdverseEventType()
	
	public String evaluateSAEReportSchedule(AdverseEventReport aeReport){
		    //ReportSchedule rs = aeReport.getReportSchedule();
		//aeReport.
		Study study = aeReport.getStudy();
		List<AdverseEvent> aes = aeReport.getAdverseEvents();
		AdverseEvent ae = aes.get(0);
		
		return this.assesAdverseEvent(ae, study);
		
		    
		
	}
	
	private String getBindURI(String name, String type, String ruleSetName){
		String bindURI = null;
		if (type.equalsIgnoreCase("SPONSOR")){
			bindURI = "gov.nih.nci.cabig.caaers.rules."+this.getStringWithoutSpaces(name)+"."+this.getStringWithoutSpaces(ruleSetName);
		}
		if(type.equalsIgnoreCase("STUDY")){
			bindURI = "gov.nih.nci.cabig.caaers.rules."+this.getStringWithoutSpaces(name)+"."+this.getStringWithoutSpaces(ruleSetName);
		}
		return bindURI;
	}
	
	private String getStringWithoutSpaces(String str){
		
		String _str= str.trim();
		return _str.replace(" ", "_");
		
		
	}
	
	private AdverseEventEvaluationResult getEvaluationObject(AdverseEvent ae, Study study, String bindURI) throws Exception{
		
		AdverseEventEvaluationResult evaluationForSponsor = new AdverseEventEvaluationResult();
		
		List<Object> inputObjects = new ArrayList<Object>();
		inputObjects.add(ae);
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

}
