package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.List;

public class ParticipantSynchronizer extends CompositeMigrator<Participant>{

	public ParticipantSynchronizer(List<Migrator<Participant>> synchronizers) {
		super(synchronizers);
	}
	
	@Override
	public void preMigrate(Participant dbParticipant, Participant xmlParticipant,
			DomainObjectImportOutcome<Participant> outcome) {
		
		dbParticipant.setFirstName(xmlParticipant.getFirstName());
		dbParticipant.setLastName(xmlParticipant.getLastName());
		dbParticipant.setMiddleName(xmlParticipant.getMiddleName());
		dbParticipant.setMaidenName(xmlParticipant.getMaidenName());
		dbParticipant.setDateOfBirth(xmlParticipant.getDateOfBirth());
		dbParticipant.setGender(xmlParticipant.getGender());
		dbParticipant.setEthnicity(xmlParticipant.getEthnicity());
		dbParticipant.setRace(xmlParticipant.getRace());
	}

}
