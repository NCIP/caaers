package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;

public class StudyDiseaseTerminologyMigratorTest extends AbstractTestCase {
	
	Study xstreamStudy;
	DomainObjectImportOutcome<Study> outcome;
	Study dest;
	StudyDiseaseTerminologyMigrator migrator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dest = new Study();
		xstreamStudy = Fixtures.createStudy("short title");
		outcome = new DomainObjectImportOutcome<Study>();
		migrator = new StudyDiseaseTerminologyMigrator();
	}
	
	public void testMigrate() {
		DiseaseTerminology diseaseTerminology = Fixtures.createDiseaseTerminology(xstreamStudy);
		xstreamStudy.setDiseaseTerminology(diseaseTerminology);
		migrator.migrate(xstreamStudy, dest, outcome);
		assertTrue("In correct error message", outcome.getMessages().isEmpty());
		assertEquals("Study should be changed properly", dest, dest.getDiseaseTerminology().getStudy());
		assertEquals("Disease code term incorrect", diseaseTerminology.getDiseaseCodeTerm(), dest.getDiseaseTerminology().getDiseaseCodeTerm());
	}
	
	public void testMigrate_InvalidDiseaseCodeTerm() {
		DiseaseTerminology diseaseTerminology = Fixtures.createDiseaseTerminology(xstreamStudy);
		diseaseTerminology.setDiseaseCodeTerm(null);
		
		xstreamStudy.setDiseaseTerminology(diseaseTerminology);
		migrator.migrate(xstreamStudy, dest, outcome);
		assertEquals("In correct error messages", 1,outcome.getMessages().size());
		assertEquals("Study should be changed properly", dest, dest.getDiseaseTerminology().getStudy());
		Message msg = (Message) outcome.getMessages().get(0);
		assertEquals("Error message incorrect","Disease Code Term is either Empty or Not Valid",msg.getMessage());
	}

}
