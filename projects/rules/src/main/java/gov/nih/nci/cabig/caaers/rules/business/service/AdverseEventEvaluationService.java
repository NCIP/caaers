package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;




/**
 * 
 * @author vinaykumar
 * The RulesEngineService is a facade for authoring, deploying
 * and executing the rules
 */
public interface AdverseEventEvaluationService {
	
	
	
	public String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception;
	
	//public String identifyAdverseEventType()
	
	public String evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport) throws Exception;
	
	
	
	

}
