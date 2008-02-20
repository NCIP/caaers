package gov.nih.nci.cabig.caaers.dao.meddra;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * This class implements the Data access related operations for the LowLevelTerm domain object.
 * @author Krikor Krumlian
 */
public class LowLevelTermDao extends CaaersDao<LowLevelTerm> {
	/**
	 * Get the Class representation of the domain object that this DAO is
	 * representing.
	 * 
	 * @return Class representation of the domain object that this DAO is
	 *         representing.
	 */
	public Class<LowLevelTerm> domainClass() {
        return LowLevelTerm.class;
    }
	
	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("meddraCode", "meddraTerm");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();
    /**
	 * Get the list of all low level meddra terms.
	 * 
	 * @return return the list of low level meddra terms.
	 */
	@SuppressWarnings("unchecked")
    public List<LowLevelTerm> getAll() {
        return getHibernateTemplate().find("from LowLevelTerm");
    }
	/**
	 * Get low level meddra terms given the meddra code.
	 * @param meddraCode The meddra code.
	 * @return The low level meddra term. 
	 */
	@SuppressWarnings("unchecked")
    public List<LowLevelTerm> getByMeddraCode(String meddraCode) {
        return getHibernateTemplate().find("from LowLevelTerm llt where meddraCode=?",new Object[] { meddraCode } );
    }
	
	/**
	 * Get the list of low level meddra terms matching the name fragments.
	 * 
	 * @param subnames the name fragments to search on.
	 * @return List of matching low level meddra terms.
	 */
	public List<LowLevelTerm> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
}
