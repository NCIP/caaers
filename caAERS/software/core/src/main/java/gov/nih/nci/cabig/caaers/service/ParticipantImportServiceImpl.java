package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.repository.ParticipantRepository;
import gov.nih.nci.cabig.caaers.service.migrator.ParticipantMigrator;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Biju Joseph
 */
public class ParticipantImportServiceImpl extends AbstractImportServiceImpl {


    private ParticipantRepository participantRepository;
    private ParticipantMigrator participantMigrator;

    /**
     * This method converts a Participant DTO to Domain Participant and sets it in ParticipantImportOutcome instance.
     * @param xstreamParticipant
     * @return
     */
    public DomainObjectImportOutcome<Participant> importParticipant(Participant xstreamParticipant) {

        Participant participant = new Participant();
        DomainObjectImportOutcome<Participant> participantImportOutcome = new DomainObjectImportOutcome<Participant>();
        participantMigrator.migrate(xstreamParticipant, participant, participantImportOutcome);

        participantImportOutcome.setImportedDomainObject(participant);
       //participantUniquenessCheck(participant, participantImportOutcome);

        return participantImportOutcome;
    }

    /**
     * This method checks for uniqueness of a Participant.
     * @param participant
     * @param participantImportOutcome
     */
    private void participantUniquenessCheck(Participant participant,
                                            DomainObjectImportOutcome<Participant> participantImportOutcome) {

        participant.firstPrimaryIndicatorInIdentifiers();

        boolean participantExists = participantRepository.checkIfParticipantExistsForGivenIdentifiers(participant.getIdentifiers());
        if (participantExists) {
            participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName()
                    + " identifier already exists.", DomainObjectImportOutcome.Severity.ERROR);

        }

    }


    @Required
    public void setParticipantMigrator(ParticipantMigrator participantMigrator) {
		this.participantMigrator = participantMigrator;
	}

	@Required
    public void setParticipantRepository(final ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
}
