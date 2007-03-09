/**
 * 
 */
package gov.nih.nci.cabig.caaers.grid.cmd;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class RegisterParticipantCommand {

    private ParticipantDao participantDao;
    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

    private Participant participant;
    private StudySite studySite;
    private StudyParticipantAssignment studyParticipantAssignment;

    /**
     * The StudySite must exist.
     * 
     * 
     * @return
     * @throws DuplicateAssignmentException
     */
    public StudyParticipantAssignment execute() throws DuplicateAssignmentException {

        ParticipantDao pDao = getParticipantDao();
        
        Participant p = getParticipant();
        StudySite s = getStudySite();
        StudyParticipantAssignment a = getStudyParticipantAssignment();

        boolean pExists = false;
        //TODO: determine if participant exists already
        if(pExists){
            //TODO: retrieve existing participant
            // p = get the participant
            setParticipant(p);
        }
        
        boolean aExists = false;
        //TODO: determine if assignment exists already
        if(aExists){
            throw new DuplicateAssignmentException("Participant " + p.getId() + " is already assigned to StudySite " + s.getId());
        }
        
        a.setStudySite(s);
        p.addAssignment(a);
        
        pDao.save(p);
        
        return a;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public StudySite getStudySite() {
        return studySite;
    }

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    public StudyParticipantAssignment getStudyParticipantAssignment() {
        return studyParticipantAssignment;
    }

    public void setStudyParticipantAssignment(StudyParticipantAssignment studyParticipantAssignment) {
        this.studyParticipantAssignment = studyParticipantAssignment;
    }

    public StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
        return studyParticipantAssignmentDao;
    }

    public void setStudyParticipantAssignmentDao(
                    StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
        this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
    }
}
