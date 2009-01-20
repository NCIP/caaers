package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

public class TreatmentAssignmentDaoTest extends DaoTestCase<TreatmentAssignmentDao> {

    public void testGetAssignmentsByStudyId() {
        List<TreatmentAssignment> tas = getDao().getAssignmentsByStudyId("123", -2);
        assertNotNull("There should be TreatmentAssignments", tas);
        assertEquals("Incorrect treatment assignment count", 3, tas.size());
        TreatmentAssignment ta = tas.get(1);
        assertTrue("The treatment assignment code is different", ta.getCode().contains("123"));

        tas = getDao().getAssignmentsByStudyId("12345", -2);
        assertNotNull("There should be TreatmentAssignments", tas);
        assertEquals("Incorrect treatment assignment count", 1, tas.size());
    }
    
    public void testGetAll(){
    	List<TreatmentAssignment> tas = getDao().getAll();
    	assertNotNull("There should be Treatment Assignments", tas);
    	assertEquals("Incorrect treatment assignment count", 3, tas.size());
    }
    
    public void testGetAssignmentByStudyIdExactMatch(){
    	TreatmentAssignment t = getDao().getAssignmentsByStudyIdExactMatch("TAC-12345", -2);
    	assertNotNull("There should be Treatment Assignment", t);
    	assertEquals("Incorrect treatment assignment fetched", "TAC-12345", t.getCode());
    }
    
    public void testGetAssignmentByStudyIdExactMatchNoResults(){
    	TreatmentAssignment t = getDao().getAssignmentsByStudyIdExactMatch("test-code", -2);
    	assertNull(t);
    }
}
