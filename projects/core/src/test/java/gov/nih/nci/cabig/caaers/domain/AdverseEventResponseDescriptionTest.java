package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT})
public class AdverseEventResponseDescriptionTest extends AbstractTestCase {
    private AdverseEventResponseDescription description;

   

    @Override
    protected void setUp() throws Exception {
        super.setUp();
       
        description = Fixtures.createAdverseEventResponseDescription();
       
    }

    public void testCopy() {
        AdverseEventResponseDescription adverseEventResponseDescription = description.copy();


        assertNull("must not coy id", adverseEventResponseDescription.getId());

        assertNull("must not coy grid id", adverseEventResponseDescription.getGridId());
        assertNull("must not coy version number", adverseEventResponseDescription.getVersion());
        assertNull("must not coy expeditedReport", adverseEventResponseDescription.getReport());
        assertEquals(description.getAutopsyPerformed(), adverseEventResponseDescription.getAutopsyPerformed());
        assertEquals(description.getBlindBroken(), adverseEventResponseDescription.getBlindBroken());
        assertEquals(description.getCauseOfDeath(), adverseEventResponseDescription.getCauseOfDeath());
        
        assertEquals(DateUtils.formatDate(description.getDateRemovedFromProtocol()), DateUtils.formatDate(adverseEventResponseDescription.getDateRemovedFromProtocol()));
        assertEquals(description.getDaysNotGiven(), adverseEventResponseDescription.getDaysNotGiven());
        assertEquals(description.getEventAbate(), adverseEventResponseDescription.getEventAbate());
        assertEquals(description.getEventDescription(), adverseEventResponseDescription.getEventDescription());
        assertEquals(description.getEventReappear(), adverseEventResponseDescription.getEventReappear());
        assertEquals(description.getPresentStatus(), adverseEventResponseDescription.getPresentStatus());
        assertEquals(description.getRecoveryDate(), adverseEventResponseDescription.getRecoveryDate());
        assertEquals(description.getReducedDate(), adverseEventResponseDescription.getReducedDate());
        assertEquals(description.getReducedDose(), adverseEventResponseDescription.getReducedDose());
        assertEquals(description.getRetreated(), adverseEventResponseDescription.getRetreated());
        assertEquals(description.getStudyDrugInterrupted(), adverseEventResponseDescription.getStudyDrugInterrupted());
        
        assertEquals("primaryTreatment must be same", description.getPrimaryTreatment(), adverseEventResponseDescription.getPrimaryTreatment());
     


    }

    public void testIsRemovedWhenRemoved() throws Exception {
        description.setDateRemovedFromProtocol(new Date());
        assertTrue(description.isRemovedFromProtocol());
    }

    public void testIsNotRemovedWhenRemoved() throws Exception {
        description.setDateRemovedFromProtocol(null);
        assertFalse(description.isRemovedFromProtocol());
    }
    
    public void testCopyPrimaryTreatmentApproximateTime() {
    	 AdverseEventResponseDescription adverseEventResponseDescription = description.copy();

        assertEquals("primaryTreatmentApproximateTime must  be refer same objects", description.getPrimaryTreatmentApproximateTime(), adverseEventResponseDescription.getPrimaryTreatmentApproximateTime());


    }
}
