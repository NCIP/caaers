package gov.nih.nci.cabig.caaers.dao;

import java.util.List;


import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import edu.nwu.bioinformatics.commons.CollectionUtils;


import gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional
public class ReportCalendarTemplateDao extends GridIdentifiableDao<ReportCalendarTemplate>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ReportCalendarTemplate> domainClass() {
		return ReportCalendarTemplate.class;
	}
	
	public void save(ReportCalendarTemplate rc){
		getHibernateTemplate().saveOrUpdate(rc);
	}
	
	public Session getHibernateSession(){
		return super.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportCalendarTemplate> getAll(){
		return getHibernateTemplate().find("from ReportCalendarTemplate");
	}
	
	@SuppressWarnings("unchecked")
	public ReportCalendarTemplate getByName(String name){
		return CollectionUtils.firstElement(
				(List<ReportCalendarTemplate>) getHibernateTemplate().find(
						"from ReportCalendarTemplate t where t.name=?", new String[]{name}
						)
				);
	}
}
