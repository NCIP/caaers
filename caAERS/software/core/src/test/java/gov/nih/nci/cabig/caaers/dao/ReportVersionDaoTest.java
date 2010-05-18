package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;

import java.util.List;

/*
*
* @author Ion C. Olaru
* 
* */
public class ReportVersionDaoTest extends DaoTestCase<ReportVersionDao> {
    private ReportVersionDao rsDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        rsDao = getDao();
    }
    
    public void testByDays() {
    	List l = rsDao.getAllSubmittedReportsInLastGivenNumberOfDays(30);
        assertEquals(0, l.size());
    }
}
