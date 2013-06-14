/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class PreExistingConditionSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    PreExistingConditionSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new PreExistingConditionSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateNew() throws Exception {
        src.addSaeReportPreExistingCondition(Fixtures.createSAEReportPreExistingCondition("t"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSaeReportPreExistingConditions().size());
    }

    public void testMigrateRemove() throws Exception {
        dest.addSaeReportPreExistingCondition(Fixtures.createSAEReportPreExistingCondition("t"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getSaeReportPreExistingConditions().size());
    }

    public void testEdit() throws Exception {
        SAEReportPreExistingCondition pec1 = Fixtures.createSAEReportPreExistingCondition("t");
        PreExistingCondition pc = pec1.getPreExistingCondition();
        pc.setId(1);

        src.addSaeReportPreExistingCondition(pec1);
        SAEReportPreExistingCondition pec2 = Fixtures.createSAEReportPreExistingCondition("t");
        pec2.setPreExistingCondition(pc);
        dest.addSaeReportPreExistingCondition(pec2);

        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSaeReportPreExistingConditions().size());
        assertSame(pec2, dest.getSaeReportPreExistingConditions().get(0));
    }
}
