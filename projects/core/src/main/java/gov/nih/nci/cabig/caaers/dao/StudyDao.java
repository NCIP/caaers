package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
@Transactional
public class StudyDao extends CaaersDao {

	@Override
	public Class<Study> domainClass() {
		return Study.class;
	}

    @SuppressWarnings("unchecked")
	public List<Study> getAllStudies() {
        List<Study> sortedList = getHibernateTemplate().find("from Study");
        return sortedList;
    }	
	
    public void save(Study study) {
        getHibernateTemplate().saveOrUpdate(study);
    }
    
    public void delete(Study study) {
    	getHibernateTemplate().delete(study);
    }
}