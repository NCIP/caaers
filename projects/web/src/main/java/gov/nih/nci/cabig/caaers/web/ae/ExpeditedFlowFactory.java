package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */

public class ExpeditedFlowFactory implements FlowFactory<ExpeditedAdverseEventInputCommand> {
    
    protected String flowName;
    private Flow<ExpeditedAdverseEventInputCommand> ctepFlow;
    private Flow<ExpeditedAdverseEventInputCommand> meddraFlow;

    public ExpeditedFlowFactory(String flowName) {
        this.flowName = flowName;
    }

    // //// LOGIC

    protected void addPreBasicTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
    	flow.addTab(new ReporterTab());
    }

    protected void addPostBasicTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
    	
    	/**
    	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
    	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
    	 */
    	flow.addTab(new DescriptionTab());
    	flow.addTab(new TreatmentTab());
        flow.addTab(new StudyInterventionsTab());
        flow.addTab(new PatientDetailsTab());
        flow.addTab(new OtherCausesTab());

        flow.addTab(new LabsTab());
        flow.addTab(new AttributionTab());
        flow.addTab(new AdditionalInformationTab());
        flow.addTab(new ViewReportTab());
    }

    public Flow<ExpeditedAdverseEventInputCommand> createFlow( ExpeditedAdverseEventInputCommand command) {
        if (command.getStudyTerminologyTerm() == Term.MEDDRA) {
            return getMeddraFlow(command);
        } else {
            return getCtepFlow(command);
        }
    }

    private Flow<ExpeditedAdverseEventInputCommand> createEmptyFlow() {
        return new Flow<ExpeditedAdverseEventInputCommand>(flowName);
    }

    private Flow<ExpeditedAdverseEventInputCommand> getMeddraFlow(ExpeditedAdverseEventInputCommand command) {
        if (meddraFlow == null || (meddraFlow != null && command.getStudy() == null)) {
            meddraFlow = createEmptyFlow();
            addPreBasicTabs(meddraFlow);
            meddraFlow.addTab(new MeddraBasicsTab());
            addPostBasicTabs(meddraFlow);
        }

        return meddraFlow;
    }

    private Flow<ExpeditedAdverseEventInputCommand> getCtepFlow(ExpeditedAdverseEventInputCommand command) {

        if (ctepFlow == null || (ctepFlow != null && command.getStudy() == null)) {
            ctepFlow = createEmptyFlow();
            addPreBasicTabs(ctepFlow);
            ctepFlow.addTab(new CtcBasicsTab());
            addPostBasicTabs(ctepFlow);
        }
        return ctepFlow;
    }
}
