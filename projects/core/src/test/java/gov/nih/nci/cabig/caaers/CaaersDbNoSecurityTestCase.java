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
	
	
	AspectJSecurityInterceptor orgInterceptor;
	StudyParticipantAssignmentAspect aspect = null;
    SecurityInterceptorAspect securityInterceptorAspect = null;
    
	/**
	 *   Will override the security interceptor in {@link StudyParticipantAssignmentAspect}
	 *   Will override the security interceptor in {@link SecurityInterceptorAspect}
	 */
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		AspectJSecurityInterceptorStub interceptorStub = new AspectJSecurityInterceptorStub();
		//
		try{
			aspect = (StudyParticipantAssignmentAspect) getDeployedApplicationContext().getBean("studyParticipantAssignmentAspect");
			orgInterceptor = aspect.getSecurityInterceptor();
			aspect.setSecurityInterceptor(interceptorStub);
		}catch(Exception e){
		}
		
    
    
        try{
        	securityInterceptorAspect = (SecurityInterceptorAspect) getDeployedApplicationContext().getBean("daoSecurityInterceptorAspectBean");
        	orgInterceptor = securityInterceptorAspect.getSecurityInterceptor();
        	securityInterceptorAspect.setSecurityInterceptor(null);
        }catch(Exception e){
        	
        }
	}
	
	@Override
	protected void tearDown() throws Exception {
		aspect.setSecurityInterceptor(orgInterceptor);
		securityInterceptorAspect.setSecurityInterceptor(orgInterceptor);
		super.tearDown();
	}
}
