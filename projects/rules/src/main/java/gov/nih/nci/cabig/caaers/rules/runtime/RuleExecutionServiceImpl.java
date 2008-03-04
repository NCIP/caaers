package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
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

/**
 * Sample Rule Engine implementation built as a facade over JBoss Rules. This calls the JBoss Rules
 * via JSR-94 apis.
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
            RuleServiceProviderManager
                            .registerRuleServiceProvider(
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

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService#fireRules(java.lang.String,
     *      java.util.List)
     */
    public void fireRules(String bindingUri, StudySDO study, List<AdverseEventSDO> inputObjects)
                    throws RemoteException {
        try {
            List allObjects = new ArrayList();
            if (study != null) {
                allObjects.add(study);
            }
            if (inputObjects != null) {
                allObjects.addAll(inputObjects);
            }

            RuleContext ruleContext = new RuleContext();
            ruleContext.setInputObjects(inputObjects);
            allObjects.add(ruleContext);

            Map customProperties = new HashMap();
            customProperties.put(Global.ACTION_DISPATCHER.getCode(), new ActionDispatcher());
            customProperties.put(Global.RULE_CONTEXT.getCode(), ruleContext);

            StatelessRuleSession statelessRuleSession = getStatelessRuleSession(bindingUri,
                            customProperties);
            List<Object> outObjects = (List) statelessRuleSession.executeRules(allObjects);
            statelessRuleSession.release();

        } catch (Exception e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    /*
     * 
     * public List<Object> fireRules(String bindingUri, List<Object> inputObjects, Map<String,
     * Object> properties) throws RemoteException { try { StatelessRuleSession statelessRuleSession =
     * getStatelessRuleSession(bindingUri, properties); List<Object> outObjects =
     * (List)statelessRuleSession.executeRules(inputObjects); statelessRuleSession.release(); return
     * outObjects; } catch (Exception e) { throw new RemoteException(e.getMessage(), e); } }
     * 
     * public void fireRules(String bindingUri, List<Object> inputObjects, ObjectFilter
     * objectFilter) throws RemoteException { try { StatelessRuleSession statelessRuleSession =
     * getStatelessRuleSession(bindingUri, new HashMap());
     * statelessRuleSession.executeRules(inputObjects, objectFilter);
     * statelessRuleSession.release(); } catch (Exception e) { throw new
     * RemoteException(e.getMessage(), e); } }
     */
    private StatelessRuleSession getStatelessRuleSession(final String key,
                    final java.util.Map properties) throws Exception {
        RuleRuntime ruleRuntime = this.ruleServiceProvider.getRuleRuntime();
        return (StatelessRuleSession) ruleRuntime.createRuleSession(key, properties,
                        RuleRuntime.STATELESS_SESSION_TYPE);
    }

    /*
     * public void fireRules(RuleExecutionInfo ruleExecutionInfo) throws RemoteException {
     * StatelessRuleSession statelessRuleSession; try { statelessRuleSession =
     * getStatelessRuleSession(ruleExecutionInfo.getBindUri(), new HashMap());
     * statelessRuleSession.executeRules(ruleExecutionInfo.getInputObjects());
     * statelessRuleSession.release(); } catch (Exception e) { throw new
     * RemoteException(e.getMessage(), e); } }
     * 
     * public void fireRules(String bindingUri, List<? extends Object> inputObjects) throws
     * RemoteException { StatelessRuleSession statelessRuleSession; try { statelessRuleSession =
     * getStatelessRuleSession(bindingUri , new HashMap());
     * statelessRuleSession.executeRules(inputObjects); statelessRuleSession.release(); } catch
     * (Exception e) { throw new RemoteException(e.getMessage(), e); }
     *  }
     */

}