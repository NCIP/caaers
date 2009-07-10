package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.dto.ReportVersionSearchResultsDTO;

import java.util.Date;

public class TrackReportsCommand {
	
	private ReportVersionSearchResultsDTO searchResultsDTO;
	
	private Date startDate;
	
	private Date endDate;
	
	private String actions;
	
	private Integer reportId;
	
	public TrackReportsCommand() {}

	public ReportVersionSearchResultsDTO getSearchResultsDTO() {
		return searchResultsDTO;
	}

	public void setSearchResultsDTO(ReportVersionSearchResultsDTO searchResultsDTO) {
		this.searchResultsDTO = searchResultsDTO;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

}
