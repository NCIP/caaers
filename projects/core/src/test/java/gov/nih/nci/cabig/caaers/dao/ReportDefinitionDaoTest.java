/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_NOTIFICATION_RULES;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionExistsQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.NotificationAttachment;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 13, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@CaaersUseCases( { CREATE_NOTIFICATION_RULES, CREATE_REPORT_FORMAT })
public class ReportDefinitionDaoTest extends DaoTestCase<ReportDefinitionDao> {
    ReportDefinitionDao rctDao;

    private TransactionTemplate transactionTemplate;

    OrganizationDao orgDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        rctDao = getDao();
        transactionTemplate = (TransactionTemplate) getApplicationContext().getBean(
                        "transactionTemplate");
        orgDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
    }

    public void testDomainClass() {
        log.debug("domainClass :" + rctDao.domainClass().getName());
        assertEquals(ReportDefinition.class.getName(), rctDao.domainClass().getName());
    }

    public void testGetByName() {
        String name = "RCT-222";
        ReportDefinition rct = rctDao.getByName(name);
        assertEquals("The name is not matching", name, rct.getName());
    }

    public void testSave() {
        ReportDefinition definition = new ReportDefinition();
        definition.setDuration(5);
        definition.setGridId("202020202044iiei90");
        definition.setName("Test-RCT");
        definition.setTimeScaleUnitType(TimeScaleUnit.DAY);
        definition.setAmendable(true);
        definition.setReportFormatType(ReportFormatType.ADEERSPDF);

        // create planned notifications
        List<PlannedNotification> pnlist = new ArrayList<PlannedNotification>();
        PlannedEmailNotification pen = new PlannedEmailNotification();
        // create notification body
        NotificationBodyContent nbc = new NotificationBodyContent();
        nbc.setBody("This is my body");
        pen.setNotificationBodyContent(nbc);
        pen.setGridId("90890d99dke");
        pen.setIndexOnTimeScale(2);
        pen.setSubjectLine("MySubjectline");

        List<Recipient> rlist = new ArrayList<Recipient>();
        RoleBasedRecipient r = new RoleBasedRecipient();
        r.setGridId("0d0d0ekjeoiio");
        r.setRoleName("Investigator");
        rlist.add(r);
        pen.setRecipients(rlist);

        // create attachents
        NotificationAttachment at = new NotificationAttachment();
        at.setContent("Hi attachment".getBytes());

        List<NotificationAttachment> alist = new ArrayList<NotificationAttachment>();
        alist.add(at);
        pen.setAttachments(alist);

        pnlist.add(pen);
        definition.setPlannedNotifications(pnlist);

        final ReportDeliveryDefinition rdd = new ReportDeliveryDefinition();
        rdd.setEndPoint("abcd");
        rdd.setEndPointType(rdd.ENDPOINT_TYPE_EMAIL);
        rdd.setFormat(ReportFormat.PDF);
        rdd.setEntityDescription("Joel");
        rdd.setEntityName("Manju");
        rdd.setEntityType(rdd.ENTITY_TYPE_ROLE);

        definition.addReportDeliveryDefinition(rdd);

        Organization org = orgDao.getById(-1001);
        org.addReportDefinition(definition);

        // add new mandatory fields.
        ReportMandatoryFieldDefinition mf1 = new ReportMandatoryFieldDefinition("biju.a1", Mandatory.OPTIONAL);
        ReportMandatoryFieldDefinition mf2 = new ReportMandatoryFieldDefinition("biju.a2", Mandatory.MANDATORY);
        List<ReportMandatoryFieldDefinition> mandatoryFields = new ArrayList<ReportMandatoryFieldDefinition>();
        mandatoryFields.add(mf1);
        mandatoryFields.add(mf2);
        definition.setMandatoryFields(mandatoryFields);

        rctDao.save(definition);
        final Integer id = definition.getId();

        interruptSession();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // ReportDefinition rctLoaded = rctDao.getById(id);
                ReportDefinition rctLoaded = rctDao.getByName("Test-RCT");
                rctDao.initialize(rctLoaded);

                log.debug(rctLoaded.getDuration());

                Organization org = rctLoaded.getOrganization();
                assertNotNull("Organization should not be null", org);
                assertEquals("Organization must be associated to ReportDefinition", org
                                .getReportDefinitions().size(), 3);
                PlannedEmailNotification nf = (PlannedEmailNotification) rctLoaded
                                .getPlannedNotifications().get(0);
                assertEquals("SubjectLine Equality failed:", "MySubjectline", nf.getSubjectLine());
                assertEquals("Body Content Equality Failed", "This is my body", nf
                                .getNotificationBodyContent().getBody());

                ReportDeliveryDefinition rdd2 = rctLoaded.getDeliveryDefinitionsInternal().get(0);
                assertEquals("Report delivery definiton name must be same", rdd2.getEndPoint(), rdd
                                .getEndPoint());

                // verify report mandatory fields.
                List<ReportMandatoryFieldDefinition> mfList = rctLoaded.getMandatoryFields();
                assertEquals("Mandatory fields size", 2, mfList.size());
                ReportMandatoryFieldDefinition mfLoaded = mfList.get(1);
                assertEquals("Path should be same", "biju.a2", mfLoaded.getFieldPath());
                assertTrue("Field biju.a2 must be mandatory", mfLoaded.getMandatory().equals(Mandatory.MANDATORY));

                // update the values.
                nf.setIndexOnTimeScale(4);
                nf.setSubjectLine("New Subject Line");
                rctDao.save(rctLoaded);
                log.debug("============= after save ===============");

            }
        });
    }

    public void testGetAll() throws Exception {
        List<ReportDefinition> actual = getDao().getAll();
        assertEquals("Wrong number found", 2, actual.size());
        assertEquals("Wrong one", -222, (int) actual.get(0).getId());
    }
    
    public void testNoOfSimilarReportDefinitions(){
    	ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
    	query.filterByDifferentId(33);
    	query.filterByName("abcd");
    	
    	Integer cnt = rctDao.noOfSimilarReportDefinitions(query);
    	System.out.println(cnt);
    	assertEquals(0, cnt.intValue());
    }
    public void testNoOfSimilarReportDefinitions_SameId_SameName(){
    	ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
    	query.filterByDifferentId(-222);
    	query.filterByName("RCT-222");
    	
    	Integer cnt = rctDao.noOfSimilarReportDefinitions(query);
    	System.out.println(cnt);
    	assertEquals(0, cnt.intValue());
    }
    
    public void testNoOfSimilarReportDefinitions_DifferentId_SameName(){
    	ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
    	query.filterByDifferentId(-2223);
    	query.filterByName("RCT-222");
    	
    	Integer cnt = rctDao.noOfSimilarReportDefinitions(query);
    	System.out.println(cnt);
    	assertEquals(1, cnt.intValue());
    }
    
    public void testNoOfSimilarReportDefinitions_NoId_SameName(){
    	ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
    	query.filterByDifferentId(-2223);
    	query.filterByName("RCT-222");
    	
    	Integer cnt = rctDao.noOfSimilarReportDefinitions(query);
    	System.out.println(cnt);
    	assertEquals(1, cnt.intValue());
    }
    
    public void testNoOfSimilarReportDefinitions_NoId_DifferentName(){
    	ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
    	query.filterByDifferentId(-2223);
    	query.filterByName("RCT-222x");
    	
    	Integer cnt = rctDao.noOfSimilarReportDefinitions(query);
    	System.out.println(cnt);
    	assertEquals(0, cnt.intValue());
    }
    
    public void testGetAllByOrganizationId(){
    	List<ReportDefinition> actual = getDao().getAll(-1001);
    	assertEquals("Wrong number found", 2, actual.size());
        assertEquals("Wrong one", -221, (int) actual.get(0).getId());
    	
    }
    
    public void testGetAllByNameAndOrganizationId(){
    	ReportDefinition actual = getDao().getByName("RCT-222",-1001);
    	assertEquals("TestDescription", actual.getDescription());
    }
    
    public void testReassociate(){
    	 ReportDefinition rct = null;
    	 
    	{
    		 String name = "RCT-222";
    	   	 rct =  rctDao.getByName(name);
    	     assertNotNull(rct);
    	}
    	interruptSession();
    	{
    		try{
    			rct.getMandatoryFields().size();
    			fail("should throw lazy exception");
    		}catch(Exception e) {
    			
    		}
    		
    		rctDao.reassociate(rct);
    		int size = rct.getMandatoryFields().size();
    		assertEquals(3, size);
    	}
    	
    }
}
