package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class FabricatedAuthenticationFilter implements Filter {

	private static final Log log = LogFactory
			.getLog(FabricatedAuthenticationFilter.class);
	private static final String FILTER_APPLIED = "gov.nih.nci.cabig.caaers.web.security.FabricatedAuthenticationFilter.FILTER_APPLIED";
	
	public static final String CAAERS_ORIGINAL_AUTHENTICATTION = "FabricatedAuthenticationFilter.CAAERS_ORIGINAL_AUTHENTICATTION";

	private CaaersSecurityFacade securityFacade;
	
	private Map<String,String> urlMap = new HashMap<String,String>();

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)) {
			throw new ServletException("Can only process HttpServletRequest");
		}
		if (!(response instanceof HttpServletResponse)) {
			throw new ServletException("Can only process HttpServletResponse");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (request.getAttribute(FILTER_APPLIED) == null) {
			doProcessing(httpRequest, httpResponse, chain);
			request.setAttribute(FILTER_APPLIED, true);
		}
		try {
			chain.doFilter(httpRequest, httpResponse);
		} finally {
			request.removeAttribute(FILTER_APPLIED);
		}
		return;
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	private void doProcessing(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityUtils.getAuthentication();
		if (authentication!=null) {
			// ok, now need to decide whether we need to filter it out.
			
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public CaaersSecurityFacade getSecurityFacade() {
		return securityFacade;
	}

	public void setSecurityFacade(CaaersSecurityFacade securityFacade) {
		this.securityFacade = securityFacade;
	}

	public Map<String, String> getUrlMap() {
		return urlMap;
	}

	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}

}
