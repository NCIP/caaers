package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;


/*
* @author Ion C. Olaru
*
* */
public class ExpeditedAdverseEventReportRepository {

    private ExpeditedAdverseEventReportDao aeReportDao;

    public ExpeditedAdverseEventReportDao getAeReportDao() {
        return aeReportDao;
    }

    public void setAeReportDao(ExpeditedAdverseEventReportDao aeReportDao) {
        this.aeReportDao = aeReportDao;
    }
}