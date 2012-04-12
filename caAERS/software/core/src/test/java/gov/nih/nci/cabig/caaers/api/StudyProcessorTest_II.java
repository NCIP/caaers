package gov.nih.nci.cabig.caaers.api;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Test case to test convrsion of jaxb study object to domain study object and call to studymigrator with study domain object.
 *
 * @author Monish Dombla
 */
public class StudyProcessorTest_II extends CaaersDbNoSecurityTestCase {

    private StudyProcessorImpl studyProcessor = null;
    private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private gov.nih.nci.cabig.caaers.integration.schema.study.Studies studies = null;
    private StudyDao studyDao = null;

    Identifier identifier = null;
    Organization organization = null;
    Study createdStudy = null;
    boolean authorizationOnByDefault;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.study");
        unmarshaller = jaxbContext.createUnmarshaller();
        studyProcessor = (StudyProcessorImpl)getDeployedApplicationContext().getBean("studyProcessorImpl");
        studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");

        createdStudy = studyDao.getByShortTitle("Study PCS");
        if (createdStudy != null) {
        	createdStudy = studyDao.getStudyDesignById(createdStudy.getId());
            studyDao.delete(createdStudy);
        }

        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.study");
        unmarshaller = jaxbContext.createUnmarshaller();
        studyProcessor = (StudyProcessorImpl)getDeployedApplicationContext().getBean("studyProcessorImpl");
        studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");

    }


