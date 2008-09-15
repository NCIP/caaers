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
/*
        studyParticipantAssignment.setDateOfEnrollment(new Date());
        studyParticipantAssignment.setParticipant(assignParticipantStudyCommand.getParticipant());
        studyParticipantAssignment.setStudySite(assignParticipantStudyCommand.getStudySite());
        studyParticipantAssignment.setStudySubjectIdentifier(assignParticipantStudyCommand.getStudySubjectIdentifier());
*/

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

/*
        OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
        organizationAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);

        this.participant.addIdentifier(organizationAssignedIdentifier);
*/
    }
    
}
