package gov.nih.nci.cabig.caaers.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;

public class ControllerToolsTest extends TestCase {

	public void testFormatDate() {
		Date date = new GregorianCalendar(1998, Calendar.DECEMBER, 25).getTime();
		assertEquals("12/25/1998", ControllerTools.formatDate(date));
	}

	public void testIsAjaxRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		assertFalse(ControllerTools.isAjaxRequest(request));
		request.addHeader("X-Requested-With", new Object());
		assertFalse(ControllerTools.isAjaxRequest(request));
		request = new MockHttpServletRequest();
		request.addHeader("X-Requested-With", "XMLHttpRequest");
		assertTrue(ControllerTools.isAjaxRequest(request));
	}

}
