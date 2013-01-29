package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/14/13
 */
public class ReportMigratorTest extends TestCase {

	 ReportMigrator migrator;
	    ExpeditedAdverseEventReport src;
	    ExpeditedAdverseEventReport dest;

	    public void setUp() throws Exception {
	        migrator = new ReportMigrator();
	        src = new ExpeditedAdverseEventReport();
	        dest = new ExpeditedAdverseEventReport();
	        
	        Report rpt = Fixtures.createReport("CTEP 24 Hour Report");
	        Organization org = Fixtures.createOrganization("Mayo Clinic", 26);
	        ReportDefinition rptDef = Fixtures.createReportDefinition("CTEP 24 hour Report Definition", org, null);
	        rpt.setReportDefinition(rptDef);
	        
	    }


	    public void testMigrateWithValues() throws Exception {
	    	DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
	        migrator.migrate(src,dest, outCome);
	        assertTrue(outCome.hasErrors());
	    }
}
