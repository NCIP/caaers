package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

/**
 * @author Krikor Krumlian
 * 
 */
// TODO: this class is unnecessary -- you don't need a custom flow factory unless the flow changes
// depending on the command
// Just use a static flow (with setFlow in the controller)
public class SubmitReportFlowFactory implements FlowFactory<ExpeditedAdverseEventInputCommand> {
    protected String flowName;

    private Flow<ExpeditedAdverseEventInputCommand> submitReportFlow;

    public SubmitReportFlowFactory(String flowName) {
        this.flowName = flowName;
    }

    // //// LOGIC

    protected void addTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
        flow.addTab(new SubmitterTab());
        flow.addTab(new SubmitReportTab());
        flow.addTab(new SubmitReportResultTab());
    }

    public Flow<ExpeditedAdverseEventInputCommand> createFlow(ExpeditedAdverseEventInputCommand command) {
        return getSubmitReportFlow();
    }

    private Flow<ExpeditedAdverseEventInputCommand> createEmptyFlow() {
        return new Flow<ExpeditedAdverseEventInputCommand>(flowName);
    }

    private Flow<ExpeditedAdverseEventInputCommand> getSubmitReportFlow() {
        if (submitReportFlow == null) {
            submitReportFlow = createEmptyFlow();
            addTabs(submitReportFlow);
        }
        return submitReportFlow;
    }
}
