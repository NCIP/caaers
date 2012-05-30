package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.dto.CTEPESYSIntegrationLogSearchResultsDTO;

import java.util.Date;

public class CTEPESYSDataIntegrationLogsCommand {
	
	private CTEPESYSIntegrationLogSearchResultsDTO searchResultsDTO;
	
	private String service;
	
	private Date startDate;
	
	private Date endDate;
	
	private String status;
	
	private boolean esbLogsLocationExists = false;
	
	public boolean isEsbLogsLocationExists() {
		return esbLogsLocationExists;
	}

	public void setEsbLogsLocationExists(boolean esbLogsLocationExists) {
		this.esbLogsLocationExists = esbLogsLocationExists;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String actions;
	
	public CTEPESYSDataIntegrationLogsCommand() {}

	public CTEPESYSIntegrationLogSearchResultsDTO getSearchResultsDTO() {
		return searchResultsDTO;
	}

	public void setSearchResultsDTO(
			CTEPESYSIntegrationLogSearchResultsDTO searchResultsDTO) {
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

}
