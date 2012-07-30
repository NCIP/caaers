package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultObjectPrivilegeGenerator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class DefaultObjectPrivilegeGeneratorTest extends AbstractTestCase {
    DefaultObjectPrivilegeGenerator gen;
    public void testResolve(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new DefaultObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);
        assertEquals("y", gen.resolve("/x"));
        assertNull(gen.resolve("xksksk"));

    }

    public void testSetObjectPrivilegeMap(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new DefaultObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);
        assertSame(map, gen.objectPrivilegeMap);
        
    }
}
