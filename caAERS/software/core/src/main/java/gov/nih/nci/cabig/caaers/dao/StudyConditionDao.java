package gov.nih.nci.cabig.caaers.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.StudyCondition;

/**
 *
 * @author Ion C. Olaru
 */
@Transactional(readOnly=true)
public class StudyConditionDao extends GridIdentifiableDao<StudyCondition> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<StudyCondition> domainClass() {
        return StudyCondition.class;
    }

}