package gov.nih.nci.cabig.caaers.web.dwr;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.Container;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * @author Rhett Sutphin
 */
public class MockWebContextBuilder implements WebContextFactory.WebContextBuilder {
    private WebContext webContext;

    public MockWebContextBuilder(WebContext webContext) {
        this.webContext = webContext;
    }

    public void set(HttpServletRequest request, HttpServletResponse response, ServletConfig config,
                    ServletContext context, Container container) {
    }

    public WebContext get() {
        return webContext;
    }

    public void unset() {
    }
}
