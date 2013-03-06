/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.List;

 
/**
 * The Class ReportVersionSearchResultsDTO.
 */
public class ReportVersionSearchResultsDTO {
	
	/** The result dto. */
	ReportVersionSearchResultDTO resultDto;
	
	/** The filtered result dto. */
	ReportVersionSearchResultDTO filteredResultDto;
	
	/**
	 * Instantiates a new report version search results dto.
	 *
	 * @param list the list
	 */
	public ReportVersionSearchResultsDTO (List<ReportVersion> list) {		
		populateResults(list);
	}
	
	/**
	 * Populate results.
	 *
	 * @param list the list
	 */
	public void populateResults(List<ReportVersion> list) {
		resultDto = new ReportVersionSearchResultDTO();
		for(ReportVersion rv : list){
			resultDto.addResult(rv);
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
		filteredResultDto = new ReportVersionSearchResultDTO();
		
			for(ReportVersion dto : resultDto.getResults()){
				if(i >= startIndex && i <= endIndex){
					filteredResultDto.addResult(dto);
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
		return resultDto.getResults().size();
	}

	/**
	 * Gets the filtered result dto.
	 *
	 * @return the filtered result dto
	 */
	public ReportVersionSearchResultDTO getFilteredResultDto() {
		return filteredResultDto;
	}

	/**
	 * Sets the filtered result dto.
	 *
	 * @param filteredResultDto the new filtered result dto
	 */
	public void setFilteredResultDto(ReportVersionSearchResultDTO filteredResultDto) {
		this.filteredResultDto = filteredResultDto;
	}

	/**
	 * Gets the result dto.
	 *
	 * @return the result dto
	 */
	public ReportVersionSearchResultDTO getResultDto() {
		return resultDto;
	}
}
