package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    ExpeditedAdverseEventSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new ExpeditedAdverseEventSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateDelete() throws Exception {
        dest.addAdverseEvent(Fixtures.createAdverseEvent(1, Grade.DEATH));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getAdverseEvents().size());
    }


    public void testMigrateAddNew() throws Exception {
        src.addAdverseEvent(Fixtures.createAdverseEvent(1, Grade.DEATH));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getAdverseEvents().size());
    }



    public void testMigrateSyncScenario() throws Exception {
        CtcTerm ctcTerm = Fixtures.createCtcTerm("1", "1");
        ctcTerm.setId(1);
        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH, ctcTerm);
        ae1.setExternalId("1");
        ae1.setStartDate(DateUtils.today());
        src.addAdverseEvent(ae1);

        AdverseEvent ae2  = Fixtures.createAdverseEvent(1, Grade.DEATH, ctcTerm);
        ae2.setExternalId("1");
        ae2.setStartDate(DateUtils.yesterday());
        dest.addAdverseEvent(ae2);


        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getAdverseEvents().size());
        assertEquals(ae1.getStartDate(), ae2.getStartDate());
    }
}
