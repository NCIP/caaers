/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * @author Ion C. Olaru
 */
public class StudyTherapyTypeTest extends TestCase {
    public void testEquals() {
        StudyTherapyType t1 = StudyTherapyType.BEHAVIORAL;
        StudyTherapyType t2 = StudyTherapyType.RADIATION;
        StudyTherapyType t3 = StudyTherapyType.DEVICE;
        StudyTherapyType t4 = StudyTherapyType.DEVICE;
        assertTrue(t3.equals(t4));
        assertFalse(t1.equals(t4));
        assertEquals(4, t3.getCode().intValue());
        assertEquals(2, t2.getCode().intValue());
        assertEquals(5, t1.getCode().intValue());
        assertSame(t3, StudyTherapyType.getByCode(4));
    }
}
