package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;

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
		ReportDTO reportDTO1 = new ReportDTO();
		List<String> actions = new ArrayList<String>();
		actions.add("something");
		reportDTO1.setPossibleActions(actions);
		dto.addAdverseEventAeReportDTO(ae1);
		ae1.addReportDTO(reportDTO1);
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
	
	public void testGetActiveAeReports() {
		ExpeditedAdverseEventReportDTO aeReport1 = new ExpeditedAdverseEventReportDTO();
		ReportDTO report1 = new ReportDTO();
		report1.setStatus(ReportStatus.AMENDED);
		ReportDTO report2 = new ReportDTO();
		report2.setStatus(ReportStatus.WITHDRAWN);
		aeReport1.addReportDTO(report1);
		aeReport1.addReportDTO(report2);
		dto.addAdverseEventAeReportDTO(aeReport1);
		ExpeditedAdverseEventReportDTO aeReport2 = new ExpeditedAdverseEventReportDTO();
		report1 = new ReportDTO();
		report1.setStatus(ReportStatus.PENDING);
		aeReport2.addReportDTO(report1);
		dto.addAdverseEventAeReportDTO(aeReport2);
		assertEquals("getAeReports returned incorrect list of aeReports", 2, dto.getAeReports().size());
		assertEquals("getActiveAeReports returned incorrect list of aeReports", 1, dto.getActiveAeReports().size());
	}
}
