package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
/**
 * 
 * @author Sameer Sawant
 *
 */
public class ReportDTOTest extends TestCase {
	ReportDTO dto;
	
	protected void setUp() throws Exception {
		super.setUp();
		dto = new ReportDTO();
	}

	public void testHasActionsToDo_NoActions() {
		assertFalse(dto.hasActionsToDo());
	}
	
	public void testHasActionsToDo_WithActions(){
		List<String> actions = new ArrayList<String>();
		actions.add("test action");
		dto.setPossibleActions(actions);
		assertTrue("hasActionToDo should have returned true", dto.hasActionsToDo());
	}

	public void testHasWorkflowEnded_WhenNotEnded(){
		dto.setReviewStatus(ReviewStatus.CENTRAL_OFFICE_REVIEW);
		assertFalse("hasWorkflowEnded should have returned false", dto.hasWorkflowEnded());
	}
	
	public void testHasWorkflowEnded_WhenEnded(){
		dto.setReviewStatus(ReviewStatus.SUBMIT_TO_SPONSOR);
		assertTrue("hasWorkflowEnded should have returned true", dto.hasWorkflowEnded());
	}

}
