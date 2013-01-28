package gov.nih.nci.cabig.caaers.service.migrator.report;


import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.DietarySupplementIntervention;
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
       
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        period.setAssignment(Fixtures.createAssignment());
        dest.setReportingPeriod(period);
        
        OtherIntervention oi = new OtherIntervention();
        oi.setStudy(study);
        String desc = "Testing Dietary Supplement Intervention Migrator";
        
        DietarySupplementIntervention dsi = Fixtures.createDietarySupplementIntervention(desc, oi);
        src.getDietaryInterventions().add(dsi);
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);       
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals("Testing Dietary Supplement Intervention Migrator", dest.getDietaryInterventions().get(0).getDescription());
    	assertEquals(1, dest.getDietaryInterventions().size());
    }
}
