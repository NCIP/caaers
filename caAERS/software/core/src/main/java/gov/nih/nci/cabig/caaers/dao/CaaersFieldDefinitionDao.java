package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nwu.bioinformatics.commons.CollectionUtils;

import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

/**
 * @author Sameer Sawant
 * This class implements the Data access related operations for the CaaersFieldDefinition domain object.
 */
public class CaaersFieldDefinitionDao extends CaaersDao<CaaersFieldDefinition>{
	
	
	/**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<CaaersFieldDefinition> domainClass() {
        return CaaersFieldDefinition.class;
    }
    
    /**
     * Get the list of CaaersFieldDefinition given the name of the tab.
     * 
     * @param tabName
     *                The name of the tab.
     * @return The list of CaaersFieldDefinitions
     */
    @SuppressWarnings("unchecked")
    public List<CaaersFieldDefinition> getByTabName(String tabName) {
        return getHibernateTemplate().find("from CaaersFieldDefinition cfd where cfd.tabName=?", new String [] {tabName});
    }
    
    /**
     * Get the list of CaaersFieldDefinition.
     * 
     * @return return the list of caaers field definitions
     */
    public List<CaaersFieldDefinition> getAll() {
    	HibernateTemplate template = getHibernateTemplate();
        return getHibernateTemplate().find("from CaaersFieldDefinition");
    }
}
