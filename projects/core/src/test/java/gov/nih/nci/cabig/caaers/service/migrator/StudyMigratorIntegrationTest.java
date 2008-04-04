package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import gov.nih.nci.cabig.caaers.service.migrator.StudyMigrator;

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
		assertEquals(8, migrators.size());
		assertTrue(migrator.getChildren().get(4) instanceof IdentifierMigrator);
	}
	
	@Override
	public String[] getConfigLocations() {
		return new String[] {
				"classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
	            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-db.xml",
	            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
	            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-service.xml",
	            "classpath*:applicationContext-test.xml"
	        };
	}

}
