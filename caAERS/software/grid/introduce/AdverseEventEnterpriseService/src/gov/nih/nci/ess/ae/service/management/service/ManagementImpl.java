package gov.nih.nci.ess.ae.service.management.service;

import gov.nih.nci.ess.ae.service.common.AdverseEventEnterpriseServiceI;
import gov.nih.nci.ess.ae.service.management.common.ManagementI;

import java.rmi.RemoteException;

import org.globus.wsrf.config.ContainerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class ManagementImpl extends ManagementImplBase {
	
	private static final String BEAN_NAME = "adverseEventManagementImpl";

    private ManagementI aeManagement;
	
	public ManagementImpl() throws RemoteException {
		super();
		String exp = ContainerConfig.getConfig().getOption(AdverseEventEnterpriseServiceI.SPRING_CLASSPATH_EXPRESSION, AdverseEventEnterpriseServiceI.DEFAULT_SPRING_CLASSPATH_EXPRESSION);
	    ApplicationContext ctx = new ClassPathXmlApplicationContext(exp);
	    aeManagement = (ManagementI) ctx.getBean(BEAN_NAME);
   }
	
  public ess.caaers.nci.nih.gov.AdverseEvent initiateAdverseEvent(ess.caaers.nci.nih.gov.Id subjectIdentifier,ess.caaers.nci.nih.gov.Id studyIdentifier,ess.caaers.nci.nih.gov.TsDateTime courseStartDate) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  return aeManagement.initiateAdverseEvent(subjectIdentifier, studyIdentifier, courseStartDate);
  }

  public ess.caaers.nci.nih.gov.AdverseEvent updateAdverseEvent(ess.caaers.nci.nih.gov.AdverseEvent adverseEvent) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
    return aeManagement.updateAdverseEvent(adverseEvent);
  }

  public ess.caaers.nci.nih.gov.AdverseEvent deactivateAdverseEvent(ess.caaers.nci.nih.gov.Id adverseEventIdentifier) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
    return aeManagement.deactivateAdverseEvent(adverseEventIdentifier);
  }

}

