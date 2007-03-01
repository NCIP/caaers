package gov.nih.nci.security.acegi.csm.authorization;

import org.aspectj.lang.ProceedingJoinPoint;

public interface PrivilegeAndObjectRetrievalStrategy {
	
	PrivilegeAndObject retrieve(ProceedingJoinPoint pjp);

}
