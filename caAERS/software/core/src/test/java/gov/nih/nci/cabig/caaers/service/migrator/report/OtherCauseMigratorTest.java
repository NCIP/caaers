package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class OtherCauseMigratorTest extends TestCase {

	OtherCauseMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new OtherCauseMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);
        
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        period.setAssignment(assignment);
        dest.setReportingPeriod(period);
        
        String text = "Testing Other Cause";
        
        OtherCause oc = Fixtures.createOtherCause(text);
        src.getOtherCauses().add(oc);
               
    }


    public void testMigrateWithValues() throws Exception {
    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(1, dest.getOtherCauses().size());
    	assertEquals("Testing Other Cause", dest.getOtherCauses().get(0).getText());
    }
}
