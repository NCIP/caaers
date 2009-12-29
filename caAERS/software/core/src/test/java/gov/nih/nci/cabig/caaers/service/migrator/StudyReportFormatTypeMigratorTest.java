package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyReportFormatTypeMigratorTest extends AbstractTestCase {

	StudyReportFormatTypeMigrator migrator;
	Study xstreamStudy;
	DomainObjectImportOutcome<Study> outcome;
	Study dest;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dest = new LocalStudy();
		xstreamStudy = Fixtures.createStudy("Short Title");
		outcome = new DomainObjectImportOutcome<Study>();
		migrator = new StudyReportFormatTypeMigrator();
	}


	public void testMigrate() {
		xstreamStudy.addReportFormatType(ReportFormatType.ADEERSPDF);
		xstreamStudy.addReportFormatType(ReportFormatType.MEDWATCHPDF);
		xstreamStudy.addReportFormatType(ReportFormatType.CAAERSXML);
		migrator.migrate(xstreamStudy, dest, outcome);
		assertEquals(3, xstreamStudy.getReportFormats().size());
		assertEquals(3, dest.getReportFormats().size());
		assertTrue("No errors should be there", outcome.getMessages().isEmpty());

	}

	public void testMigrateWithNoReportFormat() {
		migrator.migrate(xstreamStudy, dest, outcome);
		assertEquals(0, dest.getReportFormats().size());
		assertTrue("No errors should be there", outcome.getMessages().isEmpty());
	}

}