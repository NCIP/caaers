package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the SiteInvestigator domain object.
 * 
 * @author Kulasekaran
 */
public class SiteInvestigatorDao extends GridIdentifiableDao<SiteInvestigator> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("investigator.firstName", "investigator.lastName");
    private static final List<String> NCIIDENTIFIER_MATCH_PROPERTIES = Arrays.asList("investigator.nciIdentifier");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();
    private static final List<Object> EXTRA_PARAMS = Collections.emptyList();
    
    private RemoteInvestigatorDaoHelper remoteInvestigatorDaoHelper;

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<SiteInvestigator> domainClass() {
        return SiteInvestigator.class;
    }

    /**
     * Get Site investigator belonging to specified organization.
     * 
     * @param organization
     *                The organization.
     * @param investigator
     *                The investigator.
     * @return The site investigator.
     */
    @SuppressWarnings("unchecked")
    public SiteInvestigator getOrganizationInvestigator(Organization organization, Investigator investigator) {
        return CollectionUtils.firstElement((List<SiteInvestigator>) getHibernateTemplate().find("from SiteInvestigator a where a.organization = ? and a.investigator = ?", new Object[] { organization, investigator }));
    }

    /**
     * Get all site investigators belonging to specified organization.
     * 
     * @param organization
     *                The organization.
     * @return The list of site investigators.
     */
    @SuppressWarnings("unchecked")
    public List<SiteInvestigator> getOrganizationInvestigators(Organization organization) {
        return (List<SiteInvestigator>) getHibernateTemplate().find(
                        "from SiteInvestigator a where a.organization = ?",
                        new Object[] { organization });
    }

    /**
     * Get the list of site investigators matching the name fragments and belonging to specified site..
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching site investigators.
     */
    @Transactional(readOnly = false)
    public List<SiteInvestigator> getBySubnames(String[] subnames, int site) {
    	List<SiteInvestigator> siteInvestigators= findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
                        SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
        
    	List<SiteInvestigator> remoteSiteInvestigators= remoteInvestigatorDaoHelper.getSiteInvestigators(site);
    	for (SiteInvestigator siteInvestigator:remoteSiteInvestigators) {
    		
    		if (!siteInvestigators.contains(siteInvestigator)) {
    			save(siteInvestigator);
    			siteInvestigators.add(siteInvestigator);
    		}
    	}
    	return siteInvestigators;
    	
    }
    
    
    /**
     * Get the list of site investigators matching the NciIdentifier and belonging to specified site..
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching site investigators.
     */
    public List<SiteInvestigator> getByNciIdentifier(String[] subnames, int site) {
        return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
                        NCIIDENTIFIER_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

	public void setRemoteInvestigatorDaoHelper(
			RemoteInvestigatorDaoHelper remoteInvestigatorDaoHelper) {
		this.remoteInvestigatorDaoHelper = remoteInvestigatorDaoHelper;
	}
    
}
