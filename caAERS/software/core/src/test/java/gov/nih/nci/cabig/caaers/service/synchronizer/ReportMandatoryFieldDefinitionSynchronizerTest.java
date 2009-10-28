package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;


/**
 * @author Sameer Sawant
 */
public class ReportMandatoryFieldDefinitionSynchronizerTest extends AbstractTestCase{
	ReportDefinition xmlReportDefinition;
	ReportDefinition dbReportDefinition;
	ReportMandatoryFieldDefinitionSynchronizer synchronizer;
	DomainObjectImportOutcome<ReportDefinition> outcome;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		synchronizer = new ReportMandatoryFieldDefinitionSynchronizer();
		outcome = new DomainObjectImportOutcome<ReportDefinition>();
		
		xmlReportDefinition = Fixtures.createReportDefinition("test");
		dbReportDefinition = Fixtures.createReportDefinition("test");
	}
	
	public void testMigrate(){
		ReportMandatoryFieldDefinition rmfd1 = new ReportMandatoryFieldDefinition("fieldPath1", Mandatory.OPTIONAL);
		ReportMandatoryFieldDefinition rmfd2 = new ReportMandatoryFieldDefinition("fieldPath2", Mandatory.MANDATORY);
		xmlReportDefinition.addReportMandatoryFieldDefinition(rmfd1);
		xmlReportDefinition.addReportMandatoryFieldDefinition(rmfd2);
		
		synchronizer.migrate(dbReportDefinition, xmlReportDefinition, outcome);
		
		assertEquals("Incorrect number of mandatory fields", 2, dbReportDefinition.getMandatoryFields().size());
	}
}