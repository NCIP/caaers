/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportVersionRepositoryTest extends AbstractTestCase{
    ReportVersion rv1, rv2, rv3;
    List<ReportVersion> rvList;
    ReportVersionRepository repo;
    ReportVersionDao dao;
	public void setUp() throws Exception{
		super.setUp();
		repo = new ReportVersionRepository();
        dao = registerDaoMockFor(ReportVersionDao.class);
        repo.setReportVersionDao(dao);
        rv1 = Fixtures.createReportVersion();
        rv2 = Fixtures.createReportVersion();
        rv3 = Fixtures.createReportVersion();
        rvList = new ArrayList<ReportVersion>();
        rvList.add(rv1);
        rvList.add(rv2);
        rvList.add(rv3);
        rv3.setAmendedOn(new Date());
        rv3.setSubmittedOn(new Date());

        rv1.setSubmittedOn(new Date());
        rv2.setSubmittedOn(new Date());
        rv3.setSubmittedOn(new Date());

        long currTime = System.currentTimeMillis();
        long sixMinutesBack = currTime - (6 * 60 * 60 * 1000);
        rv1.getSubmittedOn().setTime(sixMinutesBack);
        rv3.getSubmittedOn().setTime(sixMinutesBack);
	}
	
	public void testUpdateInProcessReports() {
        EasyMock.expect(dao.getAllInProcessReports()).andReturn(rvList);
        dao.save(rv1);
        dao.save(rv3);
        replayMocks();
		repo.updateInProcessReports();
        assertEquals(rv1.getReportStatus(), ReportStatus.FAILED);
        assertEquals(rv3.getReportStatus(), ReportStatus.FAILED);
        assertEquals(rv2.getReportStatus() , ReportStatus.PENDING);
        verifyMocks();
	}
    
    public void testGetAllSubmittedReportsInLastGivenNumberOfDays(){
          EasyMock.expect(dao.getAllSubmittedReportsInLastGivenNumberOfDays(5)).andReturn(rvList);
        replayMocks();
          repo.getAllSubmittedReportsInLastGivenNumberOfDays(5);
        verifyMocks();
    }

}
