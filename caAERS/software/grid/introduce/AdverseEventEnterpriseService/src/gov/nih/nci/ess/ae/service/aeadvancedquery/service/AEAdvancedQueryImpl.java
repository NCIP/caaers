/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.ae.service.aeadvancedquery.service;

import gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI;
import gov.nih.nci.ess.ae.service.common.AdverseEventEnterpriseServiceI;
import gov.nih.nci.ess.ae.service.misc.SpringContextCreator;
import gov.nih.nci.ess.ae.service.misc.SpringContextHolder;

import java.rmi.RemoteException;

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
 * TODO:I am the service side implementation class. IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class AEAdvancedQueryImpl extends AEAdvancedQueryImplBase {

	private static final String BEAN_NAME = "adverseEventAdvancedQueryImpl";

	private AEAdvancedQueryI advancedQueryI;

	public AEAdvancedQueryImpl() throws RemoteException {
		super();
	}
	
	/**
	 * Will try to locate a pre-existent {@link ApplicationContext}; if failed,
	 * will create it explicitly.
	 * 
	 * @see http://jira.semanticbits.com/browse/CAAERS-4291
	 */
	private synchronized void initialize() {
		if (advancedQueryI==null) {
			ApplicationContext ctx = SpringContextCreator.getApplicationContext();
			advancedQueryI = (AEAdvancedQueryI) ctx.getBean(BEAN_NAME);			
		}
	}
	
  public ess.caaers.nci.nih.gov.DSET_AdverseEvent findAdverseEvents(ess.caaers.nci.nih.gov.AdverseEventQuery adverseEventQuery,ess.caaers.nci.nih.gov.LimitOffset limitOffset) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	    initialize();
		return advancedQueryI
				.findAdverseEvents(adverseEventQuery, limitOffset);	    
	}

  public ess.caaers.nci.nih.gov.DSET_AuditTrail getAuditTrailOfAdverseEvent(ess.caaers.nci.nih.gov.Id adverseEventIdentifier,ess.caaers.nci.nih.gov.TsDateTime minDate) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  initialize();
	  return advancedQueryI.getAuditTrailOfAdverseEvent(adverseEventIdentifier, minDate);	  
  }

}
