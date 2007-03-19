package gov.nih.nci.security.acegi.csm.web;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.HttpSessionContextIntegrationFilter;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.ExpressionEvaluationUtils;

public class CSMAccessControlTag extends TagSupport {

	protected static final Log logger = LogFactory
			.getLog(CSMAccessControlTag.class);

	private Object domainObject;

	private String hasPrivileges = "";

	private String authorizationCheckName = "";

	public int doStartTag() throws JspException {
		if (authorizationCheckName == null
				|| authorizationCheckName.trim().length() == 0) {
			throw new JspException("authorizationCheckName is required");
		}

		String evaledAuthorizationCheckName = ExpressionEvaluationUtils
				.evaluateString("authorizationCheckName",
						authorizationCheckName, pageContext);

		String evaledPrivilegesString = hasPrivileges;
		if (evaledPrivilegesString != null
				&& evaledPrivilegesString.trim().length() > 0) {
			evaledPrivilegesString = ExpressionEvaluationUtils.evaluateString(
					"hasPrivileges", hasPrivileges, pageContext);
		}

		String[] requiredPrivileges = evaledPrivilegesString.split(",");

		Object resolvedDomainObject = null;

		if (domainObject instanceof String) {
			resolvedDomainObject = ExpressionEvaluationUtils.evaluate(
					"domainObject", (String) domainObject, Object.class,
					pageContext);
		} else {
			resolvedDomainObject = domainObject;
		}

		if (resolvedDomainObject == null) {

			logger
					.debug("domainObject resolved to null, so including tag body");

			return Tag.EVAL_BODY_INCLUDE;
		}

		Authentication auth = getAuthentication();
		if (auth == null) {
			logger
					.debug("No Authentication object found in session, so skipping tag body");

			return Tag.SKIP_BODY;
		}

		ApplicationContext context = getContext(pageContext);

		CSMAuthorizationCheck authzCheck = (CSMAuthorizationCheck) context
				.getBean(evaledAuthorizationCheckName);
		if (authzCheck == null) {
			throw new JspException(
					"No authorization check found for bean name '"
							+ evaledAuthorizationCheckName + "'.");
		}

		for (String requiredPrivilege : requiredPrivileges) {

			if (authzCheck.checkAuthorization(auth, requiredPrivilege,
					resolvedDomainObject)) {
				logger.debug("Authorization succeeded, evaluating body");
				return Tag.EVAL_BODY_INCLUDE;
			}

		}

		logger.debug("No permission, so skipping tag body");

		return Tag.SKIP_BODY;
	}

	private Authentication getAuthentication() {
		Authentication auth = null;
		SecurityContext securityContext = (SecurityContext) this.pageContext.getSession().getAttribute(HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY);
		if(securityContext != null){
			auth = securityContext.getAuthentication();
		}
		return auth;
	}

	/**
	 * Allows test cases to override where application context obtained from.
	 * 
	 * @param pageContext
	 *            so the <code>ServletContext</code> can be accessed as
	 *            required by Spring's <code>WebApplicationContextUtils</code>
	 * 
	 * @return the Spring application context (never <code>null</code>)
	 */
	protected ApplicationContext getContext(PageContext pageContext) {
		ServletContext servletContext = pageContext.getServletContext();

		return WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
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
