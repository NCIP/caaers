/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LazyInitializationException;
import org.hibernate.LockMode;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ExpeditedAdverseEventReport
 * domain object.
 * 
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class ExpeditedAdverseEventReportDao extends
                GridIdentifiableDao<ExpeditedAdverseEventReport> implements
                MutableDomainObjectDao<ExpeditedAdverseEventReport> {
	
	protected final Log log = LogFactory.getLog(getClass());


    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ExpeditedAdverseEventReport> domainClass() {
        return ExpeditedAdverseEventReport.class;
    }


    /**
     * @param report
     *                Save the Expedited AE report.
     */
    @Transactional(readOnly = false)
    public void save(final ExpeditedAdverseEventReport report) {
    	saveOrUpdateExpeditedReport(report);
    }
    
    /**
     * This method is invoked from save or modifyOrSaveReviewStatusAndComments methods
     * @param report
     */
    private void saveOrUpdateExpeditedReport(final ExpeditedAdverseEventReport report){
    	log.debug("Saving ExpeditedAdverseEventReport..");
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
        try {
            if (report.getReporter() != null && report.getReporter().isSavable()) {
                getHibernateTemplate().saveOrUpdate(report.getReporter());
            } else {
                log.debug("Reporter not savable; skipping cascade");
            }
        } catch (LazyInitializationException lie) {
            log.debug("Reporter not initialized, skipping cascade", lie);
            lie.printStackTrace();
        }
        try {
            if (report.getPhysician() != null && report.getPhysician().isSavable()) {
                getHibernateTemplate().saveOrUpdate(report.getPhysician());
            } else {
                log.debug("Physican not savable; skipping cascade");
            }
        } catch (LazyInitializationException lie) {
            log.debug("Physician not initialized, skipping cascade", lie);
            lie.printStackTrace();
        }
    }

    /**
     * This method will reassociate the domain object to hibernate session. With a lock mode none.
     * 
     * @param report -
     *                the domain object instance that is to be reassociated.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void reassociate(final ExpeditedAdverseEventReport report) {
    	log.debug("Reassociating ExpeditedAdverseEventReport...");
        super.reassociate(report);
        
        if (report.getReporter() == null || report.getReporter().isTransient()) {
            log.debug("Reporter unsaved; skipping reassociate cascade");
        } else {
           getHibernateTemplate().lock(report.getReporter(), LockMode.NONE);
        }
        if (report.getPhysician() == null || report.getPhysician().isTransient()) {
            log.debug("Physican unsaved; skipping reassociate cascade");
        } else {
            getHibernateTemplate().lock(report.getPhysician(), LockMode.NONE);
        }
    }


    public ExpeditedAdverseEventReport getByExternalId(String gridId) {
    	gridId = gridId.toLowerCase();
        Object[] params = { gridId };
        return (ExpeditedAdverseEventReport)CollectionUtils.firstElement(getHibernateTemplate().find("from ExpeditedAdverseEventReport aer where lower(aer.externalId) = ?", params));
    }
}
