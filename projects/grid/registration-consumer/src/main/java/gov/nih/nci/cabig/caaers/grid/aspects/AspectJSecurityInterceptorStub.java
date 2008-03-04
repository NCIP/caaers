package gov.nih.nci.cabig.caaers.grid.aspects;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.JoinPoint;

public class AspectJSecurityInterceptorStub extends AspectJSecurityInterceptor {
    @Override
    public Object invoke(JoinPoint jp, AspectJCallback advisorProceed) {
        // TODO Auto-generated method stub
        return null;
    }
}
