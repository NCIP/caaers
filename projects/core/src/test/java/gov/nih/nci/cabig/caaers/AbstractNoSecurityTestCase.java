package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.caaers.security.stub.AspectJSecurityInterceptorStub;

import org.aspectj.lang.Aspects;

/**
 * Similar to abstract testcase, but will stub-off the AspectJSecurityAspect
 * @author Biju Joseph
 *
 */
public class AbstractNoSecurityTestCase extends AbstractTestCase {
	
	@Override
	protected void setUp() throws Exception {
		StudyParticipantAssignmentAspect aspect = null;
		try{
			aspect = Aspects.aspectOf(StudyParticipantAssignmentAspect.class);
		}catch(Exception e){
			aspect = new StudyParticipantAssignmentAspect();
		}
		AspectJSecurityInterceptorStub interceptorStub = new AspectJSecurityInterceptorStub();
        aspect.setSecurityInterceptor(interceptorStub);
		super.setUp();
	}
}
