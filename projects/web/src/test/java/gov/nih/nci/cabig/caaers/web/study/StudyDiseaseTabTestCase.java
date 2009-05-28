package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCondition;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.springframework.validation.ObjectError;

/**
 * @author Ion C. Olaru
 */

public class StudyDiseaseTabTestCase extends AbstractStudyWebTestCase {

   

    protected StudyTab createTab() {
        DiseaseTab tab = new DiseaseTab();
        tab.setConfigurationProperty(new ConfigProperty());

        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        tab.getConfigurationProperty().setMap(map);
        tab.setLowLevelTermDao(lowLevelTermDao);
        tab.setDiseaseTermDao(diseaseTermDao);
        tab.setConditionDao(conditionDao);

        return tab;
    }

    public void testReferenceData() {
        study.setDiseaseTerminology(new DiseaseTerminology());
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
        MeddraVersion mv = new MeddraVersion();
        mv.setId(new Integer(5));
        mv.setName("Meddra version name");
        study.getDiseaseTerminology().setMeddraVersion(mv);

        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();

        assertNotNull(output);
        assertNotNull(output.get("diseaseTerminology"));
        assertNotNull(output.get("meddraVersionId"));
        assertNotNull(output.get("meddraVersion"));

        assertEquals(5, output.get("meddraVersionId"));
        assertEquals("Meddra version name", output.get("meddraVersion"));
    }

    public void testValidationNoData() {
        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationWithCTEPDiseases() {

        CtepStudyDisease csd = new CtepStudyDisease();
        csd.setId(2);
        csd.setTermName("Term name");
        csd.setStudy(study);
        csd.setTerm(new DiseaseTerm());
        csd.getTerm().setId(12);
        study.addCtepStudyDisease(csd);

        csd = new CtepStudyDisease();
        csd.setId(3);
        csd.setTermName("Term name");
        csd.setStudy(study);
        csd.setTerm(new DiseaseTerm());
        csd.getTerm().setId(17);
        study.addCtepStudyDisease(csd);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationWithMeddraDiseases() {

        MeddraStudyDisease msd = new MeddraStudyDisease();
        msd.setId(2);
        msd.setTermName("Term name");
        msd.setStudy(study);
        msd.setTerm(new LowLevelTerm());
        msd.getTerm().setId(12);
        study.addMeddraStudyDisease(msd);

        msd = new MeddraStudyDisease();
        msd.setId(2);
        msd.setTermName("Term name");
        msd.setStudy(study);
        msd.setTerm(new LowLevelTerm());
        msd.getTerm().setId(17);
        study.addMeddraStudyDisease(msd);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationWithCondition() {
        StudyCondition c = new StudyCondition();

        c.setId(2);
        c.setTerm(new Condition());
        c.getTerm().setConditionName("Condition Name - 01");
        c.getTerm().setId(1);
        c.setStudy(study);
        study.addStudyCondition(c);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationWithDuplicatedCTEPDiseases() {

        CtepStudyDisease csd = new CtepStudyDisease();
        csd.setId(2);
        csd.setTermName("Term name");
        csd.setStudy(study);
        csd.setTerm(new DiseaseTerm());
        csd.getTerm().setId(12);
        csd.getTerm().setTerm("Full name 12.");
        study.addCtepStudyDisease(csd);

        csd = new CtepStudyDisease();
        csd.setId(3);
        csd.setTermName("Term name");
        csd.setStudy(study);
        csd.setTerm(new DiseaseTerm());
        csd.getTerm().setId(17);
        csd.getTerm().setTerm("Full name 17.");
        study.addCtepStudyDisease(csd);
        command.setDiseaseTermIds(new String[] {"17", "12", "1"});

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(2, errors.getErrorCount());
        assertEquals("'Full name 17.' is already associated to this study", ((ObjectError)errors.getAllErrors().get(0)).getDefaultMessage());
        assertEquals("'Full name 12.' is already associated to this study", ((ObjectError)errors.getAllErrors().get(1)).getDefaultMessage());
    }

    public void testValidationWithDuplicatedMeddraDiseases() {

        MeddraStudyDisease msd = new MeddraStudyDisease();
        msd.setId(2);
        msd.setTermName("Term name");
        msd.setStudy(study);
        msd.setTerm(new LowLevelTerm());
        msd.getTerm().setId(12);
        study.addMeddraStudyDisease(msd);

        msd = new MeddraStudyDisease();
        msd.setId(2);
        msd.setTermName("Term name");
        msd.setStudy(study);
        msd.setTerm(new LowLevelTerm());
        msd.getTerm().setId(17);
        msd.getTerm().setMeddraCode("17 Code");
        msd.getTerm().setMeddraTerm("17 Term");
        study.addMeddraStudyDisease(msd);
        command.setDiseaseLlt("17");

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(1, errors.getErrorCount());
        assertEquals("'17 Code - 17 Term' is already associated to this study", ((ObjectError)errors.getAllErrors().get(0)).getDefaultMessage());
    }

    public void testValidationWithDuplicateCondition() {
        StudyCondition c = new StudyCondition();
        study.setDiseaseTerminology(new DiseaseTerminology());
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);

        c.setId(2);
        c.setTerm(new Condition());
        c.getTerm().setConditionName("Condition Name - 01");
        c.getTerm().setId(1);
        c.setStudy(study);
        study.addStudyCondition(c);
        command.setCondition("1");

        replayMocks();
        tab.referenceData(request, command);
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(1, errors.getErrorCount());
    }


    public void testPostProcessAddMeddraStudyDisease() {
        study.setDiseaseTerminology(new DiseaseTerminology());
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);

        command.setDiseaseLlt("5");
        request.setParameter("_action", "addMeddraStudyDisease");

        LowLevelTerm llt = new LowLevelTerm();
        llt.setId(5);
        EasyMock.expect(lowLevelTermDao.getById(5)).andReturn(llt).times(2);

        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertEquals(1, study.getMeddraStudyDiseases().size());
        assertEquals(5, study.getMeddraStudyDiseases().get(0).getTerm().getId().intValue());
    }

    public void testPostProcessAddCTEPStudyDisease() {
        study.setDiseaseTerminology(new DiseaseTerminology());
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);

        command.setDiseaseTermIds(new String[] {"1", "2"});
        request.setParameter("_action", "addStudyDisease");

        DiseaseTerm t1 = new DiseaseTerm();
        t1.setId(1);
        EasyMock.expect(diseaseTermDao.getById(1)).andReturn(t1).times(1);

        DiseaseTerm t2 = new DiseaseTerm();
        t2.setId(2);
        EasyMock.expect(diseaseTermDao.getById(2)).andReturn(t2).times(1);

        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertEquals(2, study.getCtepStudyDiseases().size());
        assertEquals(1, study.getCtepStudyDiseases().get(0).getTerm().getId().intValue());
        assertEquals(2, study.getCtepStudyDiseases().get(1).getTerm().getId().intValue());
    }

    public void testPostProcessAddOtherCondition() {
        study.setDiseaseTerminology(new DiseaseTerminology());
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);

        command.setCondition("8");
        request.setParameter("_action", "addOtherCondition");

        Condition c = new Condition();
        c.setId(8);
        EasyMock.expect(conditionDao.getById(8)).andReturn(c).times(1);

        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertEquals(1, study.getStudyConditions().size());
        assertEquals(8, study.getStudyConditions().get(0).getTerm().getId().intValue());
    }

    
}