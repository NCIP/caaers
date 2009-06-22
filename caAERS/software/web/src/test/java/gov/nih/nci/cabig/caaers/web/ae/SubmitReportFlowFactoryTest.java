package gov.nih.nci.cabig.caaers.web.ae;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;


/**
 * This class tests - SubmitReportFlowFactory.java
 * @author Sameer Sawant
 */
public class SubmitReportFlowFactoryTest extends AbstractTestCase{
	private SubmitReportFlowFactory factory = new SubmitReportFlowFactory("Test flow");
	private SubmitExpeditedAdverseEventCommand command;
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        command = registerMockFor(SubmitExpeditedAdverseEventCommand.class);
	}
	
	public void testTabCount() throws Exception{
		assertEquals("Incorrect number of tabs created in the flow", 3, factory.createFlow(command).getTabCount());
	}
	
}