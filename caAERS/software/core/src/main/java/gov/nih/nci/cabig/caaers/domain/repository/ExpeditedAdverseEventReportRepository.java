package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.query.ExpeditedAdverseEventReportQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/*
* @author Ion C. Olaru
*
* */
public class ExpeditedAdverseEventReportRepository {

    private ExpeditedAdverseEventReportDao aeReportDao;

    public List<ExpeditedAdverseEventReport> getPastDue(String loginId) {
        ExpeditedAdverseEventReportQuery q = new ExpeditedAdverseEventReportQuery();
        q.joinReports();
        q.joinReportVersions();

        if (loginId != null) q.setParameter("loginId", loginId);
        // logic to get the Latest ReportVersion

        return aeReportDao.getByQuery(q);
    }

    public List<ExpeditedAdverseEventReport> getPastDue() {
        return getPastDue(null);
    }

    public ExpeditedAdverseEventReportDao getAeReportDao() {
        return aeReportDao;
    }

    public void setAeReportDao(ExpeditedAdverseEventReportDao aeReportDao) {
        this.aeReportDao = aeReportDao;
    }
}