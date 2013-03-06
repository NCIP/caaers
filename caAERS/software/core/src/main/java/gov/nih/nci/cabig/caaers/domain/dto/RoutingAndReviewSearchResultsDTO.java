/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 

/**
 * The Class RoutingAndReviewSearchResultsDTO.
 *
 * @author biju
 */
public class RoutingAndReviewSearchResultsDTO {
	
	/** The participant. */
	private Participant participant;
	
	/** The study. */
	private Study study;
	
	/** The header. */
	private String header;
	
	/** The study centric. */
	private boolean studyCentric;
	
	/** The participant centric. */
	private boolean participantCentric;
	
	
	/** The result map. */
	Map<String, RoutingAndReviewSearchResultDTO> resultMap;
	
	/** The filtered result map. */
	Map<String, RoutingAndReviewSearchResultDTO> filteredResultMap;
	
	/** The study to dto list map. */
	Map<Integer, List<AdverseEventReportingPeriodDTO>> studyToDTOListMap;

	/**
	 * Instantiates a new routing and review search results dto.
	 *
	 * @param studyCentric the study centric
	 * @param participantCentric the participant centric
	 * @param participant the participant
	 * @param study the study
	 * @param list the list
	 */
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
	
	/**
	 * Populate neither study nor participant centric results.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Populate results.
	 *
	 * @param list the list
	 */
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
	 * @param startIndex the start index
	 * @param endIndex the end index
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
	
	/**
	 * Gets the participant.
	 *
	 * @return the participant
	 */
	public Participant getParticipant() {
		return participant;
	}

	/**
	 * Sets the participant.
	 *
	 * @param participant the new participant
	 */
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	/**
	 * Gets the study.
	 *
	 * @return the study
	 */
	public Study getStudy() {
		return study;
	}

	/**
	 * Sets the study.
	 *
	 * @param study the new study
	 */
	public void setStudy(Study study) {
		this.study = study;
	}

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
	 * Checks if is study centric.
	 *
	 * @return true, if is study centric
	 */
	public boolean isStudyCentric() {
		return studyCentric;
	}

	/**
	 * Sets the study centric.
	 *
	 * @param studyCentric the new study centric
	 */
	public void setStudyCentric(boolean studyCentric) {
		this.studyCentric = studyCentric;
	}
    
	/**
	 * Gets the result map.
	 *
	 * @return the result map
	 */
	public Map<String, RoutingAndReviewSearchResultDTO> getResultMap() {
		return resultMap;
	}
	
	/**
	 * Sets the result map.
	 *
	 * @param resultMap the result map
	 */
	public void setResultMap(
			Map<String, RoutingAndReviewSearchResultDTO> resultMap) {
		this.resultMap = resultMap;
	}
	
	/**
	 * Gets the filtered result map.
	 *
	 * @return the filtered result map
	 */
	public Map<String, RoutingAndReviewSearchResultDTO> getFilteredResultMap(){
		return filteredResultMap;
	}
	
	/**
	 * Sets the filtered result map.
	 *
	 * @param filteredResultMap the filtered result map
	 */
	public void setFilteredResultMap(Map<String, RoutingAndReviewSearchResultDTO> filteredResultMap){
		this.filteredResultMap = filteredResultMap;
	}
	
	/**
	 * Gets the study to dto list map.
	 *
	 * @return the study to dto list map
	 */
	public Map<Integer, List<AdverseEventReportingPeriodDTO>> getStudyToDTOListMap(){
		return studyToDTOListMap;
	}
	
	/**
	 * Sets the study to dto list map.
	 *
	 * @param studyToDTOListMap the study to dto list map
	 */
	public void setStudyToDTOListMap(Map<Integer, List<AdverseEventReportingPeriodDTO>> studyToDTOListMap){
		this.studyToDTOListMap = studyToDTOListMap;
	}
	
	/**
	 * Gets the result count.
	 *
	 * @return the result count
	 */
	public int getResultCount(){
		return resultMap.size();
	}
	
	/**
	 * Gets the total result count.
	 *
	 * @return the total result count
	 */
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
