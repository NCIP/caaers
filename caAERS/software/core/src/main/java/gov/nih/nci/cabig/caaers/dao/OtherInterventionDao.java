package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Biju Joseph
 */
public class OtherInterventionDao extends CaaersDao<OtherIntervention>{
    
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<OtherIntervention> domainClass() {
        return OtherIntervention.class;  
    }

    
}
