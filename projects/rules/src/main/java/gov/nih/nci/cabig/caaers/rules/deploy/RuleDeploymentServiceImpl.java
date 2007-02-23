package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.common.adapter.RuleAdapter;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetDeregistrationException;
import javax.rules.admin.RuleExecutionSetRegisterException;

public class RuleDeploymentServiceImpl implements java.rmi.Remote, RuleDeploymentService {

	
	private RepositoryService repositoryService;
	

	public RuleDeploymentServiceImpl() {
		super();
		this.repositoryService = (RepositoryServiceImpl)RuleServiceContext.getInstance().applicationContext.getBean("jcrService");
	}
	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.runtime.RuleDeploymentService#registerPackage(java.lang.String, java.lang.String)
	 */
	public void registerRuleSet(String bindUri, String ruleSetName) {
		final Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("source", "xml");
		RuleSet ruleSet = this.repositoryService.getRuleSet(ruleSetName);

		try {
			RuleAdapter ruleAdapter  = (RuleAdapter)Class.forName("gov.nih.nci.cabig.caaers.rules.adapter.JBossXSLTRuleAdapter").newInstance();
			//Please note that we can only pass the Package here to the RuleExecution set.
			//Since we still use drools implementation of LocalRuleExecutionSetProvider
			final RuleExecutionSet ruleExecutionSet = RuleServiceContext.getInstance().ruleSetProvider
			.createRuleExecutionSet(ruleAdapter.adapt(ruleSet), properties);
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

}