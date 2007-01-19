package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacade {
    private StudyDao studyDao;
    private ParticipantDao participantDao;
    private CtcTermDao ctcTermDao;
    private CtcDao ctcDao;

    public List<Participant> matchParticipants(String text, Integer studyId) {
        List<Participant> participants = participantDao.getBySubnames(extractSubnames(text));
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

    public List<Study> matchStudies(String text, Integer participantId) {
        List<Study> studies = studyDao.getBySubnames(extractSubnames(text));
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

    public List<CtcTerm> matchTerms(String text, Integer ctcVersionId, Integer ctcCategoryId, int limit) throws Exception {
        List<CtcTerm> terms = ctcTermDao.getBySubname(extractSubnames(text), ctcVersionId, ctcCategoryId);
        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        while (terms.size() > limit) {
            terms.remove(terms.size() - 1);
        }
        return terms;
    }

    public List<CtcCategory> getCategories(int ctcVersionId) {
        List<CtcCategory> categories = ctcDao.getById(ctcVersionId).getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }

    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }

    ////// CONFIGURATION

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }
}
