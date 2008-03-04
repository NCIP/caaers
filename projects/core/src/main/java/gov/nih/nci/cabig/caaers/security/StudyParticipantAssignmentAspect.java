/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
@Aspect
public class StudyParticipantAssignmentAspect {

    private static final Log logger = LogFactory.getLog(StudyParticipantAssignmentAspect.class);

    private AspectJSecurityInterceptor securityInterceptor;

    @Before("execution(public void gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment+.setParticipant(gov.nih.nci.cabig.caaers.domain.Participant+))")
    public void advise(JoinPoint jp) {
        // logger.debug("############ advising " + jp.toLongString()
        // + " ############");
        AspectJCallback callback = new AspectJCallback() {
            public Object proceedWithObject() {
                return null;
            }
        };
        AspectJSecurityInterceptor interceptor = getSecurityInterceptor();
        if (interceptor == null) {
            throw new RuntimeException("interceptor not set");
        }
        interceptor.invoke(jp, callback);
    }

    public AspectJSecurityInterceptor getSecurityInterceptor() {
        return securityInterceptor;
    }

    @Required
    public void setSecurityInterceptor(AspectJSecurityInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

}
