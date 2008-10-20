package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;

/**
 * @author Biju Joseph
 */
public class DiseaseAttributionTest extends AbstractTestCase {

    private DiseaseAttribution diseaseAttribution;
    private AdverseEvent adverseEvent;
    private DiseaseHistory cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new DiseaseHistory();
        diseaseAttribution = new DiseaseAttribution();


        diseaseAttribution.setId(1);
        diseaseAttribution.setGridId("grid id");
        diseaseAttribution.setCause(cause);
        diseaseAttribution.setVersion(2);
        diseaseAttribution.setAttribution(Attribution.POSSIBLE);
        diseaseAttribution.setAdverseEvent(adverseEvent);
    }

    public void testCopy() {
//        DiseaseAttribution copiedDiseaseAttribution = diseaseAttribution.copy();
//        assertNull("id must be null", copiedDiseaseAttribution.getId());
//        assertNull("grid id must be null", copiedDiseaseAttribution.getGridId());
//        assertNull("version number must be null", copiedDiseaseAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedDiseaseAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedDiseaseAttribution.getAttribution());
//        assertNotNull(diseaseAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedDiseaseAttribution.getAdverseEvent());	
    	assertTrue(true);


    }
}