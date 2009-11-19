package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * @author Biju Joseph
 *
 */
public class RunAsAuthenticationPopulatorIncerceptorTest extends CaaersTestCase {
	
	RunAsAuthenticationPopulatorIncerceptor interceptor;
	
	protected void setUp() throws Exception {
		super.setUp();
		interceptor = (RunAsAuthenticationPopulatorIncerceptor) getDeployedApplicationContext().getBean("runAsAutenticationProviderInterceptor");
	}

	public void testInvoke() throws Exception,Throwable{

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
				return "SYSTEM".equals(SecurityUtils.getUserLoginName());
			}
			
		};
		
		
		Object retVal = interceptor.invoke(method);
		assertNotNull(retVal);
		assertEquals(Boolean.TRUE, retVal);
		
	}
	
	
	public void testInvokeThrowingException() throws Throwable{
		final Exception e = new RuntimeException("test");
		
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
				if(true) throw e;
				return "SYSTEM".equals(SecurityUtils.getUserLoginName());
			}
			
		};
		
		
		try {
			 interceptor.invoke(method);
			fail("must throw exception");
		} catch (Exception e1) {
			assertSame(e, e1);
		}
		
	}

}
