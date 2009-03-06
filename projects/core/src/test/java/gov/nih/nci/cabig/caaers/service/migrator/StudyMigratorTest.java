package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;

import junit.framework.TestCase;

public class StudyMigratorTest extends TestCase {

	public void testPreMigrateStudyStudyDomainObjectImportOutcomeOfStudy() {
		Study xstreamStudy = Fixtures.createStudy("abcd");
		DomainObjectImportOutcome<Study> outcome = new DomainObjectImportOutcome<Study>();
		Study dest = new Study();
		
		StudyMigrator migrator = new StudyMigrator(new ArrayList<Migrator<Study>>());
		migrator.migrate(xstreamStudy, dest, outcome);
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals("Incorrect short title", "abcd", dest.getShortTitle());
	}

}
