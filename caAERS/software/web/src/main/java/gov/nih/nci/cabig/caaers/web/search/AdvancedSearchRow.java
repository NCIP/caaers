package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchRow{
	List<AdvancedSearchColumn> columnList;
	List<AdvancedSearchRow> rowList;
	/**
	 * @return the columnList
	 */
	public List<AdvancedSearchColumn> getColumnList() {
		if(columnList == null)
			columnList = new ArrayList<AdvancedSearchColumn>();
		return columnList;
	}
	/**
	 * @param columnList the columnList to set
	 */
	public void setColumnList(List<AdvancedSearchColumn> columnList) {
		this.columnList = columnList;
	}
	/**
	 * @return the rowList
	 */
	public List<AdvancedSearchRow> getRowList() {
		return rowList;
	}
	/**
	 * @param rowList the rowList to set
	 */
	public void setRowList(List<AdvancedSearchRow> rowList) {
		this.rowList = rowList;
	}
	
	
}