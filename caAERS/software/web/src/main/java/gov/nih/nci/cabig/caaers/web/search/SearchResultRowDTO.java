package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.List;


public class SearchResultRowDTO{
	
	private SearchResultColumnListDTO columnListDTO;
	
	private List<SearchResultRowListDTO> listOfRowList;
	
	public void setColumnListDTO(SearchResultColumnListDTO columnListDTO){
		this.columnListDTO = columnListDTO;
	}
	
	public SearchResultColumnListDTO getColumnListDTO(){
		if(columnListDTO == null)
			columnListDTO = new SearchResultColumnListDTO();;
		return columnListDTO;
	}
	
	public void setListOfRowList(List<SearchResultRowListDTO> listOfRowList){
		this.listOfRowList = listOfRowList;
	}
	
	public List<SearchResultRowListDTO> getListOfRowList(){
		if(listOfRowList == null)
			listOfRowList = new ArrayList<SearchResultRowListDTO>();
		return listOfRowList;
	}
}