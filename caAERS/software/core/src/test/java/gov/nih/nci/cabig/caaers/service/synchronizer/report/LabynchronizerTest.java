/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 * @author medaV
 */
public class LabynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    LabSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new LabSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateDelete() throws Exception {
        dest.addLab(Fixtures.createLab("Bone Marrow blasts","Bone Marrow Biopsy"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getLabs().size());
    }


    public void testMigrateAddNew() throws Exception {
        src.addLab(Fixtures.createLab("Bone Marrow blasts","Bone Marrow Biopsy"));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getLabs().size());
    }



    public void testMigrateSyncScenario() throws Exception {
        Lab xmlLab = Fixtures.createLab("Bone Marrow blasts","Bone Marrow Biopsy");
        xmlLab.setId(1);

        Lab dbLab = Fixtures.createLab("Bone Marrow Cellularity","Bone Marrow Biopsy");
        dbLab.setId(1);

        src.addLab(xmlLab);
        dest.addLab(dbLab);

        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());

        assertEquals(xmlLab.getLabTerm().getTerm(), dbLab.getLabTerm().getTerm());
    }
}
