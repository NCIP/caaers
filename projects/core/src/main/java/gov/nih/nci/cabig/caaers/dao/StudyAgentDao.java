package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyAgent;

/**
 * This class implements the Data access related operations for the StudyAgent domain object.
 * 
 * @author Rhett Sutphin
 */
public class StudyAgentDao extends CaaersDao<StudyAgent> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<StudyAgent> domainClass() {
        return StudyAgent.class;
    }
}
