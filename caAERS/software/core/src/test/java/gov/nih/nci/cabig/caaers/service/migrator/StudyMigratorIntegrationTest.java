/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

public class StudyMigratorIntegrationTest extends CaaersTestCase {
	
	@Override
	protected void setUp() throws Exception {
		setUpAuditing();
	}
	
	@Override
	protected void tearDown() throws Exception {
		tearDownAuditing();
	}
	
	public void testLoad(){
		StudyMigrator migrator = (StudyMigrator)getDeployedApplicationContext().getBean("studyMigrator");
		List<Migrator<Study>> migrators = migrator.getChildren();
		assertEquals(12, migrators.size());
		assertTrue(migrator.getChildren().get(5) instanceof IdentifierMigrator);
	}
}
