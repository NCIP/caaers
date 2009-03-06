package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyCondition;

/**
 *
 * @author Ion C. Olaru
 */
public class StudyConditionDao extends GridIdentifiableDao<StudyCondition> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<StudyCondition> domainClass() {
        return StudyCondition.class;
    }

}