package gov.nih.nci.cabig.caaers.web.search;

/**
 * This class is used to represent one criteria in the advanced search UI.
 * The command object will have a list of its objects to represent multiple
 * search criterias.
 * 
 * @author Sameer Sawant
 */
public class AdvancedSearchCriteriaParameter implements Comparable{
	
	// This represents the object whose attribute is used in the criteria
	private String objectName;
	
	// This represents the attribute used in the criteria
	private String attributeName;
	
	// Thie represents the predicate (eg. "Greater Than, Equal To etc"
	private String predicate;
	
	// This represents the value of the attribute in the criteria
	private String value;
	
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
	
	public int compareTo(Object object){
		AdvancedSearchCriteriaParameter parameter = (AdvancedSearchCriteriaParameter) object;
		if(this.getObjectName().compareToIgnoreCase(parameter.getObjectName()) == 0)
			return this.getAttributeName().compareToIgnoreCase(parameter.getAttributeName());
		return this.getObjectName().compareToIgnoreCase(parameter.getObjectName());
	}
}