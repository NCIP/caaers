package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author biju
 *
 */
public class RoutingAndReviewSearchResultsDTO {
	private Participant participant;
	private Study study;
	private String header;
	private boolean studyCentric;
	
	
	Map<Integer, RoutingAndReviewSearchResultDTO> resultMap;
	Map<Integer, RoutingAndReviewSearchResultDTO> filteredResultMap;

	public RoutingAndReviewSearchResultsDTO(boolean studyCentric, Participant participant, Study study, List<AdverseEventReportingPeriodDTO> list) {
		super();
		this.participant = participant;
		this.study = study;
		this.studyCentric = studyCentric;
		
		if(studyCentric){
			this.header = study.getShortTitle() + " , " + study.getPrimaryIdentifierValue();
		}else{
			this.header = participant.getFullName() + ", "  + participant.getPrimaryIdentifierValue();
		}
		
		resultMap = new LinkedHashMap<Integer, RoutingAndReviewSearchResultDTO>();
		filteredResultMap = new LinkedHashMap<Integer, RoutingAndReviewSearchResultDTO>();
		populateResults(list);
	}
	
	public void populateResults(List<AdverseEventReportingPeriodDTO> list){
		for(AdverseEventReportingPeriodDTO rpDTO : list){
			if(studyCentric){
				//group by participant
				Participant groupedParticipant = rpDTO.getParticipant();
				RoutingAndReviewSearchResultDTO resultDto = null;
				if(resultMap.containsKey(groupedParticipant.getId())){
					resultDto = resultMap.get(groupedParticipant.getId());
				}else {
					resultDto = new RoutingAndReviewSearchResultDTO();
					String strHeader = groupedParticipant.getFullName() + ", " + groupedParticipant.getPrimaryIdentifierValue();
					resultDto.setHeader(strHeader);
					resultMap.put(groupedParticipant.getId(), resultDto); //add it to the map
				}
				resultDto.addResult(rpDTO);
			}else {
				//group by study
				Study groupedStudy = rpDTO.getStudy();
				RoutingAndReviewSearchResultDTO resultDto = null;
				if(resultMap.containsKey(groupedStudy.getId())){
					resultDto = resultMap.get(groupedStudy.getId());
				}else{
					resultDto = new RoutingAndReviewSearchResultDTO();
					String strHeader = groupedStudy.getShortTitle() + ", " + groupedStudy.getPrimaryIdentifierValue();
					resultDto.setHeader(strHeader);
					resultMap.put(groupedStudy.getId(), resultDto); //add it to the map
				}
				
				resultDto.addResult(rpDTO);
			}
		}
	}
	
	public void filterResultMap(int startIndex, int endIndex){
		int i = 0;
		filteredResultMap = new LinkedHashMap<Integer, RoutingAndReviewSearchResultDTO>();
		for(Integer id: resultMap.keySet()){
			RoutingAndReviewSearchResultDTO resultsDto = resultMap.get(id);
			for(AdverseEventReportingPeriodDTO dto : resultsDto.getResults()){
				if(i >= startIndex && i <= endIndex){
					RoutingAndReviewSearchResultDTO filteredResultDto = null;
					if(filteredResultMap.containsKey(id)){
						filteredResultDto = filteredResultMap.get(id);
					}else {
						filteredResultDto = new RoutingAndReviewSearchResultDTO();
						String strHeader = dto.getParticipant().getFullName() + ", " + dto.getParticipant().getPrimaryIdentifierValue();
						filteredResultDto.setHeader(strHeader);
						filteredResultMap.put(id, filteredResultDto); //add it to the map
					}
					filteredResultDto.addResult(dto);
				}
				if (++i > endIndex) break;
			}
		}
	}
	
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public boolean isStudyCentric() {
		return studyCentric;
	}

	public void setStudyCentric(boolean studyCentric) {
		this.studyCentric = studyCentric;
	}
    
	public Map<Integer, RoutingAndReviewSearchResultDTO> getResultMap() {
		return resultMap;
	}
	public void setResultMap(
			Map<Integer, RoutingAndReviewSearchResultDTO> resultMap) {
		this.resultMap = resultMap;
	}
	
	public Map<Integer, RoutingAndReviewSearchResultDTO> getFilteredResultMap(){
		return filteredResultMap;
	}
	
	public void setFilteredResultMap(Map<Integer, RoutingAndReviewSearchResultDTO> filteredResultMap){
		this.filteredResultMap = filteredResultMap;
	}
	
	public int getResultCount(){
		return resultMap.size();
	}
	
	public int getTotalResultCount(){
		int totalNumberOfResult = 0;
		if(resultMap != null){
			for(Integer id: resultMap.keySet()){
				totalNumberOfResult += resultMap.get(id).getResults().size();
			}
		}
		return totalNumberOfResult;
	}
	
}
