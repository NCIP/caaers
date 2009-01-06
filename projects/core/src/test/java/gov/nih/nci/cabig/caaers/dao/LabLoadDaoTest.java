package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.util.Calendar;
import java.util.List;

public class LabLoadDaoTest extends DaoTestCase<LabLoadDao> {
	
	private StudyParticipantAssignmentDao assignmentDao = (StudyParticipantAssignmentDao) getApplicationContext().getBean("studyParticipantAssignmentDao");
	
	public void testGetDomainClass(){
		Object obj = getDao().domainClass();
		assertNotNull(obj);
	}
	
	public void testGet() throws Exception {
		LabLoad loaded = getDao().getById(-1001);
        assertNotNull("Lab not found", loaded);
        assertEquals("New Lab", loaded.getName());
    }
	
	public void testSave(){
		LabLoad labLoad = new LabLoad();
		StudyParticipantAssignment assignment = assignmentDao.getById(-14);
		assignment.setId(-14);
		labLoad.setName("Name");
		labLoad.setLabDate(Calendar.getInstance().getTime());
		labLoad.setResult("abc");	
		labLoad.setDismissed(false);
		labLoad.setAssignment(assignment);
		getDao().save(labLoad);
		List<LabLoad> labs = getDao().getByAssignment(assignment);
		assertEquals(3, labs.size());
	}
	
	public void testGetByAssignment(){
		StudyParticipantAssignment assignment = assignmentDao.getById(-14);
		List<LabLoad> labs = getDao().getByAssignment(assignment);
		assertEquals(2, labs.size());
	}
	
	public void testDeleteById(){
		getDao().deleteById(-1002);
		StudyParticipantAssignment assignment = assignmentDao.getById(-14);
		List<LabLoad> labs = getDao().getByAssignment(assignment);
		assertEquals(1, labs.size());
	}
	
	public void testDelete(){
		StudyParticipantAssignment assignment = assignmentDao.getById(-14);
		List<LabLoad> labs = getDao().getByAssignment(assignment);
		assertEquals(2, labs.size());
		getDao().delete(labs.get(0));
		labs = getDao().getByAssignment(assignment);
		assertEquals(1, labs.size());
	}
}
