package gov.nih.nci.cabig.caaers.web.tags.csm;

import java.io.IOException;

import gov.nih.nci.cabig.caaers.security.OriginalAuthenticationHolder;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.security.FabricatedAuthenticationFilter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

/**
 * Used to swap out {@link Authentication} objects in the current security
 * context. See http://jira.semanticbits.com/browse/CAAERS-4098
 * 
 * @author dkrylov
 * @see http://jira.semanticbits.com/browse/CAAERS-4098
 * @see OriginalAuthenticationHolder
 * @see FabricatedAuthenticationFilter
 */
public final class SwapAuthenticationsTag extends SimpleTagSupport {

	private boolean useOriginal;

	public boolean isUseOriginal() {
		return useOriginal;
	}

	public void setUseOriginal(boolean useOriginal) {
		this.useOriginal = useOriginal;
	}

	@Override
	public void doTag() throws JspException, IOException {
		Authentication copy = SecurityUtils.getAuthentication();

		if (useOriginal && SecurityUtils.getOriginalAuthentication() != null) {
			SecurityContextHolder.getContext().setAuthentication(
					SecurityUtils.getOriginalAuthentication());

		}
		try {
			getJspBody().invoke(getJspContext().getOut());
		} finally {
			SecurityContextHolder.getContext().setAuthentication(copy);
		}
	}

}
