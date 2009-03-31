package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.domain.Grade.DEATH;
import static gov.nih.nci.cabig.caaers.domain.Grade.LIFE_THREATENING;
import static gov.nih.nci.cabig.caaers.domain.Grade.MILD;
import static gov.nih.nci.cabig.caaers.domain.Grade.MODERATE;
import static gov.nih.nci.cabig.caaers.domain.Grade.SEVERE;
import static gov.nih.nci.cabig.caaers.domain.Grade.getByCode;

import org.apache.commons.lang.NumberUtils;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class GradeTest extends TestCase {
    public void testToString() throws Exception {
        assertEquals("5: Death", DEATH.toString());
        assertEquals("4: Life-threatening or disabling", LIFE_THREATENING.toString());
        assertEquals("3: Severe", SEVERE.toString());
        assertEquals("2: Moderate", MODERATE.toString());
        assertEquals("1: Mild", MILD.toString());
    }

    public void testFromCode() throws Exception {
        assertEquals(DEATH, getByCode(5));
        assertEquals(LIFE_THREATENING, getByCode(4));
        assertEquals(SEVERE, getByCode(3));
        assertEquals(MODERATE, getByCode(2));
        assertEquals(MILD, getByCode(1));
        assertNotNull(Grade.getByCode(0));
        assertEquals(Grade.NOT_EVALUATED, Grade.getByCode(-1));
        
    }
   
}
