package gov.nih.nci.cabig.caaers.service.migrator.report;



import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class RadiationInterventionMigratorTest extends TestCase {

	RadiationInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new RadiationInterventionMigrator();
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

        oi.setStudyTherapyType(StudyTherapyType.RADIATION);

        study.getOtherInterventions().add(oi);

        String desc = "Testing Radiation Intervention Migrator";
        
        RadiationIntervention ri = Fixtures.createRadiationIntervention(desc, oi, new Date(), "treatment arm","1","Normal Dosage","mg","1",null,"adjustment");
        src.getRadiationInterventions().add(ri);
               
    }

    public void testMigrateWithValues() throws Exception {
    	
    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(1, dest.getRadiationInterventions().size());
    	assertEquals("Testing Radiation Intervention Migrator", dest.getRadiationInterventions().get(0).getDescription());
    	assertEquals("mg", dest.getRadiationInterventions().get(0).getDosageUnit());
    }
}
