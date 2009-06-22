package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.LabTerm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the LabTerm domain object.
 * 
 * @author Krikor Krumlian
 */
@Transactional(readOnly = true)
public class LabTermDao extends CaaersDao<LabTerm> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("term");

    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

    private static final List<String> EXACT_MATCH_PROPERTIES = Arrays.asList("term", "ctepCode");

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<LabTerm> domainClass() {
        return LabTerm.class;
    }

    /**
     * Gets the Lab term by id. This initializes the lab term and load all the objects.
     * 
     * @param id
     *                the id.
     * 
     * @return the lab term by id.
     */
    @Override
    public LabTerm getById(int id) {
        return super.getById(id);
    }

    /**
     * Get the list of lab terms matching the name fragments and belonging to a specified lab
     * category.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @param labCategoryId
     *                The ID of the lab category.
     * @return List of matching lab terms.
     */
    public List<LabTerm> getBySubname(String[] subnames, Integer labCategoryId) {
        List<Object> extraParams = new LinkedList<Object>();
        StringBuilder extraConds = new StringBuilder("");

        if (labCategoryId != null) {
            extraConds.append(" o.category.id = ? ");
            extraParams.add(labCategoryId);
        }
        return findBySubname(subnames, extraConds.toString(), extraParams,
                        SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }

    /**
     * Get the lab term specified by the name fragments.
     * 
     * @param subnames
     *                The name fragments to search on.
     * @return The lab term.
     */
    @SuppressWarnings("unchecked")
    public LabTerm getCtcTerm(final String[] subnames) {
        List<LabTerm> labTerms = findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES,
                        EMPTY_PROPERTIES);
        return labTerms.isEmpty() ? null : labTerms.get(0);
    }

    /**
     * Get the list of all lab terms.
     * 
     * @return return the list of lab terms.
     */
    @SuppressWarnings("unchecked")
    public List<LabTerm> getAll() {
        return findAll("o.term");
    }

}
