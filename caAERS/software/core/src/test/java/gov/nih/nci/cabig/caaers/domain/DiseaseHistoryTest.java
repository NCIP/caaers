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

    private DiseaseHistory diseaseHistory;
    private MetastaticDiseaseSite metastaticDiseaseSite;

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


        diseaseHistory = new DiseaseHistory();
        diseaseHistory.setId(1);
        diseaseHistory.setGridId("grid id");
        diseaseHistory.setVersion(2);
        diseaseHistory.setDiagnosisDate(startDateValue);

        diseaseHistory.setOtherPrimaryDisease(otherPrimaryDisease);

        diseaseHistory.setMeddraStudyDisease(meddraStudyDisease);
        diseaseHistory.setCodedPrimaryDiseaseSite(codedPrimaryDiseaseSite);
        diseaseHistory.setOtherPrimaryDiseaseSite(otherPrimaryDiseaseSite);
        diseaseHistory.setReport(new ExpeditedAdverseEventReport());


        metastaticDiseaseSite = new MetastaticDiseaseSite();
        diseaseHistory.addMetastaticDiseaseSite(metastaticDiseaseSite);

    }


    public void testCopyForBasicProperties() {

        DiseaseHistory history = this.diseaseHistory.copy();

        assertNotNull(history);

        assertNull("must not copy id ", history.getId());
        assertNull("must not copy grid id ", history.getGridId());
        assertNull("must not copy version no ", history.getVersion());
        assertNull("must not copy report ", history.getReport());

        assertEquals(startDateValue, history.getDiagnosisDate());

        assertEquals(meddraStudyDisease, history.getAbstractStudyDisease());
        assertSame(meddraStudyDisease, history.getAbstractStudyDisease());

        assertSame(codedPrimaryDiseaseSite, history.getCodedPrimaryDiseaseSite());


        assertSame(meddraStudyDisease, history.getMeddraStudyDisease());
        assertEquals(otherPrimaryDisease, history.getOtherPrimaryDisease());


    }


    public void testCopyForMetastaticDiseaseSites() {

        DiseaseHistory history = diseaseHistory.copy();


        assertNotNull(history.getMetastaticDiseaseSites().size());

        assertEquals(diseaseHistory.getMetastaticDiseaseSites().size(), history.getMetastaticDiseaseSites().size());
        assertEquals(diseaseHistory.getMetastaticDiseaseSites().size(), history.getMetastaticDiseaseSites().size());

        assertFalse(history.getMetastaticDiseaseSites().isEmpty());
        assertFalse(history.getMetastaticDiseaseSitesInternal().isEmpty());

        for (MetastaticDiseaseSite metastaticDiseaseSite : history.getMetastaticDiseaseSites()) {
            assertNotSame(diseaseHistory, metastaticDiseaseSite);
        }
        for (MetastaticDiseaseSite metastaticDiseaseSite : history.getMetastaticDiseaseSitesInternal()) {
            assertNotSame(diseaseHistory, metastaticDiseaseSite);
        }

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
        for (MetastaticDiseaseSite metastaticDiseaseSite : diseaseHistory.getMetastaticDiseaseSitesInternal()) {
            assertNotSame(studyParticipantMetastaticDiseaseSite, metastaticDiseaseSite);
        }

    }


    public void testMetastaticDiseaseListLazilyInited() throws Exception {
        DiseaseHistory dh = new DiseaseHistory();
        assertEquals("List not initially empty", 0, dh.getMetastaticDiseaseSites().size());
        assertNotNull("New site not dynamically created", dh.getMetastaticDiseaseSites().get(0));
    }
}
