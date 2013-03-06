/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.study.*;
import org.dbunit.operation.DatabaseOperation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 4/9/12 -4:58 PM
 */
public class StudyProcessorImplTest extends DaoTestCase {

    StudyProcessorImpl studyProcessor;
    StudyDao studyDao;
    DeviceDao deviceDao;
    OrganizationDao organizationDao;

    private File xmlFile;
    private Studies ss;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyProcessor = (StudyProcessorImpl)applicationContext.getBean("studyProcessorImpl");
        studyDao = (StudyDao)applicationContext.getBean("studyDao");
        deviceDao = (DeviceDao)applicationContext.getBean("deviceDao");
        organizationDao = (OrganizationDao)applicationContext.getBean("organizationDao");
        xmlFile = new File(StudyProcessorImplTest.class.getResource("/gov/nih/nci/cabig/caaers/api/impl/testdata/StudyProcessorImpl-Study.xml").getFile());
        ss = loadStudiesFromXML();
    }

    public void testLoadingProcedure() {
        assertNotNull(studyProcessor);
        assertNotNull(xmlFile);
        assertNotNull(ss);
        assertNotNull(ss.getStudy().get(0));
    }

//    public void testUpdateStudyWithoutChanges() {
//        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
//        assertEquals("0", csr.getServiceResponse().getResponsecode());
//    }

    public void testUpdateStudyAddExistingDevice() {
        Study s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());

        // ADD A DB-EXISTING DEVICE TO THE STUDY XML
        gov.nih.nci.cabig.caaers.integration.schema.study.Study xmlStudy = ss.getStudy().get(0);

        StudyDeviceType xmlDevice = new StudyDeviceType();
        xmlDevice.setDevice(new DeviceType());
        xmlDevice.getDevice().setCommonName("Common name 11");
        xmlDevice.getDevice().setBrandName("Brand name 11");
        xmlDevice.getDevice().setCtepDbIdentifier("-11");
        xmlStudy.getStudyDevices().getStudyDevice().add(xmlDevice);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        assertEquals(3, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());
    }

    public void testUpdateStudyCreateDevice() {
        Study s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudyDevices().size());
        assertEquals(3, deviceDao.getAllDevices().size());

        // ADD A NON DB-EXISTING DEVICE TO THE STUDY XML
        gov.nih.nci.cabig.caaers.integration.schema.study.Study xmlStudy = ss.getStudy().get(0);

        StudyDeviceType xmlDevice = new StudyDeviceType();
        xmlDevice.setDevice(new DeviceType());
        xmlDevice.getDevice().setCommonName("Common name - This device was not in the database.");
        xmlDevice.getDevice().setCtepDbIdentifier("-101");
        xmlStudy.getStudyDevices().getStudyDevice().add(xmlDevice);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        assertEquals(3, s.getStudyDevices().size());
        assertEquals(4, deviceDao.getAllDevices().size());
    }

    public void testUpdateStudyCreateTreatmentAssignment() {
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

    public void testUpdateStudyCreateAgent() {
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

    public void testUpdateStudyAddExistingOrganization() {
        Study s = studyDao.getStudyDesignById(-2);

        // DB Study has 1 site
        assertEquals(1, s.getStudySites().size());

        // XML Study has 1 site
        assertEquals(1, ss.getStudy().get(0).getStudyOrganizations().getStudySite().size());

        // Add Coordinating Center as StudySite
        StudySiteType sst = new StudySiteType();
        sst.setOrganization(new OrganizationType());
        sst.getOrganization().setName("CTEP");
        sst.getOrganization().setNciInstituteCode("CTEP");
        ss.getStudy().get(0).getStudyOrganizations().getStudySite().add(sst);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        // DB Study should have 2 sites
        s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudySites().size());
    }

    public void testUpdateStudyCreateOrganization() {
        Study s = studyDao.getStudyDesignById(-2);
        List l = organizationDao.getAll();

        // DB has 4 organizations
        assertEquals(4, l.size());

        // DB Study has 1 site
        assertEquals(1, s.getStudySites().size());

        // XML Study has 1 site
        assertEquals(1, ss.getStudy().get(0).getStudyOrganizations().getStudySite().size());

        // Add a NEW StudySite
        StudySiteType sst = new StudySiteType();
        sst.setOrganization(new OrganizationType());
        sst.getOrganization().setName("NEW ORGANIZATION");
        sst.getOrganization().setNciInstituteCode("NEW-001");
        sst.getOrganization().setStatus(ActiveInactiveStatusType.ACTIVE);
        ss.getStudy().get(0).getStudyOrganizations().getStudySite().add(sst);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        // DB should have 5 organizations
        l = organizationDao.getAll();
        assertEquals(5, l.size());

        // DB Study should have 2 sites
        s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudySites().size());
    }

    public void testUpdateStudyUpdateOrganization() {
        Study s = studyDao.getStudyDesignById(-2);
        List l = organizationDao.getAll();

        // DB has 4 organizations
        assertEquals(4, l.size());

        Organization o = organizationDao.getById(-1001);
        assertEquals("National Cancer Institute", o.getName());

        // DB Study has 1 site
        assertEquals(1, s.getStudySites().size());

        // XML Study has 1 site
        assertEquals(1, ss.getStudy().get(0).getStudyOrganizations().getStudySite().size());

        // Add a NEW StudySite
        StudySiteType sst = new StudySiteType();
        sst.setOrganization(new OrganizationType());
        sst.getOrganization().setName("NEW ORGANIZATION NAME");
        sst.getOrganization().setNciInstituteCode("NCI");
        sst.getOrganization().setStatus(ActiveInactiveStatusType.ACTIVE);
        ss.getStudy().get(0).getStudyOrganizations().getStudySite().add(sst);

        CaaersServiceResponse csr =  studyProcessor.updateStudy(ss);
        assertEquals("0", csr.getServiceResponse().getResponsecode());

        // DB should still have 4 organizations
        l = organizationDao.getAll();
        assertEquals(4, l.size());

        // DB Study should have 2 sites
        s = studyDao.getStudyDesignById(-2);
        assertEquals(2, s.getStudySites().size());

        o = organizationDao.getById(-1001);
        assertEquals("NEW ORGANIZATION NAME", o.getName());
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
