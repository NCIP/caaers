/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;

import java.util.ArrayList;

/**
 * @author Ion C. Olaru
 *         Date: 5/3/12 -4:56 PM
 */
public class StudyInterventionsSynchronizerTest extends AbstractTestCase {

    Study dbStudy;
    Study xmlStudy;
    StudyInterventionSynchronizer studyInterventionSynchronizer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        xmlStudy = Fixtures.createStudy("xmlStudy");
        dbStudy = Fixtures.createStudy("dbStudy");
        studyInterventionSynchronizer = new StudyInterventionSynchronizer();

    }

    public void testSyncOtherInterventions() {
        xmlStudy.setOtherInterventions(new ArrayList<OtherIntervention>());
        xmlStudy.getOtherInterventions().add(new OtherIntervention());
        xmlStudy.getOtherInterventions().get(0).setName("N1");
        xmlStudy.getOtherInterventions().get(0).setDescription("D1");
        xmlStudy.getOtherInterventions().get(0).setStudyTherapyType(StudyTherapyType.OTHER);

        assertEquals(0, dbStudy.getOtherInterventions().size());
        assertEquals(1, xmlStudy.getOtherInterventions().size());

        studyInterventionSynchronizer.migrate(dbStudy, xmlStudy, null);

        assertEquals(1, dbStudy.getOtherInterventions().size());
        assertEquals(1, xmlStudy.getOtherInterventions().size());

        assertEquals("N1", dbStudy.getOtherInterventions().get(0).getName());
        assertEquals("D1", dbStudy.getOtherInterventions().get(0).getDescription());
        assertEquals("OTHER", dbStudy.getOtherInterventions().get(0).getStudyTherapyType().getName());

    }
}
