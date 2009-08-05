package gov.nih.nci.cabig.caaers.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;

/**
 * This class implements the Data access related operations for the CtepStudyDisease domain object.
 * 
 * @author kulasekaran
 */
@Transactional(readOnly=true)
public class CtepStudyDiseaseDao extends GridIdentifiableDao<CtepStudyDisease> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<CtepStudyDisease> domainClass() {
        return CtepStudyDisease.class;
    }

}
