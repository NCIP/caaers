package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.MeddraVersion;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class MeddraVersionDao extends CaaersDao<MeddraVersion> {
    /**
	 * Get the Class representation of the domain object that this DAO is
	 * representing.
	 * 
	 * @return Class representation of the domain object that this DAO is
	 *         representing.
	 */
	public Class<MeddraVersion> domainClass() {
        return MeddraVersion.class;
    }
	/**
	 * Get the list of all meddra versions.
	 * 
	 * @return return the list of meddra versions.
	 */
    @SuppressWarnings("unchecked")
    public List<MeddraVersion> getAll() {
        return getHibernateTemplate().find("from MeddraVersion");
    }
    /**
     * TODO
     * @return
     */
    public MeddraVersion getMeddraV9() {
        return getById(9);
    }
}
