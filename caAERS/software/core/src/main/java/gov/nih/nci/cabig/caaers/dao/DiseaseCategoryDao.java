package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the DiseaseCategory domain object.
 * 
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class DiseaseCategoryDao extends CaaersDao<DiseaseCategory> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<DiseaseCategory> domainClass() {
        return DiseaseCategory.class;
    }

    /**
     * Get the list of all disease categories.
     * 
     * @return return the list of disease categories.
     */
    @SuppressWarnings("unchecked")
    public List<DiseaseCategory> getAll() {
        return getHibernateTemplate().find("from DiseaseCategory");
    }

    /**
     * Get list of diseases with specified parent ID.
     * 
     * @param parentId
     *                The parent ID of the parent disease.
     * @return The list of diseases with specified parent ID.
     */
    @SuppressWarnings("unchecked")
    public List<DiseaseCategory> getByParentId(Integer parentId) {
        return getHibernateTemplate().find("from DiseaseCategory dc where dc.parentCategory.id =?", new Object[] { parentId });
    }

    /**
     * Get the list of disease categories matching the name fragments.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @param parentDiseaseCategoryId
     * *                The category ID of the disease.
     * @return List of matching disease categories.
     */
    public List<DiseaseCategory> getBySubname(String[] subnames, Integer parentDiseaseCategoryId) {
        List<Object> extraParams = new LinkedList<Object>();
        StringBuilder extraConds = new StringBuilder("");
        if (parentDiseaseCategoryId != null) {
            extraConds.append(" o.parentCategory.id = ?");
            extraParams.add(parentDiseaseCategoryId);
        } else {
            extraConds.append(" o.parentCategory is null");
        }
        return findBySubname(subnames, extraConds.toString(), extraParams, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

}
