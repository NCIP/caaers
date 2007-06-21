package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Padmaja Vedula
 */
@Transactional(readOnly=true)
public class StudySiteDao extends CaaersDao<StudySite> {
    public Class<StudySite> domainClass() {
        return StudySite.class;
    }
}
