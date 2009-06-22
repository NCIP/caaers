package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.Dose;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * RuleName : SEC_BR1_CHK Rule : Either Course Information and/or Radiation Intervention must be
     * provided for AGENTS + RADIATION pathways Error Code : SEC_BR1_ERR Error Message : Either
     * COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testChemo_RadiationStudy_HavingRadiation() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);
        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.RADIATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is radiation intervention for DRug/radiation study");
    }

    /**
     * RuleName : SEC_BR1_CHK Rule : Either Course Information and/or Radiation Intervention must be
     * provided for AGENTS + RADIATION pathways Error Code : SEC_BR1_ERR Error Message : Either
     * COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testChemo_RadiationStudy_HavingCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.RADIATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is drugs for DRug/radiation study");

    }

    /**
     * RuleName : SEC_BR1_CHK Rule : Either Course Information and/or Radiation Intervention must be
     * provided for AGENTS + RADIATION pathways Error Code : SEC_BR1_ERR Error Message : Either
     * COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testChemo_RadiationStudy_HavingRadiationAndCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.RADIATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is drugs and radiation for DRug/radiation study");

    }

    /**
     * RuleName : SEC_BR1_CHK Rule : Either Course Information and/or Radiation Intervention must be
     * provided for AGENTS + RADIATION pathways Error Code : SEC_BR1_ERR Error Message : Either
     * COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testChemo_RadiationStudy_NotHavingRadiationOrCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.RADIATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SEC_BR1_ERR");
        assertSameErrorCount(errors, 1);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.firstCourseDate", "aeReport.treatmentInformation.adverseEventCourse.date", "aeReport.treatmentInformation.adverseEventCourse.number", "aeReport.treatmentInformation.totalCourses");

    }

    /**
     * RuleName : SEC_BR2_CHK Rule : Either Course Information and/or Surgery Intervention must be
     * provided for AGENTS + SURGERY pathways Error Code : SEC_BR2_ERR Error Message : Either
     * COURSE_INFORMATION and/or SURGERY_INTERVENTION must be provided.
     */
    public void testChemo_SurgeryStudy_HavingSurgery() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);
        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When surger/course info is available");

    }

    /**
     * RuleName : SEC_BR2_CHK Rule : Either Course Information and/or Surgery Intervention must be
     * provided for AGENTS + SURGERY pathways Error Code : SEC_BR2_ERR Error Message : Either
     * COURSE_INFORMATION and/or SURGERY_INTERVENTION must be provided.
     */
    public void testChemo_SurgeryStudy_HavingCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When course info is available");

    }

    /**
     * RuleName : SEC_BR2_CHK Rule : Either Course Information and/or Surgery Intervention must be
     * provided for AGENTS + SURGERY pathways Error Code : SEC_BR2_ERR Error Message : Either
     * COURSE_INFORMATION and/or SURGERY_INTERVENTION must be provided.
     */
    public void testChemo_SurgeryStudy_HavingCourseAndSurgery() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When surger/course info is available");

    }

    /**
     * RuleName : SEC_BR2_CHK Rule : Either Course Information and/or Surgery Intervention must be
     * provided for AGENTS + SURGERY pathways Error Code : SEC_BR2_ERR Error Message : Either
     * COURSE_INFORMATION and/or SURGERY_INTERVENTION must be provided.
     */
    public void testChemo_SurgeryStudy_HavingRadiationAndCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.SURGERY,
                StudyTherapyType.RADIATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When course info is available");
    }

    /**
     * RuleName : SEC_BR2_CHK Rule : Either Course Information and/or Surgery Intervention must be
     * provided for AGENTS + SURGERY pathways Error Code : SEC_BR2_ERR Error Message : Either
     * COURSE_INFORMATION and/or SURGERY_INTERVENTION must be provided.
     */
    public void testChemo_SurgeryStudy_HavingNoSurgeryAndCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] {
                StudyTherapyType.DRUG_ADMINISTRATION, StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SEC_BR2_ERR");
        assertSameErrorCount(errors, 1);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.firstCourseDate", "aeReport.treatmentInformation.adverseEventCourse.date", "aeReport.treatmentInformation.adverseEventCourse.number", "aeReport.treatmentInformation.totalCourses");

    }

    /**
     * RuleName : SEC_BR3_CHK Rule : Either Surgery Intervention and/or Radiation Intervention must
     * be provided for SURGERY + RADIATION pathways Error Code : SEC_BR3_ERR Error Message : Either
     * SURGERY_INTERVENTION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testSurgeryRadiationStudy_HavingSurgery() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);

        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When surgery info is available");
    }

    /**
     * RuleName : SEC_BR3_CHK Rule : Either Surgery Intervention and/or Radiation Intervention must
     * be provided for SURGERY + RADIATION pathways Error Code : SEC_BR3_ERR Error Message : Either
     * SURGERY_INTERVENTION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testSurgeryRadiationStudy_HavingRadiation() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When radiation info is available");

    }

    /**
     * RuleName : SEC_BR3_CHK Rule : Either Surgery Intervention and/or Radiation Intervention must
     * be provided for SURGERY + RADIATION pathways Error Code : SEC_BR3_ERR Error Message : Either
     * SURGERY_INTERVENTION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testSurgeryRadiationStudy_HavingSurgeryAndRadiation() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);

        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When radiation/surgery info is available");

    }

    /**
     * RuleName : SEC_BR3_CHK Rule : Either Surgery Intervention and/or Radiation Intervention must
     * be provided for SURGERY + RADIATION pathways Error Code : SEC_BR3_ERR Error Message : Either
     * SURGERY_INTERVENTION and/or RADIATION_INTERVENTION must be provided.
     */
    public void testSurgeryRadiationStudy_HavingNoSurgeryAndRadiation() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SEC_BR3_ERR");
        assertSameErrorCount(errors, 1);

    }

    /**
     * RuleName : SEC_BR4_CHK Rule : Either Course Information and/or Surgery Intervention and/or
     * Radiation Intervention must be provided for AGENTS + SURGERY + RADIATION pathways Error Code :
     * SEC_BR4_ERR Error Message : Either COURSE_INFORMATION and/or RADIATION_INTERVENTION and/or
     * SURGERY_INTERVENTION must be provided.
     */

    public void testCourseSurgeryRadiationStudy_HavingRadiation() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);
        aeReport.setTreatmentInformation(null);
        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY, StudyTherapyType.DRUG_ADMINISTRATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When radiation info is available");

    }

    /**
     * RuleName : SEC_BR4_CHK Rule : Either Course Information and/or Surgery Intervention and/or
     * Radiation Intervention must be provided for AGENTS + SURGERY + RADIATION pathways Error Code :
     * SEC_BR4_ERR Error Message : Either COURSE_INFORMATION and/or RADIATION_INTERVENTION and/or
     * SURGERY_INTERVENTION must be provided.
     */

    public void testCourseSurgeryRadiationStudy_HavingRadiationAndCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY, StudyTherapyType.DRUG_ADMINISTRATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When radiation/course info is available");

    }

    /**
     * RuleName : SEC_BR4_CHK Rule : Either Course Information and/or Surgery Intervention and/or
     * Radiation Intervention must be provided for AGENTS + SURGERY + RADIATION pathways Error Code :
     * SEC_BR4_ERR Error Message : Either COURSE_INFORMATION and/or RADIATION_INTERVENTION and/or
     * SURGERY_INTERVENTION must be provided.
     */

    public void testCourseSurgeryRadiationStudy_HavingRadiationSurgeryAndCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        aeReport.addRadiationIntervention(radiationIntervention);

        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY, StudyTherapyType.DRUG_ADMINISTRATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When radiation/course/surgery info is available");

    }

    /**
     * RuleName : SEC_BR4_CHK Rule : Either Course Information and/or Surgery Intervention and/or
     * Radiation Intervention must be provided for AGENTS + SURGERY + RADIATION pathways Error Code :
     * SEC_BR4_ERR Error Message : Either COURSE_INFORMATION and/or RADIATION_INTERVENTION and/or
     * SURGERY_INTERVENTION must be provided.
     */

    public void testCourseSurgeryRadiationStudy_HavingNoRadiationOrSurgeryOrCourse()
                    throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.setTreatmentInformation(null);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION, StudyTherapyType.SURGERY, StudyTherapyType.DRUG_ADMINISTRATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SEC_BR4_ERR");
        assertSameErrorCount(errors, 1, "When no course/surger/radiation is present");

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.firstCourseDate", "aeReport.treatmentInformation.adverseEventCourse.date", "aeReport.treatmentInformation.adverseEventCourse.number", "aeReport.treatmentInformation.totalCourses");
    }

    /**
     * RuleName : SEC_BR50_CHK Rule : Surgery intervention must not be provided for non SURGERY
     * pathways Error Code : SEC_BR50_ERR Error Message : Surgery intervention must not be provided
     * since the study you selected does not involve a SURGERY
     * 
     * @throws Exception
     */
    public void testSurgeryStudyHavingSurgeryInterviention() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        aeReport.addSurgeryIntervention(surgeryIntervention);

        Study s = aeReport.getStudy();
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.SURGERY };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

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
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

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
        d1.setAmount(new BigDecimal(5));
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
        d1.setAmount(new BigDecimal(5));
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
            d.setAmount(new BigDecimal(9));
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
            d.setAmount(new BigDecimal(9));
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
    	Error Message : Agent is incorrect and is removed from protocol.
     */
    public void testAllAgentsPresentInReportAreRetired() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	TreatmentInformation ti = aeReport.getTreatmentInformation();
    	ti.getCourseAgents().get(0).getStudyAgent().retire();
    	ti.getCourseAgents().get(1).getStudyAgent().retire();
    	
    	
    	ValidationErrors errors = fireRules(aeReport);


        assertSameErrorCount(errors,2, "When all study agent has been retired");
        assertCorrectErrorCode(errors, "PAG_BR5_ERR");
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.treatmentInformation.courseAgents[0].studyAgent");
        assertCorrectFieldNames(errors.getErrorAt(1), "aeReport.treatmentInformation.courseAgents[1].studyAgent");
    }
    
}

