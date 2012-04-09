package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.NotificationStatus;
import gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventProfile;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

/**
 * @author Sameer Sawant
 */

public class ObservedAdverseEventProfileDaoTest extends DaoTestCase<ObservedAdverseEventProfileDao> {
	
	private CtcTermDao ctcTermDao;
	private TreatmentAssignmentDao treatmentAssignmentDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ctcTermDao = (CtcTermDao) getApplicationContext().getBean("ctcTermDao");
		treatmentAssignmentDao = (TreatmentAssignmentDao) getApplicationContext().getBean("treatmentAssignmentDao");
	}
	
	public void testGetById() throws Exception {
		ObservedAdverseEventProfile loaded = getDao().getById(-1);
        assertNotNull(loaded);
        assertEquals(new Double(10.0), loaded.getExpectedFrequency());
        assertEquals(Grade.NORMAL, loaded.getGrade());
        assertEquals(NotificationStatus.NOTIFY, loaded.getNotificationStatus());
        assertEquals(new Double(15.0), loaded.getObservedFrequency());
        assertEquals(40, loaded.getObservedNoOfAE().intValue());
        assertEquals(new Double(.10), loaded.getObservedSignificance());
        assertEquals(new Double(0.23), loaded.getpValue());
        assertEquals(new Double(2.3), loaded.getStandardDeviation());
        assertEquals(60, loaded.getTotalNoOfRegistrations().intValue());
        assertEquals(-3, loaded.getTreatmentAssignment().getId().intValue());
        assertEquals(3012, loaded.getCtcTerm().getId().intValue());
    }
	
	public void testSave() throws Exception {
		ObservedAdverseEventProfile unloaded = new ObservedAdverseEventProfile();
		unloaded.setCtcTerm(ctcTermDao.getById(3010));
		unloaded.setExpectedFrequency(10.0);
		unloaded.setGrade(Grade.NORMAL);
		unloaded.setNotificationStatus(NotificationStatus.NOTIFY);
		unloaded.setObservedFrequency(15.0);
		unloaded.setObservedSignificance(0.10);
		unloaded.setObservedNoOfAE(40);
		unloaded.setpValue(0.23);
		unloaded.setStandardDeviation(2.3);
		unloaded.setTotalNoOfRegistrations(60);
		unloaded.setTreatmentAssignment(treatmentAssignmentDao.getById(-4));
		getDao().save(unloaded);
		interruptSession();
		ObservedAdverseEventProfile loaded = getDao().getById(unloaded.getId());
        assertNotNull(loaded);
        assertEquals(new Double(10.0), loaded.getExpectedFrequency());
        assertEquals(Grade.NORMAL, loaded.getGrade());
        assertEquals(NotificationStatus.NOTIFY, loaded.getNotificationStatus());
        assertEquals(new Double(15.0), loaded.getObservedFrequency());
        assertEquals(40, loaded.getObservedNoOfAE().intValue());
        assertEquals(new Double(.10), loaded.getObservedSignificance());
        assertEquals(new Double(0.23), loaded.getpValue());
        assertEquals(new Double(2.3), loaded.getStandardDeviation());
        assertEquals(60, loaded.getTotalNoOfRegistrations().intValue());
        assertEquals(-4, loaded.getTreatmentAssignment().getId().intValue());
        assertEquals(3010, loaded.getCtcTerm().getId().intValue());
	}
	
	public void testGetByTACs() throws Exception{
		List<ObservedAdverseEventProfile> observedProfiles = getDao().getByTACs(new TreatmentAssignment[]{treatmentAssignmentDao.getById(-3)});
		assertEquals(1, observedProfiles.size());
		assertEquals(-1, observedProfiles.get(0).getId().intValue());
		
		observedProfiles = getDao().getByTACs(new TreatmentAssignment[]{treatmentAssignmentDao.getById(-3), treatmentAssignmentDao.getById(-4)});
		assertEquals(2, observedProfiles.size());
	}
}
