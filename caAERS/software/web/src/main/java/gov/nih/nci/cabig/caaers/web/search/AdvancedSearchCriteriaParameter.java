package gov.nih.nci.cabig.caaers.web.search;

/**
 * This class is used to represent one criteria in the advanced search UI.
 * The command object will have a list of its objects to represent multiple
 * search criterias.
 * 
 * @author Sameer Sawant
 */
public class AdvancedSearchCriteriaParameter implements Comparable{
	
	// This represents the dependentObject under which this attribute will be rendered in the UI.
	private String dependentObjectName;
	
	// This represents the object whose attribute is used in the criteria
	private String objectName;
	
	// This represents the attribute used in the criteria
	private String attributeName;
	
	// Thie represents the predicate (eg. "Greater Than, Equal To etc"
	private String predicate;
	
	// This represents the value of the attribute in the criteria
	private String value;
	
	// This represents the displayValue for special input elements like autocompleters.
	private String displayValue;
	
	// This represents the boolean value which says whether the criteria parameters has been deleted.
	private boolean deleted;
	
	public void setObjectName(String objectName){
		this.objectName = objectName;
	}
	
	public String getObjectName(){
		return objectName;
	}
	
	public void setAttributeName(String attributeName){
		this.attributeName = attributeName;
	}
	
	public String getAttributeName(){
		return attributeName;
	}
	
	public void setPredicate(String predicate){
		this.predicate = predicate;
	}
	
	public String getPredicate(){
		return predicate;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}
	
	public boolean isDeleted(){
		return deleted;
	}
	
	public String getDisplayValue(){
		return displayValue;
	}
	
	public void setDisplayValue(String displayValue){
		this.displayValue = displayValue;
	}
	
	public String getDependentObjectName(){
		return dependentObjectName;
	}
	
	public void setDependentObjectName(String dependentObjectName){
		this.dependentObjectName = dependentObjectName;
	}
	
	public int compareTo(Object object){
		AdvancedSearchCriteriaParameter parameter = (AdvancedSearchCriteriaParameter) object;
		if(this.getObjectName().compareToIgnoreCase(parameter.getObjectName()) == 0)
			return this.getAttributeName().compareToIgnoreCase(parameter.getAttributeName());
		return this.getObjectName().compareToIgnoreCase(parameter.getObjectName());
	}
	
	public boolean isFilled(){
		if(this.objectName == null || this.attributeName == null || this.predicate == null || this.value == null)
			return false;
		return true;
	}
}