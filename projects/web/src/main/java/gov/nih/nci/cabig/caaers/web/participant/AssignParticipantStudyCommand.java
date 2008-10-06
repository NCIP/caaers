package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author Ion C. Olaru
 */

public class AssignParticipantStudyCommand extends ParticipantInputCommand {

    protected final Log log = LogFactory.getLog(getClass());
    private StudySite studySite;

    public AssignParticipantStudyCommand() {
        this.assignment = new StudyParticipantAssignment();
    }

    public StudySite getStudySite() {
        return studySite;
    }

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    void init() {

        if (this.participant == null) this.participant = new Participant();
        if (this.studySite == null) this.studySite = new StudySite();
        if (this.studySite.getStudy() == null) this.studySite.setStudy(new Study());

        this.assignment = new StudyParticipantAssignment();
        this.assignment.setStudySubjectIdentifier(this.getStudySubjectIdentifier());

        this.assignment.setDateOfEnrollment(new Date());
        this.assignment.setParticipant(this.participant);
        this.participant.addAssignment(this.assignment);

        this.assignment.setStudySite(this.studySite);
        this.setStudy(this.studySite.getStudy());
        this.assignment.setPriorTherapies(new ArrayList<StudyParticipantPriorTherapy>());
        StudyParticipantDiseaseHistory studyParticipantDiseaseHistory = new StudyParticipantDiseaseHistory();
        studyParticipantDiseaseHistory.setAssignment(this.assignment);

        this.assignment.setDiseaseHistory(studyParticipantDiseaseHistory);
        this.assignment.setPreExistingConditions(new ArrayList<StudyParticipantPreExistingCondition>());
        this.assignment.setConcomitantMedications(new ArrayList<StudyParticipantConcomitantMedication>());
    }

    public void setParticipantSearchResults(List<Participant> participantSearchResults) {
        this.participantSearchResults = participantSearchResults;
    }

}
