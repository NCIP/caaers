package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

public class TreatmentAssignmentDaoTest extends DaoTestCase<TreatmentAssignmentDao>{

	public void  testGetAssignmentsByStudyId(){
		List<TreatmentAssignment> tas = getDao().getAssignmentsByStudyId("123", -2);
		assertNotNull("There should be TreatmentAssignments", tas);
		assertEquals("Incorrect treatment assignment count", 3, tas.size());
		TreatmentAssignment ta = tas.get(1);
		assertEquals("The treatment assignment code is different", "TAC-12346",ta.getCode());

		tas = getDao().getAssignmentsByStudyId("12345", -2);
		assertNotNull("There should be TreatmentAssignments", tas);
		assertEquals("Incorrect treatment assignment count", 1, tas.size());
	}
}
