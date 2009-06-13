package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.List;


public class SearchResultColumnListDTO{
	
	List<SearchResultColumnDTO> columnDTOList;
	
	public void setColumnDTOList(List<SearchResultColumnDTO> columnDTOList){
		this.columnDTOList = columnDTOList;
	}
	
	public List<SearchResultColumnDTO> getColumnDTOList(){
		if(columnDTOList == null)
			columnDTOList = new ArrayList<SearchResultColumnDTO>();
		return columnDTOList;
	}
}