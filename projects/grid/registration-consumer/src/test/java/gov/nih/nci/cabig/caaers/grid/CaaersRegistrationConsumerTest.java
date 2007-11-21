/**
 *
 */
package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.ccts.grid.Registration;
import gov.nih.nci.ccts.grid.client.RegistrationConsumerClient;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;


/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class CaaersRegistrationConsumerTest extends TestCase {
    private String clientConfigFile;
    private String registrationResourceName;
    private String serviceUrl;
    

    
    @Override
    protected void setUp() throws Exception {
    	// TODO Auto-generated method stub
    	 this.clientConfigFile = "/gov/nih/nci/ccts/grid/client/client-config.wsdd"; //"C:/devtools/workspace/REF-RegistrationConsumer/src/gov/nih/nci/ccts/grid/client/client-config.wsdd";	
         this.registrationResourceName = "/SampleRegistrationMessage.xml"; //"C:/devtools/workspace/REF-RegistrationConsumer/test/resources/SampleRegistrationMessage.xml";
         this.serviceUrl = "http://localhost:8080/wsrf/services/cagrid/RegistrationConsumer"; 
    }
    
    @Override
    protected void tearDown() throws Exception {
    	// TODO Auto-generated method stub
    	super.tearDown();
    }
    
	protected String[] getConfigLocations() {
		//return new String[]{"classpath:applicationContext-grid.xml"};
		
		//<import resource="classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml" />
		//<import resource="classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml" />
		//<import resource="classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-db.xml" />
		//<import resource="classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml" />
		
		//<import resource="classpath*:gov/nih/nci/cabig/caaers/grid/applicationContext-registrationConsumer.xml"/>
		
		
		return new String[] { "classpath:applicationContext-grid.xml","classpath*:applicationContext-test.xml",
				//"classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
				//"classpath*:gov/nih/nci/cabig/caaers/applicationContext-test-security.xml",
				//"classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
		 //"classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
		};

	}
    
	public void testCommitRemote() throws Exception {
		try {
			RegistrationConsumerClient regClient = new RegistrationConsumerClient(serviceUrl);
			Registration reg = obtainRegistrationDTO();
			regClient.commit(reg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
	
	public void testRollbackRemote() throws Exception{
		try {
			RegistrationConsumerClient regClient = new RegistrationConsumerClient(serviceUrl);
			Registration reg = obtainRegistrationDTO();
			regClient.rollback(reg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void testRegisterRemote() throws Exception{
		try {
			RegistrationConsumerClient regClient = new RegistrationConsumerClient(serviceUrl);
			Registration reg = obtainRegistrationDTO();
			regClient.register(reg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void testRegistrationLocal() throws Exception{
		try{
			
			org.springframework.context.ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext-grid.xml"});
			CaaersRegistrationConsumer consumer = (CaaersRegistrationConsumer) ctx.getBean("registrationConsumer");
			Registration reg = obtainRegistrationDTO();
			consumer.register(reg);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
    
    
    
    public Registration obtainRegistrationDTO() throws Exception{
    	Registration registration = null;
    	try {
    		Reader reader = new InputStreamReader(getClass().getResourceAsStream(registrationResourceName));
    		InputStream fis = getClass().getResourceAsStream(clientConfigFile);
    		registration = (Registration) Utils.deserializeObject(reader, Registration.class,fis);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			throw e;
    		}
    		return registration;
    }
    
    protected void onSetUpInTransaction() throws Exception {
    	 this.setUp();
    }
}
