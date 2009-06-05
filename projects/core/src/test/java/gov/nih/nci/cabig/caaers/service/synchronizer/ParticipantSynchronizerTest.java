package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;

public class ParticipantSynchronizerTest extends AbstractTestCase {
	
	Participant dbParticipant;
	Participant xmlParticipant;
	ParticipantSynchronizer participantSynchronizer;
	DomainObjectImportOutcome<Participant> outcome;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		participantSynchronizer = new ParticipantSynchronizer();
		outcome = new DomainObjectImportOutcome<Participant>();
	}
	
	public void testParticipantAttrUpdate(){
		dbParticipant = Fixtures.createParticipant("FirstName", "LastName");
		dbParticipant.setMiddleName("MiddleName");
		dbParticipant.setMaidenName("MaidenName");
		dbParticipant.setDateOfBirth(new DateValue(12,12,1970));
		dbParticipant.setEthnicity("Ethnicity");
		dbParticipant.setGender("Gender");
		dbParticipant.setRace("Race");
		
		xmlParticipant = Fixtures.createParticipant("FirstName Updated", "LastName Updated");
		xmlParticipant.setMiddleName("MiddleName Updated");
		xmlParticipant.setMaidenName("MaidenName Updated");
		xmlParticipant.setDateOfBirth(new DateValue(11,11,1971));
		xmlParticipant.setEthnicity("Ethnicity Updated");
		xmlParticipant.setGender("Gender Updated");
		xmlParticipant.setRace("Race Updated");
		
		participantSynchronizer.migrate(dbParticipant, xmlParticipant, outcome);
		
		assertEquals("FirstName Updated", dbParticipant.getFirstName());
		assertEquals("LastName Updated", dbParticipant.getLastName());
		assertEquals("MiddleName Updated", dbParticipant.getMiddleName());
		assertEquals("MaidenName Updated", dbParticipant.getMaidenName());
		assertEquals("Ethnicity Updated", dbParticipant.getEthnicity());
		assertEquals("Gender Updated", dbParticipant.getGender());
		assertEquals("Race Updated", dbParticipant.getRace());
		assertEquals(new DateValue(11,11,1971),dbParticipant.getDateOfBirth());
	}
	
}
