package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Priyatam
 */
@Transactional
public class StudyDao extends GridIdentifiableDao<Study> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES
        = Arrays.asList("shortTitle", "longTitle");
    private static final List<String> EXACT_MATCH_PROPERTIES
        = Collections.emptyList();
    private static final List<String> EXACT_MATCH_UNIQUE_PROPERTIES
		= Arrays.asList("longTitle");
    private static final List<String> EMPTY_PROPERTIES
		= Collections.emptyList();
    
    public Class<Study> domainClass() {
        return Study.class;
    }

    @SuppressWarnings("unchecked")
    public List<Study> getAllStudies() {
        return (List<Study>) getHibernateTemplate().find("from Study");
    }

    /**
     //TODO - Refactor this code with Hibernate Detached objects !!!
      
	 * This is a hack to load all collection objects in memory. Useful
	 * for editing a Study when you know you will be needing all collections
	 * To avoid Lazy loading Exception by Hibernate, a call to .size() is done
	 * for each collection
	 * @param id
	 * @return Fully loaded Study
	 */
	public Study getStudyDesignById(int id) {				
        Study study =  (Study) getHibernateTemplate().get(domainClass(), id);
        study.getIdentifiers().size();
        study.getStudySites().size();
        for (StudySite studySite : study.getStudySites()) {
			studySite.getStudyInvestigators().size();
			studySite.getStudyPersonnels().size();		
		}
        study.getMeddraStudyDiseases().size();
        study.getStudyAgents().size();
       
        return study;
    }

    public void save(Study study) {
        getHibernateTemplate().saveOrUpdate(study);
    }

    public void merge(Study study) {
        getHibernateTemplate().merge(study);
    }

    public List<Study> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
    /**
     * @param subnames a set of substrings to match
     * @return a list of participants such that each entry in <code>subnames</code> is a
     *  case-insensitive substring match of the participant's name or other identifier
     */
    @SuppressWarnings("unchecked")
    public List<Study> getByUniqueIdentifiers(String[] subnames) {
        return findBySubname(subnames, EMPTY_PROPERTIES, EXACT_MATCH_UNIQUE_PROPERTIES);
    }

    public Study getByIdentifier(Identifier identifier) {
        return findByIdentifier(identifier);
    }
}