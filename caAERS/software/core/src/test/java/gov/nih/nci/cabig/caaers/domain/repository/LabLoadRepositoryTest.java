package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.LabLoadDao;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class LabLoadRepositoryTest extends AbstractTestCase {
    LabLoadRepository repo;
    LabLoadDao labLoadDao;
    LabLoad load;
    public void setUp() throws Exception {
        repo = new LabLoadRepository();
        labLoadDao = registerDaoMockFor(LabLoadDao.class);
        repo.setLabLoadDao(labLoadDao);
        load = new LabLoad();
        switchToSuperUser();
    }

    public void testSave() throws Exception {
        labLoadDao.save(load);
        replayMocks();
        repo.save(load);
        verifyMocks();
    }
}
