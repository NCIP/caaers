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
		assertEquals(10, migrators.size());
		assertTrue(migrator.getChildren().get(4) instanceof IdentifierMigrator);
	}
	
	

}
