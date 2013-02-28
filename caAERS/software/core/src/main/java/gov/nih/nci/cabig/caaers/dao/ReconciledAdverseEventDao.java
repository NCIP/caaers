/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ReconciledAdverseEvent;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Reconciled AdverseEvent domain object.
 * 
 * @author Ramakrishna Gundala
 */
@Transactional(readOnly = true)
public class ReconciledAdverseEventDao extends CaaersDao<ReconciledAdverseEvent> {
	
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ReconciledAdverseEvent> domainClass() {
        return ReconciledAdverseEvent.class;
    }

    /**
     * Save the Adverse Event.
     * 
     * @param reconciledAdverseEvent The event to be saved.
     */
    @Transactional(readOnly = false)
    public void save(final ReconciledAdverseEvent reconciledAdverseEvent) {
        getHibernateTemplate().saveOrUpdate(reconciledAdverseEvent);
    }
    
}
