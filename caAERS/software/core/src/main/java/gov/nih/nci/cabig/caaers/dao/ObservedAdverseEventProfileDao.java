package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventProfile;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ObservedAdverseEventProfile domain object.
 * 
 * @author Kruttik Aggarwal
 */
@Transactional(readOnly=true)
public class ObservedAdverseEventProfileDao extends GridIdentifiableDao<ObservedAdverseEventProfile> implements MutableDomainObjectDao<ObservedAdverseEventProfile> {

    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    /**
     * The domain class this Dao represents in this case an ObservedAdverseEventProfile
     * @return
     */
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ObservedAdverseEventProfile> domainClass() {
        return ObservedAdverseEventProfile.class;
    }

    /**
     * Get the list of all ObservedAdverseEventProfiles.
     * 
     * @return return the list of agents.
     */
    public List<ObservedAdverseEventProfile> getAll() {
        return getHibernateTemplate().find("from ObservedAdverseEventProfile");
    }

    /**
     * Will save an observedAdverseEventProfile into the DB. 
     * @param o - observedAdverseEventProfile to save. 
     */
    @Override
    @Transactional(readOnly = false)
    public void save(ObservedAdverseEventProfile o) {
        getHibernateTemplate().saveOrUpdate(o);
    }
    
    public List<ObservedAdverseEventProfile> getByTACs(TreatmentAssignment[] treatmentAssignments){
    	return getHibernateTemplate().findByNamedParam("from ObservedAdverseEventProfile oap where oap.treatmentAssignment in :params", "params", treatmentAssignments);
    }
}
