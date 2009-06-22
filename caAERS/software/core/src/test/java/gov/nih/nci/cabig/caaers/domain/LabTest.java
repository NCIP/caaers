package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Date;

/**
 * @author Saurabh Agrawal
 */
public class LabTest extends AbstractTestCase {

    private Lab lab;

    private LabTerm labTerm;

    private String other;

    private String units;

    private LabValue baseline;

    private LabValue nadir;

    private LabValue recovery;

    private String site;

    private Date labDate;

    private String infectiousAgent;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lab = new Lab();
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

    }

    public void testCopy() {

        Lab copiedLab = lab.copy();

        assertNull("must not coy id", copiedLab.getId());

        assertNull("must not coy grid id", copiedLab.getGridId());
        assertNull("must not coy version number", copiedLab.getVersion());
        assertNull("must not coy report", copiedLab.getReport());

        assertEquals(lab, copiedLab);
        assertEquals(baseline, copiedLab.getBaseline());
        assertEquals(infectiousAgent, copiedLab.getInfectiousAgent());
        assertEquals(labDate, copiedLab.getLabDate());
        assertEquals("lab term must refer same object", labTerm, copiedLab.getLabTerm());
        assertSame("lab term must refer same object", labTerm, copiedLab.getLabTerm());

        assertEquals(nadir, copiedLab.getNadir());
        assertEquals(other, copiedLab.getOther());
        assertEquals(recovery, copiedLab.getRecovery());
        assertEquals(site, copiedLab.getSite());
        assertEquals(units, copiedLab.getUnits());


    }

}
