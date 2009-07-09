package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.ArrayList;
import java.util.List;

public class ReportVersionSearchResultDTO {
	
	List<ReportVersion> results;
	
	public List<ReportVersion> getResults() {
		return results;
	}
	public void setResults(List<ReportVersion> results) {
		this.results = results;
	}
	
	public void addResult(ReportVersion dto){
		if(results == null) results = new ArrayList<ReportVersion>();
		results.add(dto);
	}

}
