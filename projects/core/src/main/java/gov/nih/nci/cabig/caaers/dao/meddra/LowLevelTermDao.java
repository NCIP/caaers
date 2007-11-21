package gov.nih.nci.cabig.caaers.dao.meddra;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author Krikor Krumlian
 */
public class LowLevelTermDao extends CaaersDao<LowLevelTerm> {

	public Class<LowLevelTerm> domainClass() {
        return LowLevelTerm.class;
    }
	
	private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("meddraCode", "meddraTerm");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	@SuppressWarnings("unchecked")
    public List<LowLevelTerm> getAll() {
        return getHibernateTemplate().find("from LowLevelTerm");
    }
	
	@SuppressWarnings("unchecked")
    public List<LowLevelTerm> getByMeddraCode(String meddraCode) {
        return getHibernateTemplate().find("from LowLevelTerm llt where meddraCode=?",new Object[] { meddraCode } );
    }
	
	public List<LowLevelTerm> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
}
