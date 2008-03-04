package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;

import java.util.Collection;
import java.util.List;

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
     * Get the list of all report deliveries.
     * 
     * @return return the list of report deliveries.
     */
    @SuppressWarnings("unchecked")
    public List<ReportDelivery> getAll() {
        return getHibernateTemplate().find("from ReportDelivery");
    }

    /**
     * Delete report delivery from db
     * 
     * @param id
     *                The ID of the report
     * @return True if report delivery successfully deleted. False otherwise.
     */
    public boolean deleteById(int id) {
        int count = getHibernateTemplate().bulkUpdate("delete ReportDelivery t where t.id=?",
                        new Object[] { id });
        return count >= 1;
    }

    /**
     * Delete report delivery from db.
     * 
     * @param rd
     *                The report delivery object to be deleted.
     */
    public void delete(ReportDelivery rd) {
        getHibernateTemplate().delete(rd);
    }

    /**
     * Delete multiple report deliveries.
     * 
     * @param c
     *                The report delivery collection.
     */
    public void delete(Collection<ReportDelivery> c) {
        getHibernateTemplate().deleteAll(c);
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
