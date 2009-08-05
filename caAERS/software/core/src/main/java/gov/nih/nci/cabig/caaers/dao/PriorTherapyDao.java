package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the PriorTherapy domain object.
 * 
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
@Transactional(readOnly=true)
public class PriorTherapyDao extends CaaersDao<PriorTherapy> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("text",
                    "meddraTerm", "meddraCode");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<PriorTherapy> domainClass() {
        return PriorTherapy.class;
    }

    /**
     * Get the list of prior therapies matching the name fragments.
     * 
     * @param subnames
     *                the name fragments to search on.
     * @return List of matching prior therapies.
     */
    public List<PriorTherapy> getBySubnames(String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

    /**
     * Get the list of all prior therapies.
     * 
     * @return return the list of prior therapies.
     */
    public List<PriorTherapy> getAll() {
    	HibernateTemplate template = getHibernateTemplate();
        return getHibernateTemplate().find("from PriorTherapy");
    }
    
    /**
     * Get the list of all prior therapies, excluding 'No prior therapy'
     * 
     * @return return the list of prior therapies.
     */
    public List<PriorTherapy> getAllExcludingNoPriorTherapy() {
    	HibernateTemplate template = getHibernateTemplate();
        return getHibernateTemplate().find("from PriorTherapy pt where pt.text  != 'No prior therapy'");
    }
}
