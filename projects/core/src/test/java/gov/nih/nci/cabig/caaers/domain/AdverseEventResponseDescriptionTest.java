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

    private String eventDescription;

    private PostAdverseEventStatus presentStatus;

    private Date recoveryDate;

    private Boolean retreated;

    private Date dateRemovedFromProtocol;

    private Boolean blindBroken;

    private Boolean studyDrugInterrupted;

    private String reducedDose;

    private Date reducedDate;

    private Integer daysNotGiven;

    private Boolean eventAbate;

    private Boolean eventReappear;

    private Boolean autopsyPerformed;

    private String causeOfDeath;
    private String primaryTreatment;
    private TimeValue primaryTreatmentApproximateTime;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
       
        eventDescription = "event desc";
        presentStatus = PostAdverseEventStatus.DEAD;
        recoveryDate = new Date();
        retreated = true;
        dateRemovedFromProtocol = new Date();
        blindBroken = true;
        studyDrugInterrupted = true;
        reducedDose = "reduce dose";
        reducedDate = new Date();
        daysNotGiven = 2;
        eventAbate = true;
        eventReappear = true;
        autopsyPerformed = true;
        causeOfDeath = "cause of death";
        primaryTreatment = "primaryTreatment";
        primaryTreatmentApproximateTime = new TimeValue();
        primaryTreatmentApproximateTime.setHour(2);
        
        description = Fixtures.createAdverseEventResponseDescription();
       
    }

    public void testCopy() {
        AdverseEventResponseDescription adverseEventResponseDescription = description.copy();


        assertNull("must not coy id", adverseEventResponseDescription.getId());

        assertNull("must not coy grid id", adverseEventResponseDescription.getGridId());
        assertNull("must not coy version number", adverseEventResponseDescription.getVersion());
        assertNull("must not coy expeditedReport", adverseEventResponseDescription.getReport());
        assertEquals(autopsyPerformed, adverseEventResponseDescription.getAutopsyPerformed());
        assertEquals(blindBroken, adverseEventResponseDescription.getBlindBroken());
        assertEquals(causeOfDeath, adverseEventResponseDescription.getCauseOfDeath());
        
        assertEquals(DateUtils.formatDate(dateRemovedFromProtocol), DateUtils.formatDate(adverseEventResponseDescription.getDateRemovedFromProtocol()));
        assertEquals(daysNotGiven, adverseEventResponseDescription.getDaysNotGiven());
        assertEquals(eventAbate, adverseEventResponseDescription.getEventAbate());
        assertEquals(eventDescription, adverseEventResponseDescription.getEventDescription());
        assertEquals(eventReappear, adverseEventResponseDescription.getEventReappear());
        assertEquals(presentStatus, adverseEventResponseDescription.getPresentStatus());
        assertEquals(recoveryDate, adverseEventResponseDescription.getRecoveryDate());
        assertEquals(reducedDate, adverseEventResponseDescription.getReducedDate());
        assertEquals(reducedDose, adverseEventResponseDescription.getReducedDose());
        assertEquals(retreated, adverseEventResponseDescription.getRetreated());
        assertEquals(studyDrugInterrupted, adverseEventResponseDescription.getStudyDrugInterrupted());
        
        assertEquals("primaryTreatment must be same", primaryTreatment, adverseEventResponseDescription.getPrimaryTreatment());
     


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

        assertEquals("primaryTreatmentApproximateTime must  be refer same objects", 
        		primaryTreatmentApproximateTime, 
        		adverseEventResponseDescription.getPrimaryTreatmentApproximateTime());

        assertEquals("primaryTreatmentApproximateTime must  refer same object ", primaryTreatmentApproximateTime, adverseEventResponseDescription.getPrimaryTreatmentApproximateTime());


    }
}
