/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.acegisecurity.intercept.web.FilterInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class TaskAuthorizationTest extends CaaersTestCase {

    private ApplicationContext ctx = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        
        /*ApplicationContext parent = getDeployedApplicationContext();

        String[] locations = new String[] { "WEB-INF/pages-servlet.xml",
                							"WEB-INF/applicationContext-acegi-security.xml" };

        String webappDir = "file:"
                        + getModuleRelativeFile(getClass(), "src/main/webapp").getAbsolutePath();
        MockServletContext servletContext = new MockServletContext(webappDir,
                        new FileSystemResourceLoader());
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setParent(parent);
        context.setServletContext(servletContext);
        context.setConfigLocations(locations);

        context.refresh();
        this.ctx = context;
        
        AspectJSecurityInterceptor interceptor = (AspectJSecurityInterceptor) ctx.getBean("daoSecurity");
        StudyParticipantAssignmentAspect aspect = Aspects.aspectOf(StudyParticipantAssignmentAspect.class);
        aspect.setSecurityInterceptor(interceptor);
*/        
    }
    

    public void testAllTasksCovered() {
//        TaskPrivilegeAndObjectIdGenerator taskGen = (TaskPrivilegeAndObjectIdGenerator) this.ctx
//                        .getBean("taskPrivilegeAndObjectIdGenerator");
//        SectionInterceptor si = (SectionInterceptor) this.ctx.getBean("sectionInterceptor");
//        assertNotNull(si);
//        List<Section> sections = si.getSections();
//        for (Section section : sections) {
//
//            List<Task> tasks = section.getTasks();
//            for (Task task : tasks) {
//
//                assertNotNull("No privilege for task " + task.getUrl(), taskGen
//                                .generatePrivilege(task));
//                assertNotNull("No objectId for task " + task.getUrl(), taskGen.generateId(task));
//
//                checkFilter(task.getUrl());
//
//            }
//
//        }
    }

    private void checkFilter(String url) {

        FilterInvocationPrivilegeAndObjectIdGenerator filterGen = (FilterInvocationPrivilegeAndObjectIdGenerator) this.ctx
                        .getBean("filterPrivilegeAndObjectIdGenerator");

        MockFilterChain chain = new MockFilterChain(true);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath(url);
        request.setScheme("https");
        request.setServerPort(443);
        FilterInvocation invocation = new FilterInvocation(request, response, chain);

        assertNotNull("No filter privilege for " + url, filterGen.generatePrivilege(invocation));
        assertNotNull("No filter objectId for " + url, filterGen.generateId(invocation));
    }

    public void testAllSectionsCovered() {

//        SectionPrivilegeAndObjectIdGenerator sectionGen = (SectionPrivilegeAndObjectIdGenerator) this.ctx
//                        .getBean("sectionPrivilegeAndObjectIdGenerator");
//        SectionInterceptor si = (SectionInterceptor) this.ctx.getBean("sectionInterceptor");
//        assertNotNull(si);
//        List<Section> sections = si.getSections();
//        for (Section section : sections) {
//
//            assertNotNull("No privilege for section " + section.getMainUrl(), sectionGen
//                            .generatePrivilege(section));
//            assertNotNull("No objectId for section " + section.getMainUrl(), sectionGen
//                            .generateId(section));
//
//            checkFilter(section.getMainUrl());
//        }
    }

    // Ripped from acegi tests
    private class MockFilterChain implements FilterChain {
        private boolean expectToProceed;

        public MockFilterChain(boolean expectToProceed) {
            this.expectToProceed = expectToProceed;
        }

        private MockFilterChain() {
            super();
        }

        public void doFilter(ServletRequest request, ServletResponse response) throws IOException,
                        ServletException {
            if (expectToProceed) {
                assertTrue(true);
            } else {
                fail("Did not expect filter chain to proceed");
            }
        }
    }

}
