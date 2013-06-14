package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class PriorTherapySynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    PriorTherapySynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new PriorTherapySynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateDelete() throws Exception {

        dest.addSaeReportPriorTherapies(Fixtures.createSAEReportPriorTherapy("abcd"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getSaeReportPriorTherapies().size());
    }


    public void testMigrateAddNew() throws Exception {
        src.addSaeReportPriorTherapies(Fixtures.createSAEReportPriorTherapy("abcd"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSaeReportPriorTherapies().size());
    }



    public void testMigrateSyncScenario() throws Exception {
        SAEReportPriorTherapy p1 = Fixtures.createSAEReportPriorTherapy("p");
        p1.addPriorTherapyAgent(Fixtures.createPriorTherapyAgent("abcd"));
        src.addSaeReportPriorTherapies(p1);

        SAEReportPriorTherapy p2 = Fixtures.createSAEReportPriorTherapy("p");
        dest.addSaeReportPriorTherapies(p2);
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSaeReportPriorTherapies().size());
        assertSame(p2, dest.getSaeReportPriorTherapies().get(0));
        assertEquals(1, dest.getSaeReportPriorTherapies().get(0).getPriorTherapyAgents().size());
    }

    public void testMigrateSyncScenarioSameAgent() throws Exception {
        SAEReportPriorTherapy p1 = Fixtures.createSAEReportPriorTherapy("p");
        p1.addPriorTherapyAgent(Fixtures.createPriorTherapyAgent("abcd"));
        src.addSaeReportPriorTherapies(p1);

        SAEReportPriorTherapy p2 = Fixtures.createSAEReportPriorTherapy("p");
        p2.addPriorTherapyAgent(Fixtures.createPriorTherapyAgent("abcd"));
        dest.addSaeReportPriorTherapies(p2);
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSaeReportPriorTherapies().size());
        assertSame(p2, dest.getSaeReportPriorTherapies().get(0));
        assertEquals(1, dest.getSaeReportPriorTherapies().get(0).getPriorTherapyAgents().size());
    }
}
