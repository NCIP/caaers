package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.runtime.action.ActionDispatcher;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.rules.ConfigurationException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;

public class BusinessRulesExecutionServiceImpl implements BusinessRulesExecutionService{
	
	public static final String RULE_SERVICE_PROVIDER = "http://drools.org/";

	private RuleServiceProvider ruleServiceProvider;

	public BusinessRulesExecutionServiceImpl() {
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
	
	public List<Object> fireRules(String bindingURI, List<Object> objects) {
		
		List<Object> outputObjects = null;
		try {
			
			
			RuleContext ruleContext = new RuleContext();
			ruleContext.setInputObjects(objects);
			objects.add(ruleContext);
			
			AdverseEventEvaluationResult adverseEventEvaluationResult = new AdverseEventEvaluationResult();
			
			objects.add(adverseEventEvaluationResult);
			
			Map customProperties = new HashMap();
			customProperties.put(Global.ACTION_DISPATCHER.getCode(), new ActionDispatcher());
			customProperties.put(Global.RULE_CONTEXT.getCode(), ruleContext);
			//customProperties.put(Global.ADVERSE_EVENT_RESULT.getCode(), new AdverseEventEvaluationResult());
			
			
			StatelessRuleSession statelessRuleSession = getStatelessRuleSession(
					bindingURI, customProperties);
			outputObjects = (List
					) statelessRuleSession.executeRules(objects);
			statelessRuleSession.release();
			
		} catch (Exception e) {
			System.out.println("We should log this exception or what!");
			
		}
		
		return outputObjects;
	}
	
	private StatelessRuleSession getStatelessRuleSession(final String key,
			final java.util.Map properties) throws Exception {
		RuleRuntime ruleRuntime = this.ruleServiceProvider.getRuleRuntime();
		return (StatelessRuleSession) ruleRuntime.createRuleSession(key,
				properties, RuleRuntime.STATELESS_SESSION_TYPE);
	}

}
