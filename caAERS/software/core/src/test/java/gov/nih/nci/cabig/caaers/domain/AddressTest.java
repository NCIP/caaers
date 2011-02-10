package gov.nih.nci.cabig.caaers.domain;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * Address Tester.
 *
 * @author Biju Joseph
 * @since <pre>01/12/2011</pre>
 * 
 */
public class AddressTest extends TestCase {
    public void testClear(){
        Address a = new Address();
        a.setCountry("US");
        a.setState("VA");
        a.setZip("20171");
        a.setCity("Herndon");
        a.setStreet("Sunrise Valley Dr");

        a.clear();

        assertNull(a.getStreet());
        assertNull(a.getCity());
        assertNull(a.getState());
        assertNull(a.getZip());
        assertNull(a.getCountry());

    }

    public void testSync(){
        Address a = new Address();
        a.setCountry("US");
        a.setState("VA");
        a.setZip("20171");
        a.setCity("Herndon");
        a.setStreet("Sunrise Valley Dr");


        Address a2 = new Address();

        a2.setCountry("IN");
        a2.setState("Kerala");
        a2.setZip("688013");
        a2.setCity("Alappuzha");
        a2.setStreet("Thathampally");

        a2.sync(a);

        assertEquals("US", a2.getCountry());
        assertEquals("VA", a2.getState());
        assertEquals("20171", a2.getZip());
        assertEquals("Herndon", a2.getCity());
        assertEquals("Sunrise Valley Dr", a2.getStreet());

        a2.sync(null);
        assertNull(a2.getStreet());
        assertNull(a2.getCity());
        assertNull(a2.getState());
        assertNull(a2.getZip());
        assertNull(a2.getCountry());

    }

}
