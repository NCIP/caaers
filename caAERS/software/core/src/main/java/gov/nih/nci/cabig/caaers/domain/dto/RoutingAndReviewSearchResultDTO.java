package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class RoutingAndReviewSearchResultDTO {
	
	private String header;
	
	List<AdverseEventReportingPeriodDTO> results;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public List<AdverseEventReportingPeriodDTO> getResults() {
		return results;
	}
	public void setResults(List<AdverseEventReportingPeriodDTO> results) {
		this.results = results;
	}
	
	public void addResult(AdverseEventReportingPeriodDTO dto){
		if(results == null) results = new ArrayList<AdverseEventReportingPeriodDTO>();
		results.add(dto);
	}
}
