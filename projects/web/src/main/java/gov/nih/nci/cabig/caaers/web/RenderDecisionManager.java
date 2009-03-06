package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Biju Joseph
 *
 */
public class RenderDecisionManager {
	
	Map<String , Boolean> decisionCache = new HashMap<String, Boolean>();
	
	
	public boolean canRenderSection(String sectionId, HttpServletRequest request, HttpServletResponse response){
		Boolean decision = decisionCache.get(findActualName(sectionId));
		if(decision == null) return true; // if decision is not set, assumed as always renderable. 
		return decision;
	}
	
	public boolean canRenderField(String fieldId, HttpServletRequest request, HttpServletResponse response){
		Boolean decision = decisionCache.get(findActualName(fieldId));
		if(decision == null) return true; //if decision is not set, assumed always renderable. 
		return decision;
	}
	
	/**
	 * Will mark the field, identified by the name as conceled (not renderable).
	 * @param fieldName
	 */
	public void conceal(String... fieldNames){
		for(String fieldName : fieldNames) decisionCache.put(findActualName(fieldName), Boolean.FALSE);
	}
	
	/**
	 * Will mark the field, identified by the name as renderable.
	 * @param fieldName
	 */
	public void reveal(String... fieldNames){
		for(String fieldName : fieldNames) decisionCache.put(findActualName(fieldName), Boolean.TRUE);
	}
	
	
	/**
	 * This method will remove all the array parameters and return the name
	 * @param name (eg : biju.joseph[4].padupurackal)
	 * @return (eg: biju.joseph[].padupurackal)
	 */
	
	public String findActualName(String name){
		String correctedName =  name.replaceAll("(\\[\\d+\\])", "[]");
		//apply field name corrections
		if(StringUtils.equals(correctedName, "aeReport.adverseEvents[].outcomes")) correctedName = "outcomes";
		return correctedName;
	}
	/**
	 * This method will be called for expedited AE flow field renderability decision
	 * @param rdList
	 */
	public void updateRenderDecision(Collection<ReportDefinition> rdList){
		decisionCache.clear();
		Map<String , Boolean> tempDecisionMap = new HashMap<String, Boolean>();
		for(ReportDefinition rd : rdList){
			for(ReportMandatoryFieldDefinition mfd : rd.getMandatoryFields()){
				Boolean currentValue = tempDecisionMap.get(mfd.getFieldPath());
				if(mfd.getMandatory() == Mandatory.NA){
					tempDecisionMap.put(mfd.getFieldPath(), (currentValue == null)? true : currentValue && true);
				}else{
					tempDecisionMap.put(mfd.getFieldPath(), false);
				}
			}
		}
		for(Map.Entry<String, Boolean> entry : tempDecisionMap.entrySet()){
			if(entry.getValue()) conceal("aeReport." + entry.getKey());
			else reveal(entry.getKey());
		}
	}
}
