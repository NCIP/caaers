/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertDayOfDate;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventQuery;
import gov.nih.nci.cabig.caaers.dao.query.SafetySignalingQuery;
import gov.nih.nci.cabig.caaers.domain.AbstractStudyInterventionExpectedAE;
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
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.Calendar;
import java.util.List;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT})
public class AdverseEventDaoTest extends CaaersDbNoSecurityTestCase {
	
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");
		participantDao = (ParticipantDao) getDeployedApplicationContext().getBean("participantDao");
		adverseEventReportingPeriodDao = (AdverseEventReportingPeriodDao) getDeployedApplicationContext().getBean("adverseEventReportingPeriodDao");
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
        assertSame(4, aeList.size());
    }
    
    public void testUpdateRequiresReporting() throws Exception {
    	AdverseEvent loaded = getDao().getById(-3);
    	loaded.setRequiresReporting(true);
    	getDao().save(loaded);
    	interruptSession();
    	loaded = getDao().getById(-3);
    	assertTrue(loaded.getRequiresReporting());
    }
    
    public void testGetByAdverseEventReportingPeriod() throws Exception{
    	AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(1001);
    	List<AdverseEvent> aeList = getDao().getByAdverseEventReportingPeriod(reportingPeriod, reportingPeriod.getStudy(), reportingPeriod.getParticipant() );
    	assertEquals("Incorrect number of adverseEvents fetched by getByAdverseEventReportingPeriod", 1, aeList.size());
    	assertEquals("Incorrect AE fetched by getByAdverseEventReportingPeriod", new Integer(-3), aeList.get(0).getId());
    }
    
    public void testGetByAdverseEventReportingPeriodWithAECorrectGrade() throws Exception{
    	AdverseEventReportingPeriod reportingPeriod  = adverseEventReportingPeriodDao.getById(1001);
    	AdverseEvent ae = new AdverseEvent();
    	ae.setSolicited(null);
    	ae.setGrade(Grade.DEATH);
    	List<AdverseEvent> aeList = getDao().getByAdverseEventReportingPeriod(reportingPeriod, reportingPeriod.getStudy(), reportingPeriod.getParticipant(), ae);
    	assertEquals("Incorrect number of adverseEvents fetched by getByAdverseEventReportingPeriod", 1, aeList.size());
    	assertEquals("Incorrect AE fetched by getByAdverseEventReportingPeriod", new Integer(-3), aeList.get(0).getId());
    }
    
    public void testGetByAdverseEventReportingPeriodWithAEIncorrectGrade() throws Exception{
    	AdverseEventReportingPeriod reportingPeriod  = adverseEventReportingPeriodDao.getById(1001);
    	AdverseEvent ae = new AdverseEvent();
    	ae.setSolicited(null);
    	ae.setGrade(Grade.LIFE_THREATENING);
    	List<AdverseEvent> aeList = getDao().getByAdverseEventReportingPeriod(reportingPeriod, reportingPeriod.getStudy(), reportingPeriod.getParticipant(), ae);
    	assertEquals("Incorrect number of adverseEvents fetched by getByAdverseEventReportingPeriod", 0, aeList.size());
    }
