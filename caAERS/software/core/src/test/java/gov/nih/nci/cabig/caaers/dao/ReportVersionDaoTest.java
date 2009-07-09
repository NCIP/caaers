package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;

public class ReportVersionDaoTest extends DaoTestCase<ReportVersionDao> {
    private ReportVersionDao rsDao;



    @Override
    protected void setUp() throws Exception {
        super.setUp();
        rsDao = getDao();

    }
    
    public void testByDays() {
    	rsDao.getAllSubmittedReportsInLastGivenNumberOfDays(30);
    }
}
