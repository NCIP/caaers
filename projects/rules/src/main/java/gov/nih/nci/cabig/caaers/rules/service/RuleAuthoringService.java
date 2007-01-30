package gov.nih.nci.cabig.caaers.rules.service;

import gov.nih.nci.cabig.caaers.rules.v1_0.Rule;
import gov.nih.nci.cabig.caaers.rules.v1_0.RuleSet;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Remote interface for Rule Provisioning. 
 * This interface can be exposed as a webservice.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public interface RuleAuthoringService extends java.rmi.Remote {

	public void createRuleSet(RuleSet ruleSet) throws RemoteException;
	
	public void createRule(Rule rule) throws RemoteException;
	
	public void updateRule(Rule rule) throws RemoteException;
	
	public void updateRuleSet(RuleSet ruleSet) throws RemoteException;
	
	public RuleSet getRuleSet(String ruleSetId) throws RemoteException;

	public Rule getRule(String ruleId) throws RemoteException;

	public List getAllRuleSets(String type) throws RemoteException;

}