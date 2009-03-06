package gov.nih.nci.cabig.caaers.web;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.web.dwr.MockWebContextBuilder;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * @author Rhett Sutphin
 */
public abstract class DwrFacadeTestCase extends WebTestCase {
    protected WebContext webContext;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        webContext = registerMockFor(WebContext.class);
        expect(webContext.getHttpServletRequest()).andReturn(request).anyTimes();
        expect(webContext.getHttpServletResponse()).andReturn(response).anyTimes();
        WebContextFactory.setWebContextBuilder(new MockWebContextBuilder(webContext));
    }
}
