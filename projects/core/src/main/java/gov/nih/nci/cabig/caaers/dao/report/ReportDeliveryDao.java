package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 21, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional(readOnly=true)
public class ReportDeliveryDao extends GridIdentifiableDao<ReportDelivery>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ReportDelivery> domainClass() {
		return ReportDelivery.class;
	}

    @Transactional(readOnly=false)
	public void save(ReportDelivery rd){
		getHibernateTemplate().saveOrUpdate(rd);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ReportDelivery> getAll(){
		return getHibernateTemplate().find("from ReportDelivery");
	}
	
	
	public boolean deleteById(int id){
		int count = getHibernateTemplate().bulkUpdate("delete ReportDelivery t where t.id=?", new Object[]{id});
		return count >= 1;
	}
	
	public void delete(ReportDelivery rd){
		getHibernateTemplate().delete(rd);
	}
	
	public void delete(Collection<ReportDelivery> c){
		getHibernateTemplate().deleteAll(c);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ReportDelivery getById(int arg0) {
		//overriden inorder to bring the read under transaction.
		return super.getById(arg0);
	}
	
}
