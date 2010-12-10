package gov.nih.nci.ess.ae.service.query.service;

import gov.nih.nci.ess.ae.service.common.AdverseEventEnterpriseServiceI;
import gov.nih.nci.ess.ae.service.misc.SpringContextHolder;
import gov.nih.nci.ess.ae.service.query.common.QueryI;

import java.rmi.RemoteException;

import org.globus.wsrf.config.ContainerConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO:I am the service side implementation class. IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class QueryImpl extends QueryImplBase {

	private static final String BEAN_NAME = "adverseEventQueryImpl";

	private static QueryI aeQuery;

	public QueryImpl() throws RemoteException {
		super();
		loadApplicationContext();
	}

	/**
	 * @throws BeansException
	 */
	private static synchronized void loadApplicationContext()
			throws BeansException {
		if (aeQuery == null) {
			ApplicationContext ctx = getApplicationContext();
			aeQuery = (QueryI) ctx.getBean(BEAN_NAME);
		}
	}

	/**
	 * @return
	 * @throws BeansException
	 */
	private static synchronized ApplicationContext getApplicationContext()
			throws BeansException {
		ApplicationContext ctx = null;
		String exp = ContainerConfig
				.getConfig()
				.getOption(
						AdverseEventEnterpriseServiceI.SPRING_CLASSPATH_EXPRESSION,
						AdverseEventEnterpriseServiceI.DEFAULT_SPRING_CLASSPATH_EXPRESSION);
		if (SpringContextHolder.getApplicationContext() == null) {
			ctx = new ClassPathXmlApplicationContext(exp);
			SpringContextHolder.setApplicationContext(ctx);
		} else {
			ctx = SpringContextHolder.getApplicationContext();
		}
		return ctx;
	}

	public ess.caaers.nci.nih.gov.AdverseEvent[] findAdverseEvents(
			ess.caaers.nci.nih.gov.AdverseEvent adverseEvent)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		return aeQuery.findAdverseEvents(adverseEvent);
	}

	public ess.caaers.nci.nih.gov.AdverseEvent getAdverseEventData(
			ess.caaers.nci.nih.gov.Id adverseEventIdentifier)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		return aeQuery.getAdverseEventData(adverseEventIdentifier);
	}

}
