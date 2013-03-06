/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.query.ReportVersionDTOQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


 
/*
* @author Ion C. Olaru
* 
* */
/**
 * The Class ReportVersionRepository.
 */
public class ReportVersionRepository {

    /** The report version dao. */
    private ReportVersionDao reportVersionDao;

    /**
     * Update in process reports.
     */
    @Transactional(readOnly = false)
    public void updateInProcessReports() {
        List<ReportVersion> rvs = reportVersionDao.getAllInProcessReports();
        NowFactory nowFactory = new NowFactory();
        for (ReportVersion rv : rvs) {
            Date submittedOrAmendedDate = null;
            if (rv.getAmendedOn() != null) {
                submittedOrAmendedDate = rv.getAmendedOn();
            } else if (rv.getSubmittedOn() != null) {
                submittedOrAmendedDate = rv.getSubmittedOn();
            }
            if (submittedOrAmendedDate != null) {
                long timeDiff = (nowFactory.getNowTimestamp().getTime() - rv.getSubmittedOn().getTime()) / 60000;
                if (timeDiff > 5) {
                    rv.setReportStatus(ReportStatus.FAILED);
                    rv.setSubmissionMessage("Submission failed for unknown reason , Please resubmit");
                    reportVersionDao.save(rv);
                }

            }
        }
    }

    /**
     * Gets the past due.
     *
     * @return the past due
     */
    public List<ReportVersion> getPastDue() {
        ReportVersionDTOQuery q = new ReportVersionDTOQuery();
        q.andWhere("rv.dueOn < :tomorrow");
        q.andWhere("rv.submittedOn is null");
        q.orderBy("rv.dueOn");

//        q.filterByReportStatus(ReportStatus.COMPLETED);

        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date d = cal.getTime();
        q.setParameter("tomorrow", d);
        List<ReportVersion> l = reportVersionDao.search(q);
        return l;
    }

    /**
     * Gets the report activity.
     *
     * @return the report activity
     */
    public List<ReportVersion> getReportActivity() {
        ReportVersionDTOQuery q = new ReportVersionDTOQuery();
        q.orderBy("coalesce(rv.dueOn, rv.submittedOn,rv.withdrawnOn)");
        
/*
        q.andWhere("rv.dueOn >= :dueOn");
        q.orWhere("rv.reportStatus = :status1");
        q.orWhere("rv.reportStatus = :status2");
        q.orWhere("rv.reportStatus = :status3");
        q.orWhere("rv.reportStatus = :status4");
        q.orWhere("rv.reportStatus = :status5");
        q.setParameter("status1", ReportStatus.INPROCESS);
        q.setParameter("status2", ReportStatus.PENDING);
        q.setParameter("status3", ReportStatus.WITHDRAWN);
        q.setParameter("status4", ReportStatus.AMENDED);
        q.setParameter("status5", ReportStatus.COMPLETED);
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        q.setParameter("dueOn", today);
*/
        List<ReportVersion> l = reportVersionDao.search(q);
        return l;
    }

    /**
     * Gets the all submitted reports in last given number of days.
     *
     * @param days the days
     * @return the all submitted reports in last given number of days
     */
    public List<ReportVersion> getAllSubmittedReportsInLastGivenNumberOfDays(int days) {
        return reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(days);
    }

    /**
     * Sets the report version dao.
     *
     * @param reportVersionDao the new report version dao
     */
    public void setReportVersionDao(ReportVersionDao reportVersionDao) {
        this.reportVersionDao = reportVersionDao;
    }

}
