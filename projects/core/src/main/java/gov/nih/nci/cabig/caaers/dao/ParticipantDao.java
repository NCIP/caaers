package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class ParticipantDao extends GridIdentifiableDao<Participant> {
    // these are for getBySubnames
    private static final List<String> SUBSTRING_MATCH_PROPERTIES
        = Arrays.asList("firstName", "lastName");
    private static final List<String> EXACT_MATCH_PROPERTIES
        = Arrays.asList("institutionalPatientNumber");
    private static final List<String> EXACT_MATCH_UNIQUE_PROPERTIES
    	= Arrays.asList("firstName","lastName");
    private static final List<String> EMPTY_PROPERTIES
		= Collections.emptyList();
    private static final String JOINS 
	= "join o.identifiers as identifier " + 
	"join o.assignments as spa join spa.studySite as ss join ss.study as s join s.identifiers as sIdentifier ";

    public Class<Participant> domainClass() {
        return Participant.class;
    }

    @Transactional(readOnly = false)
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
    
    
    public List<Participant> getByCriteria(String[] subnames, List<String> subStringMatchProperties)
    {
    	return findBySubname(subnames,null,null,subStringMatchProperties,null,JOINS);
    }
    
    /**
     * @param subnames a set of substrings to match
     * @return a list of participants such that each entry in <code>subnames</code> is a
     *  case-insensitive substring match of the participant's name or other identifier
     */
    @SuppressWarnings("unchecked")
    public List<Participant> getByUniqueIdentifiers(String[] subnames) {
        return findBySubname(subnames, EMPTY_PROPERTIES, EXACT_MATCH_UNIQUE_PROPERTIES);
    }
    

    public Participant getByIdentifier(Identifier identifier) {
        return findByIdentifier(identifier);
    }
}

