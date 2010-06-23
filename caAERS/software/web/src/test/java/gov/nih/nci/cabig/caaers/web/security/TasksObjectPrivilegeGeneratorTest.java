package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.HashMap;

/**
 * TasksObjectPrivilegeGenerator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class TasksObjectPrivilegeGeneratorTest extends AbstractTestCase {

    TasksObjectPrivilegeGenerator gen;

    public void testResolve() throws Exception {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        
        gen = new TasksObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);

       Task t1 = new Task();
       t1.setLinkName("/x");
       String x =  gen.resolve(t1);
       assertEquals("y", x);

       t1.setLinkName("/xy");
       assertNull(gen.resolve(t1));
    }

}
