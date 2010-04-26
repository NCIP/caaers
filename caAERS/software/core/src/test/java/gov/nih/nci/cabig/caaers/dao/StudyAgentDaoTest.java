package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_STUDY })
public class StudyAgentDaoTest extends DaoTestCase<StudyAgentDao> {
    public void testGetById() throws Exception {
        StudyAgent loaded = getDao().getById(-11);
        assertNotNull("Not found", loaded);
        assertEquals("Wrong study", -2, (int) loaded.getStudy().getId());
        assertEquals("Wrong agent", -3002, (int) loaded.getAgent().getId());
    }

    public void testGetByExistingAgentID() {
        List<StudyAgent> l = getDao().getByAgentID(-3002);
        assertEquals(1, l.size());
        assertEquals(-2, l.get(0).getStudy().getId().intValue());
        assertEquals("Zeppo", l.get(0).getStudy().getShortTitle());
    }

    public void testGetByNonExistingAgentID() {
        List<StudyAgent> l = getDao().getByAgentID(-3003);
        assertEquals(0, l.size());
    }
}
