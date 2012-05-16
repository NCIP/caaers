package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.LabVersion;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the LabVersion domain object.
 * 
 * @author Ramakrishna Gundala
 */
@Transactional(readOnly = true)
public class LabVersionDao extends CaaersDao<LabVersion> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<LabVersion> domainClass() {
        return LabVersion.class;
    }

    /**
     * Get the list of all lab versions.
     * 
     * @return return the list of lab versions.
     */
    @SuppressWarnings("unchecked")
    public List<LabVersion> getAll() {
        return getHibernateTemplate().find("from LabVersion");
    }
    
    // return unique element as name is unique across lab versions
    public LabVersion getByName(String name) {
    	Query query = getSessionFactory().getCurrentSession().createQuery("from LabVersion where name like :name");
    	return (LabVersion) query.setParameter("name", name).uniqueResult();
    }


}