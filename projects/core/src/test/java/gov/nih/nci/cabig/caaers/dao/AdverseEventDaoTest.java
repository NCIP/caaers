package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertDayOfDate;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT})
public class AdverseEventDaoTest extends CaaersDbNoSecurityTestCase {
	
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");
		participantDao = (ParticipantDao) getDeployedApplicationContext().getBean("participantDao");
	}

    public void testCopyBasicProperties() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertNotNull("AE not found", copiedAdverseEvent);
        assertEquals("Wrong grade", Grade.DEATH, copiedAdverseEvent.getGrade());
        assertDayOfDate("Wrong start date", 2007, Calendar.SEPTEMBER, 12, copiedAdverseEvent.getStartDate());
        assertDayOfDate("Wrong end date", 2007, Calendar.SEPTEMBER, 12, copiedAdverseEvent.getEndDate());
        assertEquals("Wrong term", 3012, (int) copiedAdverseEvent.getAdverseEventCtcTerm().getCtcTerm().getId());
        assertEquals("Wrong hosp.", Hospitalization.NONE, copiedAdverseEvent.getHospitalization());
        assertEquals("Wrong expectedness", Boolean.TRUE, copiedAdverseEvent.getExpected());
        assertEquals("Wrong attrib summary", Attribution.POSSIBLE, copiedAdverseEvent.getAttributionSummary());
        assertEquals("Wrong comments", "That was some other big AE", copiedAdverseEvent.getComments());
        assertTrue("Wrong time zone", copiedAdverseEvent.getEventApproximateTime().isPM());
        assertEquals("Wrong time", Integer.valueOf(3), copiedAdverseEvent.getEventApproximateTime().getMinute());
        assertEquals("Wrong time", Integer.valueOf(12), copiedAdverseEvent.getEventApproximateTime().getHour());


    }

    private AdverseEvent copyAdverseEvent() {
        AdverseEvent loaded = getDao().getById(-2);
        AdverseEvent copiedAdverseEvent = loaded.copy();
        getDao().save(copiedAdverseEvent);
        interruptSession();
        return copiedAdverseEvent;
    }

    public void testCopyOtherCauseAttribution() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertEquals(2, copiedAdverseEvent.getOtherCauseAttributions().size());
        OtherCauseAttribution otherCauseAttribution = copiedAdverseEvent.getOtherCauseAttributions().get(1);
        assertEquals("Wrong attribution", Attribution.UNRELATED, otherCauseAttribution.getAttribution());
        assertEquals("must not copy cause", -71, (int) otherCauseAttribution.getCause().getId());
        assertSame("Wrong reverse reference", copiedAdverseEvent, otherCauseAttribution.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNRELATED, otherCauseAttribution.getAttribution());

        assertEquals("must not copy causes", -72, (int) copiedAdverseEvent
                .getOtherCauseAttributions().get(0).getCause().getId());

    }

    public void testCopyAdverseEventReportingPeriod() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        AdverseEventReportingPeriod reportingPeriod = copiedAdverseEvent.getReportingPeriod();
        assertEquals("must not copy the reporing period", 1002, (int) reportingPeriod.getId());


    }

    public void testCopyCtcTerm() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertNotNull("Ctc Term is null", copiedAdverseEvent.getAdverseEventTerm());
        assertEquals("This term is not Ctc", true,
                copiedAdverseEvent.getAdverseEventTerm() instanceof AdverseEventCtcTerm);
        assertEquals("This term is not CtcTerm", true,
                copiedAdverseEvent.getAdverseEventTerm().getTerm() instanceof CtcTerm);
        assertSame("Wrong reverse reference", copiedAdverseEvent, copiedAdverseEvent.getAdverseEventTerm().getAdverseEvent());

        assertEquals("must not copy ctc term", 3012, (int) copiedAdverseEvent.getAdverseEventTerm().getTerm().getId());


    }

    public void testCopyDeviceAttribution() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertEquals(1, copiedAdverseEvent.getDeviceAttributions().size());
        DeviceAttribution deviceAttribution = copiedAdverseEvent.getDeviceAttributions().get(0);
        assertNotNull("Wrong attribution", (int) deviceAttribution.getId());
        assertEquals("must not copy cause", -22, (int) deviceAttribution.getCause().getId());
        assertSame("Wrong reverse reference", copiedAdverseEvent, deviceAttribution.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNRELATED, deviceAttribution.getAttribution());


    }

    public void testCopyRadiationAttribution() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertEquals(1, copiedAdverseEvent.getRadiationAttributions().size());
        RadiationAttribution radiationAttribution = copiedAdverseEvent.getRadiationAttributions().get(0);
        assertNotNull("Wrong attribution", radiationAttribution.getId());
        assertEquals("must not copy cause", -37, (int) radiationAttribution.getCause().getId());
        assertSame("Wrong reverse reference", copiedAdverseEvent, radiationAttribution.getAdverseEvent());
        assertEquals("must not copy cause", Attribution.PROBABLE, radiationAttribution.getAttribution());

    }

    public void testCopySurgeryAttribution
            () throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertEquals(1, copiedAdverseEvent.getSurgeryAttributions().size());
        SurgeryAttribution surgeryAttribution = copiedAdverseEvent.getSurgeryAttributions().get(0);
        assertNotNull("Wrong attribution", (int) surgeryAttribution.getId());
        assertEquals("must not copy cause", -34, (int) surgeryAttribution.getCause().getId());
        assertSame("Wrong reverse reference", copiedAdverseEvent, surgeryAttribution.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.POSSIBLE, surgeryAttribution.getAttribution());


    }

    public void testCopyCourseAgentAttribution() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        CourseAgentAttribution actual = copiedAdverseEvent.getCourseAgentAttributions().get(1);
        assertNotNull("Wrong attribution", (int) actual.getId());
        assertEquals("must not copy", -23, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", copiedAdverseEvent, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.POSSIBLE, actual.getAttribution());

        assertNotNull("must not copy cause", (int) copiedAdverseEvent
                .getCourseAgentAttributions().get(0).getCause().getId());

    }

    public void testCopyConcomitantMedicationAttribution() throws Exception {
        AdverseEvent copiedAdverseEvent = copyAdverseEvent();


        assertEquals(2, copiedAdverseEvent.getConcomitantMedicationAttributions().size());
        ConcomitantMedicationAttribution concomitantMedicationAttribution = copiedAdverseEvent.getConcomitantMedicationAttributions()
                .get(0);
        assertEquals("must not copy cause", -77, (int) concomitantMedicationAttribution.getCause().getId());
        assertSame("Wrong reverse reference", copiedAdverseEvent, concomitantMedicationAttribution.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNLIKELY, concomitantMedicationAttribution.getAttribution());


    }

    public void testMedraBasedTerm() throws Exception {
        AdverseEvent loaded = getDao().getById(-3);

        AdverseEvent copiedAdverseEvent = loaded.copy();

        getDao().save(copiedAdverseEvent);
        interruptSession();


        assertNotNull("Meddra Term is null", copiedAdverseEvent.getAdverseEventTerm());
        assertEquals("This term is not MedDRA", true,
                copiedAdverseEvent.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm);
        assertSame("Wrong reverse reference", copiedAdverseEvent, copiedAdverseEvent.getAdverseEventTerm().getAdverseEvent());

        assertEquals("This term is not LowLevelTerm", true,
                copiedAdverseEvent.getAdverseEventTerm().getTerm() instanceof LowLevelTerm);
        assertEquals("must not copy medra", -11, (int) copiedAdverseEvent.getAdverseEventTerm().getTerm().getId());


    }

    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertEquals("Wrong grade", Grade.DEATH, loaded.getGrade());
        assertDayOfDate("Wrong start date", 2007, Calendar.SEPTEMBER, 12, loaded.getStartDate());
        assertDayOfDate("Wrong end date", 2007, Calendar.SEPTEMBER, 12, loaded.getEndDate());
        assertEquals("Wrong term", 3012, (int) loaded.getAdverseEventCtcTerm().getCtcTerm().getId());
        assertEquals("Wrong hosp.", Hospitalization.NONE, loaded.getHospitalization());
        assertEquals("Wrong expectedness", Boolean.TRUE, loaded.getExpected());
        assertEquals("Wrong attrib summary", Attribution.POSSIBLE, loaded.getAttributionSummary());
        assertEquals("Wrong comments", "That was some other big AE", loaded.getComments());


    }

    public void testLoadCourseAgentAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(2, loaded.getCourseAgentAttributions().size());
        CourseAgentAttribution actual = loaded.getCourseAgentAttributions().get(1);
        assertEquals("Wrong attribution", -1, (int) actual.getId());
        assertEquals("Wrong agent", -23, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.POSSIBLE, actual.getAttribution());

        assertEquals("Wrong treatment attribution 0", -27, (int) loaded
                .getCourseAgentAttributions().get(0).getCause().getId());
    }

    public void testLoadConMedAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(2, loaded.getConcomitantMedicationAttributions().size());
        ConcomitantMedicationAttribution actual = loaded.getConcomitantMedicationAttributions()
                .get(0);
        assertEquals("Wrong attribution", -7, (int) actual.getId());
        assertEquals("Wrong con med", -77, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNLIKELY, actual.getAttribution());

        assertEquals("Wrong con med attrib 1", -78, (int) loaded
                .getConcomitantMedicationAttributions().get(1).getCause().getId());
    }

    public void testLoadAdverseEventReportingPeriod() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        AdverseEventReportingPeriod reportingPeriod = loaded.getReportingPeriod();
        assertEquals("Wrong ReportingPeriod", 1002, (int) reportingPeriod.getId());
    }

    public void testLoadOtherCauseAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(2, loaded.getOtherCauseAttributions().size());
        OtherCauseAttribution actual = loaded.getOtherCauseAttributions().get(1);
        assertEquals("Wrong attribution", -11, (int) actual.getId());
        assertEquals("Wrong other cause", -71, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNRELATED, actual.getAttribution());

        assertEquals("Wrong other cause attribution 0", -72, (int) loaded
                .getOtherCauseAttributions().get(0).getCause().getId());
    }

    public void testLoadDiseaseAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(1, loaded.getDiseaseAttributions().size());
        DiseaseAttribution actual = loaded.getDiseaseAttributions().get(0);
        assertEquals("Wrong attribution", -17, (int) actual.getId());
        assertEquals("Wrong disease history", -88, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.DEFINITE, actual.getAttribution());
    }

    public void testLoadSurgeryAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(1, loaded.getSurgeryAttributions().size());
        SurgeryAttribution actual = loaded.getSurgeryAttributions().get(0);
        assertEquals("Wrong attribution", -18, (int) actual.getId());
        assertEquals("Wrong intervention", -34, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.POSSIBLE, actual.getAttribution());
    }

    public void testLoadRadiationAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(1, loaded.getRadiationAttributions().size());
        RadiationAttribution actual = loaded.getRadiationAttributions().get(0);
        assertEquals("Wrong attribution", -19, (int) actual.getId());
        assertEquals("Wrong intervention", -37, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.PROBABLE, actual.getAttribution());
    }

    public void testLoadDeviceAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(1, loaded.getDeviceAttributions().size());
        DeviceAttribution actual = loaded.getDeviceAttributions().get(0);
        assertEquals("Wrong attribution", -20, (int) actual.getId());
        assertEquals("Wrong device", -22, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNRELATED, actual.getAttribution());
    }

    public void testLoadCtcBasedTerm() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("Ctc Term is null", loaded.getAdverseEventTerm());
        assertEquals("This term is not Ctc", true, loaded.getAdverseEventTerm() instanceof AdverseEventCtcTerm);
        assertEquals("This term is not CtcTerm", true, loaded.getAdverseEventTerm().getTerm() instanceof CtcTerm);
        assertEquals("Wrong Ctc Id", 3012, (int) loaded.getAdverseEventTerm().getTerm().getId());
    }

    public void testLoadMeddraBasedTerm() throws Exception {
        AdverseEvent loaded = getDao().getById(-3);
        assertNotNull("Meddra Term is null", loaded.getAdverseEventTerm());
        assertEquals("This term is not MedDRA", true, loaded.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm);
        assertEquals("This term is not LowLevelTerm", true, loaded.getAdverseEventTerm().getTerm() instanceof LowLevelTerm);
        assertEquals("Wrong Meddra Id", -11, (int) loaded.getAdverseEventTerm().getTerm().getId());
    }

    public void testFindAll() throws Exception {

        List aeList = getDao().findAll(null);
        assertNotNull(aeList);
        assertTrue(aeList.size() > 0);
        assertSame(3, aeList.size());
    }
    
    public void testUpdateRequiresReporting() throws Exception {
    	AdverseEvent loaded = getDao().getById(-3);
    	loaded.setRequiresReporting(true);
    	getDao().save(loaded);
    	interruptSession();
    	loaded = getDao().getById(-3);
    	assertTrue(loaded.getRequiresReporting());
    }
    
    public void testSearchAdverseEvents(){
    	Map<String, Object> props = new HashMap<String, Object>();
    	props.put("studyIdentifier", "13js77");
    	props.put("studyShortTitle", "Short");
    	props.put("ctcCategory", "auditory/ear");
    	props.put("ctcTerm", "Tinnitus");
    	props.put("grade", "5");
    	props.put("participantIdentifier", "11112");
    	props.put("participantFirstName", "Dilbert");
    	props.put("participantLastName", "Scott");
    	props.put("participantEthnicity", "ethnicity");
    	props.put("participantGender", "Female");
    	props.put("participantDateOfBirth", "01/02/2006");
    	
    	try {
    		List<AdverseEvent> aes = 	getDao().searchAdverseEvents(props);
    		assertNotNull(aes);
    		assertEquals(1, aes.size());
    		assertEquals(new Integer(-2), aes.get(0).getId());
    		
    		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("should not throw exception");
		}
    }
    
    public void testSearchAdverseEventsHavingOtherMeddra(){
    	Map<String, Object> props = new HashMap<String, Object>();
    	props.put("studyIdentifier", "13js77");
    	props.put("studyShortTitle", "Short");
    	props.put("grade", "5");
    	props.put("participantIdentifier", "11112");
    	props.put("participantFirstName", "Dilbert");
    	props.put("participantLastName", "Scott");
    	props.put("participantEthnicity", "ethnicity");
    	props.put("participantGender", "Female");
    	props.put("participantDateOfBirth", "01/02/2006");
    	props.put("ctcMeddra", "2");
    	
    	try {
    		List<AdverseEvent> aes = 	getDao().searchAdverseEvents(props);
    		assertNotNull(aes);
    		assertEquals(1, aes.size());
    		assertEquals(new Integer(-5), aes.get(0).getId());
    		
    		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("should not throw exception");
		}
    }
    
    public void testSearchAdverseEventsBasedOnParticipant(){
    	Map<String, Object> props = new HashMap<String, Object>();
    	props.put("participantIdentifier", "11112");
    	props.put("participantFirstName", "Dilbert");
    	props.put("participantLastName", "Scott");
    	props.put("participantEthnicity", "ethnicity");
    	props.put("participantGender", "Female");
    	props.put("participantDateOfBirth", "01/02/2006");
    	
    	try {
    		List<AdverseEvent> aes = 	getDao().searchAdverseEvents(props);
    		assertNotNull(aes);
    		assertEquals(2, aes.size());
    		assertEquals(new Integer(-5), aes.get(0).getId());
    		
    		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("should not throw exception");
		}
    }
    
    public void testGetByStudy(){
    	Study s = studyDao.getById(-2);
    	
    	List<AdverseEvent> aes = getDao().getByStudy(s);
    	assertEquals(3, aes.size());
    	
    }
    
    public void testGetByStudyAndAE(){
    	Study s = studyDao.getById(-2);
        AdverseEvent ae = getDao().getById(-2);
        List<AdverseEvent> aes = getDao().getByStudy(s, ae);
    	assertEquals(2, aes.size());
    }
    
    public void testGetByParticipant(){
    	Participant p = participantDao.getById(-4);
    	List<AdverseEvent> aes = getDao().getByParticipant(p);
    	assertEquals(3, aes.size());
    }
    
    public void testGetByParticipantHavingNoAssingment(){
    	Participant p = participantDao.getById(-3);
    	List<AdverseEvent> aes = getDao().getByParticipant(p);
    	assertEquals(0, aes.size());
    }
    
    public void testGetByParticipantAndAE(){
    	Participant p = participantDao.getById(-4);
    	AdverseEvent ae = getDao().getById(-2);
    	List<AdverseEvent> aes = getDao().getByParticipant(p, ae);
    	assertEquals(2, aes.size());
    }
    
    
    public AdverseEventDao getDao(){
    	return (AdverseEventDao) getDeployedApplicationContext().getBean("adverseEventDao");
    }
    
}
