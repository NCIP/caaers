package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ParticipantHistorySynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    ParticipantHistorySynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new ParticipantHistorySynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrate() throws Exception {

        ParticipantHistory.Measure height =  new ParticipantHistory.Measure();
        height.setCode(0);
        height.setQuantity(2.0);
        height.setUnit("x");

        ParticipantHistory.Measure weight =  new ParticipantHistory.Measure();
        weight.setUnit("y");
        weight.setQuantity(3.0);
        weight.setCode(0);

        ParticipantHistory ph = Fixtures.createParticipantHistory("1", "2", height, weight);
        src.setParticipantHistory(ph);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertEquals(2.0, dest.getParticipantHistory().getHeight().getQuantity());
        assertEquals(3.0, dest.getParticipantHistory().getWeight().getQuantity());
        assertEquals("1", dest.getParticipantHistory().getBaselinePerformanceStatus());
    }

    public void testMigrateDelete(){
        ParticipantHistory.Measure height =  new ParticipantHistory.Measure();
        height.setCode(0);
        height.setQuantity(2.0);
        height.setUnit("x");

        ParticipantHistory.Measure weight =  new ParticipantHistory.Measure();
        weight.setUnit("y");
        weight.setQuantity(3.0);
        weight.setCode(0);

        ParticipantHistory ph = Fixtures.createParticipantHistory("1", "2", height, weight);
        dest.setParticipantHistory(ph);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertSame(dest, dest.getParticipantHistory().getReport());
        assertNull(dest.getParticipantHistory().getBaselinePerformanceStatus());
    }
}
