package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import junit.framework.TestCase;

public class ReportDefinitionComparatorTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCompare() {
		Organization org1 = Fixtures.createOrganization("org1", "org1");
		org1.setId(1);
		
		ConfigProperty cp1 = Fixtures.createConfigProperty("cp1");
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org1, cp1);
		rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd1.setDuration(4);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("rd2", org1, cp1);
		rd2.setTimeScaleUnitType(TimeScaleUnit.HOUR);
		rd2.setDuration(24);
		
		ReportDefinitionComparator comparator = new ReportDefinitionComparator();
		int i = comparator.compare(rd1, rd2);
		assertTrue(i < 0);
		i = comparator.compare(rd2, rd1);
		assertTrue(i > 0);
		i = comparator.compare(rd1, rd1);
		assertEquals(0, i);
		}

}
