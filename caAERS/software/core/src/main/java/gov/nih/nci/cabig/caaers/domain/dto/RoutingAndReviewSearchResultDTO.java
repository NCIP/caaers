package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.ArrayList;
import java.util.List;

 
/**
 * The Class RoutingAndReviewSearchResultDTO.
 */
public class RoutingAndReviewSearchResultDTO {
	
	/** The header. */
	private String header;
	
	/** The group header. */
	private String groupHeader;
	
	/** The results. */
	List<AdverseEventReportingPeriodDTO> results;
	
	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}
	
	/**
	 * Sets the header.
	 *
	 * @param header the new header
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<AdverseEventReportingPeriodDTO> getResults() {
		return results;
	}
	
	/**
	 * Sets the group header.
	 *
	 * @param groupHeader the new group header
	 */
	public void setGroupHeader(String groupHeader){
		this.groupHeader = groupHeader;
	}
	
	/**
	 * Gets the group header.
	 *
	 * @return the group header
	 */
	public String getGroupHeader(){
		return groupHeader;
	}
	
	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<AdverseEventReportingPeriodDTO> results) {
		this.results = results;
	}
	
	/**
	 * Adds the result.
	 *
	 * @param dto the dto
	 */
	public void addResult(AdverseEventReportingPeriodDTO dto){
		if(results == null) results = new ArrayList<AdverseEventReportingPeriodDTO>();
		results.add(dto);
	}
}
