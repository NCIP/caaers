package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.RuleException;

import javax.rules.ConfigurationException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;

/**
 * Sample Rule Engine implementation built as a facade over JBoss Rules. This
 * calls the JBoss Rules via JSR-94 apis.
 *
 * @author Sujith Vellat Thayyilthodi
 */
public class RuleExecutionService {

	public static final String RULE_SERVICE_PROVIDER = "http://drools.org/";

	private RuleServiceProvider ruleServiceProvider;

	private RuleRuntime ruleRuntime;

	public RuleExecutionService() {
		super();
		initializeRules();
	}

	private void initializeRules() {
		try {
/*			
			RuleServiceProviderManager
					.registerRuleServiceProvider(
							RuleExecutionService.RULE_SERVICE_PROVIDER,
							Class
									.forName("org.drools.jsr94.rules.RuleServiceProviderImpl"));
*/
			RuleServiceProviderManager.registerRuleServiceProvider(
					RuleExecutionService.RULE_SERVICE_PROVIDER,
					Class
					.forName("gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.RuleServiceProviderImpl"));

			this.ruleServiceProvider = RuleServiceProviderManager
					.getRuleServiceProvider(RuleExecutionService.RULE_SERVICE_PROVIDER);

		} catch (ConfigurationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}

	/**
	 * Returns a named <code>StatelessRuleSession</code>.
	 *
	 *
	 * @return StatelessRuleSession
	 * @throws Exception
	 */
	public StatelessRuleSession getStatelessRuleSession(final String key,
			final java.util.Map properties) throws Exception {
		this.ruleRuntime = this.ruleServiceProvider.getRuleRuntime();
		return (StatelessRuleSession) this.ruleRuntime.createRuleSession(key,
				properties, RuleRuntime.STATELESS_SESSION_TYPE);
	}


	public StatelessRuleSession getStatelessRuleSession(final String key,
			final java.util.Map properties, RuleServiceProvider ruleServiceProvider) throws Exception {
		this.ruleRuntime = ruleServiceProvider.getRuleRuntime();
		return (StatelessRuleSession) this.ruleRuntime.createRuleSession(key,
				properties, RuleRuntime.STATELESS_SESSION_TYPE);
	}

}