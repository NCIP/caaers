/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyTherapyMigratorTest extends AbstractTestCase {
	
	StudyTherapyMigrator migrator;
	Study xstreamStudy;
	DomainObjectImportOutcome<Study> outcome;
	Study dest;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dest = new LocalStudy();
		xstreamStudy = Fixtures.createStudy("short title");
		outcome = new DomainObjectImportOutcome<Study>();
		migrator = new StudyTherapyMigrator();
	}

    //commented based on JIRA 	CAAERS-4576
	
//	public void testMigrate() {
//		xstreamStudy.addStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION);
//		xstreamStudy.addStudyTherapy(StudyTherapyType.RADIATION);
//
//		migrator.migrate(xstreamStudy, dest, outcome);
//		assertEquals(2, dest.getStudyTherapies().size());
//		assertTrue("No errors should be there", outcome.getMessages().isEmpty());
//
//	}
	
	
	public void testMigrateWithNoTherapy() {
		migrator.migrate(xstreamStudy, dest, outcome);
		assertEquals(0, dest.getStudyTherapies().size());
		assertTrue("No errors should be there", outcome.getMessages().isEmpty());

	}

}
