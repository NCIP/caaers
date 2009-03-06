package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditEvent;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditEventValue;
import gov.nih.nci.cabig.ctms.audit.domain.Operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;

public class AdverseEventHistoryTest extends AbstractTestCase {
    protected final Log log = LogFactory.getLog(getClass());


    private AdverseEvent adverseEvent;

    private AuditHistoryRepository auditHistoryRepository;


   // private AuditHistoryDao auditHistoryDao;
    private List<DataAuditEvent> dataAuditEvents;

    private DataAuditEvent dataAuditEvent;
    private final List<DataAuditEventValue> dataAuditEventValues = new ArrayList<DataAuditEventValue>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();

//        auditHistoryDao = registerMockFor(AuditHistoryDao.class);
//        auditHistoryRepository = new AuditHistoryRepository();
//        auditHistoryRepository.setAuditHistoryDao(auditHistoryDao);


        adverseEvent = new AdverseEvent();
        adverseEvent.setComments("comments1");
        adverseEvent.setHospitalization(Hospitalization.YES);
        adverseEvent.setDetailsForOther("details1");
        adverseEvent.setExpected(false);
        adverseEvent.setId(1);
        adverseEvent.setGrade(Grade.LIFE_THREATENING);
        gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo dataAuditInfo = new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo
                ("admin", "localhost", new Date(), "/pages/task");

        dataAuditEvent = new DataAuditEvent(adverseEvent, Operation.CREATE, dataAuditInfo);

        DataAuditEventValue value1 = new DataAuditEventValue("hospitalization", null, Hospitalization.YES.toString());
        DataAuditEventValue value2 = new DataAuditEventValue("expected", null, "false");
        DataAuditEventValue value3 = new DataAuditEventValue("comments", null, "comments1");
        DataAuditEventValue value4 = new DataAuditEventValue("detailsForOther", null, "details1");

        dataAuditEventValues.add(value1);
        dataAuditEventValues.add(value2);
        dataAuditEventValues.add(value3);
        dataAuditEventValues.add(value4);


        dataAuditEvent.addValues(dataAuditEventValues);

        dataAuditEvents = new ArrayList<DataAuditEvent>();
        dataAuditEvents.add(dataAuditEvent);


    }

    public void testCreateAdverseEvent() {


//        expect(auditHistoryDao.findDataAuditEvents(isA(DataAuditEventQuery.class))).andReturn(dataAuditEvents);
//        replayMocks();
//
//        Map<String, String> attributeNameVsDisplayNameMap = new HashMap<String, String>();
//
//        attributeNameVsDisplayNameMap.put("detailsForOther", "Details for Other");
//        attributeNameVsDisplayNameMap.put("comments", "Comments");
//        attributeNameVsDisplayNameMap.put("hospitalization", "Hospitalization");
//        attributeNameVsDisplayNameMap.put("expected", "Expected");
//        attributeNameVsDisplayNameMap.put("grade", "Grade");
//        attributeNameVsDisplayNameMap.put("attributionSummary", "Attribution Summary");
//
//
//        List<DataAuditEvent> auditHistories = auditHistoryRepository.getAuditDetailsForEntity(adverseEvent.getClass(), adverseEvent.getId());
//        assertFalse(auditHistories.isEmpty());
//        verifyMocks();
//
//        DataAuditEvent event = auditHistories.get(0);
//
//        assertAllFieldPropertiesExist(event.getValues());
//
//        for (DataAuditEventValue dataEventValue : event.getValues()) {
//            //   assertEquals(attributeNameVsDisplayNameMap.get(dataEventValue.getAttributeName()), dataEventValue.getAttributeDisplayName());
//
//        }
    }

    protected void assertAllFieldPropertiesExist(List<DataAuditEventValue> values) {

        BeanWrapper wrappedCommand = new BeanWrapperImpl(adverseEvent);
        for (DataAuditEventValue value : values) {


            String msg = "The property " + value.getAttributeName() + " is not present in the adverse event. ";
            try {
                assertNotNull(msg, wrappedCommand.getPropertyType(value.getAttributeName()));
                assertEquals(value.getCurrentValue(), wrappedCommand.getPropertyValue(value.getAttributeName()).toString());
            } catch (InvalidPropertyException ipe) {
                log.debug("Property not found exception", ipe);
                fail(msg);
            }
        }
    }

}
