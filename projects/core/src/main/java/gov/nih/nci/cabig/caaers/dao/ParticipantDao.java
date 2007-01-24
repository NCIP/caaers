package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;
import java.util.Arrays;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


/**
 * @author Rhett Sutphin
 */
public class ParticipantDao extends GridIdentifiableDao<Participant> {
    // these are for getBySubnames
    private static final List<String> SUBSTRING_MATCH_PROPERTIES
        = Arrays.asList("firstName", "lastName");
    private static final List<String> EXACT_MATCH_PROPERTIES
        = Arrays.asList("institutionalPatientNumber");

    public Class<Participant> domainClass() {
        return Participant.class;
    }

    public void save(Participant participant) {
        getHibernateTemplate().saveOrUpdate(participant);
    }

    @SuppressWarnings("unchecked")
    public List<Participant> getAll() {
        return getHibernateTemplate().find("from Participant p order by p.lastName, p.firstName");
    }

    /**
     * @param subnames a set of substrings to match
     * @return a list of participants such that each entry in <code>subnames</code> is a
     *  case-insensitive substring match of the participant's name or other identifier
     */
    @SuppressWarnings("unchecked")
    public List<Participant> getBySubnames(String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
    /**
	 * /* Searches based on an example object. Typical usage from your service
	 * class: - If you want to search based on diseaseCode, monitorCode,
	 * <li><code>Participant participant = new Participant();</li></code>
	 * <li>code>participant.setLastName("last_namee");</li>
	 * </code>
	 * <li>code>participantDao.searchByExample(study)</li>
	 * </code>
	 * 
	 * @return list of matching participant objects based on your sample
	 *         participant object
	 * @param participant
	 * @return
	 */
	public List<Participant> searchByExample(Participant participant,
			boolean isWildCard) {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Example example = Example.create(participant).excludeZeroes();
		Criteria participantCriteria = s.createCriteria(Participant.class);
		if (isWildCard) {
			example.ignoreCase().enableLike(MatchMode.ANYWHERE);
			participantCriteria.add(example);
			return (List<Participant>) participantCriteria.list();

		}
		return (List<Participant>) participantCriteria.add(example).list();

	}
	
	/**
	 * Default Search without a Wildchar
	 * 
	 * @see edu.duke.cabig.c3pr.dao.searchByExample(Participant participant,
	 *      boolean isWildCard)
	 * @param participant
	 * @return Search results
	 */
	public List<Participant> searchByExample(Participant participant) {
		return searchByExample(participant, true);
	}

	public Participant getByIdentifier(Identifier identifier) {

    	Criteria criteria = getSession().createCriteria(domainClass());
    	criteria = criteria.createCriteria("identifiers");
    	
    	if(identifier.getType() != null) {
    		criteria.add(Restrictions.eq("type", identifier.getType()));
    	}
    	
    	if(identifier.getSource() != null) {
    		criteria.add(Restrictions.eq("source", identifier.getSource()));
    	}
    	
    	if(identifier.getValue() != null) {
    		criteria.add(Restrictions.eq("value", identifier.getValue()));
    	}    			
    	return (Participant) CollectionUtils.firstElement(criteria.list());		
    	
	}

    
    
}

