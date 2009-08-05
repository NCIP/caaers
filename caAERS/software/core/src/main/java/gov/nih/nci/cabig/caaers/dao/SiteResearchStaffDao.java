package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the SiteResearchStaff domain object.
 * 
 * @author monish
 */
@Transactional(readOnly=true)
public class SiteResearchStaffDao extends GridIdentifiableDao<SiteResearchStaff> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("researchStaff.firstName", "researchStaff.lastName");
    private static final List<String> NCIIDENTIFIER_MATCH_PROPERTIES = Arrays.asList("researchStaff.nciIdentifier");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();
    private static final List<Object> EXTRA_PARAMS = Collections.emptyList();
    
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<SiteResearchStaff> domainClass() {
        return SiteResearchStaff.class;
    }

    /**
     * Get Site ResearchStaff belonging to specified organization.
     * 
     * @param organization
     *                The organization.
     * @param researchstaff
     *                The researchstaff.
     * @return The site researchstaff.
     */
    @SuppressWarnings("unchecked")
    public SiteResearchStaff getOrganizationResearchStaff(Organization organization, ResearchStaff researchStaff) {
        return CollectionUtils.firstElement((List<SiteResearchStaff>) getHibernateTemplate().find("from SiteResearchStaff a where a.organization = ? and a.researchStaff = ?", new Object[] { organization, researchStaff }));
    }

    /**
     * Get all site researchstaffs belonging to specified organization.
     * 
     * @param organization
     *                The organization.
     * @return The list of site researchstaffs.
     */
    @SuppressWarnings("unchecked")
    public List<SiteResearchStaff> getOrganizationResearchStaffs(Organization organization) {
        return (List<SiteResearchStaff>) getHibernateTemplate().find(
                        "from SiteResearchStaff a where a.organization = ?",
                        new Object[] { organization });
    }

    /**
     * Get the list of site researchstaffs matching the name fragments and belonging to specified site..
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching site researchstaffs.
     */
    @Transactional(readOnly = false)
    public List<SiteResearchStaff> getBySubnames(String[] subnames, int site) {
    	List<SiteResearchStaff> siteResearchStaffs= findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
                        SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
       
    	
    	// Implement RemoteResearchStaffDaoHelper and uncomment.
    	/*
    	List<SiteResearchStaff> remoteSiteResearchStaffs= remoteResearchStaffDaoHelper.getSiteResearchStaffs(site);
    	for (SiteResearchStaff siteResearchStaff : remoteSiteResearchStaffs) {
    		
    		if (!siteResearchStaffs.contains(siteResearchStaff)) {
    			save(siteResearchStaff);
    			siteResearchStaffs.add(siteResearchStaff);
    		}
    	}
    	*/
    	return siteResearchStaffs;
    	
    }
    
    
    /**
     * Get the list of site researchstaffs matching the NciIdentifier and belonging to specified site..
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching site researchstaffs.
     */
    public List<SiteResearchStaff> getByNciIdentifier(String[] subnames, int site) {
        return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS,
                        NCIIDENTIFIER_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

}
