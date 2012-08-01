package gov.nih.nci.cabig.caaers.web.filters;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

public class CsrfPreventionFilterTest extends AbstractTestCase{
	
	private CsrfPreventionFilter csrfPreventionFilter;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private FilterChain filterChain;
	private FilterConfig filterConfig;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		csrfPreventionFilter = new CsrfPreventionFilter();
		request = new MockHttpServletRequest();
		session = new MockHttpSession();
		session.setAttribute(CsrfPreventionFilter.CSRF_TOKEN, "123");
		request.setSession(session);
		response = new MockHttpServletResponse();
		filterChain = registerMockFor(FilterChain.class);
		filterConfig = registerMockFor(FilterConfig.class);
	}

	public void testDoFilterForGet() throws Exception{
		filterChain.doFilter(request, response);
		replayMocks();
		csrfPreventionFilter.doFilter(request, response, filterChain);
		verifyMocks();
	}
	
	public void testDoFilterForAllowedURIs() throws Exception{
		request.setMethod("POST");
		EasyMock.expect(filterConfig.getInitParameter("allowURIs")).andReturn("/someuri1,/someuri2");
		request.setRequestURI("/abc/someuri1");
		filterChain.doFilter(request, response);
		replayMocks();
		csrfPreventionFilter.init(filterConfig);
		csrfPreventionFilter.doFilter(request, response, filterChain);
		verifyMocks();
	}
	
	public void testDoFilterValidCSRF() throws Exception{
		request.setMethod("POST");
		EasyMock.expect(filterConfig.getInitParameter("allowURIs")).andReturn("/someuri1,/someuri2");
		request.setRequestURI("/abc/someotheruri");
		request.setParameter(CsrfPreventionFilter.CSRF_TOKEN, "123");
		filterChain.doFilter(request, response);
		replayMocks();
		csrfPreventionFilter.init(filterConfig);
		csrfPreventionFilter.doFilter(request, response, filterChain);
		verifyMocks();
	}
	
	public void testDoFilterInValidCSRF() throws Exception{
		request.setMethod("POST");
		EasyMock.expect(filterConfig.getInitParameter("allowURIs")).andReturn("/someuri1,/someuri2");
		request.setRequestURI("/abc/someotheruri");
		request.setParameter(CsrfPreventionFilter.CSRF_TOKEN, "456");
		replayMocks();
		csrfPreventionFilter.init(filterConfig);
		csrfPreventionFilter.doFilter(request, response, filterChain);
		assertEquals(403, response.getStatus());
		verifyMocks();
	}

	public void testIsValidCsrfToken() {
		assertFalse(csrfPreventionFilter.isValidCsrfToken(request));
		request.addHeader(CsrfPreventionFilter.CSRF_TOKEN_HEADER, "123");
		assertTrue(csrfPreventionFilter.isValidCsrfToken(request));
		request.addParameter(CsrfPreventionFilter.CSRF_TOKEN, "456");
		assertFalse(csrfPreventionFilter.isValidCsrfToken(request));
		request.setParameter(CsrfPreventionFilter.CSRF_TOKEN, "123");
		assertTrue(csrfPreventionFilter.isValidCsrfToken(request));
	}

	public void testGetCSRFToken() {
		assertNull(csrfPreventionFilter.getCSRFToken(request));
		request.addHeader(CsrfPreventionFilter.CSRF_TOKEN_HEADER, "123");
		assertEquals("123", csrfPreventionFilter.getCSRFToken(request));
		request.addParameter(CsrfPreventionFilter.CSRF_TOKEN, "456");
		assertEquals("456", csrfPreventionFilter.getCSRFToken(request));
	}

}
