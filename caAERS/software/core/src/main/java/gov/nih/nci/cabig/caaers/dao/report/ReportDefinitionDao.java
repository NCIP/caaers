package gov.nih.nci.cabig.caaers.dao.report;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionExistsQuery;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class implements the Data access related operations for the ReportDefinition domain object.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 13, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Transactional(readOnly = true)
public class ReportDefinitionDao extends GridIdentifiableDao<ReportDefinition> implements
                MutableDomainObjectDao<ReportDefinition> {

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
    public Class<ReportDefinition> domainClass() {
        return ReportDefinition.class;
    }

    /**
     * Save or update the report definition in the db.
     * 
     * @param The
     *                report definition.
     */
    @Transactional(readOnly = false)
    public void save(ReportDefinition rpDef) {
        getHibernateTemplate().saveOrUpdate(rpDef);
    }


    /**
     * Get the list of all report definitions.
     * 
     * @return return the list of report definitions.
     */
    @SuppressWarnings("unchecked")
    public List<ReportDefinition> getAll() {
        return getHibernateTemplate().find("from ReportDefinition rd order by rd.id");
    }

    /**
     * Get the list of all report definitions for a given organization.
     * 
     * @param orgId
     *                The Organization id.
     * @return the list of report definitions.
     */
    @SuppressWarnings("unchecked")
    public List<ReportDefinition> getAll(int orgId) {
        return getHibernateTemplate().find("from ReportDefinition t where t.organization.id=?", new Object[] { orgId });
    }

    /**
     * Get the report definition for a given name.
     * 
     * @param name
     *                The name of the report definition.
     * @return The report definition.
     */
    @SuppressWarnings("unchecked")
    public ReportDefinition getByName(String name) {
        return CollectionUtils.firstElement((List<ReportDefinition>) getHibernateTemplate().find(
                        "from ReportDefinition t where t.name=?", new String[] { name }));
    }
    
    

    /**
     * Get the report definition for a given name and given organization.
     * 
     * @param name
     *                The name of the report definition.
     * @param orgId
     *                The organization id.
     * @return The report definition.
     */
    @SuppressWarnings("unchecked")
    public ReportDefinition getByName(String name, int orgId) {
        return CollectionUtils.firstElement((List<ReportDefinition>) getHibernateTemplate().find(
                        "from ReportDefinition t where t.organization.id=? and t.name=?",
                        new Object[] { orgId, name }));
    }

   


    // because PlannedNotifications require a transaction, we have reassociate using
    // lock.
    /**
     * This method will reassociate the domain object to hibernate session. With a lock mode none.
     * 
     * @param o -
     *                the domain object instance that is to be reassociated
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reassociate(ReportDefinition o) {
        getHibernateTemplate().lock(o, LockMode.NONE);
    }

    /**
     * Get the report definition given the report definition id.
     * 
     * @param arg0
     *                The report definition id.
     * @return The report definition.
     */
    @Override
    public ReportDefinition getById(int arg0) {
        return super.getById(arg0); // to bring under @Transaction (readonly)
    }

    /**
     * Willl initialize the Lazy collections inside the passed ReportDefinition
     * 
     * @param rpDef
     */
    public void initialize(ReportDefinition rpDef) {
        // this method will initialize all the lazy collections
        // of a report definition
        super.initialize(rpDef.getPlannedNotifications());
        super.initialize(rpDef.getDeliveryDefinitionsInternal());
        super.initialize(rpDef.getMandatoryFields());
        for (PlannedNotification nf : rpDef.getPlannedNotifications()) {
            super.initialize(nf.getRecipients());
            super.initialize(nf.getAttachments());
        }
    }
    
    public Integer noOfSimilarReportDefinitions(final ReportDefinitionExistsQuery query){
    	List<Long> results =  (List<Long>) getHibernateTemplate().execute(new HibernateCallback(){
      	  public Object doInHibernate(final Session session) throws HibernateException,SQLException {
      		  org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
      		  Map<String, Object> queryParameterMap = query.getParameterMap();
      		  for (String key : queryParameterMap.keySet()) {
      			  Object value = queryParameterMap.get(key);
      			  hiberanteQuery.setParameter(key, value);

      		  }
      		  return hiberanteQuery.list();
      	  } 
       });
    	
    	return results.get(0).intValue();
    }

}