//    public void testSearchAdverseEvents(){
//    	Map<String, Object> props = new HashMap<String, Object>();
//    	props.put("studyIdentifier", "13js77");
//    	props.put("studyShortTitle", "Short");
//    	props.put("ctcCategory", "auditory/ear");
//    	props.put("ctcTerm", "Tinnitus");
//    	props.put("grade", "5");
//    	props.put("participantIdentifier", "11112");
//    	props.put("participantFirstName", "Dilbert");
//    	props.put("participantLastName", "Scott");
//    	props.put("participantEthnicity", "ethnicity");
//    	props.put("participantGender", "Female");
//    	props.put("participantDateOfBirth", "01/02/2006");
//    	
//    	try {
//    		List<AdverseEvent> aes = 	getDao().searchAdverseEvents(props);
//    		assertNotNull(aes);
//    		assertEquals(1, aes.size());
//    		assertEquals(new Integer(-2), aes.get(0).getId());
//    		
//    		
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("should not throw exception");
//		}
//    }
//    
//    public void testSearchAdverseEventsHavingOtherMeddra(){
//    	Map<String, Object> props = new HashMap<String, Object>();
//    	props.put("studyIdentifier", "13js77");
//    	props.put("studyShortTitle", "Short");
//    	props.put("grade", "5");
//    	props.put("participantIdentifier", "11112");
//    	props.put("participantFirstName", "Dilbert");
//    	props.put("participantLastName", "Scott");
//    	props.put("participantEthnicity", "ethnicity");
//    	props.put("participantGender", "Female");
//    	props.put("participantDateOfBirth", "01/02/2006");
//    	props.put("ctcMeddra", "2");
//    	
//    	try {
//    		List<AdverseEvent> aes = 	getDao().searchAdverseEvents(props);
//    		assertNotNull(aes);
//    		assertEquals(1, aes.size());
//    		assertEquals(new Integer(-5), aes.get(0).getId());
//    		
//    		
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("should not throw exception");
//		}
//    }
    public void testGetByStudy(){
    	Study s = studyDao.getById(-2);
    	
    	List<AdverseEvent> aes = getDao().getByStudy(s);
    	assertEquals(4, aes.size());
    	
    }
    
    public void testGetByStudyAndAE(){
    	Study s = studyDao.getById(-2);
        AdverseEvent ae = getDao().getById(-2);
        List<AdverseEvent> aes = getDao().getByStudy(s, ae);
    	assertEquals(3, aes.size());
    }
    
    public void testGetByParticipant(){
    	Participant p = participantDao.getById(-4);
    	List<AdverseEvent> aes = getDao().getByParticipant(p);
    	assertEquals(3, aes.size());
    }
    
    public void testGetByParticipantHavingNoAssingment(){
    	Participant p = participantDao.getById(-88);
    	List<AdverseEvent> aes = getDao().getByParticipant(p);
    	assertEquals(0, aes.size());
    }
    
    public void testGetByParticipantAndAE(){
    	Participant p = participantDao.getById(-4);
    	AdverseEvent ae = getDao().getById(-2);
    	List<AdverseEvent> aes = getDao().getByParticipant(p, ae);
    	assertEquals(3, aes.size());
    }
    public void testGetByStudyParticipant() throws Exception {
    	List<AdverseEvent> loaded = getDao().getByStudyParticipant(this.studyDao.getById(-2), this.participantDao.getById(-4));
    	assertTrue(loaded.size()==4);
    	
    }

    public void testGetByStudyParticipantAndAE() throws Exception {
    	AdverseEvent adverseEvent = new AdverseEvent();
    	adverseEvent.setHospitalization(Hospitalization.NONE);
    	adverseEvent.setSolicited(true);
		TimeValue tv = new TimeValue();
		tv.setType(1);
    	adverseEvent.setEventApproximateTime(tv);
    	List<AdverseEvent> loaded = getDao().getByStudyParticipant(this.studyDao.getById(-2), this.participantDao.getById(-4),adverseEvent);
    	assertEquals(4, loaded.size());
    	adverseEvent = new AdverseEvent();
    	adverseEvent.setHospitalization(Hospitalization.NO);
    	adverseEvent.setSolicited(true);
    	adverseEvent.setEventApproximateTime(tv);
    	loaded = getDao().getByStudyParticipant(this.studyDao.getById(-2), this.participantDao.getById(-4),adverseEvent);
    	assertEquals(0, loaded.size());


    }
    
//    public void testexecuteHQL() throws Exception {
//    	String hql = "select exp, ae, tac.id from AdverseEvent ae " +
//    			"join ae.reportingPeriod.assignment.studySite.study study " +
//    			"join ae.reportingPeriod.treatmentAssignment tac " +
//    			"join tac.abstractStudyInterventionExpectedAEs exp " +
//    			"join ae.adverseEventTerm aeTerm " +
//    			//"where ae.reportingPeriod.assignment.studySite.study=:study";
//    			"where exp.term=aeTerm.term and study=:study";
//    	List returnData = getDao().executeHQL(hql, "study", studyDao.getById(-2));
//    	assertEquals(2, returnData.size());
//    }
    
    public void testGetAllAEsForSafetySignaling() throws Exception {
    	List returnData = getDao().getAllAEsForSafetySignaling(studyDao.getById(-2));
    	assertEquals(2, returnData.size());
    	
    	int index1 =0;
    	int index2 =0;
    	for(int i=0 ; i<returnData.size() ; i++){
    		if (((AdverseEvent)((Object[])returnData.get(1))[0]).getId().equals(new Integer(-5))){
    			index1 = i;
    		}else if (((AdverseEvent)((Object[])returnData.get(1))[0]).getId().equals(new Integer(-2))){
    			index2 = i;
    		}
    	}
    	TreatmentAssignment tac = (TreatmentAssignment)((Object[])returnData.get(index1))[2];
    	AdverseEvent loadedAE = (AdverseEvent)((Object[])returnData.get(index1))[0];
    	AbstractStudyInterventionExpectedAE loadedASAEL = (AbstractStudyInterventionExpectedAE)((Object[])returnData.get(index1))[1];
    	StudyParticipantAssignment spa = (StudyParticipantAssignment)((Object[])returnData.get(index1))[3];
    	assertEquals(new Integer(1002), tac.getId());
    	assertEquals(new Integer(-5), loadedAE.getId());
    	assertEquals(new Integer(3007), loadedAE.getAdverseEventTerm().getTerm().getId());
    	assertEquals(new Integer(3007), loadedASAEL.getTerm().getId());
    	assertEquals(new Integer(-6), spa.getId());
    	
    	tac = (TreatmentAssignment)((Object[])returnData.get(index2))[2];
    	loadedAE = (AdverseEvent)((Object[])returnData.get(index2))[0];
    	loadedASAEL = (AbstractStudyInterventionExpectedAE)((Object[])returnData.get(index2))[1];
    	spa = (StudyParticipantAssignment)((Object[])returnData.get(index2))[3];
    	assertEquals(new Integer(1002), tac.getId());
    	assertEquals(new Integer(-2), loadedAE.getId());
    	assertEquals(new Integer(3012), loadedAE.getAdverseEventTerm().getTerm().getId());
    	assertEquals(new Integer(3012), loadedASAEL.getTerm().getId());
    	assertEquals(new Integer(-6), spa.getId());
    	
    }

    public AdverseEventDao getDao(){
    	return (AdverseEventDao) getDeployedApplicationContext().getBean("adverseEventDao");
    }
    
}
