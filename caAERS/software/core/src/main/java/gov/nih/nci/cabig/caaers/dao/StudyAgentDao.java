package gov.nih.nci.cabig.caaers.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.StudyAgent;

/**
 * This class implements the Data access related operations for the StudyAgent domain object.
 * 
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class StudyAgentDao extends CaaersDao<StudyAgent> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	 @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<StudyAgent> domainClass() {
        return StudyAgent.class;
    }
}
