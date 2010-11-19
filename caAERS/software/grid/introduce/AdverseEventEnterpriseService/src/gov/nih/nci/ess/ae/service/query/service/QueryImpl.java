package gov.nih.nci.ess.ae.service.query.service;

import gov.nih.nci.ess.ae.service.common.AdverseEventEnterpriseServiceI;
import gov.nih.nci.ess.ae.service.query.common.QueryI;

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
public class QueryImpl extends QueryImplBase {

	private static final String BEAN_NAME = "adverseEventQueryImpl";

    private QueryI aeQuery;
    
	public QueryImpl() throws RemoteException {
		super();
		String exp = ContainerConfig.getConfig().getOption(AdverseEventEnterpriseServiceI.SPRING_CLASSPATH_EXPRESSION, AdverseEventEnterpriseServiceI.DEFAULT_SPRING_CLASSPATH_EXPRESSION);
	    ApplicationContext ctx = new ClassPathXmlApplicationContext(exp);
	    aeQuery = (QueryI) ctx.getBean(BEAN_NAME);
	}
	
  public ess.caaers.nci.nih.gov.AdverseEvent[] findAdverseEvents(ess.caaers.nci.nih.gov.AdverseEvent adverseEvent) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
    return aeQuery.findAdverseEvents(adverseEvent);
  }

  public ess.caaers.nci.nih.gov.AdverseEvent getAdverseEventData(ess.caaers.nci.nih.gov.Id adverseEventIdentifier) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  return aeQuery.getAdverseEventData(adverseEventIdentifier);
  }

}

