package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
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
	private boolean participantCentric;
	
	
	Map<String, RoutingAndReviewSearchResultDTO> resultMap;
	Map<String, RoutingAndReviewSearchResultDTO> filteredResultMap;
	Map<Integer, List<AdverseEventReportingPeriodDTO>> studyToDTOListMap;

	public RoutingAndReviewSearchResultsDTO(boolean studyCentric, boolean participantCentric, Participant participant, Study study, List<AdverseEventReportingPeriodDTO> list) {
		super();
		this.participant = participant;
		this.study = study;
		this.studyCentric = studyCentric;
		this.participantCentric = participantCentric;
		
		if(studyCentric){
			this.header = "(" + study.getPrimaryIdentifierValue() + ") " + study.getShortTitle() ;
		}else if(participantCentric){
			this.header = "(" + participant.getPrimaryIdentifierValue() + ") " + participant.getFullName() ;
		}
		
		resultMap = new LinkedHashMap<String, RoutingAndReviewSearchResultDTO>();
		filteredResultMap = new LinkedHashMap<String, RoutingAndReviewSearchResultDTO>();
		studyToDTOListMap = new LinkedHashMap<Integer, List<AdverseEventReportingPeriodDTO>>();
		populateResults(list);
		if(!studyCentric && !participantCentric)
			populateNeitherStudyNorParticipantCentricResults(list);
	}
	
	public void populateNeitherStudyNorParticipantCentricResults(List<AdverseEventReportingPeriodDTO> list){
		//First populate a map with
		for(AdverseEventReportingPeriodDTO rpDTO:list){
			RoutingAndReviewSearchResultDTO resultDTO = null;
			if(studyToDTOListMap.containsKey(rpDTO.getStudy().getId())){
				List<AdverseEventReportingPeriodDTO> searchResultList = studyToDTOListMap.get(rpDTO.getStudy().getId());
				searchResultList.add(rpDTO);
			}else{
				List<AdverseEventReportingPeriodDTO> searchResultList = new ArrayList<AdverseEventReportingPeriodDTO>();
				searchResultList.add(rpDTO);
				studyToDTOListMap.put(rpDTO.getStudy().getId(), searchResultList);
			}
		}
		// Now populate the resultMap from studyToDTOListMap
		for(Integer id: studyToDTOListMap.keySet()){
			List<AdverseEventReportingPeriodDTO> subList = studyToDTOListMap.get(id);
			// This flag is to keep a check if the groupHeader is populated for the first record.
			// Set the group header only for the first item. groupHeaderAdded flag is used to track this.
			boolean groupHeaderAdded = false;
			for(AdverseEventReportingPeriodDTO rpDTO: subList){
				Participant groupedParticipant = rpDTO.getParticipant();
				Study groupedStudy = rpDTO.getStudy();
				RoutingAndReviewSearchResultDTO resultDto = null;
				String combinedId = groupedStudy.getId() + "-" + groupedParticipant.getId();
				if(resultMap.containsKey(combinedId)){
					resultDto = resultMap.get(combinedId);
				}else{
					resultDto = new RoutingAndReviewSearchResultDTO();
					String participantHeader = "(" + groupedParticipant.getPrimaryIdentifierValue() + ") " + groupedParticipant.getFullName();
					resultDto.setHeader(participantHeader);
					String studyHeader = "(" + groupedStudy.getPrimaryIdentifierValue() + ") " + groupedStudy.getShortTitle();
					if(!groupHeaderAdded){
						resultDto.setGroupHeader(studyHeader);
						groupHeaderAdded = true;
					}
					resultMap.put(combinedId, resultDto);
				}
				resultDto.addResult(rpDTO);
			}
		}
	}
	
	public void populateResults(List<AdverseEventReportingPeriodDTO> list){
		for(AdverseEventReportingPeriodDTO rpDTO : list){
			if(studyCentric){
				//group by participant
				Participant groupedParticipant = rpDTO.getParticipant();
				RoutingAndReviewSearchResultDTO resultDto = null;
				if(resultMap.containsKey(groupedParticipant.getId().toString())){
					resultDto = resultMap.get(groupedParticipant.getId().toString());
				}else {
					resultDto = new RoutingAndReviewSearchResultDTO();
					String strHeader = "(" + groupedParticipant.getPrimaryIdentifierValue() + ") " + groupedParticipant.getFullName();
					resultDto.setHeader(strHeader);
					resultMap.put(groupedParticipant.getId().toString(), resultDto); //add it to the map
				}
				resultDto.addResult(rpDTO);
			}else if(participantCentric){
				//group by study
				Study groupedStudy = rpDTO.getStudy();
				RoutingAndReviewSearchResultDTO resultDto = null;
				if(resultMap.containsKey(groupedStudy.getId().toString())){
					resultDto = resultMap.get(groupedStudy.getId().toString());
				}else{
					resultDto = new RoutingAndReviewSearchResultDTO();
					String strHeader =  "(" + groupedStudy.getPrimaryIdentifierValue() + ") " + groupedStudy.getShortTitle() ;
					resultDto.setHeader(strHeader);
					resultMap.put(groupedStudy.getId().toString(), resultDto); //add it to the map
				}
				
				resultDto.addResult(rpDTO);
			}
		}
	}
	
	/**
	 * This will go through the original search results, 
	 * Will pick the first entry in the result, then loops through it,
	 * Will only add the RoutingAndReviewSearchResultDTO, starting from startIndex, till endIndex.
	 * 
	 * @param startIndex
	 * @param endIndex
	 */
	public void filterResultMap(int startIndex, int endIndex){
		int i = 0;
		filteredResultMap = new LinkedHashMap<String, RoutingAndReviewSearchResultDTO>();
		
		for(String id: resultMap.keySet()){
			RoutingAndReviewSearchResultDTO resultsDto = resultMap.get(id);
			
			for(AdverseEventReportingPeriodDTO dto : resultsDto.getResults()){
				
				if(i >= startIndex && i <= endIndex){
					RoutingAndReviewSearchResultDTO filteredResultDto = null;
					if(filteredResultMap.containsKey(id)){
						filteredResultDto = filteredResultMap.get(id);
					}else {
						filteredResultDto = new RoutingAndReviewSearchResultDTO();
						filteredResultDto.setHeader(resultsDto.getHeader());
						filteredResultDto.setGroupHeader(resultsDto.getGroupHeader());
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
    
	public Map<String, RoutingAndReviewSearchResultDTO> getResultMap() {
		return resultMap;
	}
	public void setResultMap(
			Map<String, RoutingAndReviewSearchResultDTO> resultMap) {
		this.resultMap = resultMap;
	}
	
	public Map<String, RoutingAndReviewSearchResultDTO> getFilteredResultMap(){
		return filteredResultMap;
	}
	
	public void setFilteredResultMap(Map<String, RoutingAndReviewSearchResultDTO> filteredResultMap){
		this.filteredResultMap = filteredResultMap;
	}
	
	public Map<Integer, List<AdverseEventReportingPeriodDTO>> getStudyToDTOListMap(){
		return studyToDTOListMap;
	}
	
	public void setStudyToDTOListMap(Map<Integer, List<AdverseEventReportingPeriodDTO>> studyToDTOListMap){
		this.studyToDTOListMap = studyToDTOListMap;
	}
	
	public int getResultCount(){
		return resultMap.size();
	}
	
	public int getTotalResultCount(){
		int totalNumberOfResult = 0;
		if(resultMap != null){
			for(String id: resultMap.keySet()){
				totalNumberOfResult += resultMap.get(id).getResults().size();
			}
		}
		return totalNumberOfResult;
	}
	
}
