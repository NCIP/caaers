package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Monish Domla
 * @author Biju Joseph (refactored)
 *
 */
public class TreatmentAssignmentSynchronizerTest extends AbstractTestCase{

	Study dbStudy;
	Study xmlStudy;
	TreatmentAssignmentSynchronizer treatmentAssignmentSynchronizer;
	DomainObjectImportOutcome<Study> outcome;
	TreatmentAssignment treatmentAssignment1,treatmentAssignment2,
						treatmentAssignment1a,treatmentAssignment2a,
						treatmentAssignment3,treatmentAssignment4;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		treatmentAssignmentSynchronizer = new TreatmentAssignmentSynchronizer();
		outcome = new DomainObjectImportOutcome<Study>();
		
		dbStudy = Fixtures.createStudy("abcd");
		xmlStudy = Fixtures.createStudy("abcd");
		treatmentAssignmentSynchronizer = new TreatmentAssignmentSynchronizer();
	}
	
	public void testTreatmentAssignmentAddUpdateSync() {
		treatmentAssignment1 = Fixtures.createTreatmentAssignment("Code1");
		treatmentAssignment1.setId(1);
		dbStudy.addTreatmentAssignment(treatmentAssignment1);
		
		treatmentAssignment2 = Fixtures.createTreatmentAssignment("Code2");
		treatmentAssignment2.setId(2);
		dbStudy.addTreatmentAssignment(treatmentAssignment2);
		
		treatmentAssignment1a = Fixtures.createTreatmentAssignment("Code1");
		treatmentAssignment1a.setComments("UpdatedComments");
		treatmentAssignment1a.setDoseLevelOrder(4);
		xmlStudy.addTreatmentAssignment(treatmentAssignment1a);
		
		treatmentAssignment2a = Fixtures.createTreatmentAssignment("Code2");
		treatmentAssignment2a.setDescription("UpdatedDescription");
		xmlStudy.addTreatmentAssignment(treatmentAssignment2a);
		
		treatmentAssignment3 = Fixtures.createTreatmentAssignment("Code3");
		xmlStudy.addTreatmentAssignment(treatmentAssignment3);
		
		treatmentAssignment4 = Fixtures.createTreatmentAssignment("Code4");
		xmlStudy.addTreatmentAssignment(treatmentAssignment4);
		
		
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(4, dbStudy.getTreatmentAssignments().size());
		
		xmlStudy.getTreatmentAssignments().clear();
		
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(4, dbStudy.getTreatmentAssignments().size());
		
	}
	
	/**
	 * Will test the soft delete and addition
	 */
	public void testMigrate_AddAndRetire(){
		TreatmentAssignment ta = Fixtures.createTreatmentAssignment("a1");
		ta.setDescription("a1");
		ta.setComments("a1");
		ta.setDoseLevelOrder(1);
		dbStudy.addTreatmentAssignment(ta);

		ta = Fixtures.createTreatmentAssignment("a2");
		ta.setDescription("a2");
		ta.setComments("a2");
		ta.setDoseLevelOrder(2);
		dbStudy.addTreatmentAssignment(ta);
		
		ta = Fixtures.createTreatmentAssignment("a2");
		ta.setDescription("a2x");
		ta.setComments("a2");
		ta.setDoseLevelOrder(3);
		xmlStudy.addTreatmentAssignment(ta);
		
		ta = Fixtures.createTreatmentAssignment("a3");
		ta.setDescription("a3");
		ta.setComments("a3");
		ta.setDoseLevelOrder(3);
		xmlStudy.addTreatmentAssignment(ta);
		
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		//assert #
		assertEquals(3, dbStudy.getTreatmentAssignments().size());
		
		
		//check a3
		assertEquals("a3", dbStudy.getTreatmentAssignments().get(2).getDescription());
		assertEquals("a3", dbStudy.getTreatmentAssignments().get(2).getCode());
		assertFalse(dbStudy.getTreatmentAssignments().get(2).isRetired());
		
		//check a1
		assertTrue(dbStudy.getTreatmentAssignments().get(0).isRetired());
		assertEquals(new Integer(1), dbStudy.getTreatmentAssignments().get(0).getDoseLevelOrder());
		assertEquals("a1", dbStudy.getTreatmentAssignments().get(0).getCode());
		assertEquals("a1", dbStudy.getTreatmentAssignments().get(0).getComments());
		
		//check a2
		assertFalse(dbStudy.getTreatmentAssignments().get(1).isRetired());
		assertEquals("a2x", dbStudy.getTreatmentAssignments().get(1).getDescription()); //should be modified
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getComments()); //should be same
		assertEquals(new Integer(3), dbStudy.getTreatmentAssignments().get(1).getDoseLevelOrder()); //should be modified
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getCode());
		
	}
	
	
	/**
	 * Will test the soft delete only
	 */
	public void testMigrate_OnlyRetire(){
		TreatmentAssignment ta = Fixtures.createTreatmentAssignment("a1");
		ta.setDescription("a1");
		ta.setComments("a1");
		ta.setDoseLevelOrder(1);
		dbStudy.addTreatmentAssignment(ta);

		ta = Fixtures.createTreatmentAssignment("a2");
		ta.setDescription("a2");
		ta.setComments("a2");
		ta.setDoseLevelOrder(2);
		dbStudy.addTreatmentAssignment(ta);
		
		
		
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		//assert #
		assertEquals(2, dbStudy.getTreatmentAssignments().size());
		
		
		
		//check a1
		assertFalse(dbStudy.getTreatmentAssignments().get(0).isRetired());
		assertEquals(new Integer(1), dbStudy.getTreatmentAssignments().get(0).getDoseLevelOrder());
		assertEquals("a1", dbStudy.getTreatmentAssignments().get(0).getCode());
		assertEquals("a1", dbStudy.getTreatmentAssignments().get(0).getComments());
		
		//check a2
		assertFalse(dbStudy.getTreatmentAssignments().get(1).isRetired());
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getDescription()); 
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getComments()); 
		assertEquals(new Integer(2), dbStudy.getTreatmentAssignments().get(1).getDoseLevelOrder()); 
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getCode());
		
	}
	
	/**
	 * Will test addition
	 */
	public void testMigrate_OnlyAdd(){
		TreatmentAssignment ta = Fixtures.createTreatmentAssignment("a1");
		
		ta = Fixtures.createTreatmentAssignment("a1");
		ta.setDescription("a1");
		ta.setComments("a1");
		ta.setDoseLevelOrder(1);
		xmlStudy.addTreatmentAssignment(ta);
		
		ta = Fixtures.createTreatmentAssignment("a2");
		ta.setDescription("a2");
		ta.setComments("a2");
		ta.setDoseLevelOrder(2);
		xmlStudy.addTreatmentAssignment(ta);
		
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		//assert #
		assertEquals(2, dbStudy.getTreatmentAssignments().size());
		
		
		//check a1
		assertFalse(dbStudy.getTreatmentAssignments().get(0).isRetired());
		assertEquals(new Integer(1), dbStudy.getTreatmentAssignments().get(0).getDoseLevelOrder());
		assertEquals("a1", dbStudy.getTreatmentAssignments().get(0).getCode());
		assertEquals("a1", dbStudy.getTreatmentAssignments().get(0).getComments());
		
		//check a2
		assertFalse(dbStudy.getTreatmentAssignments().get(1).isRetired());
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getDescription()); 
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getComments());
		assertEquals(new Integer(2), dbStudy.getTreatmentAssignments().get(1).getDoseLevelOrder()); 
		assertEquals("a2", dbStudy.getTreatmentAssignments().get(1).getCode());
		
	}
}
