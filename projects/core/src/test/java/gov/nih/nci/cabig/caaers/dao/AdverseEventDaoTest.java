package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertDayOfDate;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;

import java.util.Calendar;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT })
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {
    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertEquals("Wrong grade", Grade.DEATH, loaded.getGrade());
        assertDayOfDate("Wrong start date", 2007, Calendar.SEPTEMBER, 12,loaded.getStartDate());
        assertDayOfDate("Wrong end date", 2007, Calendar.SEPTEMBER, 12,loaded.getEndDate());
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

        assertEquals("Wrong treatment attribution 0", -27,
            (int) loaded.getCourseAgentAttributions().get(0).getCause().getId());
    }

    public void testLoadConMedAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(2, loaded.getConcomitantMedicationAttributions().size());
        ConcomitantMedicationAttribution actual = loaded.getConcomitantMedicationAttributions().get(0);
        assertEquals("Wrong attribution", -7, (int) actual.getId());
        assertEquals("Wrong con med", -77, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNLIKELY, actual.getAttribution());

        assertEquals("Wrong con med attrib 1", -78,
            (int) loaded.getConcomitantMedicationAttributions().get(1).getCause().getId());
    }

    public void testLoadOtherCauseAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(2, loaded.getOtherCauseAttributions().size());
        OtherCauseAttribution actual = loaded.getOtherCauseAttributions().get(1);
        assertEquals("Wrong attribution", -11, (int) actual.getId());
        assertEquals("Wrong other cause", -71, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNRELATED, actual.getAttribution());

        assertEquals("Wrong other cause attribution 0", -72,
            (int) loaded.getOtherCauseAttributions().get(0).getCause().getId());
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
        assertEquals("This term is not Ctc",true, loaded.getAdverseEventTerm() instanceof AdverseEventCtcTerm);
        assertEquals("This term is not CtcTerm",true, loaded.getAdverseEventTerm().getTerm() instanceof CtcTerm);
        assertEquals("Wrong Ctc Id",3012, (int) loaded.getAdverseEventTerm().getTerm().getId());
    }

    public void testLoadMeddraBasedTerm() throws Exception {
        AdverseEvent loaded = getDao().getById(-3);
        assertNotNull("Meddra Term is null", loaded.getAdverseEventTerm());
		assertEquals("This term is not MedDRA",true, loaded.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm);
		assertEquals("This term is not LowLevelTerm",true, loaded.getAdverseEventTerm().getTerm() instanceof LowLevelTerm);
		assertEquals("Wrong Meddra Id",-11, (int) loaded.getAdverseEventTerm().getTerm().getId());
    }

}
