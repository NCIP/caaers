package gov.nih.nci.cabig.caaers.tools.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
/**
 * Logging on entering and exiting a method.
 * @author Biju Joseph
 *
 */
@Aspect
public class CaaersLoggingAspect {
	
	private static final Log logger = LogFactory.getLog(CaaersLoggingAspect.class);
	
	private static String entryMsgPrefix = "CaaersLoggingAspect: entering method";
	private static String exitMsgPrefix = "CaaersLoggingAspect: exiting method";
	
	@Around("execution(public * gov.nih.nci.cabig.caaers.api.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.api.impl.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.dao..*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.domain.repository.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.domain.repository.ajax.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.service..*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.validation..*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.workflow..*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.rules.business.service.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.rules.runtime.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.web.ae.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.web.study.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.web.admin.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.web.rule.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.web.participant.*.*(..))" +
			"|| execution(public * gov.nih.nci.cabig.caaers.tools.Excel*.*(..))")
	public Object log(ProceedingJoinPoint call) throws Throwable  {
		
		if(logger.isDebugEnabled()) debug(true, call, null);
		
        Object point =  call.proceed();
        
        if(logger.isDebugEnabled()) debug(false, call, point);
        
        return point;
    }
	
	
	public void debug(boolean entry, ProceedingJoinPoint call, Object retVal){
		try{
			if(entry){
				logger.debug(entryMsgPrefix + " [" + call.toShortString() + "] with param : {" + call.getArgs()[0] + "}");
			}else{
				logger.debug(exitMsgPrefix +" [" + call.toShortString()  + "with return as: {" + String.valueOf(retVal) + "}");
			}
			
		}catch(Exception ignore){ 
		}
	}

}
