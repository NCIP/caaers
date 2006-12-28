package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

/**
 * @author Krikor Krumlian
 */
public class StudyParticipantAssignmentDao extends CaaersDao<StudyParticipantAssignment> {
    public Class<StudyParticipantAssignment> domainClass() {
        return StudyParticipantAssignment.class;
    }
}
