package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ReportVersionDTOQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO;

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

    public void testSearchReportVersionDTO() {
        ReportVersionDTOQuery q = new ReportVersionDTOQuery();
    	List<ReportVersionDTO> l = rsDao.searchDTO(q);
        interruptSession();
        System.out.println(l.size());
        System.out.println(l.get(0).getRv().getId());
        System.out.println("ReportName=" + l.get(0).getReportName());
        System.out.println("FirstName=" + l.get(0).getSubjectFirstName());
        System.out.println("LastName=" + l.get(0).getSubjectLastName());
        System.out.println("Primary ID =" + l.get(0).getSubjectPrimaryIdentifier());
        System.out.println("Study Short Title=" + l.get(0).getStudyShortTitle());
    }
}
