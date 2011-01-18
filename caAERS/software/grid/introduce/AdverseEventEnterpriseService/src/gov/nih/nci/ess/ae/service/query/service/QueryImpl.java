package gov.nih.nci.ess.ae.service.query.service;

import gov.nih.nci.ess.ae.service.misc.SpringContextCreator;
import gov.nih.nci.ess.ae.service.query.common.QueryI;

import java.rmi.RemoteException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * TODO:I am the service side implementation class. IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class QueryImpl extends QueryImplBase {

	private static final String BEAN_NAME = "adverseEventQueryImpl";

	private QueryI aeQuery;

	public QueryImpl() throws RemoteException {
		super();		
	}

	/**
	 * @throws BeansException
	 */
	private void initialize() throws BeansException {
		if (aeQuery==null) {
			ApplicationContext ctx = SpringContextCreator.getApplicationContext();
			aeQuery = (QueryI) ctx.getBean(BEAN_NAME);
		}
	}

  public ess.caaers.nci.nih.gov.DSET_AdverseEvent findAdverseEvents(ess.caaers.nci.nih.gov.AdverseEvent adverseEvent) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	    initialize();
		return aeQuery.findAdverseEvents(adverseEvent);	    
	}

  public ess.caaers.nci.nih.gov.AdverseEvent getAdverseEventData(ess.caaers.nci.nih.gov.Id adverseEventIdentifier) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	    initialize();
		return aeQuery.getAdverseEventData(adverseEventIdentifier);
	}

}
