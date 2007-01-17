package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Study;


import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 */
@Transactional
public class StudyDao extends CaaersDao<Study> {
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

    public void save(Study study) {
        getHibernateTemplate().saveOrUpdate(study);
    }
    
    /*
	 * Searches based on an example object. Typical usage from your service class: -
	 * If you want to search based on diseaseCode, monitorCode,
	 * <li><code>Study study = new Study();</li></code>
	 * <li>code>study.setDiseaseCode("diseaseCode");</li></code>
	 * <li>code>study.setDMonitorCode("monitorCode");</li></code>
	 * <li>code>studyDao.searchByExample(study)</li></code>
	 * @return list of matching study objects based on your sample study object
	 */
	public List<Study> searchByExample(Study study, boolean isWildCard) {
		//Session session = getHibernateTemplate().getSessionFactory().openSession();
		// TODO : This way of getting the session will not do exception translation
		// http://forum.springframework.org/showthread.php?t=24031
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Example searchCriteria = Example.create(study).excludeZeroes();
		if (isWildCard)
		{
			searchCriteria.enableLike(MatchMode.ANYWHERE);
			searchCriteria.ignoreCase();
		}
		//getHibernateTemplate().
		//return (List<Study>) getHibernateTemplate().findByCriteria(searchCriteria);
		
		return (List<Study>)s.createCriteria(Study.class).add(searchCriteria).list();
	}

    public List<Study> getBySubnames(String[] subnames) {
        return findBySubname(subnames,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
}