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
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional(readOnly=true)
public class ReportDao extends GridIdentifiableDao<Report>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<Report> domainClass() {
		return Report.class;
	}

    @Transactional(readOnly=false)
	public void save(Report rs){
		getHibernateTemplate().saveOrUpdate(rs);
	}
	
	@SuppressWarnings("unchecked")
	public List<Report> getAll(){
		return getHibernateTemplate().find("from Report");
	}
	
	public List<Report> getAllByDueDate(Date dueDate){
		return getByDate(2, dueDate);
	}
	
	public List<Report> getAllByCreatedDate(Date dueDate){
		return getByDate(3, dueDate);
	}
	
	public List<Report> getAllBySubmittedDate(Date dueDate){
		return getByDate(1, dueDate);
	}
	
	@SuppressWarnings("unchecked")
	private List<Report> getByDate(int dateType, Date d){
		String column = (dateType == 1)? "submittedOn" : ((dateType == 2)? "dueOn" : "createdOn");
		String hsql = "from Report s where s." + column + "=?";
		return getHibernateTemplate().find( hsql, new Object[]{d});
	}
    @Transactional(readOnly=false)
	public boolean deleteById(int id){
		int count = getHibernateTemplate().bulkUpdate("delete Report s where s.id=?", new Object[]{id});
		return count >= 1;
	}
    @Transactional(readOnly=false)
	public void delete(Report rs){
		getHibernateTemplate().delete(rs);
	}
    @Transactional(readOnly=false)
	public void delete(Collection<Report> c){
		getHibernateTemplate().deleteAll(c);
	}

    // because ScheduledNotifications require a transaction, we have reassociate using lock.
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reassociate(Report report) {
        getHibernateTemplate().lock(report, LockMode.NONE);
    }
}
