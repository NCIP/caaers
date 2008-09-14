package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class AssignParticipantStudyCommand extends ParticipantInputCommand {
    protected final Log log = LogFactory.getLog(getClass());

    private List<Participant> participantSearchResults = new ArrayList<Participant>();
    private List<StudySite> studySites = new ArrayList<StudySite>();
    private StudySite studySite;

    public AssignParticipantStudyCommand() {
        this.assignment = new StudyParticipantAssignment();
    }

    public List<Participant> getParticipantSearchResults() {
        return participantSearchResults;
    }

    public void setParticipantSearchResults(final List<Participant> participantSearchResults) {
        this.participantSearchResults = participantSearchResults;
    }

    public List<StudySite> getStudySites() {
        return studySites;
    }

    public void setStudySites(final List<StudySite> studySites) {
        this.studySites = studySites;
    }

    public StudySite getStudySite() {
        return studySite;
    }

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    void init() {
        if (getParticipant() == null || getParticipant().getAssignments() == null) return;

        for (StudyParticipantAssignment spa : getParticipant().getAssignments()) {
            spa.getStudySite().getStudy().getIdentifiers();
            spa.getStudySite().getStudy().getMeddraStudyDiseases();
            spa.getStudySite().getStudy().getCtepStudyDiseases();

            if (spa.getDiseaseHistory() == null) {
                spa.setPriorTherapies(new ArrayList<StudyParticipantPriorTherapy>());
                StudyParticipantDiseaseHistory studyParticipantDiseaseHistory = new StudyParticipantDiseaseHistory();
                studyParticipantDiseaseHistory.setAssignment(spa);
                spa.setDiseaseHistory(studyParticipantDiseaseHistory);
                spa.setPreExistingConditions(new ArrayList<StudyParticipantPreExistingCondition>());
                spa.setConcomitantMedications(new ArrayList<StudyParticipantConcomitantMedication>());
            }
        }
    }
    
}
