package gov.nih.nci.security.acegi.csm.authorization;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CSMBasicObjectResultHandler implements ObjectResultHandler {

	private static final Log logger = LogFactory
			.getLog(CSMBasicObjectResultHandler.class);

	private String processDomainObjectClass;

	private boolean removeUnauthorizedObjects = true;

	private List authorizationChecks;

	public boolean isRemoveUnauthorizedObjects() {
		return removeUnauthorizedObjects;
	}

	public void setRemoveUnauthorizedObjects(boolean removeUnauthorizedObjects) {
		this.removeUnauthorizedObjects = removeUnauthorizedObjects;
	}

	public Object handle(Authentication authentication, Object secureObject,
			Object returnedObject) {

		Object retVal = returnedObject;
		if (returnedObject == null) {
			return null;
		}

		Class klass = null;
		try {
			klass = Class.forName(getProcessDomainObjectClass());
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Couldn't load "
					+ getProcessDomainObjectClass(), ex);
		}

		boolean isArray = false;
		boolean isCollection = false;
		Collection collection = null;
		if (retVal.getClass().isArray()) {
			isArray = true;
			collection = Arrays.asList((Object[]) retVal);
		} else if (retVal instanceof Collection) {
			isCollection = true;
			collection = (Collection) retVal;
		} else {
			collection = new ArrayList();
			collection.add(retVal);
		}
		for (Iterator j = collection.iterator(); j.hasNext();) {

			Object domainObject = j.next();
			if (klass.isAssignableFrom(domainObject.getClass())) {

				boolean isAuthorized = false;
				for (Iterator k = getAuthorizationChecks().iterator(); k
						.hasNext()
						&& !isAuthorized;) {
					CSMAuthorizationCheck check = (CSMAuthorizationCheck) k
							.next();
					isAuthorized = check.checkAuthorization(authentication, null,
							domainObject);
				}
				if (!isAuthorized) {
					if (isRemoveUnauthorizedObjects()) {
						logger.debug("############# removing " + domainObject);
						j.remove();
					} else {
						throw new AccessDeniedException("Access Denied");
					}
				}
			}
		}
		if (isCollection) {
			// Nothing to do
		} else if (isArray) {
			retVal = Array
					.newInstance(collection.getClass().getComponentType(),
							collection.size());
		} else {
			if (collection.size() > 0) {
				retVal = collection.iterator().next();
			} else {
				retVal = null;
			}
		}

		return retVal;
	}

	public String getProcessDomainObjectClass() {
		return processDomainObjectClass;
	}

	public void setProcessDomainObjectClass(String processDomainObject) {
		this.processDomainObjectClass = processDomainObject;
	}

	public List getAuthorizationChecks() {
		return authorizationChecks;
	}

	public void setAuthorizationChecks(List authorizationChecks) {
		this.authorizationChecks = authorizationChecks;
	}

}
