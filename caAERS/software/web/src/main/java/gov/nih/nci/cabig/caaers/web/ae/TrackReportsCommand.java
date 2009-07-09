package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.dto.ReportVersionSearchResultsDTO;

public class TrackReportsCommand {
	
	private ReportVersionSearchResultsDTO searchResultsDTO;
	
	public TrackReportsCommand() {}

	public ReportVersionSearchResultsDTO getSearchResultsDTO() {
		return searchResultsDTO;
	}

	public void setSearchResultsDTO(ReportVersionSearchResultsDTO searchResultsDTO) {
		this.searchResultsDTO = searchResultsDTO;
	}

}
