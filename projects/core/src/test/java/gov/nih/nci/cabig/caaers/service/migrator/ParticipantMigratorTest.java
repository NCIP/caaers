package gov.nih.nci.cabig.caaers.service.migrator;


import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;


public class ParticipantMigratorTest extends AbstractTestCase {
	
	 private Participant xstreamParticipant;
	 Participant participant;
	 DomainObjectImportOutcome<Participant> outcome = new DomainObjectImportOutcome<Participant>();
	 ParticipantMigrator migrator;
	 
	 @Override
	protected void setUp() throws Exception {
		super.setUp();
		xstreamParticipant = Fixtures.createParticipant("first", "last");
		participant = new Participant();
		List<Migrator<Participant>> migrators = new ArrayList<Migrator<Participant>>();
		migrator = new ParticipantMigrator();
		migrator.setChildren(migrators);
	}
	 
	public void testPreMigrateParticipant() {
		migrator.preMigrate(xstreamParticipant, participant, outcome);
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals("first", participant.getFirstName());
	}

}
