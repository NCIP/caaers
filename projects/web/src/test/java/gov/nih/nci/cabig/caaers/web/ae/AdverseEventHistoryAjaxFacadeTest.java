package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class AdverseEventHistoryAjaxFacadeTest extends AbstractTestCase {

    private AdverseEventHistoryAjaxFacade adverseEventHistoryAjaxFacade;
    private AdverseEvent adverseEvent;

    private AuditHistoryRepository auditHistoryRepository;


    private List<DataAuditEvent> dataAuditEvents = new ArrayList<DataAuditEvent>();

    private DataAuditEvent dataAuditEvent;
//    private AuditHistoryDao auditHistoryDao;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

//        adverseEvent = new AdverseEvent();
//        adverseEvent.setComments("comments1");
//        adverseEvent.setHospitalization(Hospitalization.HOSPITALIZATION);
//        adverseEvent.setDetailsForOther("details1");
//        adverseEvent.setExpected(false);
//        adverseEvent.setId(1);
//        adverseEvent.setGrade(Grade.LIFE_THREATENING);
//
//        gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo dataAuditInfo = new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo
//                ("admin", "localhost", new Date(), "/pages/task");
//
//        dataAuditEvent = new DataAuditEvent(adverseEvent, Operation.CREATE, dataAuditInfo);
//
//        DataAuditEventValue value1 = new DataAuditEventValue("hospitalization", null, Hospitalization.HOSPITALIZATION.toString());
//        DataAuditEventValue value2 = new DataAuditEventValue("expected", null, "false");
//        DataAuditEventValue value3 = new DataAuditEventValue("attributionSummary", null, Attribution.PROBABLE.toString());
//        DataAuditEventValue value4 = new DataAuditEventValue("detailsForOther", null, "details1");
//        DataAuditEventValue value5 = new DataAuditEventValue("grade", null, Grade.LIFE_THREATENING.toString());
//
//        dataAuditEvent.addValue(value1);
//        dataAuditEvent.addValue(value2);
//        dataAuditEvent.addValue(value3);
//        dataAuditEvent.addValue(value4);
//        dataAuditEvent.addValue(value5);
//
//        dataAuditEvents.add(dataAuditEvent);
//
//
//        dataAuditEvent = new DataAuditEvent(adverseEvent, Operation.UPDATE, dataAuditInfo);
//        value5 = new DataAuditEventValue("grade", value5.getCurrentValue(), Grade.DEATH.toString());
//        dataAuditEvent.addValue(value5);
//
//        dataAuditEvents.add(dataAuditEvent);
//
//
//        auditHistoryDao = registerMockFor(AuditHistoryDao.class);
//        auditHistoryRepository = new AuditHistoryRepository();
//        auditHistoryRepository.setAuditHistoryDao(auditHistoryDao);
//
        adverseEventHistoryAjaxFacade = new AdverseEventHistoryAjaxFacade();

        adverseEventHistoryAjaxFacade.setAuditHistoryRepository(auditHistoryRepository);


    }

    public void testSearchAeHistory() throws Exception {


//        expect(auditHistoryDao.findDataAuditEvents(isA(DataAuditEventQuery.class))).andReturn(dataAuditEvents);
//        replayMocks();
//        String table = adverseEventHistoryAjaxFacade.getAdeverseEventHistory(new HashMap(), new MockHttpServletRequest(), "1");
//        verifyMocks();

    }
}
