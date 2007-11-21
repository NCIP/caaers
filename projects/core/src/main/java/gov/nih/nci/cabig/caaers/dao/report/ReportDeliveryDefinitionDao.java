package gov.nih.nci.cabig.caaers.dao.report;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional(readOnly=true)
public class ReportDeliveryDefinitionDao extends GridIdentifiableDao<ReportDeliveryDefinition>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ReportDeliveryDefinition> domainClass() {
		return ReportDeliveryDefinition.class;
	}

    @Transactional(readOnly=false)
	public void save(ReportDeliveryDefinition rpDef){
		getHibernateTemplate().saveOrUpdate(rpDef);
	}
	
	public Session getHibernateSession(){
		return getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportDeliveryDefinition> getAll(){
		return getHibernateTemplate().find("from ReportDeliveryDefinition");
	}
	
	@SuppressWarnings("unchecked")
	public ReportDeliveryDefinition getByName(String name){
		return CollectionUtils.firstElement(
				(List<ReportDeliveryDefinition>) getHibernateTemplate().find(
						"from ReportDeliveryDefinition t where t.entityName=?", new String[]{name}
						)
				);
	}
	
	public boolean deleteById(int id){
		int count = getHibernateTemplate().bulkUpdate("delete ReportDeliveryDefinition t where t.id=?", new Object[]{id});
		return count >= 1;
	}
	
	public void delete(ReportDeliveryDefinition rpDef){
		getHibernateTemplate().delete(rpDef);
	}
	
	public void delete(Collection<ReportDeliveryDefinition> c){
		getHibernateTemplate().deleteAll(c);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ReportDeliveryDefinition getById(int arg0) {
		//overriden inorder to bring the read under transaction.
		return super.getById(arg0);
	}
	
	/**
	 * Willl initialize the Lazy collections inside the passed ReportDeliveryDefinition
	 * @param rpDef
	 */
	public void initialize(ReportDeliveryDefinition rpDef){
		
	}
}
