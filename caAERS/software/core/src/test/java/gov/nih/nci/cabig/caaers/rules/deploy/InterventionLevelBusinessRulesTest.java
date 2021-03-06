/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class InterventionLevelBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {

    @Override
    public String getBindUri() {
        return "gov.nih.nci.cabig.caaers.rules.reporting_submit_report_section";
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_study_interventions.xml";
    }

    /**
     * RuleName : SEC_BR50_CHK Rule : Surgery intervention must not be provided for non SURGERY
     * pathways Error Code : SEC_BR50_ERR Error Message : Surgery intervention must not be provided
     * since the study you selected does not involve a SURGERY
     * 
     * @throws Exception
     */
    public void testSurgeryStudyHavingSurgeryIntervention() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);
        Study s = aeReport.getStudy();

        OtherIntervention oi = new OtherIntervention();
        oi.setStudyTherapyType(StudyTherapyType.SURGERY);
        s.addOtherIntervention(oi);

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When surgery is present on a surgery study");
    }

    /*
     * RuleName : SEC_BR50_CHK Rule : Surgery intervention must not be provided for non SURGERY
     * pathways Error Code : SEC_BR50_ERR Error Message : Surgery intervention must not be provided
     * since the study you selected does not involve a SURGERY
     * 
     */
    public void testNonSurgeryStudyHavingSurgeryIntervention() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);

        Study s = aeReport.getStudy();
        OtherIntervention oi = new OtherIntervention();
        oi.setStudyTherapyType(StudyTherapyType.RADIATION);
        s.addOtherIntervention(oi);

        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 1, "when surgery is present on a non surgery study");
        assertCorrectErrorCode(errors, "SEC_BR50_ERR");
    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testUniqueStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When agents are unique");
    }
    
    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testDuplicateStudyAgents_OnlyOneEmptyAgent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        List<CourseAgent> courseAgents = new ArrayList<CourseAgent>();
        CourseAgent ca1 = new CourseAgent();
        ca1.setTreatmentInformation(aeReport.getTreatmentInformation());
        courseAgents.add(ca1);
        
        aeReport.getTreatmentInformation().setCourseAgentsInternal(courseAgents);
        
         ValidationErrors errors = fireRules(aeReport);
         assertNoErrors(errors, "When only one empty agent is there are unique");
    }
    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testDuplicateStudyAgents_OnlyOneEmptyAgentAndOtherValidAgent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        CourseAgent ca1 = new CourseAgent();
        ca1.setTreatmentInformation(aeReport.getTreatmentInformation());
        
        aeReport.getTreatmentInformation().getCourseAgentsInternal().add(ca1);
        
         ValidationErrors errors = fireRules(aeReport);
         assertNoErrors(errors, "When only one empty agent is there are unique");
    }
    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testDuplicateStudyAgents_WithOneAgentEmpty() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }
        
        CourseAgent ca1 = new CourseAgent();
        ca1.setTreatmentInformation(aeReport.getTreatmentInformation());
        aeReport.getTreatmentInformation().getCourseAgentsInternal().add(1, ca1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 1);
    }
    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testDuplicateStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testTwoOutOfThreeStudyAgentsSame() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }

        // add one more study agent.
        CourseAgent ca1 = new CourseAgent();
        Dose d1 = new Dose();
        d1.setAmount("5");
        d1.setUnits("abc1");

        StudyAgent sa1 = new StudyAgent();
        Agent a = new Agent();
        a.setName("xyz agent");
        sa1.setAgent(a);
        sa1.setId(44);
        ca1.setDose(d1);
        ca1.setStudyAgent(sa1);
        aeReport.getTreatmentInformation().addCourseAgent(ca1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testThreeOutOfThreeStudyAgentsSame() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }

        // add one more study agent.
        CourseAgent ca1 = new CourseAgent();
        Dose d1 = new Dose();
        d1.setAmount("5");
        d1.setUnits("abc1");
        ca1.setDose(d1);

        StudyAgent sa1 = new StudyAgent();
        Agent a = new Agent();
        a.setName("xyz");
        sa1.setAgent(a);
        sa1.setId(4);

        ca1.setStudyAgent(sa1);
        aeReport.getTreatmentInformation().addCourseAgent(ca1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replacements (index) should be correct", 2, errors.getErrorAt(0).getReplacementVariables()[0]);
        assertEquals("Replacements (agentName) should be correct", "xyz", errors.getErrorAt(0).getReplacementVariables()[1]);
        assertEquals("Replacements (index) should be correct", 3, errors.getErrorAt(1).getReplacementVariables()[0]);
        assertEquals("Replacements (agentName) should be correct", "xyz", errors.getErrorAt(1).getReplacementVariables()[1]);
    }
    
    

    /**
     * RuleName : SEC_BR5B_CHK Rule : Protocol agents must be not be provided if Course Information
     * has not been provided. Error Code : SEC_BR5A_ERR Error Message : PROTOCOL_AGENTS must not be
     * provided if COURSE_INFOMATION is not provided.
     */
    public void testNoAgent_And_NoCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setCourseAgentsInternal(new ArrayList<CourseAgent>());

        aeReport.getTreatmentInformation().getAdverseEventCourse().setDate(null);
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(null);
        aeReport.getTreatmentInformation().setFirstCourseDate(null);
        aeReport.getTreatmentInformation().setTotalCourses(null);

        ValidationErrors errors = fireRules(aeReport);

        assertNoErrors(errors, "When no courseAgent and Course Information is present");

    }

    /**
     * RuleName : SEC_BR5B_CHK Rule : Protocol agents must be not be provided if Course Information
     * has not been provided. Error Code : SEC_BR5A_ERR Error Message : PROTOCOL_AGENTS must not be
     * provided if COURSE_INFOMATION is not provided.
     */
    public void testAgents_And_Course() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is Agents and Course Information available");
    }


    /**
     * RuleName : SEC_BR5B_CHK Rule : Protocol agents must be not be provided if Course Information
     * has not been provided. Error Code : SEC_BR5B_ERR Error Message : PROTOCOL_AGENTS must not be
     * provided if COURSE_INFOMATION is not provided.
     */
    public void testAgent_WithNoCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getAdverseEventCourse().setDate(null);
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(null);
        aeReport.getTreatmentInformation().setFirstCourseDate(null);
        aeReport.getTreatmentInformation().setTotalCourses(null);

        ValidationErrors errors = fireRules(aeReport);

        assertSameErrorCount(errors, 1, "When Agent information is provided without Course information");
        assertCorrectErrorCode(errors, "SEC_BR5B_ERR");
    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testNoLastAdministeredDate_NonInvestigationalStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.setLastAdministeredDate(null);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertEquals("There should not be any error, when NON ind agents dont have last administred date", 0, errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testLastAdministeredDate_InvestigationalStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.setLastAdministeredDate(new Date());

            StudyAgent sa1 = new StudyAgent();
            sa1.setId(6 + i++);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when ind agents have last administred date",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testNoLastAdministeredDate_InvestigationalStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {

            StudyAgent sa1 = new StudyAgent();
            sa1.setId(5 + i++);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("Errors should be there when LastAdministeredDate is not available for IND agents", 2, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR3_ERR", errors.getErrorAt(0).getCode());
        assertEquals("Error replacement variable should be correct", 0, errors.getErrorAt(0).getReplacementVariables()[0]);
        assertEquals("Error replacement variable should be correct", 1, errors.getErrorAt(1).getReplacementVariables()[0]);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object j = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.courseAgents[" + j + "].lastAdministeredDate");

    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testNoLastAdministeredDate_ForOneOutOfTwo_InvestigationalStudyAgents()
            throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            if (i == 0) ca.setLastAdministeredDate(new Date());
            i++;
            StudyAgent sa1 = new StudyAgent();
            sa1.setId(5 + i);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("Errors should be there when LastAdministeredDate is not available for IND agents",1, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR3_ERR", errors.getErrorAt(0).getCode());
        assertEquals("Error replacement variable should be correct", 1, errors.getErrorAt(0).getReplacementVariables()[0]);

    }

    /**
     * RuleName : PAG_BR2B_CHK Rule : "'Unit of measure' must be provided if 'Duration of delay' is
     * provided. Error Code : PAG_BR2B_ERR Error Message : DELAY_UOM must be provided if the DELAY
     * is provided
     *
     * @throws Exception
     */
    public void testCourseAgent_HavingNoDose() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount(null);
            d.setUnits(null);
            ca.setDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors);
    }

    /**
     * RuleName : PAG_BR2B_CHK Rule : "'Unit of measure' must be provided if 'Duration of delay' is
     * provided. Error Code : PAG_BR2B_ERR Error Message : DELAY_UOM must be provided if the DELAY
     * is provided
     *
     * @throws Exception
     */
    public void testCourseAgent_HavingTotalDoseAndUnits() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount("9");
            d.setUnits("KK");
            ca.setDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors);
    }

    /**
     * RuleName : PAG_BR2B_CHK Rule : "'Unit of measure' must be provided if 'Duration of delay' is
     * provided. Error Code : PAG_BR2B_ERR Error Message : DELAY_UOM must be provided if the DELAY
     * is provided
     *
     * @throws Exception
     */
    public void testCourseAgent_NotHavingTotalDoseWithoutUOM() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount("9");
            ca.setDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_BR2B_ERR");
        assertSameErrorCount(errors, 2, "When 2 of the course agents has no UOM for dose");

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object j = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.courseAgents[" + j + "].dose.units", "aeReport.treatmentInformation.courseAgents[" + j + "].dose.amount");
        
    }
    
    /**
     * RuleName : PAG_BR4_CHK Rule : 'Was an investigational agent administered on this protocol?' must be 'Yes' if one of the agents is investigational agent
     * Error Code : PAG_BR4_ERR Error Message : investigationalAgentAdministered must be true
     */
    
    public void testInvestigationalAgentAdministeredNo_InvestigationalStudyAgents()
            throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        TreatmentInformation ti = aeReport.getTreatmentInformation();
        ti.setInvestigationalAgentAdministered(false);
        int i = 0;
        for (CourseAgent ca : ti.getCourseAgents()) {
            ca.setLastAdministeredDate(new Date());
            i++;
            StudyAgent sa1 = new StudyAgent();
            sa1.setId(5 + i);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("Error code should be same", "PAG_BR4_ERR", errors.getErrorAt(0).getCode());


    }
    
    /**
     * 	RuleName : PAG_BR5_CHK
    	Rule : 'Protocol Agent' must be active."
    	Error Code : PAG_BR5_ERR
    	Error Message : Agent is incorrect and is removed from protocol.
     */
    public void testRetiredAgentPresentInReport() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	TreatmentInformation ti = aeReport.getTreatmentInformation();
    	ti.getCourseAgents().get(0).getStudyAgent().retire();
    	
    	ValidationErrors errors = fireRules(aeReport);


        assertSameErrorCount(errors, 1, "When study agent has been retired");
        assertCorrectErrorCode(errors, "PAG_BR5_ERR");
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.courseAgents[0].studyAgent");
    }
    
    /**
     * 	RuleName : PAG_BR5_CHK
    	Rule : 'Protocol Agent' must be active."
    	Error Code : PAG_BR5_ERR
    	Error Message : Study Agent is incorrect and is removed from protocol.
     */
    public void testAllStudyAgentsPresentInReportAreRetired() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	TreatmentInformation ti = aeReport.getTreatmentInformation();
    	ti.getCourseAgents().get(0).getStudyAgent().retire();
    	ti.getCourseAgents().get(1).getStudyAgent().retire();
    	
    	
    	ValidationErrors errors = fireRules(aeReport);


        assertSameErrorCount(errors,2, "When all study agents have been retired");
        assertCorrectErrorCode(errors, "PAG_BR5_ERR");
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.courseAgents[0].studyAgent");
        assertCorrectFieldNames(errors.getErrorAt(1), "aeReport.treatmentInformation.courseAgents[1].studyAgent");
    }
    
    /**
     * 	RuleName : SME_BR12_CHK
    	Rule : 'Agent' must be active."
    	Error Code : SME_BR12_ERR
    	Error Message : Agent is incorrect and is removed from protocol.
     */
    public void testAllAgentsPresentInReportAreRetired() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	TreatmentInformation ti = aeReport.getTreatmentInformation();
    	ti.getCourseAgents().get(0).getStudyAgent().getAgent().retire();
    	
    	ValidationErrors errors = fireRules(aeReport);


        assertSameErrorCount(errors,1, "When all agents have been retired");
        assertCorrectErrorCode(errors, "SME_BR12_ERR");
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.courseAgents[0].studyAgent.agent");
    }


    /**
     * RuleName : SME_BR4_CHK, SME_BR5_CHK
     * Rule : "Name of Reprocessor" cannot be given if IS_SINGLE_USE_DEVICE is not YES.
     * : "Name of Reprocessor" should be given if IS_SINGLE_USE_DEVICE is "YES".
     */
    public void testReprocessorNameProvided() throws Exception{
        ExpeditedAdverseEventReport aeReport = createAEReport();
        StudyDevice sd1 = new StudyDevice();
        aeReport.getStudy().addStudyDevice(sd1);
        StudyDevice sd = Fixtures.createStudyDevice();
        MedicalDevice device = new MedicalDevice(sd);
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().getDevice().setCommonName(null);
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors);
    }

    /**
     * RuleName : SME_BR4_CHK, SME_BR5_CHK
     * Rule : "Name of Reprocessor" cannot be given if IS_SINGLE_USE_DEVICE is not YES.
     * : "Name of Reprocessor" should be given if IS_SINGLE_USE_DEVICE is "YES".
     */
    public void testReprocessorNameMissing() throws Exception{
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        StudyDevice sd = Fixtures.createStudyDevice();
        MedicalDevice device = new MedicalDevice(sd);
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().getDevice().setCommonName(null);
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR5_ERR");
        assertSameErrorCount(errors, 1, "Name of Reprocessor should be given if reprocessed is YES");
        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertEquals("aeReport.medicalDevices[0].reprocessorName", errors.getErrorAt(0).getFieldNames()[0]);
    }



    /**
     * RuleName : SME_BR4_CHK, SME_BR5_CHK
     * Rule : "Name of Reprocessor" cannot be given if IS_SINGLE_USE_DEVICE is not YES.
     * : "Name of Reprocessor" should be given if IS_SINGLE_USE_DEVICE is "YES".
     */
    public void testReprocessorNameMustNotBeProvided() throws Exception{
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().getDevice().setCommonName(null);
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.NO);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR4_ERR");
        assertSameErrorCount(errors, 1, "Name of Reprocessor should not be given if reprocessed is YES");
        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertEquals("aeReport.medicalDevices[0].reprocessorName", errors.getErrorAt(0).getFieldNames()[0]);

    }


    /**
     * RuleName : SME_BR6_CHK,SME_BR7_CHK
     * Rule : Address of Reprocessor' cannot be given if IS_SINGLE_USE_DEVICE is not 'Yes'
     * : 'Address of Reprocessor' should be given if IS_SINGLE_USE_DEVICE is 'Yes'.
     */
    public void testReprocessorAddressProvided() throws Exception{
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().getDevice().setCommonName(null);
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors);
    }



    /**
     * RuleName : SME_BR6_CHK,SME_BR7_CHK
     * Rule : Address of Reprocessor' cannot be given if IS_SINGLE_USE_DEVICE is not 'Yes'
     * : 'Address of Reprocessor' should be given if IS_SINGLE_USE_DEVICE is 'Yes'.
     */
    public void testReprocessorAddressMissing() throws Exception{
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR7_ERR");
        assertSameErrorCount(errors, 1, "Address of Reprocessor should be given if reprocessed is YES");
        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertEquals("aeReport.medicalDevices[0].reprocessorAddress", errors.getErrorAt(0).getFieldNames()[0]);
    }



    /**
     * RuleName : SME_BR6_CHK,SME_BR7_CHK
     * Rule : Address of Reprocessor' cannot be given if IS_SINGLE_USE_DEVICE is not 'Yes'
     * : 'Address of Reprocessor' should be given if IS_SINGLE_USE_DEVICE is 'Yes'.
     */
    public void testReprocessorAddressMustNotBeProvided() throws Exception{
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setDeviceReprocessed(ReprocessedDevice.NO);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR6_ERR");
        assertSameErrorCount(errors, 1, "Address of Reprocessor should not be given if reprocessed is YES");
        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertEquals("aeReport.medicalDevices[0].reprocessorAddress", errors.getErrorAt(0).getFieldNames()[0]);

    }

    /**
     * RuleName : SME_BR9_CHK
    Rule : 'Returned Date' should be given if DEVICE is not 'Returned'.
    Error Code : SME_BR9_ERR
    Error Message : 'Returned Date' should be given if DEVICE is not 'Returned'.  
     * @throws Exception
     */
    public void testReturnedDateMissing() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setEvaluationAvailability(Availability.YES);
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);

        device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setEvaluationAvailability(Availability.RETURNED);
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR9_ERR");
        assertSameErrorCount(errors, 1, "Returned date should  be given if evaluation availability is returned");
        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertEquals("aeReport.medicalDevices[1].returnedDate", errors.getErrorAt(0).getFieldNames()[0]);
    }

    /**
     * RuleName : SME_BR8_CHK
    Rule : 'Returned Date' cannot be given if DEVICE is not 'Returned'.
    Error Code : SME_BR8_ERR
    Error Message : 'Returned Date' cannot be given if DEVICE is not 'Returned'.     
     * @throws Exception
     */
    public void testReturnedDateMustNotBeProvided() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        aeReport.addMedicalDevice(device);

        device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        device.setReturnedDate(new Date());
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR8_ERR");
        assertSameErrorCount(errors, 1, "Returned date should not be given if evaluation availability is not returned");
        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertEquals("aeReport.medicalDevices[1].returnedDate", errors.getErrorAt(0).getFieldNames()[0]);
    }

    /**
     * RuleName : SME_BR10_CHK
     * Rule : DEVICE_OPERATOR_DESCRIPTION must not be provided if DEVICE_OPERATOR is not 'Other'
     * Error: SME_BR10_ERR 
     */
    public void testDeviceOperatorNoOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);

        device.setDeviceOperator(DeviceOperator.HEALTH_PROFESSIONAL);
        device.setOtherDeviceOperator("Some Other Device Operator");

        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertEquals(1, errors.getErrorCount());
        assertCorrectErrorCode(errors, "SME_BR10_ERR");
        assertEquals("aeReport.medicalDevices[0].deviceOperator", errors.getErrorAt(0).getFieldNames()[0]);
        assertEquals("aeReport.medicalDevices[0].otherDeviceOperator", errors.getErrorAt(0).getFieldNames()[1]);
    }

    /**
     * RuleName : SME_BR11_CHK
     * Rule : DEVICE_OPERATOR_DESCRIPTION must not be provided if DEVICE_OPERATOR is not 'Other'
     * Error: SME_BR11_ERR
     */
    public void testDeviceOperatorOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");

        device.setDeviceReprocessed(ReprocessedDevice.YES);
        device.setDeviceOperator(DeviceOperator.OTHER);
        
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertEquals(1, errors.getErrorCount());
        assertCorrectErrorCode(errors, "SME_BR11_ERR");
        assertEquals("aeReport.medicalDevices[0].deviceOperator", errors.getErrorAt(0).getFieldNames()[0]);
        assertEquals("aeReport.medicalDevices[0].otherDeviceOperator", errors.getErrorAt(0).getFieldNames()[1]);
    }

    /**
     * RuleName : SME_BR10_CHK
     * Rule : DEVICE_OPERATOR_DESCRIPTION must not be provided if DEVICE_OPERATOR is not 'Other'
     * Error: No Error
     *
     */
    public void testDeviceOperatorOtherOK() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        device.setDeviceOperator(DeviceOperator.HEALTH_PROFESSIONAL);
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertEquals(0, errors.getErrorCount());
    }

    /**
     * RuleName : SME_BR11_CHK
     * Rule : DEVICE_OPERATOR_DESCRIPTION must be provided if DEVICE_OPERATOR is 'Other'
     * Error: No Error
     */
    public void testDeviceOperatorOtherOK2() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getStudy().addStudyDevice(new StudyDevice());
        MedicalDevice device = new MedicalDevice(Fixtures.createStudyDevice());
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        device.setDeviceOperator(DeviceOperator.OTHER);
        device.setOtherDeviceOperator("SOME OTHER OPERATOR");
        aeReport.addMedicalDevice(device);
        ValidationErrors errors = fireRules(aeReport);
        assertEquals(0, errors.getErrorCount());
    }
    
    
    /**
     * 	RuleName : SME_BR13_CHK
    	Rule : 'Study Device' must be active."
    	Error Code : SME_BR13_ERR
    	Error Message : Study Device or Device is incorrect and is removed from protocol.
     */
    public void testRetiredStudyDevicePresentInReport() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	StudyDevice studyDevice = Fixtures.createStudyDevice();
    	aeReport.getStudy().getStudyDevices().add(studyDevice);
    	
    	MedicalDevice device = new MedicalDevice(studyDevice);
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        device.setDeviceOperator(DeviceOperator.OTHER);
        device.setOtherDeviceOperator("SOME OTHER OPERATOR");
        aeReport.addMedicalDevice(device);
        studyDevice.retire();
    	
    	ValidationErrors errors = fireRules(aeReport); 


        assertSameErrorCount(errors, 2, "When study device has been retired");
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.medicalDevices[0].studyDevice");
    }
    
    /**
     * 	RuleName : SME_BR13_CHK
    	Rule : 'Study Device' must be active."
    	Error Code : SME_BR13_ERR
    	Error Message : Study Device or Device is incorrect and is removed from protocol.
     */
    public void testRetiredDevicePresentInReport() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	StudyDevice studyDevice = Fixtures.createStudyDevice();
    	aeReport.getStudy().getStudyDevices().add(studyDevice);
    	
    	MedicalDevice device = new MedicalDevice(studyDevice);
        device.getStudyDevice().getDevice().setBrandName("Brand Name");
        device.getStudyDevice().setModelNumber("123");
        device.setReprocessorAddress("test");
        device.setReprocessorName("test");
        device.setDeviceReprocessed(ReprocessedDevice.YES);
        device.setDeviceOperator(DeviceOperator.OTHER);
        device.setOtherDeviceOperator("SOME OTHER OPERATOR");
        aeReport.addMedicalDevice(device);
        studyDevice.getDevice().retire();
    	
    	ValidationErrors errors = fireRules(aeReport); 


        assertSameErrorCount(errors, 1, "When device has been retired");
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.medicalDevices[0].studyDevice");
    }

}

