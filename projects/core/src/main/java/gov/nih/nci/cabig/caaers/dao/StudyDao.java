package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Identifier;
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

    @Override
    public Class<Study> domainClass() {
        return Study.class;
    }

    @SuppressWarnings("unchecked")
    public List<Study> getAllStudies() {
        return (List<Study>) getHibernateTemplate().find("from Study");
    }

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
    
    /**
     * Searches based on an example object. Typical usage from your service class: -
     * If you want to search based on diseaseCode, monitorCode,
     * <li><code>Study study = new Study();</li></code>
     * <li>code>study.setDiseaseCode("diseaseCode");</li></code>
     * <li>code>study.setDMonitorCode("monitorCode");</li></code>
     * <li>code>studyDao.searchByExample(study)</li></code>
     * @return list of matching study objects based on your sample study object
     */
    @SuppressWarnings("unchecked")
    public List<Study> searchByExample(final Study example, final boolean isWildCard) {
        return (List<Study>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Example searchCriteria = Example.create(example).excludeZeroes();
                if (isWildCard) {
                    searchCriteria.enableLike(MatchMode.ANYWHERE);
                    searchCriteria.ignoreCase();
                }
                return session.createCriteria(Study.class).add(searchCriteria).list();
            }
        });
    }

    public List<Study> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

    @SuppressWarnings("unchecked")
    public Study getByIdentifier(Identifier identifier) {
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
    	return (Study) CollectionUtils.firstElement(criteria.list());
	}
}