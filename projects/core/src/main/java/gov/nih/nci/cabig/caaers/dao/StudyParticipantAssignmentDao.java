package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import edu.nwu.bioinformatics.commons.CollectionUtils;

import java.util.List;

import org.hibernate.LockMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class StudyParticipantAssignmentDao extends GridIdentifiableDao<StudyParticipantAssignment> {
    @Override
	public Class<StudyParticipantAssignment> domainClass() {
        return StudyParticipantAssignment.class;
    }

    /*
     * I guess this is not safe to use , A study might have multiple sites. 
     */
    @SuppressWarnings("unchecked")
    public StudyParticipantAssignment getAssignment(Participant participant, Study study) {
        return CollectionUtils.firstElement(
            (List<StudyParticipantAssignment>) getHibernateTemplate().find(
                "from StudyParticipantAssignment a where a.participant = ? and a.studySite.study = ?",
                new Object[] { participant, study })
        );
    }
    
    @SuppressWarnings("unchecked")
    public StudyParticipantAssignment getAssignment(Participant participant, StudySite studySite) {
        return CollectionUtils.firstElement(
            (List<StudyParticipantAssignment>) getHibernateTemplate().find(
                "from StudyParticipantAssignment a where a.participant = ? and a.studySite = ?",
                new Object[] { participant, studySite })
        );
    }

    @Override
    public void reassociate(StudyParticipantAssignment assignment) {
    	getHibernateTemplate().lock(assignment, LockMode.NONE);
    }
}
