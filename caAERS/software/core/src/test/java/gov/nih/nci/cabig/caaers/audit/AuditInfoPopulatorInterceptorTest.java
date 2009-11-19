package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AuditInfoPopulatorInterceptorTest extends CaaersTestCase {
	
	AuditInfoPopulatorInterceptor auditInterceptor;
	
	protected void setUp() throws Exception {
		super.setUp();
		auditInterceptor = (AuditInfoPopulatorInterceptor) getDeployedApplicationContext().getBean("auditInfoPopulatorInterceptor");
	}

	public void testInvoke() throws Throwable {

		MethodInvocation method = new MethodInvocation(){

			public Method getMethod() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object[] getArguments() {
				// TODO Auto-generated method stub
				return null;
			}

			public AccessibleObject getStaticPart() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getThis() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object proceed() throws Throwable {
				return gov.nih.nci.cabig.ctms.audit.DataAuditInfo.getLocal() != null;
			}
			
		};
		
		
		Object retVal = auditInterceptor.invoke(method);
		assertNotNull(retVal);
		assertEquals(Boolean.TRUE, retVal);
	}

}
