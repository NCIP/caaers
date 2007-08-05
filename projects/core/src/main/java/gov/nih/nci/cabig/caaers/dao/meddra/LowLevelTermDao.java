package gov.nih.nci.cabig.caaers.dao.meddra;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;



import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


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
