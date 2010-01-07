package gov.nih.nci.cabig.caaers.tools.configuration;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ConfigurationTest extends CaaersTestCase {
	Configuration configuration;
	protected void setUp() throws Exception {
		super.setUp();
		configuration = (Configuration) getDeployedApplicationContext().getBean("configuration");
	}
	
	public void testLoad(){
		assertNotNull(Configuration.LAST_LOADED_CONFIGURATION);
	}
	
	public void testGetProperties() {
		assertNotNull(configuration.get(Configuration.UNIDENTIFIED_MODE));
	}
	
	public void testLoad_WillReflectChange(){
		
		configuration.set(Configuration.AUTO_COMPLETER_CHARS, "33");
		assertEquals("33", Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.AUTO_COMPLETER_CHARS));
	}

}
