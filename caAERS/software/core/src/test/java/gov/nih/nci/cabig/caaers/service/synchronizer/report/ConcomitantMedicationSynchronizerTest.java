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
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ConcomitantMedicationSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    ConcomitantMedicationSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new ConcomitantMedicationSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateNew() throws Exception {
        src.addConcomitantMedication(Fixtures.createConcomitantMedication("t"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getConcomitantMedications().size());
    }

    public void testMigrateRemove() throws Exception {
        dest.addConcomitantMedication(Fixtures.createConcomitantMedication("t"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getConcomitantMedications().size());
    }

    public void testEdit() throws Exception {
        ConcomitantMedication conMed1 = Fixtures.createConcomitantMedication("t");
        conMed1.setStillTakingMedications(true);
       
        src.addConcomitantMedication(conMed1);
        ConcomitantMedication conMed2 = Fixtures.createConcomitantMedication("t");
        dest.addConcomitantMedication(conMed2);

        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getConcomitantMedications().size());
        assertSame(conMed2, dest.getConcomitantMedications().get(0));
        assertTrue(dest.getConcomitantMedications().get(0).getStillTakingMedications());
    }
}
