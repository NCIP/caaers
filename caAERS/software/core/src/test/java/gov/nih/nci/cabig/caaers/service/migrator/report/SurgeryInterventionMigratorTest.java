package gov.nih.nci.cabig.caaers.service.migrator.report;



import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.InterventionSite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
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
        dest.setReportingPeriod(period);
     
        OtherIntervention oi = new OtherIntervention();
        oi.setStudy(study);
        String desc = "Testing Surgery Intervention Migrator";
        
        SurgeryIntervention si = Fixtures.createSurgeryIntervention(desc, null, "treatment Arm", oi);
        src.getSurgeryInterventions().add(si);
               
    }

    public void testMigrateWithValues() throws Exception {
    	
    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals(1, dest.getSurgeryInterventions().size());
    	assertEquals("Testing Surgery Intervention Migrator", dest.getSurgeryInterventions().get(0).getDescription());
    	assertEquals("treatment Arm", dest.getSurgeryInterventions().get(0).getTreatmentArm());
    }
}
