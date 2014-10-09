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
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.study.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.study.IndType;
import gov.nih.nci.cabig.caaers.integration.schema.study.Studies;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudySiteType;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
    
    public void testCheckDuplicateStudyWithSameProcolAuthorityID() throws Exception {
    	
    	Study s = new LocalStudy();
    	OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
    	id.setValue("12345-XYZ");
    	id.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
    	
    	Organization org = organizationDao.getById(-1003);
    	id.setOrganization(org);
    	s.getIdentifiers().add(id);
    	//Study s = studyDao.getStudyDesignById(-2);
    	Study searchResult = studyProcessor.checkDuplicateStudyBasedOnProcolAuthorityIdentifier(s);
    	assertNotNull(searchResult);
    	
    }

/*
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
*/
}
