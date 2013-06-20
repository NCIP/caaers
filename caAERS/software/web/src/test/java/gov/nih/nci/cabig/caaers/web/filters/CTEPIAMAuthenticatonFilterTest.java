/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.filters;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.CTEPIAMAuthenEntryPoint;
import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

public class CTEPIAMAuthenticatonFilterTest extends AbstractTestCase{
	
	private CTEPIAMAuthenFilter ctepIAMAutenticationFIlter;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private FilterChain filterChain;
	private FilterConfig filterConfig;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ctepIAMAutenticationFIlter = new CTEPIAMAuthenFilter();
		request = new MockHttpServletRequest();
		session = new MockHttpSession();
		request.setSession(session);
		response = new MockHttpServletResponse();
		filterChain = registerMockFor(FilterChain.class);
		filterConfig = registerMockFor(FilterConfig.class);
	}

	public void testDoFilterForGet() throws Exception{
		filterChain.doFilter(request, response);
		replayMocks();
        ctepIAMAutenticationFIlter.doFilter(request, response, filterChain);
		verifyMocks();
	}
	
	public void testDoFilterForCTEPLogin() throws Exception{
		request.setMethod("POST");
		EasyMock.expect(new CTEPIAMAuthenEntryPoint());
		request.setRequestURI("/ctepLogin");
		filterChain.doFilter(request, response);
		replayMocks();
        ctepIAMAutenticationFIlter.doFilter(request, response, filterChain);
		verifyMocks();
	}
}
