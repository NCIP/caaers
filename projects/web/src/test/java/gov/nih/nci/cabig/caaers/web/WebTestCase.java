package gov.nih.nci.cabig.caaers.web;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.ctms.lang.StaticNowFactory;

import java.sql.Timestamp;
import java.util.Calendar;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.JoinPoint;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC. Refactor into a shared library. */
public abstract class WebTestCase extends AbstractNoSecurityTestCase {
    protected static final Timestamp NOW = DateUtils.createTimestamp(2004, Calendar.MARCH, 27);

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockServletContext servletContext;
    protected MockHttpSession session;
    protected StaticNowFactory nowFactory; 

    @Override
    protected void setUp() throws Exception {
        super.setUp();
      //  StudyParticipantAssignmentAspect spaAspect = Aspects.aspectOf(StudyParticipantAssignmentAspect.class);
      //  spaAspect.setSecurityInterceptor(new MockAspectJSecurityInterceptor());
        
        servletContext = new MockServletContext();
        session = new MockHttpSession(servletContext);
        request = new MockHttpServletRequest(servletContext);
        request.setMethod("POST");
        request.setSession(session);
        response = new MockHttpServletResponse();
        nowFactory = new StaticNowFactory();
        nowFactory.setNowTimestamp(NOW);
    }
    
    static class MockAspectJSecurityInterceptor extends AspectJSecurityInterceptor {

		@Override
		public Object invoke(JoinPoint jp, AspectJCallback advisorProceed) {
			return null;
		}
    	
    }
}
