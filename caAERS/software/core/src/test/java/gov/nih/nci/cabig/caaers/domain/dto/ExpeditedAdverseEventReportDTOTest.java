package gov.nih.nci.cabig.caaers.domain.dto;

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
		dto.setPossibleActions(actions);
		assertTrue(dto.hasActionsToDo());
	}
	

}
