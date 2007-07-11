package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class DiseaseHistoryTest extends CaaersTestCase {
    public void testMetastaticDiseaseListLazilyInited() throws Exception {
        DiseaseHistory dh = new DiseaseHistory();
        assertEquals("List not initially empty", 0, dh.getMetastaticDiseaseSites().size());
        assertNotNull("New site not dynamically created", dh.getMetastaticDiseaseSites().get(0));
    }
}
