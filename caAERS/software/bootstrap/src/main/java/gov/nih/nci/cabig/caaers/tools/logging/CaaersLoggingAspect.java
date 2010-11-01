package gov.nih.nci.cabig.caaers.tools.logging;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;
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
	

	private static String entryMsgPrefix = "Entering Method ";
	private static String exitMsgPrefix = "Exiting Method ";
	
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
		String userName = "[" + getUserLoginName() + "] - ";

		long startTime = System.currentTimeMillis();
		
        Log logger = (call.getTarget() == null) ? LogFactory.getLog(CaaersLoggingAspect.class) : LogFactory.getLog(call.getTarget().getClass());

		if(logger.isDebugEnabled() || logger.isTraceEnabled()) log(logger, true, call, null, 0);
		
        Object point =  call.proceed();
        
        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime);
        if(logger.isInfoEnabled()){
            if(executionTime > 500){
            	logger.info(userName + "More than 500ms [ " + call.toShortString() + " executionTime : " +  executionTime + "]");
            }
        }
        
        if(logger.isDebugEnabled() || logger.isTraceEnabled()){
        	log(logger, false, call, point, executionTime);
        }
        
        return point;
    }
	


	public void log(Log logger, boolean entry, ProceedingJoinPoint call, Object retVal, long time){
              try{
                StringBuilder sb = new StringBuilder("[").append(getUserLoginName()).append("] - ");
                if(entry) {
                    sb.append(entryMsgPrefix)
                      .append(" [").append(call.toShortString()).append(" ] with params { ").append(String.valueOf(call.getArgs()[0])).append("}");
                }else {
                    sb.append(exitMsgPrefix)
                   .append(" [").append(call.toShortString()).append(" ] with return as: {").append( String.valueOf(retVal) ).append("} - executionTime : ")
                   .append( time );

                }
                if(logger.isDebugEnabled()){
                    logger.debug(sb.toString());
                } else {
                    logger.trace(sb.toString());
                }

           }catch(Exception e) {

           }

	}


    public static String getUserLoginName(){
       try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal  =  authentication.getPrincipal();
            String userName = "";
            if (principal instanceof User) {
                userName = ((User)principal).getUsername();
            } else {
                userName = principal.toString();
            }

            return userName;
       } catch(Exception e){
          return ""; 
       }
	}

}
