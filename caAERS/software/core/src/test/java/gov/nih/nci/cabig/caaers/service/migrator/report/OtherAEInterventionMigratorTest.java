package gov.nih.nci.cabig.caaers.service.migrator.report;



import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class OtherAEInterventionMigratorTest extends TestCase {

	OtherAEInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new OtherAEInterventionMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();

        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");

        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);

        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        period.setAssignment(assignment);
        assignment.setStudySite(site);
        assignment.getStudySite().setStudy(study);

        //Set the destination.

        dest.setReportingPeriod(period);
        //Set the destination.

        dest.setReportingPeriod(period);
        OtherIntervention oi = new OtherIntervention();
        oi.setId(-100);
        oi.setName("Other Behavioral Name");
        oi.setDescription("Other Behavioral Description");
        oi.setRetiredIndicator(false);

        oi.setStudyTherapyType(StudyTherapyType.OTHER);

        study.getOtherInterventions().add(oi);

        String desc = "Testing OtherAE Intervention Migrator";
        
        OtherAEIntervention oai = Fixtures.createOtherAEIntervention(desc, oi);
        src.getOtherAEInterventions().add(oai);
               
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(1, dest.getOtherAEInterventions().size());
    	assertEquals("Testing OtherAE Intervention Migrator", dest.getOtherAEInterventions().get(0).getDescription());
    }
}
