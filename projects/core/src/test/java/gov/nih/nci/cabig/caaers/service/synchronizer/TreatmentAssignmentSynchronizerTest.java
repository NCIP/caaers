package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

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
		
		treatmentAssignmentSynchronizer = new TreatmentAssignmentSynchronizer();
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(4, dbStudy.getTreatmentAssignments().size());
		
		xmlStudy.getTreatmentAssignments().clear();
		
		treatmentAssignmentSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(4, dbStudy.getTreatmentAssignments().size());
		
	}
	
}
