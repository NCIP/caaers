package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import java.util.List;

/**
 * This class implements the Data access related operations for the DiseaseTerm domain object.
 * 
 * @author Krikor Krumlian
 */
public class DiseaseTermDao extends CaaersDao<DiseaseTerm> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<DiseaseTerm> domainClass() {
        return DiseaseTerm.class;
    }

    /**
     * Get the list of all disease terms.
     * 
     * @return return the list of disease terms.
     */
    @SuppressWarnings("unchecked")
    public List<DiseaseTerm> getAll() {
        return getHibernateTemplate().find("from DiseaseTerm");
    }

    /**
     * Get disease terms given ID of the parent disease.
     * 
     * @param parentId
     *                The ID of the parent disease.
     * @return The disease terms.
     */
    @SuppressWarnings("unchecked")
    public List<DiseaseTerm> getByCategoryId(Integer parentId) {
        return getHibernateTemplate().find("from DiseaseTerm dt where dt.category.id =?",
                        new Object[] { parentId });
    }

    /**
     * Get the disease term object given the disease term name.
     * 
     * @param name
     *                The disease term name.
     * @return The disease term object.
     */
    @SuppressWarnings("unchecked")
    public DiseaseTerm getByTermName(final String name) {
        List<DiseaseTerm> results = getHibernateTemplate().find(
                        "from " + domainClass().getName() + " where term= ?", name);
        return results.size() > 0 ? results.get(0) : null;
    }

    /**
     * Get the disease term object given the CTEP disease term name.
     * 
     * @param name
     *                The CTEP disease term name.
     * @return The disease term object.
     */
    @SuppressWarnings("unchecked")
    public DiseaseTerm getByCTEPTermName(final String name) {
        List<DiseaseTerm> results = getHibernateTemplate().find("from " + domainClass().getName() + " where ctep_term= ?", name);
        return results.size() > 0 ? results.get(0) : null;
    }

    /**
     * Get the disease term object given the meddra code.
     * 
     * @param name
     *                The meddra code.
     * @return The disease term object.
     */
    @SuppressWarnings("unchecked")
    public DiseaseTerm getByMeddra(final String name) {
        List<DiseaseTerm> results = getHibernateTemplate().find("from " + domainClass().getName() + " where medraCode= ?", name);
        return results.size() > 0 ? results.get(0) : null;
    }

}
