package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class AdverseEventDTOTest extends TestCase {

    AdverseEventDTO ae1, ae2;

    public void setUp(){
        ae1 = Fixtures.createAdverseEventDTO();
        ae2 = Fixtures.createAdverseEventDTO();
    }

    public void testIsSamePerMatchPercentage() throws Exception {
         assertTrue(ae1.isSame(ae2));
         assertTrue(ae1.isSamePerMatchPercentage(ae2));
    }

    public void testMatch() throws Exception {
         assertEquals(100, ae1.match(ae2));
        ae2.setStartDate("05/05/2011");
        assertEquals(84, ae1.match(ae2));
    }
}
