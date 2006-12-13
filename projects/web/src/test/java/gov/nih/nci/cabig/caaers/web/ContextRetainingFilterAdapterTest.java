package gov.nih.nci.cabig.caaers.web;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import gov.nih.nci.cabig.caaers.web.ContextRetainingFilterAdapter;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public class ContextRetainingFilterAdapterTest extends WebTestCase {
    private ContextRetainingFilterAdapter adapter = new ContextRetainingFilterAdapter() { };

    public void testServletContextRetained() throws Exception {
        expectRetainServletContext();

        assertSame("Wrong servlet context retained", servletContext, adapter.getServletContext());
    }

    public void testGetApplicationContext() throws Exception {
        expectRetainServletContext();
        WebApplicationContext applicationContext = createMock(WebApplicationContext.class);
        replay(applicationContext);
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);

        assertSame("Application context not correct", applicationContext, adapter.getApplicationContext());
        verify(applicationContext);
    }

    public void testGetApplicationContextFailsWhenMissing() throws Exception {
        expectRetainServletContext();
        try {
            adapter.getApplicationContext();
            fail("Exception not thrown");
        } catch (IllegalStateException iae) {
            // good
        }
    }

    private void expectRetainServletContext() throws ServletException {
        FilterConfig config = createMock(FilterConfig.class);
        expect(config.getServletContext()).andReturn(servletContext);
        replay(config);

        adapter.init(config);
        verify(config);
    }
}
