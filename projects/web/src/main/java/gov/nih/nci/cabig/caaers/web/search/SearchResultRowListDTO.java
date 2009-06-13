package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.List;



public class SearchResultRowListDTO{
	
	private List<SearchResultRowDTO> rowListDTO;
	
	public void setRowListDTO(List<SearchResultRowDTO> rowListDTO){
		this.rowListDTO = rowListDTO;
	}
	
	public List<SearchResultRowDTO> getRowListDTO(){
		if(rowListDTO == null)
			rowListDTO = new ArrayList<SearchResultRowDTO>();
		return rowListDTO;
	}
}