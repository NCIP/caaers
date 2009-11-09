package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.util.ExpressionEvaluationUtils;
/**
 * This is coded in the same lines of {@link gov.nih.nci.security.acegi.csm.web.CSMAccessControlTag}
 * 
 * @author Biju Joseph
 *
 */
@SuppressWarnings("serial")
public class CSMAccessControlTag extends RequestContextAwareTag {
	
	public static final String AUTH_DECISION_CACHE_KEY = "AUTH_DECISION_CACHE_KEY";
	
	protected static final Log logger = LogFactory.getLog(CSMAccessControlTag.class);

	private Object domainObject;

	private String hasPrivileges = "";

	private String authorizationCheckName = "";
	
	@Override
	protected int doStartTagInternal() throws Exception {
		
		//mandatory authorizationCheckName
		if(StringUtils.isEmpty(authorizationCheckName)) throw new JspException("authorizationCheckName is required");
		
		//evaluate scriptlets if any
		String evaledAuthorizationCheckName = ExpressionEvaluationUtils.evaluateString("authorizationCheckName",authorizationCheckName, pageContext);
		
		//evaluate the privileges
		String evaledPrivilegesString = "";
		if(!StringUtils.isEmpty(hasPrivileges)){
			evaledPrivilegesString = ExpressionEvaluationUtils.evaluateString("hasPrivileges", hasPrivileges, pageContext);
		}
		String[] requiredPrivileges = evaledPrivilegesString.split(",");

		//evaluate the domain object
		Object resolvedDomainObject = null;
		if (domainObject instanceof String) {
			resolvedDomainObject = ExpressionEvaluationUtils.evaluate("domainObject", (String) domainObject, Object.class,pageContext);
		} else {
			resolvedDomainObject = domainObject;
		}

		if (resolvedDomainObject == null) {
			logger.debug("domainObject resolved to null, so including tag body");
			return Tag.EVAL_BODY_INCLUDE;
		}
		
		//check in cahce
		AuthorizationDecisionCache authorizationCache = getAuthorizationDecisionCache();
		Boolean authFlag = authorizationCache.isAuthorized(resolvedDomainObject, evaledPrivilegesString);
		if(authFlag == null){
			//check in CSM and add it to cache
			authFlag = checkAuthorization(evaledAuthorizationCheckName, resolvedDomainObject, requiredPrivileges);
			authorizationCache.addDecision(resolvedDomainObject, evaledPrivilegesString, authFlag);
		}
		
		if(authFlag){
			logger.debug("Authorization succeeded, evaluating body");
			return Tag.EVAL_BODY_INCLUDE;
		}
		
		logger.debug("No permission, so skipping tag body");
		return Tag.SKIP_BODY;

		
	}
	
	protected boolean checkAuthorization(String authCheckBeanName, Object resolvedDomainObject, String[] requiredPrivileges) throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ApplicationContext context = getRequestContext().getWebApplicationContext();

		CSMAuthorizationCheck authzCheck = (CSMAuthorizationCheck) context.getBean(authCheckBeanName);
		if (authzCheck == null) {
			throw new JspException("No authorization check found for bean name '"+ authCheckBeanName + "'.");
		}
		
		for(String privilege : requiredPrivileges){
			if(authzCheck.checkAuthorization(auth, privilege, resolvedDomainObject)) return true;
		}
		
		return false;

	}
	
	public AuthorizationDecisionCache getAuthorizationDecisionCache(){
		
		AuthorizationDecisionCache cache = (AuthorizationDecisionCache) pageContext.getSession().getAttribute(AUTH_DECISION_CACHE_KEY);
		if(cache == null){
			synchronized (CSMAccessControlTag.class) {
				cache = new AuthorizationDecisionCache();
				pageContext.getSession().setAttribute(AUTH_DECISION_CACHE_KEY, cache);
			}
		}
		return cache;
	}
	

	public Object getDomainObject() {
		return domainObject;
	}

	public String getHasPrivileges() {
		return hasPrivileges;
	}

	public void setDomainObject(Object domainObject) {
		this.domainObject = domainObject;
	}

	public void setHasPrivileges(String hasPermission) {
		this.hasPrivileges = hasPermission;
	}

	public String getAuthorizationCheckName() {
		return authorizationCheckName;
	}

	public void setAuthorizationCheckName(String authorizationCheckName) {
		this.authorizationCheckName = authorizationCheckName;
	}
}
