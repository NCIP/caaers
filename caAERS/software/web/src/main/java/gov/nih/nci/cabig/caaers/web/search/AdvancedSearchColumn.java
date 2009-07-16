package gov.nih.nci.cabig.caaers.web.search;

public class AdvancedSearchColumn{
	private String columnHeader;
	
	private Object value;
	
	public void setValue(Object value){
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}
	
	public void setColumnHeader(String columnHeader){
		this.columnHeader = columnHeader;
	}
	
	public String getColumnHeader(){
		return columnHeader;
	}
}