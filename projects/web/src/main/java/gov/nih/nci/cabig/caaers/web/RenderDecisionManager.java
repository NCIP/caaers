package gov.nih.nci.cabig.caaers.web;
/**
 * 
 * @author Biju Joseph
 *
 */
public class RenderDecisionManager {
	
	public boolean canRenderSection(String sectionId){
		return true;
	}
	
	public boolean canRenderField(String fieldId){
		return true;
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
