package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;

import java.util.ArrayList;
import java.util.List;

 
/**
 * The Class IntegrationLogDetailSearchResultsDTO.
 */
public class CTEPESYSIntegrationLogSearchResultsDTO {
	
	/** The result dto. */
	List<IntegrationLogDetail> results = new ArrayList<IntegrationLogDetail>();
	
	/** The filtered result dto. */
	List<IntegrationLogDetail> filteredResults = new ArrayList<IntegrationLogDetail>();
	
	/**
	 * Instantiates a new report version search results dto.
	 *
	 * @param list the list
	 */
	public CTEPESYSIntegrationLogSearchResultsDTO (List<IntegrationLogDetail> list) {		
		populateResults(list);
	}
	
	public List<IntegrationLogDetail> getResults() {
		if(results == null){
			return new ArrayList<IntegrationLogDetail>();
		}
		return results;
	}

	public void setResults(List<IntegrationLogDetail> results) {
		this.results = results;
	}

	public List<IntegrationLogDetail> getFilteredResults() {
		return filteredResults;
	}

	public void setFilteredResults(List<IntegrationLogDetail> filteredResults) {
		this.filteredResults = filteredResults;
	}

	/**
	 * Populate results.
	 *
	 * @param list the list
	 */
	public void populateResults(List<IntegrationLogDetail> list) {
		for(IntegrationLogDetail rv : list){
			addResult(rv);
		}
	}
	
	/**
	 * Filter result.
	 *
	 * @param startIndex the start index
	 * @param endIndex the end index
	 */
	public void filterResult(int startIndex, int endIndex) {
		int i = 0;
		filteredResults = new ArrayList<IntegrationLogDetail>();
		
			for(IntegrationLogDetail result : results){
				if(i >= startIndex && i <= endIndex){
					addFilteredResult(result);
				}
				
				if (++i > endIndex) break;
			}
	}
	
	/**
	 * Gets the result count.
	 *
	 * @return the result count
	 */
	public int getResultCount(){
		return results.size();
	}

	
	public void addResult(IntegrationLogDetail logDetail){
		getResults().add(logDetail);
	}
	
	public void addFilteredResult(IntegrationLogDetail filteredLogDetail){
		getFilteredResults().add(filteredLogDetail);
	}
}
