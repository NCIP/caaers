package gov.nih.nci.cabig.caaers.tools.spring;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;

/**
 * @author Rhett Sutphin
 */
public class DeferredLoadingFactoryBeanTest extends CaaersTestCase {
    private DeferredLoadingFactoryBean factoryBean;

    private ApplicationContext context;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = registerMockFor(ApplicationContext.class);

        factoryBean = new DeferredLoadingFactoryBean();
        factoryBean.setApplicationContext(context);
        factoryBean.setBeanNames(Arrays.asList("ONE", "two", "3"));
    }

    public void testUserFirstIfNonNull() throws Exception {
        expect(context.getBean("ONE")).andReturn("1");

        replayMocks();
        assertEquals("1", factoryBean.getObject());
        verifyMocks();
    }

    public void testSecondIfFirstNull() throws Exception {
        expect(context.getBean("ONE")).andReturn(null);
        expect(context.getBean("two")).andReturn("due");

        replayMocks();
        assertEquals("due", factoryBean.getObject());
        verifyMocks();
    }

    public void testNullIfAllNull() throws Exception {
        expect(context.getBean("ONE")).andReturn(null);
        expect(context.getBean("two")).andReturn(null);
        expect(context.getBean("3")).andReturn(null);

        replayMocks();
        assertNull(factoryBean.getObject());
        verifyMocks();
    }
}
