package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportContent;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
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
            if (report.getScheduledNotifications() != null) initialize(report.getScheduledNotifications());
            if (report.getReportDeliveries() != null) initialize(report.getReportDeliveries());
            if(report.getReportVersions() != null) initialize(report.getReportVersions());
        }
        return report;
    }
    
    
    public ReportContent getReportContent(final Report report){
    	final int lastVersionId = report.getLastVersion().getId();
    	ReportVersion reportVersion = (ReportVersion)getHibernateTemplate().load(ReportVersion.class, lastVersionId);
    	return reportVersion.getXmlContent();
//    	return (ReportContent)getHibernateTemplate().execute(new HibernateCallback(){
//    		public Object doInHibernate(Session session)
//    				throws HibernateException, SQLException {
//    			session.beginTransaction();
//    			ReportVersion reportVersion = (ReportVersion)session.get(ReportVersion.class, lastVersionId);
//    			ReportContent reportContent =  reportVersion.getXmlContent();
//    			return reportContent;
//    		}
//    	});
    	
    	
    }
    
    @SuppressWarnings({"unchecked"})
    @Override
    public List<Report> search(final AbstractQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<Report>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);

                }
                return hiberanteQuery.list();
            }

        });

    }
}
