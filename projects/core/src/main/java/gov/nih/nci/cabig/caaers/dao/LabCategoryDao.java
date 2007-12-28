package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.LabCategory;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class LabCategoryDao extends CaaersDao<LabCategory> {
    public Class<LabCategory> domainClass() {
        return LabCategory.class;
    }

    @SuppressWarnings("unchecked")
    public List<LabCategory> getAll() {
        return getHibernateTemplate().find("from LabCategory");
    }

}