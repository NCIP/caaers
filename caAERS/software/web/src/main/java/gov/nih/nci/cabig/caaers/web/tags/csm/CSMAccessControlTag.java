package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.caaers.utils.el.EL;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.acegisecurity.Authentication;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.util.ExpressionEvaluationUtils;
/**
 * This is coded in the same lines of {@link gov.nih.nci.cabig.ctms.acegi.csm.web.CSMAccessControlTag}
 * The tag usages :-
 *  &lt;csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck" /&gt;
 * or
 * &lt;csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="siteAuthorizationCheck"  hasPrivileges="user_admin" /&gt;
 * or
 * &lt;csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck"  hasPrivileges="ACCESS" /&gt;
 * or
 * &lt;csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck"  objectPrivilege="gov.nih..Study:READ" /&gt;
 * 
 * @author Biju Joseph
 * @author Ion C. Olaru
 *
 */
@SuppressWarnings("serial")
public class CSMAccessControlTag extends RequestContextAwareTag {
	
	public static final String AUTH_DECISION_CACHE_KEY = "AUTH_DECISION_CACHE_KEY";
	protected static final Log logger = LogFactory.getLog(CSMAccessControlTag.class);

    private String var;
    private String scope;

    private Object domainObject;
	private String hasPrivileges = "";
    
	private String objectPrivilege = "";
	private String authorizationCheckName = "";

    private String securityFacade = "caaersSecurityFacade";

	@Override
	protected int doStartTagInternal() throws Exception {

        if(StringUtils.isBlank(authorizationCheckName) && StringUtils.isBlank(objectPrivilege)) {
            throw new JspException("Either 'authorizationCheckName' or 'objectPrivilege' are required");
        }

        //evaluate the domain object
		Object resolvedDomainObject = null;
		if (domainObject instanceof String) {
			resolvedDomainObject = ExpressionEvaluationUtils.evaluate("domainObject", (String) domainObject, Object.class, pageContext);
		} else {
			resolvedDomainObject = domainObject;
		}

        //evaluate the privileges
		String evaledPrivilegesString = "";
		if(!StringUtils.isEmpty(hasPrivileges)){
			evaledPrivilegesString = ExpressionEvaluationUtils.evaluateString("hasPrivileges", hasPrivileges, pageContext);
		}
        String[] requiredPrivileges = evaledPrivilegesString.split(",");

        boolean authFlag = false;
        if(StringUtils.isNotBlank(authorizationCheckName)){
            authFlag =  checkAuthorization(resolvedDomainObject, requiredPrivileges);
        }else{
            authFlag = checkAuthorization(objectPrivilege);
        }

        setVariable(authFlag);

        
        if(authFlag) {
			logger.debug("Authorization succeeded, evaluating body");
			return Tag.EVAL_BODY_INCLUDE;
		}

		logger.debug("No permission, so skipping tag body");
		return Tag.SKIP_BODY;

		

	}

    /**
     * Populates the variable in the specified scope. 
     * @param varValue
     */
    private void setVariable(boolean varValue) {

        if (var == null) return;

        int intScope = StringUtils.equals(scope, "request") ? PageContext.REQUEST_SCOPE :
                       StringUtils.equals(scope, "session") ? PageContext.SESSION_SCOPE :
                       StringUtils.equals(scope, "application") ? PageContext.APPLICATION_SCOPE : PageContext.PAGE_SCOPE;

        pageContext.setAttribute(getVar(), varValue, intScope);
    }



    protected boolean checkAuthorization(String objectPrivilege) throws Exception {

        String op = objectPrivilege;
        ObjectPrivilegeParser p = new ObjectPrivilegeParser(objectPrivilege);
        for (String[] s : p.getDomainObjectPrivileges()) {
            String domainObject = s[0];
            String privilege = s[1];
            boolean isAuth = checkAuthorization(domainObject, privilege);
            op = op.replace(domainObject + ":" + privilege, String.valueOf(isAuth));
        }
        EL el = new EL();
        String s = el.evaluate("${" + op + "}");
        boolean isAuth = Boolean.parseBoolean(s);
        return isAuth;

    }
    protected boolean checkAuthorization(Object object, String[] privileges){
        for(String privilege : privileges) {
            if(checkAuthorization(object, privilege)) return true;
        }
        return false;
    }
    protected boolean checkAuthorization(Object object, String privilege){

        if (object == null) return false;

        //get it from the cache
        Boolean decision = getAuthorizationDecisionCache().isAuthorized(object, privilege);
        if(decision != null) return decision;

        Authentication auth = SecurityUtils.getAuthentication();
        ApplicationContext context = getRequestContext().getWebApplicationContext();
        CSMAuthorizationCheck authzCheck = (!StringUtils.isEmpty(authorizationCheckName)) ? (CSMAuthorizationCheck)context.getBean(authorizationCheckName) : null;

        if(authzCheck == null){
           CaaersSecurityFacade caaersSecurityFacade = (CaaersSecurityFacade)context.getBean(securityFacade);
           decision =  caaersSecurityFacade.checkAuthorization(auth, object.toString(), privilege);
        }else{
           decision = authzCheck.checkAuthorization(auth, privilege, object);
        }

        getAuthorizationDecisionCache().addDecision(object, privilege, decision);
        return decision;
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

    public String getVar() {
        return this.var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
