package gov.nih.nci.cabig.caaers.resolver;

import com.semanticbits.coppa.infrastructure.hibernate.RemoteEntityInterceptor;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class RemoteEntityInterceptorIntegrationTest extends CaaersTestCase{
	RemoteEntityInterceptor interceptorBean;
	
	public void testBeanLoading(){
		interceptorBean = (RemoteEntityInterceptor) getDeployedApplicationContext().getBean("remoteEntityInterceptor");
		assertNotNull(interceptorBean);
		
		//coppaMode=true , makesure you have set that, before you uncomment the below line.
		//assertTrue(interceptorBean.isEnabled());
	}
}