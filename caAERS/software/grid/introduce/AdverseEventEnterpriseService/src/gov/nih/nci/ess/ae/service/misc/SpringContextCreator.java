/**
 * 
 */
package gov.nih.nci.ess.ae.service.misc;

import gov.nih.nci.ess.ae.service.common.AdverseEventEnterpriseServiceI;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.xml.rpc.handler.soap.SOAPMessageContext;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.globus.wsrf.config.ContainerConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Denis G. Krylov
 * 
 */
public final class SpringContextCreator {

	/**
	 * @return
	 * @throws BeansException
	 */
	public static synchronized ApplicationContext getApplicationContext()
			throws BeansException {
		ApplicationContext ctx = null;
		// see http://jira.semanticbits.com/browse/CAAERS-4291
		// let's see if ApplicationContext is already available as a
		// WebApplicationContext
		// if so, use it; otherwise, fall back to standard approach for
		// backward compatibility.
		SOAPMessageContext messageContext = MessageContext.getCurrentContext();
		if (messageContext != null) {
			HttpServlet srv = (HttpServlet) messageContext
					.getProperty(HTTPConstants.MC_HTTP_SERVLET);
			if (srv != null) {
				ServletContext servletContext = srv.getServletContext();
				ctx = WebApplicationContextUtils
						.getWebApplicationContext(servletContext);
			}
		}

		if (ctx == null) {
			System.out
					.println("Unable to find pre-existing spring context in servlet context; falling back to direct context creation.");
			if (SpringContextHolder.getApplicationContext() == null) {
				ctx = new ClassPathXmlApplicationContext(
						ContainerConfig
								.getConfig()
								.getOption(
										AdverseEventEnterpriseServiceI.SPRING_CLASSPATH_EXPRESSION,
										AdverseEventEnterpriseServiceI.DEFAULT_SPRING_CLASSPATH_EXPRESSION));
				SpringContextHolder.setApplicationContext(ctx);
			} else {
				ctx = SpringContextHolder.getApplicationContext();
			}
		}

		return ctx;
	}

}
