package gov.nih.nci.cabig.caaers.rules.service;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.adapter.RuleAdapter;
import gov.nih.nci.cabig.caaers.rules.v1_0.Rule;
import gov.nih.nci.cabig.caaers.rules.v1_0.RuleSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.rules.ConfigurationException;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetRegisterException;
/**
 * The entry point for Managing Rules.
 *
 * The client applications can
 * 	<p> 1) either accessed by reference
 * 		2) make use of this as a service exposed using WSDL.
 *  </p>
 *
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleAuthoringServiceImpl implements RuleAuthoringService{

	private RuleServiceProvider ruleServiceProvider;

	private LocalRuleExecutionSetProvider ruleSetProvider;

	private RuleAdministrator ruleAdministrator;

	public RuleAuthoringServiceImpl() {
		super();
		initializeService();
	}

	private void initializeService() {
		try {
/*			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionService.RULE_SERVICE_PROVIDER,
					Class
					.forName("org.drools.jsr94.rules.RuleServiceProviderImpl"));
*/
			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionService.RULE_SERVICE_PROVIDER,
					Class
					.forName("gov.nih.nci.cabig.caaers.rules.jbossrules.jsr94.RuleServiceProviderImpl"));
			
			this.ruleServiceProvider = RuleServiceProviderManager
					.getRuleServiceProvider(RuleExecutionService.RULE_SERVICE_PROVIDER);

			this.ruleAdministrator = this.ruleServiceProvider
					.getRuleAdministrator();

			this.ruleSetProvider = this.ruleAdministrator
					.getLocalRuleExecutionSetProvider(null);
		} catch (RemoteException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}

	
	/**
	 * Save the set of rules.
	 * This will save the set of rules in three different formats.
	 * 1) The generic XML will be saved.
	 * 2) The converted XML (or any language specific to the Rule Engine being used)
	 * 3) The compiled AST representation specific to the Rule Engine.
	 * 
	 * */
	public void createRuleSet(RuleSet ruleSet) {
		String bindUri = UUID.randomUUID().toString();
		ruleSet.setId(bindUri);
		final Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("source", "xml");
		try {
			RuleAdapter ruleAdapter  = (RuleAdapter)Class.forName("gov.nih.nci.cabig.caaers.rules.adapter.JBossXSLTRuleAdapter").newInstance();
			//Please note that we can only pass the Package here to the RuleExecution set.
			//Since we still use drools implementation of LocalRuleExecutionSetProvider
			final RuleExecutionSet ruleExecutionSet = this.ruleSetProvider
			.createRuleExecutionSet(ruleAdapter.adapt(ruleSet), properties);
			this.ruleAdministrator.registerRuleExecutionSet(bindUri,
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
	


	/**
	 * This is a direct method for the testing code. So that rules can be tested by
	 * directly authoring them in the JBoss Rule XML or DRL format.
	 * */
    public void addRuleExecutionSet(final String bindUri,
			final InputStream resourceAsStream, final Map properties) {
		final Reader ruleReader = new InputStreamReader(resourceAsStream);
		
		RuleExecutionSet ruleExecutionSet;
		try {
			ruleExecutionSet = this.ruleSetProvider
					.createRuleExecutionSet(ruleReader, properties);
			this.ruleAdministrator.registerRuleExecutionSet(bindUri,
					ruleExecutionSet, properties);
			

			
		} catch (RuleExecutionSetCreateException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (RuleExecutionSetRegisterException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}

	public RuleServiceProvider getRuleServiceProvider() {
		return ruleServiceProvider;
	}

	public void createRule(Rule rule) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public List getAllRuleSets(String type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Rule getRule(String ruleId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleSet getRuleSet(String ruleSetId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateRule(Rule rule) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void updateRuleSet(RuleSet ruleSet) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}