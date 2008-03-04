/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDeliveryDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : June 20, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@CaaersUseCases( { CREATE_REPORT_FORMAT })
public class ReportDeliveryDefinitionDaoTest extends DaoTestCase<ReportDeliveryDefinitionDao> {

    ReportDeliveryDefinitionDao rddDao;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        rddDao = getDao();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        rddDao = null;
        super.tearDown();

    }

    /**
     */
    public void testDomainClass() {
        log.debug("domainClass :" + rddDao.domainClass().getName());
        assertEquals(ReportDeliveryDefinition.class.getName(), rddDao.domainClass().getName());
    }

    public void testGetByName() {
        String name = "RDD-222";
        ReportDeliveryDefinition rdd = rddDao.getByName(name);
        assertEquals("The name is not matching", name, rdd.getEntityName());
        assertEquals("The format dosent match", rdd.getFormat(), ReportFormat.XML);
    }

    public void testSave() {
        ReportDeliveryDefinition rdd = new ReportDeliveryDefinition();
        rdd.setEndPoint("www.biju.com/joel");
        rdd.setEndPointType(ReportDeliveryDefinition.ENDPOINT_TYPE_URL);
        rdd.setEntityDescription("Joel Biju Joseph");
        rdd.setEntityName("Joel");
        rdd.setFormat(ReportFormat.PDF);
        rdd.setEntityType(rdd.ENTITY_TYPE_PERSON);
        getDao().save(rdd);
        int id = rdd.getId();
        interruptSession();
        ReportDeliveryDefinition rdd2 = getDao().getById(id);
        assertEquals("Name shouldbe equal", rdd.getEntityName(), rdd2.getEntityName());
        assertEquals("Entity type should be equal", rdd.getEntityType(), rdd2.getEntityType());
        assertEquals("ReportFormat should be equal", rdd.getFormat(), rdd2.getFormat());
    }

    public void xtestExample() {
        assertTrue(true);
    }

}
