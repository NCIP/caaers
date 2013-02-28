/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.filters;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

public class SessionFixationFilterTest extends AbstractTestCase{

	public void testDoFilter() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);
		session.setAttribute("abc", "def");
		String id = session.getId();
		FilterChain filterChain = registerMockFor(FilterChain.class);
		filterChain.doFilter(EasyMock.isA(HttpServletRequest.class), EasyMock.isA(HttpServletResponse.class));
		replayMocks();
		new SessionFixationFilter().doFilter(request, new MockHttpServletResponse(), filterChain);
		assertNotEquals(id, request.getSession());
		assertNotEquals(session, request.getSession());
		assertTrue(session.isInvalid());
		assertEquals("def", request.getSession().getAttribute("abc"));
		verifyMocks();
	}

}
