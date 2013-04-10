/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import junit.framework.TestCase;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Biju Joseph
 * @version $Revision: 1.1 $
 */
public class Caaers2AdeersIntegrationTest extends TestCase {
    protected ConfigurableApplicationContext applicationContext;

    public void testDeploy() throws Exception {
        Thread.sleep(180000 * 10);
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
