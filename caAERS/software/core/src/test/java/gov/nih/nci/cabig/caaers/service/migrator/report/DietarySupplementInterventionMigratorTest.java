package gov.nih.nci.cabig.caaers.service.migrator.report;


import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class DietarySupplementInterventionMigratorTest extends TestCase {

	DietarySupplementInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new DietarySupplementInterventionMigrator();
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

        oi.setStudyTherapyType(StudyTherapyType.DIETARY_SUPPLEMENT);
        study.getOtherInterventions().add(oi);
        String desc = "Testing Dietary Supplement Intervention Migrator";
        
        DietarySupplementIntervention dsi = Fixtures.createDietarySupplementIntervention(desc, oi);
        src.getDietaryInterventions().add(dsi);
    }


    public void testMigrateWithValues() throws Exception {
    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals("Testing Dietary Supplement Intervention Migrator", dest.getDietaryInterventions().get(0).getDescription());
    	assertEquals(1, dest.getDietaryInterventions().size());
    }
}
