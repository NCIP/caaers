package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;
import java.util.Map;




/**
 * 
 * @author vinaykumar
 * The RulesEngineService is a facade for authoring, deploying
 * and executing the rules
 */
public interface AdverseEventEvaluationService {
	
	
	
	public String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception;
	
	//public String identifyAdverseEventType()
	
	public Map<String,List<String>> evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport) throws Exception;
	
	public List<String> mandatorySections(ExpeditedAdverseEventReport expeditedData) throws Exception;

}
