package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.List;

public class ReportVersionSearchResultsDTO {
	
	ReportVersionSearchResultDTO resultDto;
	ReportVersionSearchResultDTO filteredResultDto;
	
	public ReportVersionSearchResultsDTO (List<ReportVersion> list) {		
		populateResults(list);
	}
	
	public void populateResults(List<ReportVersion> list) {
		resultDto = new ReportVersionSearchResultDTO();
		for(ReportVersion rv : list){
			resultDto.addResult(rv);
		}
	}
	
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
	
	public int getResultCount(){
		return resultDto.getResults().size();
	}

	public ReportVersionSearchResultDTO getFilteredResultDto() {
		return filteredResultDto;
	}

	public void setFilteredResultDto(ReportVersionSearchResultDTO filteredResultDto) {
		this.filteredResultDto = filteredResultDto;
	}

	public ReportVersionSearchResultDTO getResultDto() {
		return resultDto;
	}
}
