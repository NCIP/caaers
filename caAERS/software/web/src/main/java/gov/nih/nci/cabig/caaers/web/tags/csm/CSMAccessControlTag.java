package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.caaers.web.utils.el.EL;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.util.ExpressionEvaluationUtils;
/**
 * This is coded in the same lines of {@link gov.nih.nci.cabig.ctms.acegi.csm.web.CSMAccessControlTag}
 * 
 * @author Biju Joseph
 * @author Ion C. Olaru
 *
 */
@SuppressWarnings("serial")
public class CSMAccessControlTag extends RequestContextAwareTag {
	
	public static final String AUTH_DECISION_CACHE_KEY = "AUTH_DECISION_CACHE_KEY";
	protected static final Log logger = LogFactory.getLog(CSMAccessControlTag.class);
	private Object domainObject;
	private String hasPrivileges = "";
	private String objectPrivilege = "";
	private String authorizationCheckName = "";

    private String securityFacade;
    private CaaersSecurityFacade caaersSecurityFacade;
	private String defaultSecurityFacadeBeanID = "caaersSecurityFacade";

    // objectPrivilege != null
    // 

    // private final static String delimiter = "([&|\\||(|)\\s])+"; // "&|() " 

	@Override
	protected int doStartTagInternal() throws Exception {

        if(StringUtils.isEmpty(authorizationCheckName) && StringUtils.isEmpty(objectPrivilege)) {
            throw new JspException("Either 'authorizationCheckName' or 'objectPrivilege' are required");
        }

/*
        if(!StringUtils.isEmpty(authorizationCheckName) && domainObject == null) {
            throw new JspException("'authorizationCheckName' requires 'domainObject'");
        }
*/

        if (!StringUtils.isEmpty(securityFacade)) {
            caaersSecurityFacade = (CaaersSecurityFacade)getRequestContext().getWebApplicationContext().getBean(securityFacade);
        } else {
            caaersSecurityFacade = (CaaersSecurityFacade)getRequestContext().getWebApplicationContext().getBean(defaultSecurityFacadeBeanID);
        }

        AuthorizationDecisionCache authorizationCache = getAuthorizationDecisionCache();
        String evaledAuthorizationCheckName = ExpressionEvaluationUtils.evaluateString("authorizationCheckName", authorizationCheckName, pageContext);
        
        if (!StringUtils.isEmpty(objectPrivilege)) {
            logger.debug("Evaluating OBJECT PRIVILEGE attribute: '" + objectPrivilege + "'");
            ObjectPrivilegeParser p = new ObjectPrivilegeParser(objectPrivilege);

            for (String[] line : p.getDomainObjectPrivileges()) {
                Boolean isAuth = false;
                String domainObject = line[0];
                String privilege = line[1];
                isAuth = authorizationCache.isAuthorized(domainObject, privilege);
                if (isAuth == null) {
                    //check in CSM and add it to cache
                    isAuth = checkAuthorization(evaledAuthorizationCheckName, domainObject, new String[] {privilege});
                    authorizationCache.addDecision(domainObject, privilege, isAuth);
                }

                // replace the evaluated Boolean String in the original String
                objectPrivilege = objectPrivilege.replace(domainObject + ":" + privilege, String.valueOf(isAuth));
                logger.debug("OBJECT PRIVILEGE attribute evaluated to : '" + objectPrivilege + "'");
            }

            // Evaluate the Boolean expression
            EL el = new EL();
            String s = el.evaluate("${" + objectPrivilege + " || false}");
            Boolean isAuth = Boolean.parseBoolean(s);
            if (isAuth) return Tag.EVAL_BODY_INCLUDE; else return Tag.SKIP_BODY;
        }

		//evaluate the privileges
		String evaledPrivilegesString = "";
		if(!StringUtils.isEmpty(hasPrivileges)){
			evaledPrivilegesString = ExpressionEvaluationUtils.evaluateString("hasPrivileges", hasPrivileges, pageContext);
		}
		String[] requiredPrivileges = evaledPrivilegesString.split(",");

		//evaluate the domain object
		Object resolvedDomainObject = null;
		if (domainObject instanceof String) {
			resolvedDomainObject = ExpressionEvaluationUtils.evaluate("domainObject", (String) domainObject, Object.class, pageContext);
		} else {
			resolvedDomainObject = domainObject;
		}

		if (resolvedDomainObject == null) {
			logger.debug("domainObject resolved to null, so including tag body");
			return Tag.EVAL_BODY_INCLUDE;
		}
		
		//check in cahce
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

        // 

		logger.debug("No permission, so skipping tag body");
		return Tag.SKIP_BODY;
	}
	
	protected boolean checkAuthorization(String authCheckBeanName, Object resolvedDomainObject, String[] requiredPrivileges) throws Exception {
		Authentication auth = SecurityUtils.getAuthentication();
		ApplicationContext context = getRequestContext().getWebApplicationContext();
		CSMAuthorizationCheck authzCheck = (!StringUtils.isEmpty(authCheckBeanName)) ? (CSMAuthorizationCheck)context.getBean(authCheckBeanName) : null;

        if (requiredPrivileges == null) requiredPrivileges = new String[] { "READ" };

		if (authzCheck != null) {
            for(String privilege : requiredPrivileges){
                if (authzCheck.checkAuthorization(auth, privilege, resolvedDomainObject)) return true;
            }
        } else {
            for(String privilege : requiredPrivileges){
                if (caaersSecurityFacade.checkAuthorization(auth, (String)resolvedDomainObject, privilege)) return true;
            }
        }
		
		return false;
	}
	
	public AuthorizationDecisionCache getAuthorizationDecisionCache() {
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

    public String getObjectPrivilege() {
        return objectPrivilege;
    }

    public void setObjectPrivilege(String objectPrivilege) {
        this.objectPrivilege = objectPrivilege;
    }

    public String getSecurityFacade() {
        return securityFacade;
    }

    public void setSecurityFacade(String securityFacade) {
        this.securityFacade = securityFacade;
    }
}
