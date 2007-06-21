package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class AdverseEventDao extends CaaersDao<AdverseEvent> {
    public Class<AdverseEvent> domainClass() {
        return AdverseEvent.class;
    }

    @Transactional(readOnly=false)
    public void save(AdverseEvent event) {
        getHibernateTemplate().saveOrUpdate(event);
    }
}
