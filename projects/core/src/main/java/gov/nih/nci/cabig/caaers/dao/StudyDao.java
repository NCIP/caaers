package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
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
    
    @Override
    public Class<Study> domainClass() {
        return Study.class;
    }

    @SuppressWarnings("unchecked")
    public List<Study> getAllStudies() {
        return (List<Study>) getHibernateTemplate().find("from Study");
    }

    // TODO: how is this different from #getById ?
    // KK : I am guessing the difference here is that identifiers and studysites are being 
    // loaded hence no lazy initialization exception . 
    public Study getStudyDesignById(int id) {
        Study study =  (Study) getHibernateTemplate().get(domainClass(), id);
        study.getIdentifiers().size();
        study.getStudySites().size();

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