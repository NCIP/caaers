package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ReportDelivery domain object.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 21, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Transactional(readOnly = true)
public class ReportDeliveryDao extends GridIdentifiableDao<ReportDelivery> {

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
     */
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ReportDelivery> domainClass() {
        return ReportDelivery.class;
    }

    /**
     * Save or update the report delivery in the db.
     * 
     * @param The
     *                report delivery.
     */
    @Transactional(readOnly = false)
    public void save(ReportDelivery rd) {
        getHibernateTemplate().saveOrUpdate(rd);
    }

    /**
     * Get the report delivery given the report delivery id.
     * 
     * @param arg0
     *                The report delivery id.
     * @return The report delivery.
     */
    @Override
    @Transactional(readOnly = true)
    public ReportDelivery getById(int arg0) {
        // overriden inorder to bring the read under transaction.
        return super.getById(arg0);
    }

}
