package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.ArrayList;
import java.util.List;

 
/**
 * The Class ReportVersionSearchResultDTO.
 */
public class ReportVersionSearchResultDTO {
	
	/** The results. */
	List<ReportVersion> results;
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<ReportVersion> getResults() {
		if(results == null) results = new ArrayList<ReportVersion>();
		return results;
	}
	
	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<ReportVersion> results) {
		this.results = results;
	}
	
	/**
	 * Adds the result.
	 *
	 * @param dto the dto
	 */
	public void addResult(ReportVersion dto){
		if(results == null) results = new ArrayList<ReportVersion>();
		results.add(dto);
	}

}
