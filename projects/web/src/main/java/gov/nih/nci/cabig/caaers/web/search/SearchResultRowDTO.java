package gov.nih.nci.cabig.caaers.web.search;


public class SearchResultRowDTO{
	
	private SearchResultColumnListDTO columnListDTO;
	
	public void setColumnListDTO(SearchResultColumnListDTO columnListDTO){
		this.columnListDTO = columnListDTO;
	}
	
	public SearchResultColumnListDTO getColumnListDTO(){
		if(columnListDTO == null)
			columnListDTO = new SearchResultColumnListDTO();;
		return columnListDTO;
	}
}