package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class AdverseEventReportingPeriodDTOTest extends TestCase {
	AdverseEventReportingPeriodDTO dto;
	
	protected void setUp() throws Exception {
		super.setUp();
		dto = new AdverseEventReportingPeriodDTO();
		
	}

	public void testHasActionsToDo() {
		assertFalse(dto.hasActionsToDo());
	}
	
	public void testHasActionsToDo_WhenReportingPeriodHasOne(){
		List<String> actions = new ArrayList<String>();
		actions.add("something");
		dto.setPossibleActions(actions);
		assertTrue(dto.hasActionsToDo());
	}
	
	public void testHasActionsToDo_IfAeReportHasOne() {
		ExpeditedAdverseEventReportDTO ae1 = new ExpeditedAdverseEventReportDTO();
		List<String> actions = new ArrayList<String>();
		actions.add("something");
		ae1.setPossibleActions(actions);
		dto.addAdverseEventAeReportDTO(ae1);
		assertTrue(dto.hasActionsToDo());
	}
	public void testHasActionsToDo_IfAeReportHasNone() {
		ExpeditedAdverseEventReportDTO ae1 = new ExpeditedAdverseEventReportDTO();
		
		dto.addAdverseEventAeReportDTO(ae1);
		assertFalse(dto.hasActionsToDo());
	}
	public void testHasActionsToDo_WhenReportingPeriodHasOneAndAeReportHasNone(){
		List<String> actions = new ArrayList<String>();
		actions.add("something");
		dto.setPossibleActions(actions);
		assertTrue(dto.hasActionsToDo());
	}
}
