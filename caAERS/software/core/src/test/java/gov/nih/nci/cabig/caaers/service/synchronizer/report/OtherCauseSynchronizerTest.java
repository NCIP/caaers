package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class OtherCauseSynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    OtherCauseSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new OtherCauseSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }
    public void testMigrate() throws Exception {
        OtherCause oc = Fixtures.createOtherCause("o");
        src.addOtherCause(oc);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertSame(oc, dest.getOtherCauses().get(0));
    }

    public void testMigrateDeleteWithAttribution() throws Exception {
        CtcTerm ctcTerm = Fixtures.createCtcTerm("1", "1");
        ctcTerm.setId(1);
        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH, ctcTerm);
        ae1.setExternalId("1");
        ae1.setStartDate(DateUtils.today());

        OtherCause oc = Fixtures.createOtherCause("o");
        oc.setId(1);
        ae1.addAttribution(new OtherCauseAttribution(oc), ae1.getOtherCauseAttributions());

        dest.addOtherCause(oc);
        dest.addAdverseEvent(ae1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getOtherCauses().size());
        assertEquals(0, dest.getAdverseEvents().get(0).getOtherCauseAttributions().size());
    }
}
