package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.study.*;
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
        assertEquals("0", csr.getServiceResponse().getResponsecode());
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
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        assertEquals(3, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());
    }

    public void testUpdateStudyAddNewDevice() {
        Study s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());

        // ADD A NON DB-EXISTING DEVICE TO THE STUDY XML
        gov.nih.nci.cabig.caaers.integration.schema.study.Study xmlStudy = ss.getStudy().get(0);

        StudyDeviceType xmlDevice = new StudyDeviceType();
        xmlDevice.setDevice(new DeviceType());
        xmlDevice.getDevice().setCommonName("Common name - This device was not in the database.");
        xmlStudy.getStudyDevices().getStudyDevice().add(xmlDevice);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        assertEquals(3, s.getStudyDevices().size());
        assertEquals(4, deviceDao.getAllDevices().size());
    }

    public void testUpdateStudyAddTreatmentAssignment() {
        Study s = studyDao.getStudyDesignById(-2);

        // DB Study has 0 TACs
        assertEquals(0, s.getTreatmentAssignments().size());

        // XML Study has 1 TAC
        assertEquals(1, ss.getStudy().get(0).getTreatmentAssignments().getTreatmentAssignment().size());

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        // DB Study should have 1 TAC
        s = studyDao.getStudyDesignById(-2);
        assertEquals(1, s.getTreatmentAssignments().size());
    }

    public void testUpdateStudyAddExistingAgent() {
        Study s = studyDao.getStudyDesignById(-2);

        // DB Study has 0 Agents
        assertEquals(0, s.getStudyAgents().size());

        // XML Study has 1 Agent
        assertEquals(1, ss.getStudy().get(0).getStudyAgents().getStudyAgent().size());

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        // DB Study should have 1 Agent
        assertEquals(1, s.getStudyAgents().size());

    }

    public void testUpdateStudyAddNewAgent() {
        Study s = studyDao.getStudyDesignById(-2);

        // DB Study has 0 Agents
        assertEquals(0, s.getStudyAgents().size());

        // XML Study has 1 Agent
        assertEquals(1, ss.getStudy().get(0).getStudyAgents().getStudyAgent().size());

        // Add one more Agent (NEW) to the XML
        StudyAgentType sat = new StudyAgentType();
        sat.setAgent(new AgentType());
        sat.getAgent().setNscNumber("999000");
        sat.getAgent().setName("NEW AGENT");
        sat.setIndType(IndType.OTHER);
        sat.setPartOfLeadIND(false);
        ss.getStudy().get(0).getStudyAgents().getStudyAgent().add(sat);

        // XML Study has 2 Agent now
        assertEquals(2, ss.getStudy().get(0).getStudyAgents().getStudyAgent().size());

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        // DB Study should have 2 Agents now
        s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudyAgents().size());

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
