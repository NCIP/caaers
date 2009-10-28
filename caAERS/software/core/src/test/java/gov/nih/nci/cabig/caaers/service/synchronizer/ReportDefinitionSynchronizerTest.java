package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportType;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import groovy.mock.interceptor.MockFor;


/**
 * Tests the Synch of Study Attributes.
 * @author Sameer Sawant
 *
 */
public class ReportDefinitionSynchronizerTest extends AbstractTestCase{
	
	ReportDefinition dbReportDefinition;
	ReportDefinition xmlReportDefinition;
	ReportDefinitionSynchronizer synchronizer;
	DomainObjectImportOutcome<ReportDefinition> outcome;
	ConfigPropertyDao configPropertyDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		synchronizer = new ReportDefinitionSynchronizer();
		outcome = new DomainObjectImportOutcome<ReportDefinition>();
		dbReportDefinition = Fixtures.createReportDefinition("test");
		xmlReportDefinition = Fixtures.createReportDefinition("test");
		configPropertyDao = registerMockFor(ConfigPropertyDao.class);

		synchronizer.setConfigPropertyDao(configPropertyDao);
	}
	
	public void testPreMigrateReportDefinitionSynchronizer(){
		xmlReportDefinition.setName("xmlName");
		xmlReportDefinition.setLabel("xmlLabel");
		xmlReportDefinition.setDescription("xmlDescription");
		xmlReportDefinition.setAmendable(true);
		xmlReportDefinition.setDuration(25);
		xmlReportDefinition.setPhysicianSignOff(true);
		xmlReportDefinition.setTimeScaleUnitType(TimeScaleUnit.HOUR);
		Organization xmlOrganization = Fixtures.createOrganization("xmlOrganization");
		xmlReportDefinition.setOrganization(xmlOrganization);
		xmlReportDefinition.setAttributionRequired(true);
		xmlReportDefinition.setGroup(null);
		xmlReportDefinition.setReportType(ReportType.REPORT);
		xmlReportDefinition.setReportFormatType(ReportFormatType.ADEERSPDF);
		
		synchronizer.migrate(xmlReportDefinition, dbReportDefinition, outcome);
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals("Attribute 'name' updated incorrectly", "xmlName", dbReportDefinition.getName());
		assertEquals("Attribute 'label' updated incorrectly", "xmlLabel", dbReportDefinition.getLabel());
		assertEquals("Attribute 'description' updated incorrectly", "xmlDescription", dbReportDefinition.getDescription());
		assertEquals("Attribute 'amendable' updated incorrectly", new Boolean(true), dbReportDefinition.getAmendable());
		assertEquals("Attribute 'duration' updated incorrectly", new Integer(25), dbReportDefinition.getDuration());
		assertEquals("Attribute 'physicianSignOff updated incorrectly", new Boolean(true), dbReportDefinition.getPhysicianSignOff());
		assertEquals("Attribute 'timeScaleUnitType' updated incorrectly", TimeScaleUnit.HOUR, dbReportDefinition.getTimeScaleUnitType());
		assertEquals("Attribute 'organization' updated incorrectly", xmlOrganization.getName(), dbReportDefinition.getOrganization().getName());
		assertEquals("Attribute 'attributionRequired' updated incorrectly", new Boolean(true), dbReportDefinition.getAttributionRequired());
		assertEquals("Attribute 'reportType' updated incorrectly", ReportType.REPORT, dbReportDefinition.getReportType());
		assertEquals("Attribute reportFormatType' updated incorrectly", ReportFormatType.ADEERSPDF, dbReportDefinition.getReportFormatType());
	}
	
}