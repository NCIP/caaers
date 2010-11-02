package gov.nih.nci.cabig.caaers.web;

import org.easymock.IArgumentMatcher;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.easymock.EasyMock.*;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC. Refactor into a shared library. */
public class OpenSessionInViewInterceptorFilterTest extends WebTestCase {
    private static final String BEAN_NAME = "interceptor";

    private OpenSessionInViewInterceptorFilter filter;

    private ApplicationContext applicationContext;

    private OpenSessionInViewInterceptor interceptor;

    private FilterChain filterChain;

    private WebRequestMatcher webRequestMatcher = new WebRequestMatcher();
    private SessionFactory hibernateSessionFactory;
    private Session hibernateSession;


    protected void setUp() throws Exception {
        super.setUp();
        applicationContext = registerMockFor(WebApplicationContext.class);
        interceptor = registerMockFor(OpenSessionInViewInterceptor.class);
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                        applicationContext);
        expect(applicationContext.getBean(BEAN_NAME)).andReturn(interceptor);
        filterChain = registerMockFor(FilterChain.class);
        // put a parameter on request for use by WebRequestMatcher
        request.addParameter("Salutation", "Friends, Romans, Countrymen");

        filter = new OpenSessionInViewInterceptorFilter();
        MockFilterConfig filterConfig = new MockFilterConfig(servletContext);
        filterConfig.addInitParameter("interceptorBeanName", BEAN_NAME);
        filter.init(filterConfig);

        hibernateSession = registerMockFor(Session.class);
        hibernateSessionFactory = registerMockFor(SessionFactory.class);
    }

    public void testBasicBehavior() throws Exception {
        interceptor.preHandle(webRequest());
        filterChain.doFilter(request, response);
        interceptor.postHandle(webRequest(), (ModelMap) isNull());
        interceptor.afterCompletion(webRequest(), (Exception) isNull());

        doFilter();
    }

    public void testExceptionInFilterChainBehavior() throws Exception {
        ServletException exception = new ServletException("Uh oh");
        expect(interceptor.getSessionFactory()).andReturn(hibernateSessionFactory);
        expect(hibernateSessionFactory.getCurrentSession()).andReturn(hibernateSession);
        interceptor.preHandle(webRequest());
        filterChain.doFilter(request, response);
        expectLastCall().andThrow(exception);
        // postHandle should not be called
        interceptor.afterCompletion(webRequest(), (Exception) isNull());

        try {
            doFilter();
            fail("Exception not propagated");
        } catch (Exception se) {
            se.printStackTrace();
            assertSame(exception, se.getCause());
        }
    }

    public void testExceptionInPostHandleBehavior() throws Exception {
        DataAccessException exception = new PermissionDeniedDataAccessException("Uh oh", null);

        interceptor.preHandle(webRequest());
        filterChain.doFilter(request, response);
        expect(interceptor.getSessionFactory()).andReturn(hibernateSessionFactory);
        expect(hibernateSessionFactory.getCurrentSession()).andReturn(hibernateSession);

        interceptor.postHandle(webRequest(), (ModelMap) isNull());
        expectLastCall().andThrow(exception);
        interceptor.afterCompletion(webRequest(), (Exception) isNull());

        try {
            doFilter();
            fail("Exception not propagated");
        } catch (Exception dae) {
            dae.printStackTrace();
            assertSame(exception, dae.getCause());
        }
    }

    private void doFilter() throws IOException, ServletException {
        replayMocks();
        filter.doFilter(request, response, filterChain);
        verifyMocks();
    }

    private WebRequest webRequest() {
        reportMatcher(webRequestMatcher);
        return null;
    }

    private class WebRequestMatcher implements IArgumentMatcher {
        public boolean matches(Object argument) {
            if (!(argument instanceof ServletWebRequest)) return false;
            ServletWebRequest actual = (ServletWebRequest) argument;
            return actual.getParameterMap().equals(request.getParameterMap());
        }

        public void appendTo(StringBuffer buffer) {
            buffer.append("wraps ").append(request.toString());
        }
    }
}
