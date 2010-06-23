package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.TestCase;
import org.acegisecurity.intercept.web.FilterInvocation;
import org.easymock.classextension.EasyMock;

import java.util.HashMap;

/**
 * FilterInvocationObjectPrivilegeGenerator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class FilterInvocationObjectPrivilegeGeneratorTest extends AbstractTestCase {
    FilterInvocationObjectPrivilegeGenerator gen;
    public void setUp() throws Exception {
        super.setUp();
    }



    public void testResolve(){
         HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new FilterInvocationObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);

        FilterInvocation fi = registerMockFor(FilterInvocation.class);
        EasyMock.expect(fi.getRequestUrl()).andReturn("/x?y=10");



        FilterInvocation fi2 = registerMockFor(FilterInvocation.class);
        EasyMock.expect(fi2.getRequestUrl()).andReturn("/x");


        FilterInvocation fi3 = registerMockFor(FilterInvocation.class);
        EasyMock.expect(fi3.getRequestUrl()).andReturn("/xy");

        FilterInvocation fi4 = registerMockFor(FilterInvocation.class);
        EasyMock.expect(fi4.getRequestUrl()).andReturn("/x?j=0;jsessionId=9e9e9e9;");

        replayMocks();
        assertEquals("y", gen.resolve(fi));
        assertEquals("y", gen.resolve(fi2));
        assertNull(gen.resolve(fi3));
        assertEquals("y", gen.resolve(fi4));
        verifyMocks();
    }

}
