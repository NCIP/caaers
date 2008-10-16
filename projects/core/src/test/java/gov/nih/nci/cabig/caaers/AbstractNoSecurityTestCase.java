package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.caaers.security.stub.AspectJSecurityInterceptorStub;

import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.Aspects;

/**
 * Similar to abstract testcase, but will stub-off the AspectJSecurityAspect
 * @author Biju Joseph
 *
 */
public class AbstractNoSecurityTestCase extends AbstractTestCase {
	private StudyParticipantAssignmentAspect aspect;
	private AspectJSecurityInterceptor interceptor ;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try{
			aspect = Aspects.aspectOf(StudyParticipantAssignmentAspect.class);
			interceptor = aspect.getSecurityInterceptor();
			AspectJSecurityInterceptorStub interceptorStub = new AspectJSecurityInterceptorStub();
	        aspect.setSecurityInterceptor(interceptorStub);
			
		}catch(Exception e){
			aspect = new StudyParticipantAssignmentAspect();
		}
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		aspect.setSecurityInterceptor(interceptor);
		super.tearDown();
	}
}
