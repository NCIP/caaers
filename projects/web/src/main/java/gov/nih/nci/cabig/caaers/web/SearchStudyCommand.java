package gov.nih.nci.cabig.caaers.web;

public class SearchStudyCommand {
	
	private String searchTypeText;
	private String searchType;
	
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return the searchTypeText
	 */
	public String getSearchTypeText() {
		return searchTypeText;
	}
	/**
	 * @param searchTypeText the searchTypeText to set
	 */
	public void setSearchTypeText(String searchTypeText) {
		this.searchTypeText = searchTypeText;
	}
	

}