package gov.nih.nci.cabig.caaers.rules.author;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

/**
 * Remote interface for Rule Provisioning. 
 * This interface can be exposed as a webservice.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
@WebService(
        serviceName = "RuleAuthoringService", endpointInterface = "gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService"
)
public interface RuleAuthoringService extends java.rmi.Remote {

	public void createCategory(Category category) throws RemoteException;
	
	public void createRuleSet(RuleSet ruleSet) throws RemoteException;
	
	public String createRule(Rule rule) throws RemoteException;
	
	public void updateRule(Rule rule) throws RemoteException;
	
	public void updateRuleSet(RuleSet ruleSet) throws RemoteException;
	
	public RuleSet getRuleSet(String ruleSetId) throws RemoteException;

	public Rule getRule(String ruleId) throws RemoteException;

	public List<RuleSet> getAllRuleSets() throws RemoteException;
	
	public void setRepositoryService(RepositoryService repositoryService);

	public void addRuleExecutionSet(final String bindUri,
				final InputStream resourceAsStream, final Map properties); 
}