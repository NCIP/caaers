package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Rhett Sutphin
 */

public class DiseaseHistoryTest extends AbstractTestCase {

    private StudyParticipantDiseaseHistory studyParticipantDiseaseHistory;
    private DateValue startDateValue;
    private String otherPrimaryDisease;
    private MeddraStudyDisease meddraStudyDisease;
    private AnatomicSite codedPrimaryDiseaseSite;
    private String otherPrimaryDiseaseSite;
    private StudyParticipantMetastaticDiseaseSite studyParticipantMetastaticDiseaseSite;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        studyParticipantDiseaseHistory = new StudyParticipantDiseaseHistory();
        studyParticipantDiseaseHistory.setId(1);
        studyParticipantDiseaseHistory.setGridId("grid id");
        studyParticipantDiseaseHistory.setVersion(2);
        startDateValue = new DateValue(2008);
        studyParticipantDiseaseHistory.setDiagnosisDate(startDateValue);

        otherPrimaryDisease = "other primary disease";
        studyParticipantDiseaseHistory.setOtherPrimaryDisease(otherPrimaryDisease);

        meddraStudyDisease = new MeddraStudyDisease();
        studyParticipantDiseaseHistory.setMeddraStudyDisease(meddraStudyDisease);
        codedPrimaryDiseaseSite = new AnatomicSite();
        studyParticipantDiseaseHistory.setCodedPrimaryDiseaseSite(codedPrimaryDiseaseSite);
        otherPrimaryDiseaseSite = "other primary disease site";
        studyParticipantDiseaseHistory.setOtherPrimaryDiseaseSite(otherPrimaryDiseaseSite);
        studyParticipantDiseaseHistory.setAssignment(new StudyParticipantAssignment());


        studyParticipantMetastaticDiseaseSite = new StudyParticipantMetastaticDiseaseSite();
        studyParticipantDiseaseHistory.addMetastaticDiseaseSite(studyParticipantMetastaticDiseaseSite);

    }

    public void testCreateAssignmentDiseaseHistoryForBasicProperties() {

        DiseaseHistory studyParticipantDiseaseHistory = DiseaseHistory.createDiseaseHistory(this.studyParticipantDiseaseHistory);

        assertNotNull(studyParticipantDiseaseHistory);

        assertNull("must not copy id ", studyParticipantDiseaseHistory.getId());
        assertNull("must not copy grid id ", studyParticipantDiseaseHistory.getGridId());
        assertNull("must not copy version no ", studyParticipantDiseaseHistory.getVersion());
        assertNull("must not copy report ", studyParticipantDiseaseHistory.getReport());

        assertEquals(startDateValue, studyParticipantDiseaseHistory.getDiagnosisDate());

        assertEquals(meddraStudyDisease, studyParticipantDiseaseHistory.getAbstractStudyDisease());
        assertSame(meddraStudyDisease, studyParticipantDiseaseHistory.getAbstractStudyDisease());

        assertSame(codedPrimaryDiseaseSite, studyParticipantDiseaseHistory.getCodedPrimaryDiseaseSite());


        assertSame(meddraStudyDisease, studyParticipantDiseaseHistory.getMeddraStudyDisease());
        assertEquals(otherPrimaryDisease, studyParticipantDiseaseHistory.getOtherPrimaryDisease());


    }

    public void testCreateAssignmentDiseaseHistoryForMetastaticDiseaseSites() {

        DiseaseHistory diseaseHistory = DiseaseHistory.createDiseaseHistory(this.studyParticipantDiseaseHistory);


        assertNotNull(diseaseHistory.getMetastaticDiseaseSites().size());

        assertEquals(studyParticipantDiseaseHistory.getMetastaticDiseaseSites().size(), diseaseHistory.getMetastaticDiseaseSites().size());
        assertEquals(studyParticipantDiseaseHistory.getMetastaticDiseaseSites().size(), diseaseHistory.getMetastaticDiseaseSites().size());

        assertFalse(diseaseHistory.getMetastaticDiseaseSites().isEmpty());
        assertFalse(diseaseHistory.getMetastaticDiseaseSitesInternal().isEmpty());

        for (MetastaticDiseaseSite metastaticDiseaseSite : diseaseHistory.getMetastaticDiseaseSites()) {
            assertNotSame(studyParticipantMetastaticDiseaseSite, metastaticDiseaseSite);
        }
        for (MetastaticDiseaseSite metastaticDiseaseSite : diseaseHistory.getMetastaticDiseaseSites()) {
            assertNotSame(studyParticipantMetastaticDiseaseSite, metastaticDiseaseSite);
        }

    }


    public void testMetastaticDiseaseListLazilyInited() throws Exception {
        DiseaseHistory dh = new DiseaseHistory();
        assertEquals("List not initially empty", 0, dh.getMetastaticDiseaseSites().size());
        assertNotNull("New site not dynamically created", dh.getMetastaticDiseaseSites().get(0));
    }
}
