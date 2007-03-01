package gov.nih.nci.security.acegi.csm.aop;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;
import gov.nih.nci.security.acegi.csm.authorization.ObjectResultHandler;
import gov.nih.nci.security.acegi.csm.authorization.PrivilegeAndObject;
import gov.nih.nci.security.acegi.csm.authorization.PrivilegeAndObjectRetrievalStrategy;

import java.util.List;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

public class CSMAuthorizationAspect {

	private Log logger = LogFactory.getLog(CSMAuthorizationAspect.class);

	private List<CSMAuthorizationCheck> authorizationChecks;

	private PrivilegeAndObjectRetrievalStrategy privilegeAndObjectRetrievalStrategy;

	private ObjectResultHandler objectResultHandler;

	public Object advise(ProceedingJoinPoint pjp) throws Throwable {

		logger.debug("############# advising...");

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		PrivilegeAndObject pao = getPrivilegeAndObjectRetrievalStrategy()
				.retrieve(pjp);

		boolean isAuthorized = false;
		for (CSMAuthorizationCheck check : getAuthorizationChecks()) {
			logger.debug("checking authoriziation...");

			isAuthorized = check.checkAuthorization(authentication, pao
					.getPrivilege(), pao.getObject());
			if (isAuthorized) {
				break;
			}

		}

		if (!isAuthorized) {
			throw new AccessDeniedException("Access Denied");
		}

		return getObjectResultHandler().handle(authentication, pjp,
				pjp.proceed());
	}

	public List<CSMAuthorizationCheck> getAuthorizationChecks() {
		return authorizationChecks;
	}

	public void setAuthorizationChecks(
			List<CSMAuthorizationCheck> authorizationChecks) {
		this.authorizationChecks = authorizationChecks;
	}

	public ObjectResultHandler getObjectResultHandler() {
		return objectResultHandler;
	}

	public void setObjectResultHandler(ObjectResultHandler objectResultHandler) {
		this.objectResultHandler = objectResultHandler;
	}

	public PrivilegeAndObjectRetrievalStrategy getPrivilegeAndObjectRetrievalStrategy() {
		return privilegeAndObjectRetrievalStrategy;
	}

	public void setPrivilegeAndObjectRetrievalStrategy(
			PrivilegeAndObjectRetrievalStrategy privilegeAndObjectRetrievalStrategy) {
		this.privilegeAndObjectRetrievalStrategy = privilegeAndObjectRetrievalStrategy;
	}

}
