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

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {
    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertDayOfDate("Wrong day for AE time", 2007, Calendar.APRIL, 3, loaded.getDetectionDate());
        assertTimeOfDate("Wrong time for AE time", 21, 43, 56, 987, loaded.getDetectionDate());
        assertEquals("Wrong grade", Grade.DEATH, loaded.getGrade());
        assertEquals("Wrong term", 3007, (int) loaded.getCtcTerm().getId());
        assertEquals("Wrong hosp.", Hospitalization.NONE, loaded.getHospitalization());
        assertEquals("Wrong expectedness", Boolean.TRUE, loaded.getExpected());
        assertEquals("Wrong comments", "That was some big AE", loaded.getComments());
    }
}
