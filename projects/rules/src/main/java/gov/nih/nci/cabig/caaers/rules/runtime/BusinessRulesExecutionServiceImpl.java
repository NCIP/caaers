package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.exception.RuleException;
import gov.nih.nci.cabig.caaers.rules.exception.RuleSetNotFoundException;
import gov.nih.nci.cabig.caaers.rules.runtime.action.ActionDispatcher;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.rules.ConfigurationException;
import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.RuleSessionCreateException;
import javax.rules.RuleSessionTypeUnsupportedException;
import javax.rules.StatelessRuleSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.repository.RulesRepositoryException;

public class BusinessRulesExecutionServiceImpl implements BusinessRulesExecutionService {

    private static final Log logger = LogFactory.getLog(BusinessRulesExecutionServiceImpl.class);

    public static final String RULE_SERVICE_PROVIDER = "http://drools.org/";

    private RuleServiceProvider ruleServiceProvider;

    public BusinessRulesExecutionServiceImpl() {
        super();
        initializeRules();
    }

    private void initializeRules() {
        try {
            RuleServiceProviderManager
                            .registerRuleServiceProvider(
                                            RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER,
                                            Class
                                                            .forName("gov.nih.nci.cabig.caaers.rules.runtime.RuleServiceProviderImpl"));

            this.ruleServiceProvider = RuleServiceProviderManager
                            .getRuleServiceProvider(RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER);

        } catch (ConfigurationException e) {
            throw new RuleException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuleException(e.getMessage(), e);
        }
    }

    @SuppressWarnings(value = "unchecked")
    public List<Object> fireRules(String bindingURI, List<Object> objects) {

        List<Object> outputObjects = null;
        RuleContext ruleContext = new RuleContext();
        ruleContext.setInputObjects(objects);
        objects.add(ruleContext);

        AdverseEventEvaluationResult adverseEventEvaluationResult = new AdverseEventEvaluationResult();

        objects.add(adverseEventEvaluationResult);

        Map customProperties = new HashMap();
        customProperties.put(Global.ACTION_DISPATCHER.getCode(), new ActionDispatcher());
        customProperties.put(Global.RULE_CONTEXT.getCode(), ruleContext);
        // customProperties.put(Global.ADVERSE_EVENT_RESULT.getCode(), new
        // AdverseEventEvaluationResult());
        try {
            StatelessRuleSession statelessRuleSession = getStatelessRuleSession(bindingURI,
                            customProperties);
            outputObjects = (List) statelessRuleSession.executeRules(objects);
            statelessRuleSession.release();
        } catch (RulesRepositoryException e) {
            logger.info("There is no ruleset available in the bindURI :" + bindingURI);
            throw new RuleSetNotFoundException("Unable to locate rule set at bindingUrl :["
                            + bindingURI + "]" + e.getMessage(), e);
        } catch (RuleExecutionSetNotFoundException e) {
            logger.info("There is no ruleset available in the bindURI :" + bindingURI);
            throw new RuleSetNotFoundException("Unable to locate rule set at bindingUrl :["
                            + bindingURI + "]" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error while executing rules", e);
            throw new RuleException("Error while executing rules :" + e.getMessage(), e);
        }

        return outputObjects;
    }

    private StatelessRuleSession getStatelessRuleSession(final String key,
                    final java.util.Map properties) throws ConfigurationException, RemoteException,
                    RuleSessionCreateException, RuleExecutionSetNotFoundException,
                    RuleSessionTypeUnsupportedException {
        RuleRuntime ruleRuntime = this.ruleServiceProvider.getRuleRuntime();
        return (StatelessRuleSession) ruleRuntime.createRuleSession(key, properties,
                        RuleRuntime.STATELESS_SESSION_TYPE);
    }

}
