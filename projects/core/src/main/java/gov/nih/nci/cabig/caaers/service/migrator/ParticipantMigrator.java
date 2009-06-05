package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

public class ParticipantMigrator extends CompositeMigrator<Participant> {
	
	
	@Override
	public void preMigrate(Participant src, Participant dest, DomainObjectImportOutcome<Participant> outcome) {
		//TODO: BJ, Does this go to Participant ?
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setMiddleName(src.getMiddleName());
        dest.setMaidenName(src.getMaidenName());
        dest.setDateOfBirth(src.getDateOfBirth());
        dest.setGender(src.getGender());
        dest.setRace(src.getRace());
        dest.setEthnicity(src.getEthnicity());
	}
}
