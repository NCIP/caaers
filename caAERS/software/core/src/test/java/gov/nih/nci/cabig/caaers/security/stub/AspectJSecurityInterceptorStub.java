package gov.nih.nci.cabig.caaers.security.stub;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.JoinPoint;

public class AspectJSecurityInterceptorStub extends AspectJSecurityInterceptor{
	@Override
	public Object invoke(JoinPoint jp, AspectJCallback advisorProceed) {
		return null;
	
	}
}
