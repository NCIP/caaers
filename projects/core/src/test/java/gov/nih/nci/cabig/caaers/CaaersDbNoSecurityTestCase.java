package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.caaers.security.stub.AspectJSecurityInterceptorStub;
import gov.nih.nci.security.acegi.csm.aop.SecurityInterceptorAspect;

import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.Aspects;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CaaersDbNoSecurityTestCase extends CaaersDbTestCase {
	
	@Override
	protected void setUp() throws Exception {
		// change the security interceptor with stub.
		super.setUp();
		aspect.setSecurityInterceptor(stubInterceptor);
	}
}
