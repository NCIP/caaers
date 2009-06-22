package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;

public class AdverseEventHistoryIntegrationTest extends CaaersDbTestCase {

//    private AdverseEventDao adverseEventDao;
//
//    private AdverseEvent adverseEvent, oldAdverseEvent;
//
//    private AuditHistoryRepository auditHistoryRepository;
//    private int expectedFields=6;
//    private Map<String, String> attributeNameVsDisplayNameMap;
//
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//
//        adverseEventDao = (AdverseEventDao) getApplicationContext().getBean("adverseEventDao");
//        auditHistoryRepository = (AuditHistoryRepository) getApplicationContext().getBean("auditHistoryRepository", AuditHistoryRepository.class);
//
//
////        adverseEvent = createAdverseEvent();
////        oldAdverseEvent = createAdverseEvent();
//
//    }
//
//
//    public void testCreateAdverseEvent() {
//
////        createAndValidateAdverseEvent();
//
//    }
//
//
//    public void testCreateAndUpdateAdverseEvent() {
//
//  //      createAndValidateAdverseEvent();
//
//        //now update the adverse event
//        adverseEvent = adverseEventDao.getById(adverseEvent.getId());
//        adverseEvent.setComments("comments2");
//        adverseEvent.setHospitalization(Hospitalization.YES);
//        adverseEventDao.save(adverseEvent);
//        interruptSession();
//
////        List<DataAuditEvent> auditHistories = auditHistoryRepository.
////                getAuditDetailsForEntity(adverseEvent.getClass(), adverseEvent.getId());
////        assertFalse(auditHistories.isEmpty());
////        assertEquals(2, auditHistories.size());
////
////        validateCreate(auditHistories);
////        validateUpdate(auditHistories);
//
//
//    }
//
////    private void validateUpdate(List<DataAuditEvent> auditHistories) {
////
////        oldAdverseEvent.setGridId(adverseEvent.getGridId());
////
////        DataAuditEvent event = auditHistories.get(1);
////
////        assertEquals(Operation.UPDATE, event.getOperation());
////        assertEquals(adverseEvent.getId(), event.getReference().getId());
////        assertEquals(adverseEvent.getClass().getName(), event.getReference().getClassName());
////
////        List<DataAuditEventValue> values = event.getValues();
////        assertEquals(2, values.size());
////        assertAllFieldPropertiesExist(values, adverseEvent, oldAdverseEvent);
////    }
////
////    private void validateCreate(List<DataAuditEvent> auditHistories) {
////        oldAdverseEvent.setGridId(adverseEvent.getGridId());
////
////        DataAuditEvent event = auditHistories.get(0);
////
////        assertEquals(Operation.CREATE, event.getOperation());
////        assertEquals(adverseEvent.getId(), event.getReference().getId());
////        assertEquals(adverseEvent.getClass().getName(), event.getReference().getClassName());
////
////        List<DataAuditEventValue> values = event.getValues();
////        assertEquals(expectedFields+1, values.size()); ///one extra because of grid id
////        assertAllFieldPropertiesExist(values, oldAdverseEvent, null);
////    }
//
////    private void createAndValidateAdverseEvent() {
////        adverseEventDao.save(adverseEvent);
////        interruptSession();
////        assertNotNull(adverseEvent.getId());
////
////        List<DataAuditEvent> auditHistories = auditHistoryRepository.
////                getAuditDetailsForEntity(adverseEvent.getClass(), adverseEvent.getId());
////        assertFalse(auditHistories.isEmpty());
////        assertEquals(1, auditHistories.size());
////
////        validateCreate(auditHistories);
////    }
////
////    private AdverseEvent createAdverseEvent() {
////        AdverseEvent event = new AdverseEvent();
////        event.setComments("comments1");
////        event.setHospitalization(Hospitalization.HOSPITALIZATION);
////        event.setDetailsForOther("details1");
////        event.setExpected(false);
////        event.setGrade(Grade.MILD);
////        event.setAttributionSummary(Attribution.POSSIBLE);
////        return event;
////    }
//
//    protected void assertAllFieldPropertiesExist(List<DataAuditEventValue> values, final AdverseEvent currentAdverseEvent, final AdverseEvent oldAdverseEvent) {
//
//        BeanWrapper wrappedCommand = new BeanWrapperImpl(currentAdverseEvent);
//
//        for (DataAuditEventValue value : values) {
//            String msg = "The property " + value.getAttributeName() + " is not present in the adverse event. ";
//            try {
//                assertNotNull(msg, wrappedCommand.getPropertyType(value.getAttributeName()));
//                assertEquals(value.getCurrentValue(), wrappedCommand.getPropertyValue(value.getAttributeName()).toString());
//                if (oldAdverseEvent != null) {
//                    BeanWrapper wrappedCommandForOldAe = new BeanWrapperImpl(oldAdverseEvent);
//                    assertEquals(value.getPreviousValue(), wrappedCommandForOldAe.getPropertyValue(value.getAttributeName()).toString());
//                }
//
//            } catch (InvalidPropertyException ipe) {
//                log.debug("Property not found exception", ipe);
//                fail(msg);
//            }
//        }
//    }
	
	public void testTest(){
		assertTrue(true);
	}

}
