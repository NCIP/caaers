package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

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
        StudyTherapyType[] therapies = new StudyTherapyType[] { StudyTherapyType.RADIATION,
                StudyTherapyType.SURGERY, StudyTherapyType.DRUG_ADMINISTRATION };
        for (int i = 0; i < therapies.length; i++) {
            StudyTherapy st = new StudyTherapy();
            st.setId(i + 1);
            st.setStudyTherapyType(therapies[i]);
            s.addStudyTherapy(st);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SEC_BR4_ERR");
        assertSameErrorCount(errors, 1, "When no course/surger/radiation is present");
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

}