/**
 * Test Create Study
 * @throws Exception
 */
    public void testCreateStudy_WithEpochs_CtcTerm() throws Exception {

        createStudy("studydata/CreateStudy_WithEpochs_CtcTerm.xml");
        interruptSession();
        createdStudy = studyDao.getByShortTitle("Study_PCS");
        assertNotNull(createdStudy);

        createdStudy = studyDao.getStudyDesignById(createdStudy.getId());

        assertNotNull(createdStudy);

        assertEquals("Pancreatic Cancer Study ph 5", createdStudy.getLongTitle());
        assertEquals("Precis", createdStudy.getPrecis());
        assertEquals("Test Study", createdStudy.getDescription());

        assertEquals("Phase I Trial", createdStudy.getPhaseCode());
        assertEquals("Active - Trial is open to accrual", createdStudy.getStatus());
        assertEquals("OPEN_UNBLIND", createdStudy.getDesign().name());

        assertTrue(createdStudy.getMultiInstitutionIndicator());
        assertTrue(createdStudy.getAdeersReporting());

        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.DRUG_ADMINISTRATION));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.RADIATION));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.DEVICE));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.SURGERY));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.BEHAVIORAL));
        
        assertNotNull(createdStudy.getEpochs());
        assertEquals(1, createdStudy.getEpochs().size());
        assertEquals("Baseline", createdStudy.getEpochs().get(0).getName());
        assertEquals("BaseLine_Desc", createdStudy.getEpochs().get(0).getDescriptionText());
        assertEquals(new Integer(1), createdStudy.getEpochs().get(0).getEpochOrder());
        assertNotNull(createdStudy.getEpochs().get(0).getArms());
        assertEquals(1, createdStudy.getEpochs().get(0).getArms().size());
        assertEquals("Baseline", createdStudy.getEpochs().get(0).getArms().get(0).getName());
        assertEquals("BaseLine_Desc", createdStudy.getEpochs().get(0).getArms().get(0).getDescriptionText());
        assertNotNull(createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents());
        assertEquals(2, createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
        CtcTerm ctcT1 = createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getCtcterm();
        CtcTerm ctcT2 = createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(1).getCtcterm();
        assertNotNull(ctcT1);
        assertNotNull(ctcT2);
        
    }
    
    public void testCreateStudy_WithEpochs_LowLevelTerm() throws Exception {

        createStudy("studydata/CreateStudy_WithEpochs_LowLevelTerm.xml");
        interruptSession();
        createdStudy = studyDao.getByShortTitle("Study_PCS");
        assertNotNull(createdStudy);

        createdStudy = studyDao.getStudyDesignById(createdStudy.getId());

        assertNotNull(createdStudy);

        assertEquals("Pancreatic Cancer Study ph 5", createdStudy.getLongTitle());
        assertEquals("Precis", createdStudy.getPrecis());
        assertEquals("Test Study", createdStudy.getDescription());

        assertEquals("Phase I Trial", createdStudy.getPhaseCode());
        assertEquals("Active - Trial is open to accrual", createdStudy.getStatus());
        assertEquals("OPEN_UNBLIND", createdStudy.getDesign().name());

        assertTrue(createdStudy.getMultiInstitutionIndicator());
        assertTrue(createdStudy.getAdeersReporting());

        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.DRUG_ADMINISTRATION));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.RADIATION));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.DEVICE));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.SURGERY));
        assertTrue(createdStudy.hasTherapyOfType(StudyTherapyType.BEHAVIORAL));
        
        assertNotNull(createdStudy.getEpochs());
        assertEquals(1, createdStudy.getEpochs().size());
        assertEquals("Baseline", createdStudy.getEpochs().get(0).getName());
        assertEquals("BaseLine_Desc", createdStudy.getEpochs().get(0).getDescriptionText());
        assertEquals(new Integer(1), createdStudy.getEpochs().get(0).getEpochOrder());
        assertNotNull(createdStudy.getEpochs().get(0).getArms());
        assertEquals(1, createdStudy.getEpochs().get(0).getArms().size());
        assertEquals("Baseline", createdStudy.getEpochs().get(0).getArms().get(0).getName());
        assertEquals("BaseLine_Desc", createdStudy.getEpochs().get(0).getArms().get(0).getDescriptionText());
        assertNotNull(createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents());
        assertEquals(2, createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
        LowLevelTerm llt1 = createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getLowLevelTerm();
        LowLevelTerm llt2 = createdStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(1).getLowLevelTerm();
        assertNotNull(llt1);
        assertNotNull(llt2);
        
    }
    
    public void testCreateStudy_WithEpochs_OtherTerm() throws Exception {
        studies = (gov.nih.nci.cabig.caaers.integration.schema.study.Studies) unmarshaller.unmarshal(createInputStream("studydata/CreateStudy_WithEpochs_OtherTerm.xml"));
        CaaersServiceResponse res = studyProcessor.createStudy(studies);
        assertNotNull(res);
        assertNotNull(res.getServiceResponse());
        assertNotNull(res.getServiceResponse().getEntityProcessingOutcomes());
        assertEquals(1, res.getServiceResponse().getWsError().size());
        assertEquals("SolicitedAdverseEvent with only otherMeddraCode is not allowed", res.getServiceResponse().getWsError().get(0).getErrorDesc());
    }
    
    public void testCreateStudy_WithNoBaseLineEpoch() throws Exception {
        studies = (gov.nih.nci.cabig.caaers.integration.schema.study.Studies) unmarshaller.unmarshal(createInputStream("studydata/CreateStudy_WithNoBaseLineEpoch.xml"));
        CaaersServiceResponse res = studyProcessor.createStudy(studies);
        assertNotNull(res);
        assertNotNull(res.getServiceResponse());
        assertNotNull(res.getServiceResponse().getEntityProcessingOutcomes());
        assertEquals(1, res.getServiceResponse().getWsError().size());
        assertEquals("One EvaluationPeriod with name as \"Baseline\" is mandatory", res.getServiceResponse().getWsError().get(0).getErrorDesc());
    }
    
    public void testCreateStudy_WithNoBaseLineEpoch_OnlyOtherTerm() throws Exception {
        studies = (gov.nih.nci.cabig.caaers.integration.schema.study.Studies) unmarshaller.unmarshal(createInputStream("studydata/CreateStudy_WithNoBaseLineEpoch_OnlyOtherTerm.xml"));
        CaaersServiceResponse res = studyProcessor.createStudy(studies);
        assertNotNull(res);
        assertNotNull(res.getServiceResponse());
        assertNotNull(res.getServiceResponse().getEntityProcessingOutcomes());
        assertEquals(2, res.getServiceResponse().getWsError().size());
    }

    private void createStudy(String studyXmlLocation) throws Exception {
        studies = (gov.nih.nci.cabig.caaers.integration.schema.study.Studies) unmarshaller.unmarshal(createInputStream(studyXmlLocation));
        studyProcessor.createStudy(studies);
    }

    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        if (testDataStream == null) {
            testDataStream = handleTestDataFileNotFound();
            // if it is still null, fail gracefully
            if (testDataStream == null) {
                throw new NullPointerException(
                        "Test data resource " + ResourceRetriever.getResourceName(getClass().getPackage(), testDataFileName)
                                + " not found and fallback call to handleTestDataFileNotFound() did not provide a substitute.");
            }
        }
        return testDataStream;
    }
    
    public void testCreateStudy_WithExpectedAE_CtcTerms() throws Exception {
    	
    	createStudy("studydata/CreateStudy_WithExpectedAE_CtcTerms.xml");
        interruptSession();
        createdStudy = studyDao.getByShortTitle("Study_PCS");
        assertNotNull(createdStudy);

        createdStudy = studyDao.getStudyDesignById(createdStudy.getId());

        assertNotNull(createdStudy);

        assertEquals("Pancreatic Cancer Study ph 5", createdStudy.getLongTitle());
        assertEquals("Precis", createdStudy.getPrecis());
        assertEquals("Test Study", createdStudy.getDescription());
        assertNotNull(createdStudy.getExpectedAECtcTerms());
        assertEquals(2, createdStudy.getExpectedAECtcTerms().size());
        assertEquals(0, createdStudy.getExpectedAEMeddraLowLevelTerms().size());
    }
    
    public void testCreateStudy_WithExpectedAE_WithOnlyOtherTerm() throws Exception {
    	studies = (gov.nih.nci.cabig.caaers.integration.schema.study.Studies) unmarshaller.unmarshal(createInputStream("studydata/CreateStudy_WithExpectedAE_WithOnlyOtherTerm.xml"));
        CaaersServiceResponse res = studyProcessor.createStudy(studies);
        assertNotNull(res);
        assertNotNull(res.getServiceResponse());
        assertNotNull(res.getServiceResponse().getEntityProcessingOutcomes());
        assertEquals("ExpectedAECtcTerm cannot contain only otherMeddraCode", res.getServiceResponse().getWsError().get(0).getErrorDesc());
    }
    
    public void testCreateStudy_WithExpectedAE_LowLevelTerms() throws Exception {
    	
    	createStudy("studydata/CreateStudy_WithExpectedAE_LowLevelTerms.xml");
        interruptSession();
        createdStudy = studyDao.getByShortTitle("Study_PCS");
        assertNotNull(createdStudy);

        createdStudy = studyDao.getStudyDesignById(createdStudy.getId());

        assertNotNull(createdStudy);

        assertEquals("Pancreatic Cancer Study ph 5", createdStudy.getLongTitle());
        assertEquals("Precis", createdStudy.getPrecis());
        assertEquals("Test Study", createdStudy.getDescription());
        assertNotNull(createdStudy.getExpectedAEMeddraLowLevelTerms());
        assertEquals(2, createdStudy.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(0, createdStudy.getExpectedAECtcTerms().size());
    }
    
}
