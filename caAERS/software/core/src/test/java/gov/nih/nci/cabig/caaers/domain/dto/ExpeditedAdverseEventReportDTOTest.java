package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ExpeditedAdverseEventReportDTOTest extends TestCase {
	ExpeditedAdverseEventReportDTO dto;
	
	protected void setUp() throws Exception {
		super.setUp();
		dto = new ExpeditedAdverseEventReportDTO();
		
	}

	public void testHasActionsToDo() {
		assertFalse(dto.hasActionsToDo());
	}
	public void testHasActionsToDo_WhenActionIsThere() {
		List<String> actions = new ArrayList<String>();
		actions.add("xyz");
		
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setPossibleActions(actions);
		dto.addReportDTO(reportDTO);
		assertTrue(dto.hasActionsToDo());
	}

	public void testHasWorkflowEnded_WhenNotEnded(){
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setReviewStatus(ReviewStatus.CENTRAL_OFFICE_REVIEW);
		dto.addReportDTO(reportDTO);
		assertFalse("hasWorkflowEnded should have returned false", dto.hasWorkflowEnded());
	}
	
	public void testHasWorkflowEnded_WhenEnded(){
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setReviewStatus(ReviewStatus.SUBMIT_TO_SPONSOR);
		dto.addReportDTO(reportDTO);
		assertTrue("hasWorkflowEnded should have returned true", dto.hasWorkflowEnded());
	}

}
