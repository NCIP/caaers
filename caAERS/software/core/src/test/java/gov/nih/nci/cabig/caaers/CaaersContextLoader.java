package gov.nih.nci.cabig.caaers;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * Class to Load caAERS Application Context for TestCases.
 * @author Moni
 *
 */
public class CaaersContextLoader {

	private static Log log = LogFactory.getLog(CaaersTestCase.class);
    private static RuntimeException acLoadFailure = null;

    private static ApplicationContext applicationContext = null;
    
    public synchronized static ApplicationContext getApplicationContext() {
        if (acLoadFailure == null && applicationContext == null) {
            try {
                SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            } catch (NamingException e) {
                throw new RuntimeException("", e);
            }

            try {
                log.debug("Initializing test version of deployed application context");
                applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
            } catch (RuntimeException e) {
                acLoadFailure = e;
                throw e;
            }
        } else if (acLoadFailure != null) {
            throw new CaaersSystemException("Application context loading already failed.  Will not retry.  " + "Original cause attached.", acLoadFailure);
        }
        return applicationContext;
    }
    
    /**
     * The sub classes(testclasses) can override the config locations at runtime. 
     * @return
     */
    private static String[] getConfigLocations() {
        return new String[] {
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
            "classpath*:applicationContext-test.xml"
        };
    }
}
