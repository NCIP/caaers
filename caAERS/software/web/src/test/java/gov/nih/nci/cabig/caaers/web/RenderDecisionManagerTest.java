package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */


public class RenderDecisionManagerTest extends WebTestCase {

	RenderDecisionManager mgr = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mgr = new RenderDecisionManager();
	}
	public void testCanRenderSection() {
		assertTrue(mgr.canRenderSection("", request, response));
	}

	public void testCanRenderField() {
		assertTrue(mgr.canRenderField("", request, response));
	}

	public void testFindActualName() {
		
		String newName = mgr.findActualName("biju.joseph");
		assertEquals("biju.joseph", newName);
		
		newName = mgr.findActualName("biju.");
		assertEquals("biju.", newName);
		
		newName = mgr.findActualName("biju[15].name.abc");
		assertEquals("biju[].name.abc", newName);
		
		newName = mgr.findActualName("biju.name[13]");
		assertEquals("biju.name[]", newName);
		
		newName = mgr.findActualName("biju[6].joseph[7].name");
		assertEquals("biju[].joseph[].name", newName);
	}
	
	
	public void testMandatoryFieldRenderability(){
		List< ReportMandatoryFieldDefinition> mflistA = new ArrayList<ReportMandatoryFieldDefinition>();
		List< ReportMandatoryFieldDefinition> mflistB = new ArrayList<ReportMandatoryFieldDefinition>();
		ReportDefinition rd1 = Fixtures.createReportDefinition("a");
		ReportDefinition rd2 = Fixtures.createReportDefinition("b");
		
		mflistA.add(Fixtures.createMandatoryField("a.k[" + 1 + "].xz", RequirednessIndicator.NA));
		mflistA.add(Fixtures.createMandatoryField("a.k[" + 3 + "].xy", RequirednessIndicator.OPTIONAL));
		mflistA.add(Fixtures.createMandatoryField("c.k[" + 2 + "].x", RequirednessIndicator.MANDATORY));
		rd1.setMandatoryFields(mflistA);

		mflistB.add(Fixtures.createMandatoryField("a.k[" + 1 + "].xz", RequirednessIndicator.NA));
		mflistB.add(Fixtures.createMandatoryField("a.k[" + 3 + "].xy", RequirednessIndicator.NA));
		mflistB.add(Fixtures.createMandatoryField("b.k[" + 2 + "].x", RequirednessIndicator.NA));
		mflistB.add(Fixtures.createMandatoryField("z.k[].x", RequirednessIndicator.NA));
		rd2.setMandatoryFields(mflistB);

        Report r1 = rd1.createReport();
        Report r2 = rd2.createReport();
        Fixtures.updateMandatoryFields(rd1, r1);
        Fixtures.updateMandatoryFields(rd2, r2);

		List<Report> rdList = new ArrayList<Report>();
		rdList.add(r1);
		rdList.add(r2);


		mgr.updateRenderDecision(rdList);
		
		assertFalse(mgr.canRenderField("aeReport.a.k[1].xz", request, response));
		assertFalse(mgr.canRenderField("aeReport.a.k[].xz", request, response));
		assertTrue(mgr.canRenderField("aeReport.a.k[3].xy", request, response));
		assertTrue(mgr.canRenderField("aeReport.c.k[2].x", request, response));
		assertFalse(mgr.canRenderField("aeReport.b.k[" + 2 + "].x", request, response));
		
	}
	
}
