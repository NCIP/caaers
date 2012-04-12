package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.study.Studies;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceType;
import org.dbunit.operation.DatabaseOperation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Ion C. Olaru
 *         Date: 4/9/12 -4:58 PM
 */
public class StudyProcessorImplTest extends DaoTestCase {

    StudyProcessorImpl studyProcessor;
    StudyDao studyDao;
    DeviceDao deviceDao;

    private File xmlFile;
    private Studies ss;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyProcessor = (StudyProcessorImpl)applicationContext.getBean("studyProcessorImpl");
        studyDao = (StudyDao)applicationContext.getBean("studyDao");
        deviceDao = (DeviceDao)applicationContext.getBean("deviceDao");
        xmlFile = new File(StudyProcessorImplTest.class.getResource("/gov/nih/nci/cabig/caaers/api/impl/testdata/StudyProcessorImpl-Study.xml").getFile());
        ss = loadStudiesFromXML();
    }

    public void testLoadingProcedure() {
        assertNotNull(studyProcessor);
        assertNotNull(xmlFile);
        assertNotNull(ss);
        assertNotNull(ss.getStudy().get(0));
    }

    public void testUpdateStudyWithoutChanges() {
        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("1", csr.getServiceResponse().getResponsecode());
    }

    public void testUpdateStudyAddExistingDevice() {
        Study s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());

        // ADD A DB-EXISTING DEVICE TO THE STUDY XML
        gov.nih.nci.cabig.caaers.integration.schema.study.Study xmlStudy = ss.getStudy().get(0);

        StudyDeviceType xmlDevice = new StudyDeviceType();
        xmlDevice.setDevice(new DeviceType());
        xmlDevice.getDevice().setCommonName("Common name 11");
        xmlStudy.getStudyDevices().getStudyDevice().add(xmlDevice);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("1", csr.getServiceResponse().getResponsecode());

        assertEquals(3, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());
    }

    public Studies loadStudiesFromXML() {
        gov.nih.nci.cabig.caaers.integration.schema.study.Studies studies = null;
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.study");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object importObject = unmarshaller.unmarshal(xmlFile);
            studies = (gov.nih.nci.cabig.caaers.integration.schema.study.Studies) importObject;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return studies;
    }

/*
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
*/
}
