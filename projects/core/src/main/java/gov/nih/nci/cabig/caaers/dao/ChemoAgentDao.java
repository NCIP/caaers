package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ChemoAgent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ChemoAgent domain object.
 * 
 * @author Krikor Krumlian
 */
@Transactional(readOnly = true)
public class ChemoAgentDao extends CaaersDao<ChemoAgent> {
    
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");
    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<ChemoAgent> domainClass() {
        return ChemoAgent.class;
    }

    /**
     * Search for Chemo agent by identifier.
     * 
     * @param id
     *                The chemo agent identifer.
     * @return The chemo agent for the given identifier.
     */
    @Override
    public ChemoAgent getById(int id) {
        return super.getById(id);
    }

    /**
     * Get the list of chemo agents matching the name fragments.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching chemo agents.
     */
    public List<ChemoAgent> getBySubname(String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }

    /**
     * Get the list of all chemo agents.
     * 
     * @return return the list of chemo agents.
     */
    @SuppressWarnings("unchecked")
    public List<ChemoAgent> getAll() {
        return findAll("o.name");
    }

}
