package gov.nih.nci.cabig.caaers.dao.report;

import java.util.Collection;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.LockMode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import edu.nwu.bioinformatics.commons.CollectionUtils;


import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.NotificationAttachment;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional(readOnly=true)
public class ReportDefinitionDao extends GridIdentifiableDao<ReportDefinition>{

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
	 */
	@Override
	public Class<ReportDefinition> domainClass() {
		return ReportDefinition.class;
	}

    @Transactional(readOnly=false)
	public void save(ReportDefinition rpDef){
		getHibernateTemplate().saveOrUpdate(rpDef);
	}
	
	public Session getHibernateSession(){
		return getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportDefinition> getAll(){
		return getHibernateTemplate().find("from ReportDefinition");
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportDefinition> getAll(int orgId){
		return getHibernateTemplate().find("from ReportDefinition t where t.organization.id=?", new Object[]{orgId});
	}
	
	@SuppressWarnings("unchecked")
	public ReportDefinition getByName(String name){
		return CollectionUtils.firstElement(
				(List<ReportDefinition>) getHibernateTemplate().find(
						"from ReportDefinition t where t.name=?", new String[]{name}
						)
				);
	}
	 
	@SuppressWarnings("unchecked")
	public ReportDefinition getByName(String name, int orgId){
		return CollectionUtils.firstElement((List<ReportDefinition>)getHibernateTemplate().find("from ReportDefinition t where t.organization.id=? and t.name=?", 
				new Object[]{ orgId, name}));
	}
	
	public boolean deleteById(int id){
		int count = getHibernateTemplate().bulkUpdate("delete ReportDefinition t where t.id=?", new Object[]{id});
		return count >= 1;
	}
	
	public void delete(ReportDefinition rpDef){
		getHibernateTemplate().delete(rpDef);
	}
	
	public void delete(Collection<ReportDefinition> c){
		getHibernateTemplate().deleteAll(c);
	}

    // because PlannedNotifications require a transaction, we have reassociate using
    // lock.
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reassociate(ReportDefinition o) {
        getHibernateTemplate().lock(o, LockMode.NONE);
    }

    /**
	 * Willl initialize the Lazy collections inside the passed ReportDefinition
	 * @param rpDef
	 */
	public void initialize(ReportDefinition rpDef){
		//this method will initialize all the lazy collections
		// of a report definition
		super.initialize(rpDef.getPlannedNotifications());
		super.initialize(rpDef.getDeliveryDefinitionsInternal());
		for(PlannedNotification nf : rpDef.getPlannedNotifications()){
			super.initialize(nf.getRecipients());
			super.initialize(nf.getAttachments());
		}
	}
}
