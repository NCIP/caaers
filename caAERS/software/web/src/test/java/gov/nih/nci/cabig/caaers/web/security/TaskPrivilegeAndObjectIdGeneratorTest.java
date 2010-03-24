package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.ctms.web.chrome.Task;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * TaskPrivilegeAndObjectIdGenerator Tester.
 *
 * @author Biju Joseph
 * @since <pre>03/18/2010</pre>
 * 
 */
public class TaskPrivilegeAndObjectIdGeneratorTest extends TestCase {

    Map<String, String> objectPrivilegeMap;
    TaskPrivilegeAndObjectIdGenerator generator;

    public void setUp() throws Exception {
        super.setUp();
        objectPrivilegeMap = new HashMap<String,String>();
        objectPrivilegeMap.put("/pages/ae/list", "gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport:READ");
        objectPrivilegeMap.put("/pages/ae/routingAndReview","gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport:READ") ;
        objectPrivilegeMap.put("/pages/ae/edit","gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport:UPDATE") ;
        generator = new TaskPrivilegeAndObjectIdGenerator();
        generator.setObjectPrivilegeMap(objectPrivilegeMap);

    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetKeyValue() throws Exception {
        Task t= new Task();
        t.setDisplayName("abcd");
        t.setLinkName("/pages/ae/list");
        assertEquals("/pages/ae/list", generator.getKeyValue(t));
    }

    public void testGenerateId(){
        Task t= new Task();
        t.setDisplayName("abcd");
        t.setLinkName("/pages/ae/list");
        String id = generator.generateId(t);
        assertEquals("gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport", id);
    }

}
