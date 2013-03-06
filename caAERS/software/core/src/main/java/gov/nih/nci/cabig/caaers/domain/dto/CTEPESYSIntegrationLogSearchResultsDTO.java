/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.IntegrationLog;

import java.util.ArrayList;
import java.util.List;

 
/**
 * The Class IntegrationLogSearchResultsDTO.
 */
public class CTEPESYSIntegrationLogSearchResultsDTO {
	
	/** The result dto. */
	List<IntegrationLog> results = new ArrayList<IntegrationLog>();
	
	/** The filtered result dto. */
	List<IntegrationLog> filteredResults = new ArrayList<IntegrationLog>();
	
	/**
	 * Instantiates a new report version search results dto.
	 *
	 * @param list the list
	 */
	public CTEPESYSIntegrationLogSearchResultsDTO (List<IntegrationLog> list) {		
		populateResults(list);
	}
	
	public List<IntegrationLog> getResults() {
		if(results == null){
			return new ArrayList<IntegrationLog>();
		}
		return results;
	}

	public void setResults(List<IntegrationLog> results) {
		this.results = results;
	}

	public List<IntegrationLog> getFilteredResults() {
		return filteredResults;
	}

	public void setFilteredResults(List<IntegrationLog> filteredResults) {
		this.filteredResults = filteredResults;
	}

	/**
	 * Populate results.
	 *
	 * @param list the list
	 */
	public void populateResults(List<IntegrationLog> list) {
		for(IntegrationLog rv : list){
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
		filteredResults = new ArrayList<IntegrationLog>();
		
			for(IntegrationLog result : results){
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

	
	public void addResult(IntegrationLog logDetail){
		getResults().add(logDetail);
	}
	
	public void addFilteredResult(IntegrationLog filteredLogDetail){
		getFilteredResults().add(filteredLogDetail);
	}
}
