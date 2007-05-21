package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CtcCategoryDao extends CaaersDao<CtcCategory> {
    public Class<CtcCategory> domainClass() {
        return CtcCategory.class;
    }

    @SuppressWarnings("unchecked")
    public List<CtcCategory> getAll() {
        return getHibernateTemplate().find("from CtcCategory");
    }

}