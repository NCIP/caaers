package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the External AdverseEvent domain object.
 * 
 * @author Ramakrishna Gundala
 */
@Transactional(readOnly = true)
public class ExternalAdverseEventDao extends CaaersDao<ExternalAdverseEvent> {
	
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ExternalAdverseEvent> domainClass() {
        return ExternalAdverseEvent.class;
    }

    /**
     * Save the Adverse Event.
     * 
     * @param event
     *                The event to be saved.
     */
    @Transactional(readOnly = false)
    public void save(final ExternalAdverseEvent externalAdverseEvent) {
        getHibernateTemplate().saveOrUpdate(externalAdverseEvent);
    }

}
