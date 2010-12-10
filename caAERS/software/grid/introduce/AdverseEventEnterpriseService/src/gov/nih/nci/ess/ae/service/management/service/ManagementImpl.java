package gov.nih.nci.ess.ae.service.management.service;

import gov.nih.nci.ess.ae.service.common.AdverseEventEnterpriseServiceI;
import gov.nih.nci.ess.ae.service.management.common.ManagementI;
import gov.nih.nci.ess.ae.service.misc.SpringContextHolder;

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
public class ManagementImpl extends ManagementImplBase {

	private static final String BEAN_NAME = "adverseEventManagementImpl";

	private static ManagementI aeManagement;

	public ManagementImpl() throws RemoteException {
		super();
		loadApplicationContext();
	}

	/**
	 * @throws BeansException
	 */
	private synchronized static void loadApplicationContext() throws BeansException {
		if (aeManagement==null) {
			ApplicationContext ctx = getApplicationContext();
			aeManagement = (ManagementI) ctx.getBean(BEAN_NAME);
		}
	}

	/**
	 * @return
	 * @throws BeansException
	 */
	private synchronized static ApplicationContext getApplicationContext() throws BeansException {
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

	public ess.caaers.nci.nih.gov.AdverseEvent initiateAdverseEvent(
			ess.caaers.nci.nih.gov.Id subjectIdentifier,
			ess.caaers.nci.nih.gov.Id studyIdentifier,
			ess.caaers.nci.nih.gov.AdverseEvent adverseEvent,
			ess.caaers.nci.nih.gov.TsDateTime courseStartDate)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		return aeManagement.initiateAdverseEvent(subjectIdentifier,
				studyIdentifier, adverseEvent, courseStartDate);
	}

	public ess.caaers.nci.nih.gov.AdverseEvent updateAdverseEvent(
			ess.caaers.nci.nih.gov.AdverseEvent adverseEvent)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		return aeManagement.updateAdverseEvent(adverseEvent);
	}

	public ess.caaers.nci.nih.gov.AdverseEvent deactivateAdverseEvent(
			ess.caaers.nci.nih.gov.Id adverseEventIdentifier)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		return aeManagement.deactivateAdverseEvent(adverseEventIdentifier);
	}

}
