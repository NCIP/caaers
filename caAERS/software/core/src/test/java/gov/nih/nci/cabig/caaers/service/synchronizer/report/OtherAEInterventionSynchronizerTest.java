package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherInterventionAttribution;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class OtherAEInterventionSynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    OtherAEInterventionSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new OtherAEInterventionSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }
    public void testMigrate() throws Exception {
        OtherIntervention si = Fixtures.createOtherIntervention(1,"x", StudyTherapyType.OTHER);
        OtherAEIntervention oai = Fixtures.createOtherAEIntervention("other", si) ;
        src.addOtherAEIntervention(oai);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertSame(oai, dest.getOtherAEInterventions().get(0));
    }

    public void testMigrateDeleteWithAttribution() throws Exception {
        CtcTerm ctcTerm = Fixtures.createCtcTerm("1", "1");
        ctcTerm.setId(1);
        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH, ctcTerm);
        ae1.setExternalId("1");
        ae1.setStartDate(DateUtils.today());
        OtherIntervention si = Fixtures.createOtherIntervention(1, "x", StudyTherapyType.OTHER);
        OtherAEIntervention oai = Fixtures.createOtherAEIntervention("other", si) ;
        ae1.addAttribution(new OtherInterventionAttribution(oai), ae1.getOtherInterventionAttributions());

        dest.addOtherAEIntervention(oai);
        dest.addAdverseEvent(ae1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getOtherAEInterventions().size());
        assertEquals(0, dest.getAdverseEvents().get(0).getOtherInterventionAttributions().size());
    }
}
