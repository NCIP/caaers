package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantDiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class EditParticipantCommand extends ParticipantInputCommand {

    public EditParticipantCommand(Participant participant) {
        super(participant);
        init();
    }

    void init() {
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

    @Override
    public List<StudyParticipantAssignment> getAssignments() {
        return getParticipant().getAssignments(); 
    }
}