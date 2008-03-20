package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Biju Joseph
 */
public class ParticipantImportServiceImpl extends AbstractImportServiceImpl {

    private StudySiteDao studySiteDao;

    private ParticipantRepository participantRepository;

    public DomainObjectImportOutcome<Participant> importParticipant(Participant xstreamParticipant) {

        Participant participant = new Participant();
        DomainObjectImportOutcome<Participant> participantImportOutcome = new DomainObjectImportOutcome<Participant>();

        participant.setFirstName(xstreamParticipant.getFirstName());
        participant.setLastName(xstreamParticipant.getLastName());
        participant.setMiddleName(xstreamParticipant.getMiddleName());
        participant.setMaidenName(xstreamParticipant.getMaidenName());
        participant.setDateOfBirth(xstreamParticipant.getDateOfBirth());
        participant.setGender(xstreamParticipant.getGender());
        participant.setRace(xstreamParticipant.getRace());
        participant.setEthnicity(xstreamParticipant.getEthnicity());

        // migrateIdentifiers(participant,xstreamParticipant);
        migrateIdentifiers(participant, xstreamParticipant, participantImportOutcome);

        //now migrate assignments
        migrateAssignments(participant, xstreamParticipant, participantImportOutcome);

        participantImportOutcome.setImportedDomainObject(participant);
        participantUniquenessCheck(participant, participantImportOutcome);

        return participantImportOutcome;
    }

    private void migrateAssignments(Participant destination, Participant source,
                                    DomainObjectImportOutcome participantImportOutcome) {

        for (StudyParticipantAssignment studyParticipantAssignment : source.getAssignments()) {


            for (Identifier identifier : studyParticipantAssignment.getStudySite().getStudy().getIdentifiers()) {

                log.debug("Size of identifiers : " + studyParticipantAssignment.getStudySite().getStudy().getIdentifiers());
                String identifierValue = identifier.getValue();
                String organizationName = studyParticipantAssignment.getStudySite().getOrganization().getName();
                String identifierType = identifier.getType();

                StudySite studySite = studySiteDao.matchByStudyAndOrg(organizationName, identifierValue, identifierType);

                destination.addAssignment(new StudyParticipantAssignment(destination, studySite));


                participantImportOutcome.ifNullObject(studySite, DomainObjectImportOutcome.Severity.ERROR,
                        "The Study with Identifier \" "
                                + identifierValue
                                + " \" is either nonexistant or does not match the provided Site");

            }

        }
        participantImportOutcome.ifNullOrEmptyList(source.getAssignments(), DomainObjectImportOutcome.Severity.ERROR,
                "Assignments are either Empty or Not Valid");
    }


    private void participantUniquenessCheck(Participant participant,
                                            DomainObjectImportOutcome participantImportOutcome) {

        participant.firstPrimaryIndicatorInIdentifiers();

        boolean participantExists = participantRepository.checkIfParticipantExistsForGivenIdentifiers(participant.getIdentifiers());
        if (participantExists) {
            participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName()
                    + " identifier already exists.", DomainObjectImportOutcome.Severity.ERROR);

        }


    }

    @Required
    public void setStudySiteDao(final StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }


    @Required
    public void setParticipantRepository(final ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
}
