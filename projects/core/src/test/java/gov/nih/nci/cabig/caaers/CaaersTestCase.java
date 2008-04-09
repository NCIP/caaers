package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;
import java.util.Date;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class CaaersTestCase extends AbstractTestCase {

    private static Log log = LogFactory.getLog(CaaersTestCase.class);
    private static RuntimeException acLoadFailure = null;

    private static ApplicationContext applicationContext = null;

    private boolean authorizationOnByDefault;
    
    
    
    protected void setUpAuditing(){
    	DataAuditInfo.setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo
                ("admin", "localhost", new Date(), "/pages/task"));
    }
    
    protected void setUpTestAuthorization(){
        
        // JAP: need this to ensure that security aspect
        // is initialized by Spring before it is applied
        // by AspectJ.
        // RMS: This is needed often enough that we'll
        // just do it everywhere.
    	 ApplicationContext ctx = getDeployedApplicationContext();
         // DataSource dataSource = (DataSource) ctx.getBean("dataSource");
         // SecurityTestUtils.insertCSMPolicy(dataSource);
    	 authorizationOnByDefault = SecurityTestUtils.enableAuthorization(false, ctx);
         SecurityTestUtils.switchToSuperuser();
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setUpAuditing();
        setUpTestAuthorization();
    }
    
    /*
     * AspectJ compiling is not required to test integration areas that don't
     * involve testing of security aspects. 
     */
    protected void tearDownTestAuthorization(){
    	 SecurityTestUtils.switchToNoUser();
         ApplicationContext ctx = getDeployedApplicationContext();
         SecurityTestUtils.enableAuthorization(authorizationOnByDefault, ctx);
         // DataSource dataSource = (DataSource) ctx.getBean("dataSource");
         // SecurityTestUtils.deleteCSMPolicy(dataSource);
    }
    
    protected void tearDownAuditing(){
    	 DataAuditInfo.setLocal(null);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        tearDownTestAuthorization();
        tearDownAuditing();
    }
    
    public synchronized  ApplicationContext getDeployedApplicationContext() {
        if (acLoadFailure == null && applicationContext == null) {
            // This might not be the right place for this
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
            throw new CaaersSystemException(
                "Application context loading already failed.  Will not retry.  " +
                    "Original cause attached.", acLoadFailure);
        }
        return applicationContext;
    }
    
    /**
     * The sub classes(testclasses) can override the config locations at runtime. 
     * @return
     */
    public  String[] getConfigLocations() {
        return new String[] {
        	"classpath*:config/spring/applicationContext-rules-*.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
            "classpath*:applicationContext-test.xml"
        };
    }

}
