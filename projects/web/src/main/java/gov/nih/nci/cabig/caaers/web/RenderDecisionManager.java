package gov.nih.nci.cabig.caaers.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		return name.replaceAll("(\\[\\d+\\])", "[]");
	}
}
