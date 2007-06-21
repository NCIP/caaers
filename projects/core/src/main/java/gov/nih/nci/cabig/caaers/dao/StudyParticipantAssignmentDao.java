package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import edu.nwu.bioinformatics.commons.CollectionUtils;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class StudyParticipantAssignmentDao extends GridIdentifiableDao<StudyParticipantAssignment> {
    public Class<StudyParticipantAssignment> domainClass() {
        return StudyParticipantAssignment.class;
    }

    @SuppressWarnings("unchecked")
    public StudyParticipantAssignment getAssignment(Participant participant, Study study) {
        return CollectionUtils.firstElement(
            (List<StudyParticipantAssignment>) getHibernateTemplate().find(
                "from StudyParticipantAssignment a where a.participant = ? and a.studySite.study = ?",
                new Object[] { participant, study })
        );
    }
}
