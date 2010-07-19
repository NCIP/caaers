package gov.nih.nci.cabig.caaers.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * Will store the method parameters in a thread-local.
 * @author: Biju Joseph
 */
@Aspect
public class MethodParamsTrackerAspect {

    @Around("execution(public * gov.nih.nci.cabig.caaers.dao..*.merge*(..))" +
            "|| execution(public * gov.nih.nci.cabig.caaers.dao..*.reassociate*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.dao..*.lock*(..))" +
            "|| execution(public * gov.nih.nci.cabig.caaers.dao..*.save*(..))" +
            "|| execution(public * gov.nih.nci.cabig.caaers.dao..*.update*(..))")
    public Object captureParams(ProceedingJoinPoint call) throws Throwable{
        MethodParamsHolder.setParams(call.getArgs());
        return call.proceed();
    }
}
