package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class StudyParticipantMetastaticDiseaseSiteTest extends AbstractTestCase {

    private MetastaticDiseaseSite metastaticDiseaseSite;
    private AnatomicSite codedSite;
    private String otherSite;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        metastaticDiseaseSite = new MetastaticDiseaseSite();
        metastaticDiseaseSite.setGridId("grid id");

        codedSite = new AnatomicSite();
        codedSite.setName("codedSite");
        metastaticDiseaseSite.setCodedSite(codedSite);
        otherSite = "other site";
        metastaticDiseaseSite.setOtherSite(otherSite);

        metastaticDiseaseSite.setVersion(2);


    }
    
    public void testGetName(){
    	StudyParticipantMetastaticDiseaseSite studyParticipantMetastaticDiseaseSite = new StudyParticipantMetastaticDiseaseSite();
    	assertNull(studyParticipantMetastaticDiseaseSite.getName());
    	studyParticipantMetastaticDiseaseSite = StudyParticipantMetastaticDiseaseSite.
        createAssignmentMetastaticDiseaseSite(metastaticDiseaseSite);
    	assertEquals("codedSite - other site", studyParticipantMetastaticDiseaseSite.getName());
    }

    public void testcreateAssignmentMetastaticDiseaseSiteForBasicProperties() {


        StudyParticipantMetastaticDiseaseSite studyParticipantMetastaticDiseaseSite = StudyParticipantMetastaticDiseaseSite.
                createAssignmentMetastaticDiseaseSite(metastaticDiseaseSite);

        assertNotNull(studyParticipantMetastaticDiseaseSite);

        assertNull("must not copy id ", studyParticipantMetastaticDiseaseSite.getId());
        assertNull("must not copy grid id ", studyParticipantMetastaticDiseaseSite.getGridId());
        assertNull("must not copy version no ", studyParticipantMetastaticDiseaseSite.getVersion());

        assertEquals(otherSite, studyParticipantMetastaticDiseaseSite.getOtherSite());


        assertSame("codedSite must refer to same object", codedSite, studyParticipantMetastaticDiseaseSite.getCodedSite());


    }


}