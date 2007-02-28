package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.domain.Study;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.rules.ConfigurationException;
import javax.rules.ObjectFilter;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;

@WebService
/**
 * Sample Rule Engine implementation built as a facade over JBoss Rules. This
 * calls the JBoss Rules via JSR-94 apis.
 *
 * @author Sujith Vellat Thayyilthodi
 */
public class RuleExecutionServiceImpl implements RuleExecutionService {

	public static final String RULE_SERVICE_PROVIDER = "http://drools.org/";

	private RuleServiceProvider ruleServiceProvider;

	public RuleExecutionServiceImpl() {
		super();
		initializeRules();
	}

	private void initializeRules() {
		try {
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

	public String fireRules(String bindUri) {
		try {
			List inputObjects = new ArrayList();
			Study study = new Study();
			study.setPrimarySponsorCode("SC_1");
			StatelessRuleSession statelessRuleSession = getStatelessRuleSession(bindUri, new HashMap());
			statelessRuleSession.executeRules(inputObjects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUJITH";
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService#fireRules(java.lang.String, java.util.List)
	 */
	public List<Study> fireRules(String bindingUri, List<Study> inputObjects) throws RemoteException {
		return fireRules(bindingUri, inputObjects, new HashMap());
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService#fireRules(java.lang.String, java.util.List, java.util.Map)
	 */
	public List<Study> fireRules(String bindingUri, List<Study> inputObjects, Map<String, Object> properties) throws RemoteException {
		try {
			StatelessRuleSession statelessRuleSession = getStatelessRuleSession(bindingUri, properties);
			List<Study> outObjects = (List)statelessRuleSession.executeRules(inputObjects);
			statelessRuleSession.release();
			return outObjects;
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService#fireRules(java.lang.String, java.util.List, javax.rules.ObjectFilter)
	 */
	public void fireRules(String bindingUri, List<Study> inputObjects, ObjectFilter objectFilter) throws RemoteException {
		try {
			StatelessRuleSession statelessRuleSession = getStatelessRuleSession(bindingUri, new HashMap());
			statelessRuleSession.executeRules(inputObjects, objectFilter);
			statelessRuleSession.release();
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	/**
	 * Returns a named <code>StatelessRuleSession</code>.
	 *
	 *
	 * @return StatelessRuleSession
	 * @throws Exception
	 */
	private StatelessRuleSession getStatelessRuleSession(final String key,
			final java.util.Map properties) throws Exception {
		RuleRuntime ruleRuntime = this.ruleServiceProvider.getRuleRuntime();
		return (StatelessRuleSession) ruleRuntime.createRuleSession(key,
				properties, RuleRuntime.STATELESS_SESSION_TYPE);
	}
	
}