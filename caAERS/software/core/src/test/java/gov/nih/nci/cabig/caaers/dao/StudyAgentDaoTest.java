package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

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
}
