package gov.nih.nci.cabig.caaers.dao;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import edu.nwu.bioinformatics.commons.CollectionUtils;

import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional
public class ReportScheduleDao extends GridIdentifiableDao<ReportSchedule>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ReportSchedule> domainClass() {
		return ReportSchedule.class;
	}
	
	public void save(ReportSchedule rs){
		getHibernateTemplate().saveOrUpdate(rs);
	}
	
	public Session getHibernateSession(){
		return super.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportSchedule> getAll(){
		return getHibernateTemplate().find("from ReportSchedule");
	}
	
	@SuppressWarnings("unchecked")
	public ReportSchedule getByName(String name){
		return CollectionUtils.firstElement(
				(List<ReportSchedule>) getHibernateTemplate().find(
						"from ReportSchedule s where s.name=?", new String[]{name}
						)
				);
	}
	
	public List<ReportSchedule> getAllByDueDate(Date dueDate){
		return getByDate(2, dueDate);
	}
	
	public List<ReportSchedule> getAllByCreatedDate(Date dueDate){
		return getByDate(3, dueDate);
	}
	
	public List<ReportSchedule> getAllBySubmittedDate(Date dueDate){
		return getByDate(1, dueDate);
	}
	
	@SuppressWarnings("unchecked")
	private List<ReportSchedule> getByDate(int dateType, Date d){
		String column = (dateType == 1)? "submittedOn" : ((dateType == 2)? "dueOn" : "createdOn");
		String hsql = "from ReportSchedule s where s." + column + "=?";
		return getHibernateTemplate().find( hsql, new Object[]{d});
	}
	
	public boolean deleteById(int id){
		int count = getHibernateTemplate().bulkUpdate("delete ReportSchedule s where s.id=?", new Object[]{id});
		return count >= 1;
	}
	
	public void delete(ReportSchedule rs){
		getHibernateTemplate().delete(rs);
	}
	
	public void delete(Collection<ReportSchedule> c){
		getHibernateTemplate().deleteAll(c);
	}
	
}
