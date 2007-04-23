package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyAgent;

/**
 * @author Rhett Sutphin
 */
public class StudyAgentDao extends CaaersDao<StudyAgent> {
    @Override
    public Class<StudyAgent> domainClass() {
        return StudyAgent.class;
    }
}
