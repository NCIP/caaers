package gov.nih.nci.cabig.caaers.api;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Test case to test convrsion of jaxb study object to domain study object and call to studymigrator with study domain object.
 *
 * @author Monish Dombla
 */
public class StudyProcessorTest extends CaaersDbTestCase {
//
//    private StudyProcessor studyProcessor = null;
//    private JAXBContext jaxbContext = null;
//    private Unmarshaller unmarshaller = null;
//    private gov.nih.nci.cabig.caaers.webservice.Studies studies = null;
//    private StudyDao studyDao = null;
//
//    Identifier identifier = null;
//    Organization organization = null;
//    Study updatedStudy = null;
//    boolean authorizationOnByDefault;
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//
//
//        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
//        unmarshaller = jaxbContext.createUnmarshaller();
//        studyProcessor = (StudyProcessor) getDeployedApplicationContext().getBean("studyProcessorImpl");
//        studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        if (updatedStudy != null) {
//            updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//            studyDao.delete(updatedStudy);
//        }
//
//    }
//
//
//    /**
//     * Tests the update of attributes shortTitle,longTitle,percis,description,phaseCode,
//     * status,design,multiInsitutionIndicator,adeersReporting,
//     * Also tests the update of Study Therapies.
//     * DrugAdministrationTherapyType,RadiationTherapyType,DeviceTherapyType,SurgeryTherapyType,
//     * BehavioralTherapyType
//     */
//    public void testStudyUpdateOfInstanceAtt() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfInstanceAtt.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        interruptSession();
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study_PCS_Updated");
//        assertNotNull(updatedStudy);
//
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals("Pancreatic Cancer Study ph 5 Updated", updatedStudy.getLongTitle());
//        assertEquals("Precis_Updated", updatedStudy.getPrecis());
//        assertEquals("Test Study_Updated", updatedStudy.getDescription());
//
//        assertEquals("Phase III Trial", updatedStudy.getPhaseCode());
//        assertEquals("Temporarily Closed to Accrual", updatedStudy.getStatus());
//        assertEquals("BLIND", updatedStudy.getDesign().name());
//
//        assertFalse(updatedStudy.getMultiInstitutionIndicator());
//        assertFalse(updatedStudy.getAdeersReporting());
//
//        assertFalse(updatedStudy.getDrugAdministrationTherapyType());
//        assertFalse(updatedStudy.getRadiationTherapyType());
//        assertFalse(updatedStudy.getDeviceTherapyType());
//        assertFalse(updatedStudy.getSurgeryTherapyType());
//        assertFalse(updatedStudy.getBehavioralTherapyType());
//    }
//
//
//    /**
//     * theCode1 and theCode3 are 2 TreatmentAssignments for the study with shorttitle Study PSC.
//     * Tests Treatmentassignment updates. The attributes doseLevelOrder,description and comments
//     * are updated for 2 exisitng treatmentassignments.
//     */
//    public void testStudyUpdateOfTreatmentAssignmentAttr() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfTreatmentAssignmentAttr.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getTreatmentAssignments().size());
//
//        for (TreatmentAssignment treatmentAssignment : updatedStudy.getTreatmentAssignments()) {
//            if (treatmentAssignment.getCode().equals("theCode1")) {
//                assertEquals(2, treatmentAssignment.getDoseLevelOrder().intValue());
//                assertEquals("Description_Updated", treatmentAssignment.getDescription());
//                assertEquals("Comments_Updated", treatmentAssignment.getComments());
//            }
//            if (treatmentAssignment.getCode().equals("theCode3")) {
//                assertEquals(3, treatmentAssignment.getDoseLevelOrder().intValue());
//                assertEquals("Description3_Updated", treatmentAssignment.getDescription());
//                assertEquals("Comments3_Updated", treatmentAssignment.getComments());
//            }
//        }
//
//
//    }
//
//
//    /**
//     * Study with short title Study PSC has 2 TreatmentAssignments.
//     * This testcase tests the addition of a TreatmentAssignment to an existing study.
//     * theCode1 and theCode3 are the existing treatment assignments.
//     * theCode4 is the new treamentAssignment.
//     */
//    public void testStudyUpdateOfTreatmentAssignmentAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfTreatmentAssignmentAdd.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(3, updatedStudy.getTreatmentAssignments().size());
//
//        for (TreatmentAssignment treatmentAssignment : updatedStudy.getTreatmentAssignments()) {
//            if (treatmentAssignment.getCode().equals("theCode1")) {
//                assertEquals(1, treatmentAssignment.getDoseLevelOrder().intValue());
//                assertEquals("description1", treatmentAssignment.getDescription());
//                assertEquals("Comments1", treatmentAssignment.getComments());
//            }
//            if (treatmentAssignment.getCode().equals("theCode3")) {
//                assertEquals(2, treatmentAssignment.getDoseLevelOrder().intValue());
//                assertEquals("description3", treatmentAssignment.getDescription());
//                assertEquals("Comments3", treatmentAssignment.getComments());
//            }
//            if (treatmentAssignment.getCode().equals("theCode4")) {
//                assertEquals(4, treatmentAssignment.getDoseLevelOrder().intValue());
//                assertEquals("description4", treatmentAssignment.getDescription());
//                assertEquals("Comments4", treatmentAssignment.getComments());
//            }
//        }
//
//
//    }
//
//
//    /**
//     * Study with short title Study PSC has 2 TreatmentAssignments.
//     * This testcase tests the deletion of a TreatmentAssignment from existing study.
//     * theCode1 and theCode3 are the existing treatment assignments.
//     * theCode3 is the treamentAssignment removed/deleted.
//     */
//    public void testStudyUpdateOfTreatmentAssignmentRemove() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfTreatmentAssignmentRemove.xml"));
//
//        studyProcessor.updateStudy(studies);
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(1, updatedStudy.getTreatmentAssignments().size());
//
//        for (TreatmentAssignment treatmentAssignment : updatedStudy.getTreatmentAssignments()) {
//            if (treatmentAssignment.getCode().equals("theCode1")) {
//                assertEquals(1, treatmentAssignment.getDoseLevelOrder().intValue());
//                assertEquals("description1", treatmentAssignment.getDescription());
//                assertEquals("Comments1", treatmentAssignment.getComments());
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 2 ctepStudyDiseases with term "Chondrosarcoma" and "Medulloblastoma" exists.
//     * Tests the addition of an other ctepStudyDisease with term "Osteosarcoma"
//     */
//    public void testStudyUpdateOfCtepStudyDiseasesAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfCtepStudyDiseasesAdd.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(3, updatedStudy.getCtepStudyDiseases().size());
//
//        for (CtepStudyDisease ctepStudyDisease : updatedStudy.getCtepStudyDiseases()) {
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Chondrosarcoma")) {
//                assertFalse(ctepStudyDisease.getLeadDisease());
//            }
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Osteosarcoma")) {
//                assertFalse(ctepStudyDisease.getLeadDisease());
//            }
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Medulloblastoma")) {
//                assertTrue(ctepStudyDisease.getLeadDisease());
//            }
//        }
//
//    }
//
//
//    /**
//     * 2 ctepStudyDiseases with terms "Chondrosarcoma" and "Medulloblastoma" exists.
//     * Tests the deletion of ctepStudyDisease with term "Chondrosarcoma"
//     */
//    public void testStudyUpdateOfCtepStudyDiseasesRemove() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfCtepStudyDiseasesRemove.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(1, updatedStudy.getCtepStudyDiseases().size());
//
//        for (CtepStudyDisease ctepStudyDisease : updatedStudy.getCtepStudyDiseases()) {
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Chondrosarcoma")) {
//                assertFalse(ctepStudyDisease.getLeadDisease());
//            }
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Medulloblastoma")) {
//                assertTrue(ctepStudyDisease.getLeadDisease());
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 2 ctepStudyDiseases with terms "Chondrosarcoma" and "Medulloblastoma" exists.
//     * leadDisease = false for Chondrosarcoma
//     * leadDisease = true for  Medulloblastoma
//     * <p/>
//     * Tests the leadDisease change.
//     * leadDisease = true for Chondrosarcoma
//     * leadDisease = false for  Medulloblastoma
//     */
//    public void testStudyUpdateOfCtepStudyDiseasesUpdate() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfCtepStudyDiseasesUpdate.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getCtepStudyDiseases().size());
//
//        for (CtepStudyDisease ctepStudyDisease : updatedStudy.getCtepStudyDiseases()) {
//            //System.out.println("Term  " + ctepStudyDisease.getDiseaseTerm().getCtepTerm());
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Chondrosarcoma")) {
//                assertTrue(ctepStudyDisease.getLeadDisease());
//            }
//            if (ctepStudyDisease.getDiseaseTerm().getCtepTerm().equals("Medulloblastoma")) {
//                assertFalse(ctepStudyDisease.getLeadDisease());
//            }
//        }
//
//    }
//
//
//    /**
//     * 2 meddraStudyDiseases with code "10000002" and "10000003" exists.
//     * Tests the addition of an other meddraStudyDisease with code "10000004"
//     */
//    public void testStudyUpdateOfMeddraStudyDiseasesAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfMeddraStudyDiseasesAdd.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(3, updatedStudy.getMeddraStudyDiseases().size());
//
//    }
//
//
//    /**
//     * 2 meddraStudyDiseases with code "10000002" and "10000003" exists.
//     * Tests the deletion of meddraStudyDisease with code "10000003"
//     */
//    public void testStudyUpdateOfMeddraStudyDiseasesRemove() throws Exception {
//
//        createStudy("studydata/CreateStudyTest_2.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfMeddraStudyDiseasesRemove.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(1, updatedStudy.getMeddraStudyDiseases().size());
//
//
//    }
//
//
//    /**
//     * 2 StudySites Cancer Therapy Evaluation Program and University of Jonathan Dean already exists
//     * Tests the addition of a third StudySite Sydney Hospital with one ResearchStaff
//     */
//    public void testStudyUpdateOfStudySiteAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySiteAdd.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(3, updatedStudy.getStudySites().size());
//
//
//    }
//
//
//    /**
//     * 2 StudySites "Cancer Therapy Evaluation Program" and "University of Jonathan Dean" already exists
//     * Tests the deletion of StudySite University of Jonathan Dean with one ResearchStaff
//     */
//    public void testStudyUpdateOfStudySiteRemove() throws Exception {
//
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySiteRemove.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(1, updatedStudy.getStudySites().size());
//
//    }
//
//
//    /**
//     * 2 StudyInvestigators are on for Cancer Therapy Evaluation Program
//     * Tests the addition of a StudyInvestigator to Cancer Therapy Evaluation Program (David Algor added)
//     */
//    public void testStudyUpdateOfStudySite_StudyInvestigatorAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySite_StudyInvestigatorAdd.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getStudySites().size());
//
//        for (StudySite studySite : updatedStudy.getStudySites()) {
//            if (studySite.getOrganization() != null) {
//                if ("Cancer Therapy Evaluation Program".equals(studySite.getOrganization().getName())) {
//                    assertEquals(3, studySite.getStudyInvestigators().size());
//                }
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 2 StudyInvestigators are on for Cancer Therapy Evaluation Program
//     * Tests the removal of a StudyInvestigator from StudySite (George Clinton Removed)
//     */
//    public void testStudyUpdateOfStudySite_StudyInvestigatorRemove() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySite_StudyInvestigatorRemove.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getStudySites().size());
//
//        for (StudySite studySite : updatedStudy.getStudySites()) {
//            if (studySite.getOrganization() != null) {
//                if ("Cancer Therapy Evaluation Program".equals(studySite.getOrganization().getName())) {
//                    assertEquals(1, studySite.getStudyInvestigators().size());
//                }
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 2 StudyInvestigators are on for Cancer Therapy Evaluation Program
//     * Tests the status update of a StudyInvestigator from "Active" to "Inactive"
//     * RoleCode updated from "Site Investigator" to "Site Principal Investigator" (George Clinton Status updated)
//     */
//    public void testStudyUpdateOfStudySite_StudyInvestigatorUpdate() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySite_StudyInvestigatorUpdate.xml"));
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getStudySites().size());
//
//        for (StudySite studySite : updatedStudy.getStudySites()) {
//            if (studySite.getOrganization() != null) {
//                if ("Cancer Therapy Evaluation Program".equals(studySite.getOrganization().getName())) {
//                    assertEquals(2, studySite.getStudyInvestigators().size());
//                    for (StudyInvestigator studyInvestigator : studySite.getStudyInvestigators()) {
//                        if ("George".equals(studyInvestigator.getSiteInvestigator().getInvestigator().getFirstName()) &&
//                                "Clinton".equals(studyInvestigator.getSiteInvestigator().getInvestigator().getLastName())) {
//                            assertEquals("Inactive", studyInvestigator.getStatusCode());
//                            assertEquals("Site Principal Investigator", studyInvestigator.getRoleCode());
//                        }
//                        if ("Gerry".equals(studyInvestigator.getSiteInvestigator().getInvestigator().getFirstName()) &&
//                                "Elbridge".equals(studyInvestigator.getSiteInvestigator().getInvestigator().getLastName())) {
//                            assertEquals("Active", studyInvestigator.getStatusCode());
//                            assertEquals("Site Principal Investigator", studyInvestigator.getRoleCode());
//                        }
//                    }
//                }
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 1 StudyPersonnel exists on for University of Jonathan Dean
//     * Tests the addition of a StudyPersonnel to University of Jonathan Dean (Roger Keith added)
//     */
//    public void testStudyUpdateOfStudySite_StudyPersonnelAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySite_StudyPersonnelAdd.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getStudySites().size());
//
//        for (StudySite studySite : updatedStudy.getStudySites()) {
//            if (studySite.getOrganization() != null) {
//                if ("University of Jonathan Dean".equals(studySite.getOrganization().getName())) {
//                    assertEquals(2, studySite.getStudyPersonnels().size());
//                }
//            }
//        }
//
//    }
//
//
//    /**
//     * 1 StudyPersonnel exists on for University of Jonathan Dean
//     * Tests the removal of all StudyPersonnel from University of Jonathan Dean site
//     */
//    public void testStudyUpdateOfStudySite_StudyPersonnelRemove() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySite_StudyPersonnelRemove.xml"));
//        
//        studyProcessor.updateStudy(studies);
//        
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//
//        assertEquals(2, updatedStudy.getStudySites().size());
//
//        for (StudySite studySite : updatedStudy.getStudySites()) {
//            if (studySite.getOrganization() != null) {
//                if ("University of Jonathan Dean".equals(studySite.getOrganization().getName())) {
//                    assertEquals(0, studySite.getStudyPersonnels().size());
//                }
//            }
//        }
//
//    }
//
//
//    /**
//     * 1 StudyPersonnel exists on for University of Jonathan Dean
//     * Tests the updation of status and roleCode
//     */
//    public void testStudyUpdateOfStudySite_StudyPersonnelUpdate() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudySite_StudyPersonnelUpdate.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//
//        assertEquals(2, updatedStudy.getStudySites().size());
//
//        for (StudySite studySite : updatedStudy.getStudySites()) {
//            if (studySite.getOrganization() != null) {
//                if ("University of Jonathan Dean".equals(studySite.getOrganization().getName())) {
//                    assertEquals(1, studySite.getStudyPersonnels().size());
//                    for (StudyPersonnel studyPersonnel : studySite.getStudyPersonnels()) {
//                        if ("Allan".equals(studyPersonnel.getResearchStaff().getFirstName()) &&
//                                "Border".equals(studyPersonnel.getResearchStaff().getLastName())) {
//                            assertEquals("Inactive", studyPersonnel.getStatusCode());
//                            assertEquals("Study Coordinator", studyPersonnel.getRoleCode());
//                        }
//                    }
//                }
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 1 StudyAgent "1-Aminocyclopentane" exists for Study "Study PSC"
//     * Tests the addition of an other StudyAgent "17-Methyltestosterone"
//     */
//    public void testStudyUpdateOfStudyAgentAdd() throws Exception {
//
//        createStudy("studydata/CreateStudyTest.xml");
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudyAgentAdd.xml"));
//
//
//        studyProcessor.updateStudy(studies);
//
//        SecurityTestUtils.switchToSuperuser();
//
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//        assertEquals(2, updatedStudy.getStudyAgents().size());
//
//    }
//
//
//    /**
//     * 2 StudyAgent "1-Aminocyclopentane" and "17-Methyltestosterone" exists for Study "Study PSC"
//     * Tests the removal of StudyAgent "17-Methyltestosterone"
//     */
//    public void testStudyUpdateOfStudyAgentRemove() throws Exception {
//
//        createStudy("studydata/CreateStudyTest_2.xml");
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream("studydata/StudyUpdateOfStudyAgentRemove.xml"));
//        
//        studyProcessor.updateStudy(studies);
//        
//        SecurityTestUtils.switchToSuperuser();
//
//        updatedStudy = studyDao.getByShortTitle("Study PCS");
//        assertNotNull(updatedStudy);
//        updatedStudy = studyDao.getStudyDesignById(updatedStudy.getId());
//
//        assertNotNull(updatedStudy);
//        assertEquals(1, updatedStudy.getStudyAgents().size());
//
//    }
//
//    private void createStudy(String studyXmlLocation) throws Exception {
//
//
//        studies = (gov.nih.nci.cabig.caaers.webservice.Studies) unmarshaller.unmarshal(createInputStream(studyXmlLocation));
//
//        studyProcessor.createStudy(studies);
//
//
//    }
//
//    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
//        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
//        if (testDataStream == null) {
//            testDataStream = handleTestDataFileNotFound();
//            // if it is still null, fail gracefully
//            if (testDataStream == null) {
//                throw new NullPointerException(
//                        "Test data resource " + ResourceRetriever.getResourceName(getClass().getPackage(), testDataFileName)
//                                + " not found and fallback call to handleTestDataFileNotFound() did not provide a substitute.");
//            }
//        }
//        return testDataStream;
//    }


	public void testTest(){
		assertTrue(true);
	}
}
