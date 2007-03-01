package gov.nih.nci.security.acegi.csm.web;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.acegisecurity.Authentication;
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


	public int doStartTag() throws JspException {
		if ((null == hasPrivileges) || "".equals(hasPrivileges)) {
			return Tag.SKIP_BODY;
		}

		final String evaledPrivilegesString = ExpressionEvaluationUtils
				.evaluateString("hasPrivilege", hasPrivileges, pageContext);

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
			if (logger.isDebugEnabled()) {
				logger
						.debug("domainObject resolved to null, so including tag body");
			}

			// Of course they have access to a null object!
			return Tag.EVAL_BODY_INCLUDE;
		}

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("SecurityContextHolder did not return a non-null Authentication object, so skipping tag body");
			}

			return Tag.SKIP_BODY;
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		ApplicationContext context = getContext(pageContext);

		List authzChecks = (List) context.getBean("commonAuthorizationChecks");

		if ((authzChecks == null) || (authzChecks.size() == 0)) {
			return Tag.SKIP_BODY;
		}

		for (int i = 0; i < requiredPrivileges.length; i++) {

			for (Iterator j = authzChecks.iterator(); j.hasNext();) {
				CSMAuthorizationCheck check = (CSMAuthorizationCheck) j.next();
				if(check.checkAuthorization(auth, requiredPrivileges[i], resolvedDomainObject)){
					return Tag.EVAL_BODY_INCLUDE;
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("No permission, so skipping tag body");
		}

		return Tag.SKIP_BODY;
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

}
