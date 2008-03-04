package gov.nih.nci.cabig.caaers.dao.report;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ReportDeliveryDefinition domain
 * object.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 13, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Transactional(readOnly = true)
public class ReportDeliveryDefinitionDao extends GridIdentifiableDao<ReportDeliveryDefinition> {

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
    public Class<ReportDeliveryDefinition> domainClass() {
        return ReportDeliveryDefinition.class;
    }

    /**
     * Save or update the report delivery definition in the db.
     * 
     * @param The
     *                report delivery definition.
     */
    @Transactional(readOnly = false)
    public void save(ReportDeliveryDefinition rpDef) {
        getHibernateTemplate().saveOrUpdate(rpDef);
    }

    /**
     * TODO kkk
     * 
     * @return
     */
    public Session getHibernateSession() {
        return getSession();
    }

    /**
     * Get the list of all report delivery definitions.
     * 
     * @return return the list of report delivery definitions.
     */
    @SuppressWarnings("unchecked")
    public List<ReportDeliveryDefinition> getAll() {
        return getHibernateTemplate().find("from ReportDeliveryDefinition");
    }

    /**
     * Get the report delivery definition for a given name.
     * 
     * @param name
     *                The name of the report delivery definition.
     * @return The report delivery definition.
     */
    @SuppressWarnings("unchecked")
    public ReportDeliveryDefinition getByName(String name) {
        return CollectionUtils.firstElement((List<ReportDeliveryDefinition>) getHibernateTemplate()
                        .find("from ReportDeliveryDefinition t where t.entityName=?",
                                        new String[] { name }));
    }

    /**
     * Delete report delivery definition from db
     * 
     * @param id
     *                The ID of the report delivery definition.
     * @return True if report delivery definition successfully deleted. False otherwise.
     */
    public boolean deleteById(int id) {
        int count = getHibernateTemplate().bulkUpdate(
                        "delete ReportDeliveryDefinition t where t.id=?", new Object[] { id });
        return count >= 1;
    }

    /**
     * Delete report delivery definition from db.
     * 
     * @param rd
     *                The report delivery definition object to be deleted.
     */
    public void delete(ReportDeliveryDefinition rpDef) {
        getHibernateTemplate().delete(rpDef);
    }

    /**
     * Delete multiple report delivery definitions.
     * 
     * @param c
     *                The report delivery definition collection.
     */
    public void delete(Collection<ReportDeliveryDefinition> c) {
        getHibernateTemplate().deleteAll(c);
    }

    /**
     * Get the report delivery definition given the report delivery id.
     * 
     * @param arg0
     *                The report delivery definition id.
     * @return The report delivery definition.
     */
    @Override
    @Transactional(readOnly = true)
    public ReportDeliveryDefinition getById(int arg0) {
        // overriden inorder to bring the read under transaction.
        return super.getById(arg0);
    }

    /**
     * Willl initialize the Lazy collections inside the passed ReportDeliveryDefinition
     * 
     * @param rpDef
     */
    public void initialize(ReportDeliveryDefinition rpDef) {

    }
}
