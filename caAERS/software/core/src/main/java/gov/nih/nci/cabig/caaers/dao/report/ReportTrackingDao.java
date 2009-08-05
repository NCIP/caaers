package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportTracking;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ReportTrackingDao extends GridIdentifiableDao<ReportTracking> {
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
    public Class<ReportTracking> domainClass() {
        return ReportTracking.class;
    }

    /**
     * Save or update the report in the db.
     * 
     * @param rs
     *                The report.
     */
    @Transactional(readOnly = false)
    public void save(ReportTracking rs) {
        getHibernateTemplate().saveOrUpdate(rs);
    }
    
    @Transactional(readOnly = false)
    public boolean deleteById(int id) {
        int count = getHibernateTemplate().bulkUpdate("delete ReportTracking s where s.id=?", new Object[] { id });
        return count >= 1;
    }
    
    @SuppressWarnings("unchecked")
    public List<ReportTracking> getAll() {
        return getHibernateTemplate().find("from ReportTracking");
    }

}
