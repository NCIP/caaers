package gov.nih.nci.security.acegi.csm.aop;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;

public class SecurityInterceptorAspect {

	private AspectJSecurityInterceptor securityInterceptor;

	public Object advise(ProceedingJoinPoint pjp) throws Throwable {
		if (getSecurityInterceptor() != null) {
			return getSecurityInterceptor().invoke(pjp, new Callback(pjp));
		} else {
			return pjp.proceed();
		}
	}

	private class Callback implements AspectJCallback {
		private ProceedingJoinPoint pjp;

		public Callback(ProceedingJoinPoint pjp) {
			this.pjp = pjp;
		}

		public Object proceedWithObject() {
			try {
				return pjp.proceed();
			} catch (Throwable t) {
				throw new RuntimeException("Error proceeding: "
						+ t.getMessage(), t);
			}
		}

	}

	public AspectJSecurityInterceptor getSecurityInterceptor() {
		return securityInterceptor;
	}

	public void setSecurityInterceptor(
			AspectJSecurityInterceptor securityInterceptor) {
		this.securityInterceptor = securityInterceptor;
	}

}
