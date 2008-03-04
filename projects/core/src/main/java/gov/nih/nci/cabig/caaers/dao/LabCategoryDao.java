package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.LabCategory;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the LabCategory domain object.
 * 
 * @author Krikor Krumlian
 */
@Transactional(readOnly = true)
public class LabCategoryDao extends CaaersDao<LabCategory> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<LabCategory> domainClass() {
        return LabCategory.class;
    }

    /**
     * Get the list of all lab categories.
     * 
     * @return return the list of lab categories.
     */
    @SuppressWarnings("unchecked")
    public List<LabCategory> getAll() {
        return getHibernateTemplate().find("from LabCategory");
    }

}