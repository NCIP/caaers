package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.GENERATE_REPORT_FORM;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDeliveryDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDeliveryDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;

@CaaersUseCases( { GENERATE_REPORT_FORM })
public class ReportDeliveryDaoTest extends DaoTestCase<ReportDeliveryDao> {
    /**
     */
    public void testDomainClass() {
        log.debug("domainClass :" + getDao().domainClass().getName());
        assertEquals(ReportDelivery.class.getName(), getDao().domainClass().getName());
    }

    public void testSave() throws Exception {
        ReportDao rDao = (ReportDao) getApplicationContext().getBean("reportDao");
        ReportDeliveryDefinitionDao rdDao = (ReportDeliveryDefinitionDao) getApplicationContext()
                        .getBean("reportDeliveryDefinitionDao");
        ReportDelivery rd = new ReportDelivery();
        rd.setDeliveryStatus(DeliveryStatus.DELIVERED);
        rd.setEndPoint("biju.joseph@semanticbits.com");
        ReportDeliveryDefinition rdd = rdDao.getById(-222);
        rd.setReportDeliveryDefinition(rdd);
        Report r = rDao.getById(-223);
        r.addReportDelivery(rd);

        rd.setReport(r);
        getDao().save(rd);
        int id = rd.getId();
        interruptSession();
        ReportDelivery rd2 = getDao().getById(id);
        ReportDeliveryDefinition rdd2 = rd2.getReportDeliveryDefinition();
        Report r2 = rd2.getReport();
        assertEquals("end point should be equal", rd.getEndPoint(), rd2.getEndPoint());
        assertEquals("ReportDeliveryDef name should be same", rdd2.getEntityName(), "RDD-222");
    }

}
