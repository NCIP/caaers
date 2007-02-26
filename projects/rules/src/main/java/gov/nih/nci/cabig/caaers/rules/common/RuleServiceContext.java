package gov.nih.nci.cabig.caaers.rules.common;

import java.rmi.RemoteException;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

import javax.rules.ConfigurationException;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RuleServiceContext {

	private static final String DEFAULT_RULE_SERVICE_PROVIDER = "gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.RuleServiceProviderImpl";

	public RuleServiceProvider ruleServiceProvider;

	public LocalRuleExecutionSetProvider ruleSetProvider;

	public RuleAdministrator ruleAdministrator;
	
	public RepositoryService repositoryService;
	
	public ApplicationContext applicationContext;
	
	private static RuleServiceContext instance;
	
	private RuleServiceContext() {
		initializeService();
	}

	private void initializeService() {
		try {
/*			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionService.RULE_SERVICE_PROVIDER,
					Class
					.forName("org.drools.jsr94.rules.RuleServiceProviderImpl"));
*/

			this.applicationContext = new ClassPathXmlApplicationContext(
	                new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-rules-jc*.xml" });

			this.repositoryService = (RepositoryServiceImpl)applicationContext.getBean("jcrService");			

			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionService.RULE_SERVICE_PROVIDER, Class
							.forName(DEFAULT_RULE_SERVICE_PROVIDER));
			
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

/*	private static class RuleServiceContextHolder {
		static RuleServiceContext instance = new RuleServiceContext();
	}*/
	
	public static RuleServiceContext getInstance() {
		if(instance == null)
			instance = new RuleServiceContext();
		return instance;
		//return RuleServiceContextHolder.instance;
	}


}
