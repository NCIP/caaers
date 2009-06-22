package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.MeddraVersion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the MeddraVersion domain object.
 * 
 * @author Krikor Krumlian
 */
@Transactional(readOnly = true)
public class MeddraVersionDao extends CaaersDao<MeddraVersion> {
	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Collections.emptyList();
	private static final List<String> EXACT_MATCH_PROPERTIES = Arrays.asList("name");
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
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
     * Get the meddraVersion given the meddraName
     */
    @SuppressWarnings("unchecked")
    public List<MeddraVersion> getMeddraByName(String name){
    	String[] names = {name};
    	return findBySubname(names, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

    /**
     * TODO
     * 
     * @return
     */
    public MeddraVersion getMeddraV9() {
        return getById(9);
    }
    
    /**
     * Save or update the MeddraVersion in the db.
     * 
     * @param MeddraVersion
     *                the meddraVersion
     */
    @Transactional(readOnly = false)
    public void save(final MeddraVersion meddraVersion) {
        getHibernateTemplate().saveOrUpdate(meddraVersion);
    }
    
    /**
     * Delete the specified meddra version.
     * 
     * @param p
     *                The meddra version.
     */
    @Transactional(readOnly = false)
    public void delete(MeddraVersion meddraVersion) {
        getHibernateTemplate().delete(meddraVersion);
    }
}
