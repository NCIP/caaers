package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.InterventionSite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the InterventionSite domain object.
 * This class implements the Data access related operations for the InterventionSite domain object.
 * 
 * @author Krikor Krumlian
 */
@Transactional(readOnly = true)
public class InterventionSiteDao extends CaaersDao<InterventionSite> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");

    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<InterventionSite> domainClass() {
        return InterventionSite.class;
    }

    /**
     * Get the Intervention Site given the ID.
     * 
     * @param id
     *                The ID of the Intervention site.
     * @return The Intervention Site.
     */
    @Override
    public InterventionSite getById(int id) {
        return super.getById(id);
    }

    /**
     * Get the list of Intervention sites matching the name fragments.
     * 
     * @param subnames
     *                The name fragments to search on.
     * @return List of matching Intervention sites.
     */
    public List<InterventionSite> getBySubname(String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }

    /**
     * Get the list of all Intervention sites.
     * 
     * @return return the list of Intervention sites.
     */
    @SuppressWarnings("unchecked")
    public List<InterventionSite> getAll() {
        return getHibernateTemplate().find("from InterventionSite");
    }

}
