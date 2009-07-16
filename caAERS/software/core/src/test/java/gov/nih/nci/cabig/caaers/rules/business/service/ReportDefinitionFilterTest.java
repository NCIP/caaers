package gov.nih.nci.cabig.caaers.rules.business.service;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionFilterTest extends TestCase {
	
	ReportDefinitionFilter filter;
	protected void setUp() throws Exception {
		super.setUp();
		filter = new ReportDefinitionFilter();
	}

	public void testAdd() {
		Organization org1 = Fixtures.createOrganization("org1", "org1");
		org1.setId(1);
		Organization org2 = Fixtures.createOrganization("org2", "org2");
		org2.setId(2);
		
		ConfigProperty cp1 = Fixtures.createConfigProperty("cp1");
		ConfigProperty cp2 = Fixtures.createConfigProperty("cp2");
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org1, cp1);
		rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd1.setDuration(4);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("rd2", org1, cp1);
		rd2.setTimeScaleUnitType(TimeScaleUnit.HOUR);
		rd2.setDuration(24);
		
		ReportDefinition rd3 = Fixtures.createReportDefinition("rd3", org1, cp1);
		rd3.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd3.setDuration(3);
		
		ReportDefinition rd4 = Fixtures.createReportDefinition("rd4", org1, cp2);
		rd4.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd4.setDuration(1);
		
		ReportDefinition rd5 = Fixtures.createReportDefinition("rd5", org1, cp2);
		rd5.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd5.setDuration(4);
		
		ReportDefinition rd6 = Fixtures.createReportDefinition("rd5", org2, cp1);
		rd6.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd6.setDuration(1);
		
		ReportDefinition rd7 = Fixtures.createReportDefinition("rd5", org2, cp1);
		rd7.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd7.setDuration(4);
		
		assertTrue(filter.add(rd1));
		assertTrue(filter.add(rd2));
		assertFalse(filter.add(rd3));
		assertTrue(filter.add(rd4));
		assertFalse(filter.add(rd5));
		assertTrue(filter.add(rd6));
		assertFalse(filter.add(rd7));
	}

	public void testGenerateKey() {
		Organization org1 = Fixtures.createOrganization("org1", "org1");
		org1.setId(1);
		ConfigProperty cp1 = Fixtures.createConfigProperty("cp1");
		ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org1, cp1);
		rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd1.setDuration(4);
		assertEquals("1:cp1",  filter.generateKey(rd1));
	}

	public void testFilter() {
		Organization org1 = Fixtures.createOrganization("org1", "org1");
		org1.setId(1);
		Organization org2 = Fixtures.createOrganization("org2", "org2");
		org2.setId(2);
		
		ConfigProperty cp1 = Fixtures.createConfigProperty("cp1");
		ConfigProperty cp2 = Fixtures.createConfigProperty("cp2");
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org1, cp1);
		rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd1.setDuration(4);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("rd2", org1, cp1);
		rd2.setTimeScaleUnitType(TimeScaleUnit.HOUR);
		rd2.setDuration(24);
		
		ReportDefinition rd3 = Fixtures.createReportDefinition("rd3", org1, cp1);
		rd3.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd3.setDuration(3);
		
		ReportDefinition rd4 = Fixtures.createReportDefinition("rd4", org1, cp2);
		rd4.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd4.setDuration(1);
		
		ReportDefinition rd5 = Fixtures.createReportDefinition("rd5", org1, cp2);
		rd5.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd5.setDuration(4);
		
		ReportDefinition rd6 = Fixtures.createReportDefinition("rd5", org2, cp1);
		rd6.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd6.setDuration(1);
		
		ReportDefinition rd7 = Fixtures.createReportDefinition("rd5", org2, cp1);
		rd7.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd7.setDuration(4);
		
		ArrayList<ReportDefinition> rdList = new ArrayList<ReportDefinition>();
		rdList.add(rd1);
		rdList.add(rd2);
		rdList.add(rd3);
		rdList.add(rd4);
		rdList.add(rd5);
		rdList.add(rd6);
		rdList.add(rd7);
		
		List<ReportDefinition> filterdList = filter.filter(rdList);
		assertEquals(3, filterdList.size());
		assertTrue(filterdList.contains(rd2));
		assertTrue(filterdList.contains(rd4));
		assertTrue(filterdList.contains(rd6));
		assertFalse(filterdList.contains(rd1));
	}

}
