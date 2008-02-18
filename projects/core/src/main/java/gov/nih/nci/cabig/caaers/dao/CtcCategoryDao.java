package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class CtcCategoryDao extends CaaersDao<CtcCategory> {
    /**
	 * Get the Class representation of the domain object that this DAO is
	 * representing.
	 * 
	 * @return Class representation of the domain object that this DAO is
	 *         representing.
	 */
	public Class<CtcCategory> domainClass() {
        return CtcCategory.class;
    }
/**
 * Get the list of all CTC categories.
 * 
 * @return return the list of CTC categories.
 */
    @SuppressWarnings("unchecked")
    public List<CtcCategory> getAll() {
        return getHibernateTemplate().find("from CtcCategory");
    }

}