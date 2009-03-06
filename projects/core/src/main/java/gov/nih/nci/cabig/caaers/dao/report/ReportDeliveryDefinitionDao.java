package gov.nih.nci.cabig.caaers.dao.report;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;

import java.util.List;

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

  
}
