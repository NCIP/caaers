package gov.nih.nci.cabig.caaers.web;

import org.springframework.mock.web.MockServletContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.ctms.lang.StaticNowFactory;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.Date;
import java.util.Calendar;
import java.sql.Timestamp;

import edu.nwu.bioinformatics.commons.DateUtils;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class WebTestCase extends CaaersTestCase {
    protected static final Timestamp NOW = DateUtils.createTimestamp(2004, Calendar.MARCH, 27);

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockServletContext servletContext;
    protected MockHttpSession session;
    protected StaticNowFactory nowFactory;  // TODO: probably makes sense to put this in the main test case

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        servletContext = new MockServletContext();
        session = new MockHttpSession(servletContext);
        request = new MockHttpServletRequest(servletContext);
        request.setMethod("POST");
        request.setSession(session);
        response = new MockHttpServletResponse();
        nowFactory = new StaticNowFactory();
        nowFactory.setNowTimestamp(NOW);
    }
}
