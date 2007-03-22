package gov.nih.nci.cabig.caaers.rules.author;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.rules.ConfigurationException;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetRegisterException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
@WebService(
        serviceName = "RuleAuthoringService", endpointInterface = "gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService"
)
public class RuleAuthoringServiceImpl implements RuleAuthoringService {

	private RuleServiceProvider ruleServiceProvider;
	
	private RepositoryService repositoryService;
	
	public RuleAuthoringServiceImpl() {
		super();
		initializeService();
	}

	private void initializeService() {
		try {
/*			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER,
					Class
					.forName("org.drools.jsr94.rules.RuleServiceProviderImpl"));
*/

/*			this.applicationContext = new ClassPathXmlApplicationContext(
	                new String[] { "classpath*:config/spring/applicationContext-rules-jc*.xml" });
*/
			this.repositoryService = (RepositoryServiceImpl)RuleServiceContext.getInstance().repositoryService;

			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER,
					Class
					.forName("gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.RuleServiceProviderImpl"));
			
			this.ruleServiceProvider = RuleServiceProviderManager
					.getRuleServiceProvider(RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER);
		
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
/*	public void createRuleSet(RuleSet ruleSet) {
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
*/	


	/**
	 * This is a direct method for the testing code. So that rules can be tested by
	 * directly authoring them in the JBoss Rule XML or DRL format.
	 * */
    public void addRuleExecutionSet(final String bindUri,
			final InputStream resourceAsStream, final Map properties) {
		final Reader ruleReader = new InputStreamReader(resourceAsStream);
		
		RuleExecutionSet ruleExecutionSet;
		try {
			ruleExecutionSet = this.ruleServiceProvider.getRuleAdministrator().getLocalRuleExecutionSetProvider(null)
					.createRuleExecutionSet(ruleReader, properties);
			this.ruleServiceProvider.getRuleAdministrator().registerRuleExecutionSet(bindUri,
					ruleExecutionSet, properties);			
		} catch (RuleExecutionSetCreateException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (RuleExecutionSetRegisterException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}




	public void createCategory(Category category) throws RemoteException {		
		this.repositoryService.createCategory(category);
	}
	
	public void createRuleSet(RuleSet ruleSet) throws RemoteException {
		this.repositoryService.createRuleSet(ruleSet);
	}
	
	public String createRule(Rule rule) throws RemoteException {
		return this.repositoryService.createRule(rule);		
	}

	public void updateRule(Rule rule) throws RemoteException {
		this.repositoryService.updateRule(rule);		
	}

	public void updateRuleSet(RuleSet ruleSet) throws RemoteException {
		this.repositoryService.createRuleSet(ruleSet);
	}

	public RuleSet getRuleSet(String ruleSetName) throws RemoteException {
		return this.repositoryService.getRuleSet(ruleSetName);
	}
	
	public Rule getRule(String ruleId) throws RemoteException {
		return this.repositoryService.getRule(ruleId);
	}
	
	public List<RuleSet> getAllRuleSets() throws RemoteException {
		return this.repositoryService.listRuleSets();
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	
}