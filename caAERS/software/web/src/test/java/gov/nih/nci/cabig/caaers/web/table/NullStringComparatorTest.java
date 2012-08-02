package gov.nih.nci.cabig.caaers.web.table;

import junit.framework.TestCase;

import java.util.Comparator;

/**
 * @author: Biju Joseph
 */
public class NullStringComparatorTest extends TestCase {
    NullStringComparator c;
    public void setUp() throws Exception {
       c = new NullStringComparator();
    }
    
    public void testConstructor() throws Exception{
        Comparator<String> comparator =  String.CASE_INSENSITIVE_ORDER;
        c = new NullStringComparator(comparator) ;
        assertSame(comparator, c.getStringComparator());
        
        c.setStringComparator(comparator);
        assertSame(comparator, c.getStringComparator());
    }

    public void testCompare() throws Exception {
         int i = c.compare("x",  "y");
         assertEquals(-1, i);
        
        i = c.compare(null, null);
        assertEquals(0, i);

        i = c.compare("", null);
        assertEquals(-1, i);

        i = c.compare("", "");
        assertEquals(0, i);
    }
}
