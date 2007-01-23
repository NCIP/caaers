/**
 * 
 */
package gov.nih.nci.cabig.caaers.grid;

import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;

import gov.nih.nci.cabig.ctms.client.RegistrationConsumerClient;
import gov.nih.nci.cabig.ctms.common.RegistrationConsumer;
import gov.nih.nci.cabig.ctms.grid.RegistrationType;
import gov.nih.nci.cagrid.common.Utils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class CaaersRegistrationConsumerTest extends TestCase {
    
    private String clientConfigFile;
    private String regFile;
    private String serviceUrl;
    
    public CaaersRegistrationConsumerTest(){
        
    }
    public CaaersRegistrationConsumerTest(String name){
        super(name);
    }
    public void setUp(){
        this.clientConfigFile = System.getProperty("caaers.test.clientConfigFile", "/gov/nih/nci/cabig/ctms/client/client-config.wsdd");
        this.regFile = System.getProperty("caaers.test.sampleRegistrationFile", "test/resources/SampleRegistrationMessage.xml");
        this.serviceUrl = System.getProperty("caaers.test.serviceUrl", "http://localhost:8080/wsrf/services/cagrid/RegistrationConsumer");
    }
    
    public void testCreateRegistrationLocal(){
        RegistrationType reg = getRegistration();
        try{
            RegistrationConsumer consumer = new CaaersRegistrationConsumer();
            consumer.register(reg);
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Error creating registration: " + ex.getMessage());
        }
        validateRegistration(reg);
    }
    
    public void testCreateRegistrationRemote(){
        RegistrationType reg = getRegistration();
        try{
            RegistrationConsumerClient client = new RegistrationConsumerClient(this.serviceUrl);
            client.register(reg);
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Error making call: " + ex.getMessage());
        }
    }
    
    private RegistrationType getRegistration(){
        RegistrationType reg = null;
        try{
            InputStream config = getClass().getResourceAsStream(clientConfigFile);
            Reader reader = new FileReader(regFile);
            reg = (RegistrationType) Utils.deserializeObject(reader, RegistrationType.class, config);
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Error deserializing RegistrationType object: " + ex.getMessage());
        }
        return reg;
    }
    
    private void validateRegistration(RegistrationType reg) {
        //TODO: Check if it was correctly populated.
    }
    
    
    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite(){
        TestSuite suite = new TestSuite();
//        suite.addTest(new CaaersRegistrationConsumerTest("testCreateRegistrationLocal"));
        suite.addTest(new CaaersRegistrationConsumerTest("testCreateRegistrationRemote"));
        return suite;
    }
    
}
