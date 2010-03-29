package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import junit.framework.TestCase;

/**
 * This class tests - RoutingAndReviewSearchResultsDTO.java
 * @author Sameer Sawant
 */
public class RoutingAndReviewSearchResultsDTOTest extends TestCase {
	RoutingAndReviewSearchResultsDTO dto;
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testPopulateResultsStudyCentric() throws Exception{
		Study study = Fixtures.createStudy("test study");
		Participant participant = Fixtures.createParticipant("fName", "lName");
		Boolean studyCentric = true;
		Boolean participantCentric = false;
		List<AdverseEventReportingPeriodDTO> list = createStudyCentricList(study);
		RoutingAndReviewSearchResultsDTO dto = new RoutingAndReviewSearchResultsDTO(studyCentric, participantCentric, participant, study, list);
		assertEquals("Incorrect number of entries in resultMap", 4, dto.getResultCount());
		assertEquals("Incorrect number of AdverseEventReportingPeriodDTO entered in resultMap", 20, dto.getTotalResultCount());
	}
	
	public void testPopulateResultsParticipantCentric() throws Exception{
		//Study study = Fixtures.createStudy("test study");
		Participant participant = Fixtures.createParticipant("fName", "lName");
		Boolean studyCentric = false;
		Boolean participantCentric = true;
		List<AdverseEventReportingPeriodDTO> list = createParticipantCentricList(participant);
		RoutingAndReviewSearchResultsDTO dto = new RoutingAndReviewSearchResultsDTO(studyCentric, participantCentric, participant, null, list);
		assertEquals("Incorrect number of entries in resultMap", 4, dto.getResultCount());
		assertEquals("Incorrect number of AdverseEventReportingPeriodDTO entered in resultMap", 20, dto.getTotalResultCount());
	}
	
	public void testPopulateNeitherStudyNorParticipantCentricResults() throws Exception{
		Boolean studyCentric = false;
		Boolean participantCentric = false;
		List<AdverseEventReportingPeriodDTO> list = createNeitherStudyNorParticipantCentricStudy();
		RoutingAndReviewSearchResultsDTO dto = new RoutingAndReviewSearchResultsDTO(studyCentric, participantCentric, null, null, list);
		assertEquals("Incorrect number of entries in studyToDTOListMap", 4, dto.getStudyToDTOListMap().keySet().size());
		assertEquals("Incorrect number of entries in resultMap", 16, dto.getResultCount());
		assertEquals("Incorrect number of AdverseEventReportingPeriodDTO entered in resultMap", 80, dto.getTotalResultCount());
	}
	
	public void testFilterResultMap() throws Exception{
		Study study = Fixtures.createStudy("test study");
		Participant participant = Fixtures.createParticipant("fName", "lName");
		Boolean studyCentric = true;
		Boolean participantCentric = false;
		List<AdverseEventReportingPeriodDTO> list = createStudyCentricList(study);
		RoutingAndReviewSearchResultsDTO dto = new RoutingAndReviewSearchResultsDTO(studyCentric, participantCentric, participant, study, list);
		dto.filterResultMap(5, 12);
		assertEquals("filteredResultsMap created incorrectly", 2, dto.getFilteredResultMap().size());
	}
	
	private List<AdverseEventReportingPeriodDTO> createStudyCentricList(Study study){
		List<AdverseEventReportingPeriodDTO> list = new ArrayList<AdverseEventReportingPeriodDTO>();
		int i = 1;
		for(int j = 1; j < 5; j++){
			Participant participant = Fixtures.createParticipant("first-" + j, "last-" + j);
			participant.setId(j);
			List<AdverseEventReportingPeriodDTO> subList = createList(study, participant, i, i + 5);
			i = i + 6;
			list.addAll(subList);
		}
		return list;
	}
	
	private List<AdverseEventReportingPeriodDTO> createParticipantCentricList(Participant participant){
		List<AdverseEventReportingPeriodDTO> list = new ArrayList<AdverseEventReportingPeriodDTO>();
		int i = 1;
		for(int j = 1; j < 5; j++){
			Study study = Fixtures.createStudy("title - " + j);
			study.setId(j);
			List<AdverseEventReportingPeriodDTO> subList = createList(study, participant, i, i + 5);
			i = i + 6;
			list.addAll(subList);
		}
		return list;
	}
	
	private List<AdverseEventReportingPeriodDTO> createNeitherStudyNorParticipantCentricStudy(){
		List<AdverseEventReportingPeriodDTO> list = new ArrayList<AdverseEventReportingPeriodDTO>();
		for(int j = 1; j < 5; j++){
			Study study = Fixtures.createStudy("title - " + j);
			study.setId(j);
			List<AdverseEventReportingPeriodDTO> subList = createStudyCentricList(study);
			list.addAll(subList);
		}
		return list;
	}
	
	private List<AdverseEventReportingPeriodDTO> createList(Study study, Participant participant, int start, int end){
		List<AdverseEventReportingPeriodDTO> list = new ArrayList<AdverseEventReportingPeriodDTO>();
		for(int i = start; i < end; i++){
			AdverseEventReportingPeriodDTO dto = new AdverseEventReportingPeriodDTO();
			dto.setStudy(study);
			dto.setParticipant(participant);
			dto.setId(i);
			list.add(dto);
		}
		return list;
	}
}