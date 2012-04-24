package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;
import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.cabig.caaers.domain.dto.CTEPESYSIntegrationLogSearchResultsDTO;

import java.util.Date;
import java.util.List;

public class CTEPESYSDataIntegrationLogsCommand {
	
	private CTEPESYSIntegrationLogSearchResultsDTO searchResultsDTO;
	
	private Date startDate;
	
	private Date endDate;
	
	private String businessId;
	
	private SynchStatus synchStatus;
	
	private String entity;
	
	private String outcome;
	
	public String getEntity() {
		return entity;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public SynchStatus getSynchStatus() {
		return synchStatus;
	}

	public void setSynchStatus(SynchStatus synchStatus) {
		this.synchStatus = synchStatus;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
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
