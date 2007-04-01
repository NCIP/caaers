package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.DateUtils;
import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.attribution.StudyAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {
    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertEquals("Wrong grade", Grade.DEATH, loaded.getGrade());
        assertEquals("Wrong term", 3007, (int) loaded.getCtcTerm().getId());
        assertEquals("Wrong hosp.", Hospitalization.NONE, loaded.getHospitalization());
        assertEquals("Wrong expectedness", Boolean.TRUE, loaded.getExpected());
        assertEquals("Wrong comments", "That was some big AE", loaded.getComments());
    }

    public void testLoadStudyAgentAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(1, loaded.getStudyAgentAttributions().size());
        StudyAgentAttribution actual = loaded.getStudyAgentAttributions().get(0);
        assertEquals("Wrong attribution", -1, (int) actual.getId());
        assertEquals("Wrong study agent", -23, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.POSSIBLE, actual.getAttribution());
    }

    public void testLoadConMedAttributions() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertEquals(1, loaded.getConcomitantMedicationAttributions().size());
        ConcomitantMedicationAttribution actual = loaded.getConcomitantMedicationAttributions().get(0);
        assertEquals("Wrong attribution", -7, (int) actual.getId());
        assertEquals("Wrong con med", -77, (int) actual.getCause().getId());
        assertSame("Wrong reverse reference", loaded, actual.getAdverseEvent());
        assertEquals("Wrong attribution", Attribution.UNLIKELY, actual.getAttribution());
    }
}
