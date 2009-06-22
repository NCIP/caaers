package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class CaaersTestCase extends AbstractTestCase {

    private static Log log = LogFactory.getLog(CaaersTestCase.class);
    private static RuntimeException acLoadFailure = null;

    protected static ApplicationContext applicationContext = null;
    private boolean authorizationOnByDefault;
    
  
    
    
    protected void setUpAuditing(){
    	DataAuditInfo.setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo("admin", "localhost", new Date(), "/pages/task"));
    }
    
    protected void setUpTestAuthorization(){
        
        // JAP: need this to ensure that security aspect is initialized by Spring before it is applied by AspectJ.
        // RMS: This is needed often enough that we'll just do it everywhere.
    	 authorizationOnByDefault = SecurityTestUtils.enableAuthorization(true, applicationContext);
         SecurityTestUtils.switchToSuperuser();
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        applicationContext = getDeployedApplicationContext();
        setUpAuditing();
        setUpTestAuthorization();
    }
    
    /*
     * AspectJ compiling is not required to test integration areas that don't
     * involve testing of security aspects. 
     */
    protected void tearDownTestAuthorization(){
    	 SecurityTestUtils.switchToNoUser();
         SecurityTestUtils.enableAuthorization(true, applicationContext);
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
    	return CaaersContextLoader.getApplicationContext();
    }
    
    /**
     * The sub classes(testclasses) can override the config locations at runtime. 
     * @return
     */
    public final String[] getConfigLocations() {
        return new String[] {
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
            "classpath*:applicationContext-test.xml"
        };
    }

}
