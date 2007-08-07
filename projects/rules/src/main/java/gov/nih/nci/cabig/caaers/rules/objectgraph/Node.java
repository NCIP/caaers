package gov.nih.nci.cabig.caaers.rules.objectgraph;

/**
 * This class represent a node in the navigtion path.
 * @author vinaykumar
 *
 */
public class Node {
	
	private String name;
	private boolean isCollection;
	private String objectType;
	private String genericTypeInList;
	
	public String getGenericTypeInList() {
		return genericTypeInList;
	}
	public void setGenericTypeInList(String genericTypeInList) {
		this.genericTypeInList = genericTypeInList;
	}
	public boolean isCollection() {
		return isCollection;
	}
	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

}
