package gov.nih.nci.cabig.caaers.service.migrator.report;


import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/14/13
 */
public class BehavioralInterventionMigratorTest extends TestCase {

	BehavioralInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new BehavioralInterventionMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
       
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        period.setAssignment(Fixtures.createAssignment());
        dest.setReportingPeriod(period);
        
        OtherIntervention oi = new OtherIntervention();
        oi.setStudy(study);
        String desc = "Testing behavioral Migrator";
        
        
        BehavioralIntervention bi = Fixtures.createBehavioralIntervention(desc, oi);
        src.getBehavioralInterventions().add(bi);
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);       
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals("Testing behavioral Migrator", dest.getBehavioralInterventions().get(0).getDescription());
    	assertEquals(1, dest.getBehavioralInterventions().size());
    }
}
