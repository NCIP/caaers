package gov.nih.nci.cabig.caaers2adeers;

import java.util.Properties;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version $Revision: 1.1 $
 */
public class Caaers2AdeersIntegrationTest extends TestCase {
    protected ConfigurableApplicationContext applicationContext;

    public void testDeploy() throws Exception {
        Thread.sleep(45000);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        applicationContext = createApplicationContext();
        assertNotNull("Could not create the applicationContext!", applicationContext);
        applicationContext.start();
    }

    @Override
    protected void tearDown() throws Exception {
        if (applicationContext != null) {
            applicationContext.stop();
        }
        super.tearDown();
    }

    protected ConfigurableApplicationContext createApplicationContext() throws Exception {
        return new ClassPathXmlApplicationContext("c2a_test.xml");
    }
}
