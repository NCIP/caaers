package gov.nih.nci.cabig.caaers.service.migrator.report;



import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class SurgeryInterventionMigratorTest extends TestCase {

	SurgeryInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new SurgeryInterventionMigrator();
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

        OtherIntervention oi = new OtherIntervention();
        oi.setId(-100);
        oi.setName("Other Radiation Name");
        oi.setDescription("Other Radiation Description");
        oi.setRetiredIndicator(false);

        oi.setStudyTherapyType(StudyTherapyType.SURGERY);

        study.getOtherInterventions().add(oi);

        String desc = "Testing Surgery Intervention Migrator";


        SurgeryIntervention si = Fixtures.createSurgeryIntervention(desc, null, "treatment Arm", oi);
        src.getSurgeryInterventions().add(si);
               
    }

    public void testMigrateWithValues() throws Exception {
    // TODO : Fixed Later
    //	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    //	assertEquals(1, dest.getSurgeryInterventions().size());
   // 	assertEquals("Testing Surgery Intervention Migrator", dest.getSurgeryInterventions().get(0).getDescription());
    //	assertEquals("treatment Arm", dest.getSurgeryInterventions().get(0).getTreatmentArm());
    }
}
