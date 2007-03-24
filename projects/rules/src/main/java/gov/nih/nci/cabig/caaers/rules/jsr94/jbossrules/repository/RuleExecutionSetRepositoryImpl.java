package gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.repository;

import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetRegisterException;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleExecutionSetRepositoryImpl implements RuleExecutionSetRepository {


    public RuleExecutionSetRepositoryImpl() {
    }


    /**
     * Retrieves a <code>List</code> of the URIs that currently have
     * <code>RuleExecutionSet</code>s associated with them.
     * 
     * An empty list is returned is there are no associations.
     * 
     * @return a <code>List</code> of the URIs that currently have
     *         <code>RuleExecutionSet</code>s associated with them.
     */
    public List getRegistrations() {
    	RuleSetInfo[] ruleSetInfos = getRepositoryService().listRegistrations();
       	final List<String> list = new ArrayList<String>();    	
    	for(int i = 0; i < ruleSetInfos.length; i++) {
    		list.add(ruleSetInfos[i].getBindUri());
    	}
        return list;
    }

    /**
     * Get the <code>RuleExecutionSet</code> bound to this URI, or return
     * <code>null</code>.
     * 
     * @param bindUri
     *            the URI associated with the wanted
     *            <code>RuleExecutionSet</code>.
     * 
     * @return the <code>RuleExecutionSet</code> bound to the given URI.
     */
    public RuleExecutionSet getRuleExecutionSet(final String bindUri) {
    	RuleSetInfo ruleSetInfo = getRepositoryService().getRegisteredRuleset(bindUri);
    	RuleExecutionSet ruleExecutionSet = (RuleExecutionSet)ruleSetInfo.getContent();
    	return ruleExecutionSet;
    }

    /**
     * Register a <code>RuleExecutionSet</code> under the given URI.
     * 
     * @param bindUri
     *            the URI to associate with the <code>RuleExecutionSet</code>.
     * @param ruleExecutionSet
     *            the <code>RuleExecutionSet</code> to associate with the URI
     * 
     * @throws RuleExecutionSetRegisterException
     *             if an error occurred that prevented registration (i.e. if
     *             bindUri or ruleSet are <code>null</code>)
     */
    public void registerRuleExecutionSet(final String bindUri,
                                         final RuleExecutionSet ruleExecutionSet) throws RuleExecutionSetRegisterException {
        if ( bindUri == null ) {
            throw new RuleExecutionSetRegisterException( "bindUri cannot be null" );
        }
        if ( ruleExecutionSet == null ) {
            throw new RuleExecutionSetRegisterException( "ruleSet cannot be null" );
        }
		RuleSetInfo ruleSetInfo = new RuleSetInfo();
		ruleSetInfo.setContent(ruleExecutionSet);
		ruleSetInfo.setBindUri(bindUri);
		try {
			getRepositoryService().registerRuleSet(bindUri, ruleSetInfo);
		} catch (RemoteException e) {
			throw new RuleExecutionSetRegisterException(e.getMessage(), e);
		}
    }
    

    /**
     * Unregister a <code>RuleExecutionSet</code> from the given URI.
     * 
     * @param bindUri
     *            the URI to disassociate with the <code>RuleExecutionSet</code>.
     */
    public void unregisterRuleExecutionSet(final String bindUri) {
        if ( bindUri == null ) {
            throw new NullPointerException( "bindUri cannot be null" );
        }
        getRepositoryService().deregisterRuleExecutionSet(bindUri);
    }


	public RepositoryService getRepositoryService() {
		return RuleServiceContext.getInstance().repositoryService;
	}

}