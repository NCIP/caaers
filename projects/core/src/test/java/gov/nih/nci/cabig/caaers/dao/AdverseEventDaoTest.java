package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertDayOfDate;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.Calendar;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT})
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {

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
        assertEquals("Wrong comments", "That was some big AE", copiedAdverseEvent.getComments());
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
        assertEquals("Wrong comments", "That was some big AE", loaded.getComments());


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
        System.out.println(aeList);
        assertNotNull(aeList);
        assertTrue(aeList.size() > 0);
        assertSame(2, aeList.size());
    }
    
    public void testUpdateRequiresReporting() throws Exception {
    	AdverseEvent loaded = getDao().getById(-3);
    	loaded.setRequiresReporting(true);
    	getDao().save(loaded);
    	interruptSession();
    	loaded = getDao().getById(-3);
    	assertTrue(loaded.getRequiresReporting());
    }

}
