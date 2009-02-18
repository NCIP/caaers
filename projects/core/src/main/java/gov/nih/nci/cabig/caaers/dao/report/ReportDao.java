package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import org.hibernate.LockMode;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * This class implements the Data access related operations for the Report domain object.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 13, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Transactional(readOnly = true)
public class ReportDao extends GridIdentifiableDao<Report> {

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
    public Class<Report> domainClass() {
        return Report.class;
    }

    /**
     * Save or update the report in the db.
     * 
     * @param rs
     *                The report.
     */
    @Transactional(readOnly = false)
    public void save(Report rs) {
        getHibernateTemplate().saveOrUpdate(rs);
    }

   

    /**
     * Get the list of reports due by given date.
     * 
     * @param dueDate
     *                The due date.
     * @return The list of reports.
     */
    public List<Report> getAllByDueDate(Date dueDate) {
        return getByDate(2, dueDate);
    }



    @SuppressWarnings("unchecked")
    private List<Report> getByDate(int dateType, Date d) {
        String column = (dateType == 1) ? "submittedOn" : ((dateType == 2) ? "dueOn" : "createdOn");
        String hsql = "from Report s where s." + column + "=?";
        return getHibernateTemplate().find(hsql, new Object[] { d });
    }

    /**
     * Delete report from db
     * 
     * @param id
     *                The ID of the report
     * @return True if report successfully deleted. False otherwise.
     */
    @Transactional(readOnly = false)
    public boolean deleteById(int id) {
        int count = getHibernateTemplate().bulkUpdate("delete Report s where s.id=?",
                        new Object[] { id });
        return count >= 1;
    }

   

    // because ScheduledNotifications require a transaction, we have reassociate using lock.
    /**
     * This method will reassociate the domain object to hibernate session. With a lock mode none.
     * 
     * @param report -
     *                the domain object instance that is to be reassociated
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reassociate(Report report) {
        getHibernateTemplate().lock(report, LockMode.NONE);
    }

    /**
     * Initialize the report object with the supplied ID.
     * 
     * @param id
     *                The ID of the report.
     * @return The initialized report.
     */
    public Report getInitializedReportById(int id) {
        Report report = getById(id);
        if (report != null) {
            if (report.getScheduledNotifications() != null) initialize(report
                            .getScheduledNotifications());
            if (report.getReportDeliveries() != null) initialize(report.getReportDeliveries());
        }
        return report;
    }
}
