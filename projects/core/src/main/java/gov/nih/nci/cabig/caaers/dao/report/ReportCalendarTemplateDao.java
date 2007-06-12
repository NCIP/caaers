package gov.nih.nci.cabig.caaers.dao.report;

import java.util.Collection;
import java.util.List;


import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import edu.nwu.bioinformatics.commons.CollectionUtils;


import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional
public class ReportCalendarTemplateDao extends GridIdentifiableDao<ReportDefinition>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ReportDefinition> domainClass() {
		return ReportDefinition.class;
	}
	
	public void save(ReportDefinition rc){
		getHibernateTemplate().saveOrUpdate(rc);
	}
	
	public Session getHibernateSession(){
		return super.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportDefinition> getAll(){
		return getHibernateTemplate().find("from ReportDefinition");
	}
	
	@SuppressWarnings("unchecked")
	public ReportDefinition getByName(String name){
		return CollectionUtils.firstElement(
				(List<ReportDefinition>) getHibernateTemplate().find(
						"from ReportDefinition t where t.name=?", new String[]{name}
						)
				);
	}
	
	public boolean deleteById(int id){
		int count = getHibernateTemplate().bulkUpdate("delete ReportDefinition t where t.id=?", new Object[]{id});
		return count >= 1;
	}
	
	public void delete(ReportDefinition rct){
		getHibernateTemplate().delete(rct);
	}
	
	public void delete(Collection<ReportDefinition> c){
		getHibernateTemplate().deleteAll(c);
	}
}
