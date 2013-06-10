package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ObjectUtilsTest extends AbstractTestCase {

    public void testEqualsShort() throws Exception {
       Short a =  new Short((short)1);
       Short b =  new Short((short)1);
       assertFalse(ObjectUtils.equals((Short)null, (Short)null)) ;
       assertFalse(ObjectUtils.equals(a, (Short)null)) ;
       assertFalse(ObjectUtils.equals( (Short)null, b)) ;
       assertTrue(ObjectUtils.equals( a, b)) ;
       assertFalse(ObjectUtils.equals( new Short((short)3), b)) ;
    }

    public void testEqualsInteger() throws Exception {
        assertFalse(ObjectUtils.equals((Integer)null, (Integer)null));
        assertTrue(ObjectUtils.equals(1,1));
        assertFalse(ObjectUtils.equals(1, null)) ;
        assertFalse(ObjectUtils.equals(null, 1)) ;
        assertFalse(ObjectUtils.equals(3, 1)) ;
    }

    public void testEqualsLong() throws Exception {
       assertFalse(ObjectUtils.equals((Long)null, (Long)null));
       assertFalse(ObjectUtils.equals(1L, null));
       assertFalse(ObjectUtils.equals(null, 1L));
       assertTrue(ObjectUtils.equals(1L, 1L));
    }

    public void testEqualsDouble() throws Exception {
        assertFalse(ObjectUtils.equals((Double)null, (Double)null));
        assertFalse(ObjectUtils.equals(1.0, null));
        assertFalse(ObjectUtils.equals(null, 1.0));
        assertTrue(ObjectUtils.equals(1.0, 1.0));
    }

    public void testEqualsBoolean() throws Exception {
       assertTrue(ObjectUtils.equals(true, true)) ;
       assertTrue(ObjectUtils.equals(false, false)) ;
       assertFalse(ObjectUtils.equals(true, false));
       assertFalse(ObjectUtils.equals((Boolean)null, (Boolean)null));
    }

    public void testEquals() throws Exception {
        assertFalse(ObjectUtils.equals((String)null, (String)null));
        assertFalse(ObjectUtils.equals("", null));
        assertFalse(ObjectUtils.equals(null, ""));
        assertTrue(ObjectUtils.equals("x", "x"));
        assertTrue(ObjectUtils.equals("", ""));
    }

}
