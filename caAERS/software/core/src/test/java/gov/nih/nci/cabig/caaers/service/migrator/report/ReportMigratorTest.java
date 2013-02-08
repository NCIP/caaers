package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
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
	        
	        ReportDeliveryDefinition deliveryDef =  Fixtures.createReportDeliveryDefinition("url", 2);
	        List<ReportDeliveryDefinition> deliveryDefinitions = new ArrayList<ReportDeliveryDefinition>();
	        deliveryDefinitions.add(deliveryDef);
	        rptDef.setDeliveryDefinitionsInternal(deliveryDefinitions);
	    }


	    public void testMigrateWithValues() throws Exception {
	    	DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
	        migrator.migrate(src,dest, outCome);
	        assertTrue(outCome.hasErrors());
	    }
}
