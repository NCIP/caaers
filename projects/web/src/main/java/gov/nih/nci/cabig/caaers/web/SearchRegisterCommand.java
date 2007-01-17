package gov.nih.nci.cabig.caaers.web;

public class SearchRegisterCommand {
	private String searchCategory;
	private String searchTypeText;
	private String searchTypeTextPart;
	private String searchTypePart;
	private String searchType;
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchTypeText() {
		return searchTypeText;
	}
	public void setSearchTypeText(String searchTypeText) {
		this.searchTypeText = searchTypeText;
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getSearchTypePart() {
		return searchTypePart;
	}
	public void setSearchTypePart(String searchTypePart) {
		this.searchTypePart = searchTypePart;
	}
	public String getSearchTypeTextPart() {
		return searchTypeTextPart;
	}
	public void setSearchTypeTextPart(String searchTypeTextPart) {
		this.searchTypeTextPart = searchTypeTextPart;
	}
}