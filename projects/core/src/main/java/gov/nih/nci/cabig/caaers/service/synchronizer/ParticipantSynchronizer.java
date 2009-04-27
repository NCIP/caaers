package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ParticipantSynchronizer extends CompositeMigrator<Participant>{

	public ParticipantSynchronizer(List<Migrator<Participant>> synchronizers) {
		super(synchronizers);
	}
	
	@Override
	public void preMigrate(Participant dbParticipant, Participant xmlParticipant,
			DomainObjectImportOutcome<Participant> outcome) {
		
		dbParticipant.setFirstName(xmlParticipant.getFirstName());
		dbParticipant.setLastName(xmlParticipant.getLastName());
		if(xmlParticipant.getMiddleName() != null &&  StringUtils.isNotEmpty(xmlParticipant.getMiddleName())){
			dbParticipant.setMiddleName(xmlParticipant.getMiddleName());
		}
		if(xmlParticipant.getMaidenName() != null &&  StringUtils.isNotEmpty(xmlParticipant.getMaidenName())){
			dbParticipant.setMaidenName(xmlParticipant.getMaidenName());
		}
		dbParticipant.setDateOfBirth(xmlParticipant.getDateOfBirth());
		if(xmlParticipant.getGender() != null &&  StringUtils.isNotEmpty(xmlParticipant.getGender())){
			dbParticipant.setGender(xmlParticipant.getGender());
		}
		if(xmlParticipant.getEthnicity() != null &&  StringUtils.isNotEmpty(xmlParticipant.getEthnicity())){
			dbParticipant.setEthnicity(xmlParticipant.getEthnicity());
		}
		if(xmlParticipant.getRace() != null &&  StringUtils.isNotEmpty(xmlParticipant.getRace())){
			dbParticipant.setRace(xmlParticipant.getRace());
		}
	}
}