package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * @author Padmaja Vedula
 */
public class StudySiteDao extends CaaersDao<StudySite> {
    public Class<StudySite> domainClass() {
        return StudySite.class;
    }

    public void save(StudySite studySite) {
        getHibernateTemplate().saveOrUpdate(studySite);
    }
    
    public List<StudySite> getAll() {
        return getHibernateTemplate().find("from StudySite");
    }
    
}
