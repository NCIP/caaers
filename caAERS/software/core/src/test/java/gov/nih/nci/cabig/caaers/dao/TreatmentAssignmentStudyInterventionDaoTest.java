/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import org.dbunit.operation.DatabaseOperation;

import java.util.List;

/**
 * @author Ion C. Olaru
 */
public class TreatmentAssignmentStudyInterventionDaoTest extends DaoTestCase<TreatmentAssignmentStudyInterventionDao> {

    private TreatmentAssignmentDao tadao = (TreatmentAssignmentDao)getApplicationContext().getBean("treatmentAssignmentDao");
    private CtcTermDao ctcTermDao = (CtcTermDao)getApplicationContext().getBean("ctcTermDao");
    private StudyAgentDao studyAgentDao = (StudyAgentDao)getApplicationContext().getBean("studyAgentDao");
    private StudyDeviceDao studyDeviceDao = (StudyDeviceDao)getApplicationContext().getBean("studyDeviceDao");
    private OtherInterventionDao otherInterventionDao = (OtherInterventionDao)getApplicationContext().getBean("otherInterventionDao");

    public void testLoadTreatmentAssignment() {
        TreatmentAssignment ta = tadao.getById(-11);
        System.out.println(">>> TA:" + ta);
        System.out.println(ta.getCode());
        System.out.println(ta.getId());
    }

    public void testAddTreatmentAssignmentAgent() {
        TreatmentAssignmentAgent taa = new TreatmentAssignmentAgent();
        TreatmentAssignment ta = tadao.getById(-11);
        StudyAgent studyAgent = studyAgentDao.getById(-1000);

        taa.setTreatmentAssignment(ta);
        taa.setStudyAgent(studyAgent);
        getDao().save(taa);
        assertEquals(1, getDao().getAll().size());
        assertEquals(1, tadao.getTreatmentAssignmentAgentsByStudyId(-2).size());
    }

    public void testAddDifferentTreatmentAssignments() {
        TreatmentAssignment ta = tadao.getById(-11);
        StudyAgent studyAgent = studyAgentDao.getById(-1000);
        StudyDevice studyDevice = studyDeviceDao.getById(-2000);
        OtherIntervention otherIntervention = otherInterventionDao.getById(-3000);

        TreatmentAssignmentAgent taa = new TreatmentAssignmentAgent();
        taa.setTreatmentAssignment(ta);
        taa.setStudyAgent(studyAgent);
        getDao().save(taa);

        TreatmentAssignmentDevice tad1 = new TreatmentAssignmentDevice();
        tad1.setTreatmentAssignment(ta);
        tad1.setStudyDevice(studyDevice);
        getDao().save(tad1);

        TreatmentAssignmentDevice tad2 = new TreatmentAssignmentDevice();
        tad2.setTreatmentAssignment(ta);
        tad2.setStudyDevice(studyDevice);
        getDao().save(tad2);

        TreatmentAssignmentOtherIntervention tao = new TreatmentAssignmentOtherIntervention();
        tao.setTreatmentAssignment(ta);
        tao.setOtherIntervention(otherIntervention);
        getDao().save(tao);

        assertEquals(4, getDao().getAll().size());

        List<TreatmentAssignmentStudyIntervention> loaded = getDao().loadAllByClass(TreatmentAssignmentDevice.class);
        assertEquals(2, loaded.size());

        assertEquals(TreatmentAssignmentDevice.class, loaded.get(0).getClass());
        assertEquals(TreatmentAssignmentDevice.class, loaded.get(1).getClass());

        assertEquals(4, ta.getTreatmentAssignmentStudyInterventions().size());

        assertEquals(1, tadao.getTreatmentAssignmentAgentsByStudyId(-2).size());
        assertEquals(2, tadao.getTreatmentAssignmentDevicesByStudyId(-2).size());
        assertEquals(1, tadao.getTreatmentAssignmentOthersByStudyId(-2).size());

        assertEquals(0, tadao.getTreatmentAssignmentDevicesByStudyId(-3).size());

    }

    public void testSaveStudyInterventionExpectedTerms() {
        CtcTerm t = ctcTermDao.getById(3012);
        TreatmentAssignment ta = tadao.getById(-11);
        StudyAgent studyAgent = studyAgentDao.getById(-1000);

        TreatmentAssignmentAgent taa = new TreatmentAssignmentAgent();
        taa.setTreatmentAssignment(ta);
        taa.setStudyAgent(studyAgent);

        StudyInterventionExpectedCtcTerm term = new StudyInterventionExpectedCtcTerm();
        term.setTerm(t);
        term.setExpectednessFrequency(0.1);
        term.setExpectednessFrequency(0.9);
        term.setGrade1Frequency(0.1);
        term.setGrade2Frequency(0.2);
        term.setGrade3Frequency(0.3);
        term.setGrade4Frequency(0.4);
        term.setGrade5Frequency(0.5);
        term.setOtherMeddraTerm(null);
        //taa.addAbstractStudyInterventionExpectedAEs(term);
        getDao().save(taa);

    }


/*
    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
*/
}
