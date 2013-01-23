package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medaV
 * Date: 1/18/13
 */
public class DeviceMigratorTest extends TestCase {

    private LabMigrator migrator;
    
    private ExpeditedAdverseEventReport src;
    
    private ExpeditedAdverseEventReport dest;
    
    private DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    
    private LabTerm labTerm;

    private String other;

    private String units;

    private LabValue baseline;

    private LabValue nadir;

    private LabValue recovery;

    private String site;

    private Date labDate;

    private String infectiousAgent;


    public void setUp() throws Exception {
        migrator = new LabMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrate() throws Exception {
    	Lab  lab = new Lab();
        labTerm = new LabTerm();
        other = "other";
        units = "units";
        baseline = new LabValue();
        nadir = new LabValue();
        recovery = new LabValue();
        site = "site";
        labDate = new Date();
        infectiousAgent = "infectious agent";
        lab.setBaseline(baseline);
        lab.setGridId("grid id");
        lab.setId(2);
        lab.setInfectiousAgent(infectiousAgent);
        lab.setLabDate(labDate);
        lab.setLabTerm(labTerm);
        lab.setNadir(nadir);
        lab.setOther(other);
        lab.setRecovery(recovery);
        lab.setReport(new ExpeditedAdverseEventReport());
        lab.setSite(site);
        lab.setUnits(units);
        lab.setVersion(2);
        src.getLabs().add(lab);
        migrator.migrate(src,dest, outcome);
        
        assertEquals(dest.getLabs().get(0).getInfectiousAgent(), "infectious agent");
    }

}
