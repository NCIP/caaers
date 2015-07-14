/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.TestCase;
import org.acegisecurity.intercept.web.FilterInvocation;
import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

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
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();		
		FilterInvocation fi = new FilterInvocation(request, response, chain) {

			@Override
			public String getRequestUrl() {
				// TODO Auto-generated method stub
				return "/x?y=10";
			}
			
		}; 
        	
		FilterInvocation fi2 = new FilterInvocation(request, response, chain) {

			@Override
			public String getRequestUrl() {
				// TODO Auto-generated method stub
				return "/x";
			}
			
		}; 
		
		FilterInvocation fi3 = new FilterInvocation(request, response, chain) {

			@Override
			public String getRequestUrl() {
				// TODO Auto-generated method stub
				return "/xy";
			}
			
		}; 
		
		FilterInvocation fi4 = new FilterInvocation(request, response, chain) {

			@Override
			public String getRequestUrl() {
				// TODO Auto-generated method stub
				return "/x?j=0;jsessionId=9e9e9e9;";
			}
			
		}; 

        replayMocks();
        assertEquals("y", gen.resolve(fi));
        assertEquals("y", gen.resolve(fi2));
        assertNull(gen.resolve(fi3));
        assertEquals("y", gen.resolve(fi4));
        verifyMocks();
    }

}
