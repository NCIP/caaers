package testapp.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * A filter which implements the Open Session In View pattern. Different from
 * the one built into Spring because this one delegates to an instance of
 * {@link OpenSessionInViewInterceptor} configured in the application context.
 * This permits the use of the same interceptor for deployed code & unit tests.
 * 
 * @see org.springframework.orm.hibernate3.support.OpenSessionInViewFilter
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC. Refactor into a shared library. */
public class OpenSessionInViewInterceptorFilter implements Filter {
	private String interceptorBeanName;

	protected final Log log = LogFactory.getLog(getClass());

	private ServletContext servletContext;

	protected ServletContext getServletContext() {
		return servletContext;
	}

	protected WebApplicationContext getApplicationContext() {
		return WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		servletContext = filterConfig.getServletContext();
		setInterceptorBeanName(filterConfig
				.getInitParameter("interceptorBeanName"));
	}

	/**
	 * @see OpenSessionInViewInterceptor
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		log.debug("###### Opening session for request ########");
		OpenSessionInViewInterceptor interceptor = (OpenSessionInViewInterceptor) getApplicationContext()
				.getBean(getInterceptorBeanName());
		WebRequest webRequest = new ServletWebRequest(
				(HttpServletRequest) request);
		interceptor.preHandle(webRequest);
		try {
			chain.doFilter(request, response);
			interceptor.postHandle(webRequest, null);
		} finally {
			interceptor.afterCompletion(webRequest, null);
			log.debug("############ Session closed ###########");
		}
	}

	public String getInterceptorBeanName() {
		return interceptorBeanName;
	}

	public void setInterceptorBeanName(String interceptorBeanName) {
		this.interceptorBeanName = interceptorBeanName;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
