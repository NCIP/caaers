package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class DescriptionTabTest extends AeTabTestCase {
    @Override
    protected DescriptionTab createTab() {
        return new DescriptionTab();
    }

    public void testFields() throws Exception {
        assertFieldProperties("desc", "aeReport.responseDescription.eventDescription",
        				"aeReport.responseDescription.primaryTreatmentApproximateTime",
                        "aeReport.responseDescription.presentStatus",
                        "aeReport.responseDescription.recoveryDate",
                        "aeReport.responseDescription.retreated",
                        "aeReport.responseDescription.dateRemovedFromProtocol");
    }
    
    public void testOnBindWithPresentStatusDead() throws Exception {
    	command.getAeReport().getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
    	command.getAeReport().getResponseDescription().setAutopsyPerformed(false);
    	createTab().onBind(request, command, errors);
    	assertFalse("onBind method incorrectly modifying the autopsyPerfomed value", command.getAeReport().getResponseDescription().getAutopsyPerformed());
    }
    
    public void testOnBindWithPresentStatusNotDead() throws Exception{
    	command.getAeReport().getResponseDescription().setPresentStatus(PostAdverseEventStatus.INTERVENTION_CONTINUES);
    	command.getAeReport().getResponseDescription().setAutopsyPerformed(false);
    	createTab().onBind(request, command, errors);
    	assertNull("onBind method not setting the value of autopsyPerformed to null", command.getAeReport().getResponseDescription().getAutopsyPerformed());
    }
}
