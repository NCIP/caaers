package gov.nih.nci.cabig.caaers.service.migrator.report;


import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.GeneticIntervention;
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
public class GeneticInterventionMigratorTest extends TestCase {

	GeneticInterventionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
        migrator = new GeneticInterventionMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        
        Study study = Fixtures.createStudy("CTEP");
        Organization org = Fixtures.createOrganization("Mayo Clinic");
       
        AdverseEventReportingPeriod period = Fixtures.createReportingPeriod();
        period.setAssignment(Fixtures.createAssignment());
        dest.setReportingPeriod(period);
        
        OtherIntervention oi = new OtherIntervention();
        oi.setStudy(study);
        String desc = "Testing Genetic Migrator";
        
        GeneticIntervention gi = Fixtures.createGeneticIntervention(desc, oi);
        src.getGeneticInterventions().add(gi);
        
        StudySite site = Fixtures.createStudySite(org, 26);
        study.addStudySite(site);       
    }


    public void testMigrateWithValues() throws Exception {

    	migrator.migrate(src,dest,new DomainObjectImportOutcome<ExpeditedAdverseEventReport>());
    	assertEquals("Testing Genetic Migrator", dest.getGeneticInterventions().get(0).getDescription());
    	assertEquals(1, dest.getGeneticInterventions().size());
    }
    

    public void testMigrateWithNoInput() throws Exception {
    	src.getGeneticInterventions().clear();
    	DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    	migrator.migrate(src, dest, outCome);
    	assertEquals(0, dest.getGeneticInterventions().size());
    	assertEquals("Input doesn't contain any GeneticIntervention Values.",outCome.getMessages().get(0).getMessage());
    	
    }
    
    
}
