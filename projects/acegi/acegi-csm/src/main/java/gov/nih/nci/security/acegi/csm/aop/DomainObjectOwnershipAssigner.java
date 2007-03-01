package gov.nih.nci.security.acegi.csm.aop;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;

import org.acegisecurity.context.SecurityContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;

public class DomainObjectOwnershipAssigner {
	
	
	private AuthorizationManager csmAuthorizationManager;

	public Object assignOwner(ProceedingJoinPoint pjp) throws Throwable{
		Object domainObject = pjp.getArgs()[0];
		Object id = pjp.proceed();
		
		String owner = SecurityContextHolder.getContext().getAuthentication().getName();
		String objectId = generateId(domainObject, id);
		
		ProtectionElement pe = new ProtectionElement();
		pe.setProtectionElementName(objectId);
		pe.setObjectId(objectId);
		getCsmAuthorizationManager().createProtectionElement(pe);
		getCsmAuthorizationManager()
				.setOwnerForProtectionElement(objectId,
						new String[] { owner });
		
		
		return id;
	}
	
	protected String generateId(Object object, Object id){
		return object.getClass().getName() + ":" + id;
	}

	public AuthorizationManager getCsmAuthorizationManager() {
		return csmAuthorizationManager;
	}

	public void setCsmAuthorizationManager(
			AuthorizationManager csmAuthorizationManager) {
		this.csmAuthorizationManager = csmAuthorizationManager;
	}

}
