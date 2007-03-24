package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.common.adapter.RuleAdapter;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RepositoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetDeregistrationException;
import javax.rules.admin.RuleExecutionSetRegisterException;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
@WebService(
        serviceName = "RuleDeploymentService" , endpointInterface = "gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService"
)
public class RuleDeploymentServiceImpl implements java.rmi.Remote, RuleDeploymentService {
	
	public RuleDeploymentServiceImpl() {
		super();
	}

	public void login(String userName, String password) throws RemoteException {
		throw new RemoteException("Not Implemented");
	}

	/**
	 * Read this configuration and make sure we have a JCR Repository implementation to support that.
	 * @throws RemoteException 
	 * */
	public void configureRepository(RepositoryConfiguration repositoryConfiguration) throws RemoteException {
		throw new RemoteException("Not Implemented");
	}
	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.runtime.RuleDeploymentService#registerPackage(java.lang.String, java.lang.String)
	 */
	public void registerRuleSet(String bindUri, String ruleSetName) throws RemoteException {
		final Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("source", "xml");
		
		//The repository configurations can be passed in AS  PROPERTIES
		
		RuleSet ruleSet = getRepositoryService().getRuleSet(ruleSetName);

		try {
			RuleAdapter ruleAdapter  = (RuleAdapter)Class.forName("gov.nih.nci.cabig.caaers.rules.common.adapter.JBossXSLTRuleAdapter").newInstance();
			Object ruleSetObj = ruleAdapter.adapt(ruleSet);
			//Please note that we can only pass the Package here to the RuleExecution set.
			//Since we still use drools implementation of LocalRuleExecutionSetProvider
			final RuleExecutionSet ruleExecutionSet = RuleServiceContext.getInstance().ruleSetProvider
			.createRuleExecutionSet(ruleSetObj, properties);
			RuleServiceContext.getInstance().ruleAdministrator.registerRuleExecutionSet(bindUri,
					ruleExecutionSet, properties);
		} catch (RuleExecutionSetCreateException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (RuleExecutionSetRegisterException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}

	public void deregisterRuleSet(String bindUri) {
		try {
			RuleServiceContext.getInstance().ruleAdministrator.deregisterRuleExecutionSet(bindUri,null);
		} catch (RuleExecutionSetDeregistrationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}

	public RuleSetInfo[] listRegistrations() throws RemoteException {
		return getRepositoryService().listRegistrations();
	}
	
	
	private RepositoryService getRepositoryService() {
		return (RepositoryServiceImpl)RuleServiceContext.getInstance().repositoryService;
	}


}