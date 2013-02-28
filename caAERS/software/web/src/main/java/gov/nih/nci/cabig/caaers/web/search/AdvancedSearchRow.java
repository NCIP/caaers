/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.List;


public class AdvancedSearchRow{
	List<AdvancedSearchColumn> columnList;
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
	@Override
	public boolean equals(Object target){
		AdvancedSearchRow targetRow = (AdvancedSearchRow)target;
		for (int i= 0 ; i<columnList.size() ; i++) {
			AdvancedSearchColumn sourceCol = columnList.get(i);
			AdvancedSearchColumn targetCol = targetRow.getColumnList().get(i);
			if (sourceCol.getValue() != null  && targetCol.getValue() != null) {
				if (!sourceCol.getValue().equals(targetCol.getValue())) {
					return false;
				}
			}
		}
		return true;
	}
}
