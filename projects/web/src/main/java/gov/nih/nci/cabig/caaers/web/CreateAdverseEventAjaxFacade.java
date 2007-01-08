package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * @author Rhett Sutphin
 */
// TODO: Obviously, this is all fake data
public class CreateAdverseEventAjaxFacade {
    private StudyDao studyDao;
    private ParticipantDao participantDao;

    public List<Participant> matchParticipants(String text, Integer studyId) {
        List<Participant> participants = participantDao.getBySubnames(text.split("\\s+"));
        if (studyId != null) {
            for (Iterator<Participant> it = participants.iterator(); it.hasNext();) {
                Participant participant = it.next();
                if (!onStudy(participant, studyId)) it.remove();
            }
        }
        return participants;
    }

    private boolean onStudy(Participant participant, Integer studyId) {
        boolean onStudy = false;
        for (StudyParticipantAssignment assignment : participant.getAssignments()) {
            if (assignment.getStudySite().getStudy().getId().equals(studyId)) {
                onStudy = true;
                break;
            }
        }
        return onStudy;
    }

    private Study createFakeStudy(int id) {
        Study s = new Study();
        s.setId(id);
        s.setShortTitle("Study " + id);
        return s;
    }

    public List<Study> matchStudies(String text, Integer participantId) {
        List<Study> studies = studyDao.getBySubnames(text.split("\\s+"));
        if (participantId != null) {
            for (Iterator<Study> it = studies.iterator(); it.hasNext();) {
                Study study = it.next();
                if (!onStudy(study, participantId)) it.remove();
            }
        }
        return studies;
    }

    private boolean onStudy(Study study, Integer participantId) {
        boolean onStudy = false;
        for (StudySite studySite : study.getStudySites()) {
            for (StudyParticipantAssignment assignment : studySite.getStudyParticipantAssignments()) {
                if (assignment.getParticipant().getId().equals(participantId)) {
                    onStudy = true;
                    break;
                }
            }
        }
        return onStudy;
    }

    ////// CONFIGURATION

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
}
