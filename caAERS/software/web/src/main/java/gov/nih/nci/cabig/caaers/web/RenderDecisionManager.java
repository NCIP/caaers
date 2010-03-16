package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.report.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
        return canRenderField(fieldId);
	}
	
	public boolean canRenderField(String fieldId){
        Boolean decision = decisionCache.get(findActualName(fieldId));
		if (decision == null) return true; 
        return decision;
    }

	/**
	 * Will mark the field, identified by the name as conceled (not renderable).
	 * @param fieldNames
	 */
	public void conceal(String... fieldNames){
		for(String fieldName : fieldNames) decisionCache.put(findActualName(fieldName), Boolean.FALSE);
	}
	
	/**
	 * Will mark the field, identified by the name as renderable.
	 * @param fieldNames
	 */
	public void reveal(String... fieldNames){
		for(String fieldName : fieldNames) decisionCache.put(findActualName(fieldName), Boolean.TRUE);
	}
	
	/**
	 * Will mark the field, identified by the name as conceled (not renderable).
	 * @param fieldNamesList
	 */
	public void conceal(List<String> fieldNamesList){
		for(String fieldName : fieldNamesList) decisionCache.put(findActualName(fieldName), Boolean.FALSE);
	}
	
	/**
	 * Will mark the field, identified by the name as renderable.
	 * @param fieldNamesList
	 */
	public void reveal(List<String> fieldNamesList){
		for(String fieldName : fieldNamesList) decisionCache.put(findActualName(fieldName), Boolean.TRUE);
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
        if(correctedName.startsWith("aeReport.")) correctedName = correctedName.substring(9);
		return correctedName;
	}

    /**
     * Updates the renderability, based on the ReportMandatoryField in Report.
     * @param reportList - A list of reports
     */
    public void updateRenderDecision(List<Report> reportList){
       decisionCache.clear();
       Map<String , Boolean> tempDecisionMap = new HashMap<String, Boolean>();
		for(Report r : reportList){
			for(ReportMandatoryField mf : r.getMandatoryFields()){
				Boolean currentValue = tempDecisionMap.get(mf.getFieldPath());
				if(mf.getMandatory() == Mandatory.NA){
					tempDecisionMap.put(mf.getFieldPath(), (currentValue == null)? true : currentValue && true);
				}else{
					tempDecisionMap.put(mf.getFieldPath(), false);
				}
			}
		}
		for(Map.Entry<String, Boolean> entry : tempDecisionMap.entrySet()){
			if(entry.getValue()) conceal(entry.getKey());
			else reveal(entry.getKey());
		}
    }

}
