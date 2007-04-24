/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class JoinPointPrivilegeAndObjectIdGenerator extends
		AbstractPrivilegeAndObjectIdGenerator implements InitializingBean {
	
	private Map<AspectJExpressionPointcut,String[]> internalObjectPrivilegeMap;

	/**
	 * 
	 */
	public JoinPointPrivilegeAndObjectIdGenerator() {
	}
	
	@Override
	protected String[] getObjectPrivilege(Object object){
		String[] objectPrivilege = null;
		if(object != null){
			assertSupports(object);
			JoinPoint joinPoint = (JoinPoint)object;
			MethodSignature sig = (MethodSignature)joinPoint.getSignature();
			Class klass = sig.getDeclaringType();
			Method method = sig.getMethod();
			Object[] params = joinPoint.getArgs();
			for(AspectJExpressionPointcut pointcut : this.internalObjectPrivilegeMap.keySet()){
				if(pointcut.matches(method, klass, params)){
					objectPrivilege = this.internalObjectPrivilegeMap.get(pointcut);
					break;
				}
			}
		}
		return objectPrivilege;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.acegi.csm.authorization.AbstractPrivilegeAndObjectIdGenerator#getKeyValue(java.lang.Object)
	 */
	@Override
	protected String getKeyValue(Object object) {
		// not used
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.acegi.csm.authorization.AbstractPrivilegeAndObjectIdGenerator#supports(java.lang.Object)
	 */
	@Override
	protected boolean supports(Object object) {
		return object instanceof JoinPoint && ((JoinPoint)object).getSignature() instanceof MethodSignature;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(getObjectPrivilegeMap(), "An objectPrivilegeMap property is required.");
		this.internalObjectPrivilegeMap = new LinkedHashMap<AspectJExpressionPointcut, String[]>();
		for(String expression : getObjectPrivilegeMap().keySet()){
			String objectPrivilegeStr = getObjectPrivilegeMap().get(expression);
			String[] objectPrivilege = splitObjectPrivilege(objectPrivilegeStr);
			AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
			pointcut.setExpression(expression);
			this.internalObjectPrivilegeMap.put(pointcut, objectPrivilege);
		}
	}

}
