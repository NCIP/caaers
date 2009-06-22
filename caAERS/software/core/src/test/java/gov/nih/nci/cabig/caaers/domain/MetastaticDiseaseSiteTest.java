package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class MetastaticDiseaseSiteTest extends AbstractTestCase {

    private StudyParticipantMetastaticDiseaseSite studyParticipantMetastaticDiseaseSite;
    private AnatomicSite codedSite;
    private String otherSite;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        studyParticipantMetastaticDiseaseSite = new StudyParticipantMetastaticDiseaseSite();
        studyParticipantMetastaticDiseaseSite.setGridId("grid id");

        codedSite = new AnatomicSite();
        studyParticipantMetastaticDiseaseSite.setCodedSite(codedSite);
        otherSite = "other site";
        studyParticipantMetastaticDiseaseSite.setOtherSite(otherSite);

        studyParticipantMetastaticDiseaseSite.setVersion(2);


    }

    public void testcreateReportMetastaticDiseaseSite() {


        MetastaticDiseaseSite metastaticDiseaseSite = MetastaticDiseaseSite.
                createReportMetastaticDiseaseSite(studyParticipantMetastaticDiseaseSite);

        assertNotNull(metastaticDiseaseSite);

        assertNull("must not copy id ", metastaticDiseaseSite.getId());
        assertNull("must not copy grid id ", metastaticDiseaseSite.getGridId());
        assertNull("must not copy version no ", metastaticDiseaseSite.getVersion());

        assertEquals(otherSite, metastaticDiseaseSite.getOtherSite());


        assertSame("codedSite must refer to same object", codedSite, metastaticDiseaseSite.getCodedSite());


    }


}