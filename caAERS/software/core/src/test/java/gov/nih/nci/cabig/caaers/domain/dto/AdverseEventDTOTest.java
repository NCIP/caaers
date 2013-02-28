/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import junit.framework.TestCase;

import java.util.List;

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

    public void testDiff(){
        ae1 = Fixtures.createAdverseEventDTO();
        ae2 = Fixtures.createAdverseEventDTO();

        List<String> diffList =  ae2.diff(ae1);
        assertTrue(diffList.isEmpty());

        ae1.setEndDate(null);
        ae2.setEndDate(null);

        diffList =  ae2.diff(ae1);
        assertTrue(diffList.isEmpty());

        ae1.setAttribution("Unlikely");
        diffList =  ae2.diff(ae1);
        assertTrue(diffList.contains("attribution"));


    }

    public void testCreate(){
        AdverseEvent ae = Fixtures.createAdverseEvent(1, Grade.SEVERE);
        AdverseEventDTO dto = AdverseEventDTO.create(ae);
        assertEquals("--" , dto.getWhySerious());
    }
}
