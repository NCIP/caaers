package gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.repository;

import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;

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

	
	/** The Singleton instance of the repository. */
    // private static RuleExecutionSetRepository REPOSITORY;
    /** Holds the registered <code>RuleExecutionSet</code> objects. */
    //private final Map         map              = new HashMap();

    private RepositoryService repositoryService;


    /** Private constructor; use <code>getInstance</code> instead. */
    public RuleExecutionSetRepositoryImpl() {
        // Hide the constructor.
		this.repositoryService = (RepositoryServiceImpl)RuleServiceContext.getInstance().applicationContext.getBean("jcrService");
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
        //list.addAll( this.map.keySet() );
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
    	RuleExecutionSet ruleExecutionSet = (RuleExecutionSet)getRepositoryService().getRegisteredRuleset(bindUri);
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
			getRepositoryService().registerRuleSet(ruleExecutionSet.getName(), ruleSetInfo);
		} catch (RemoteException e) {
			throw new RuleExecutionSetRegisterException(e.getMessage(), e);
		}

		//this.map.put( bindUri, ruleExecutionSet );
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

        //this.map.remove( bindUri );
    }


	public RepositoryService getRepositoryService() {
		return repositoryService;
	}


	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
}