package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

/**
 * This factory is a lot like {@link ExpeditedFlowFactory}, but not in such a way that it's
 * fruitful to give them a common superclass.
 * 
 * @author Rhett Sutphin
 */
public class RoutineFlowFactory implements FlowFactory<RoutineAdverseEventInputCommand> {
    private String flowName;

    private Flow<RoutineAdverseEventInputCommand> ctepFlow;

    private Flow<RoutineAdverseEventInputCommand> meddraFlow;

    public RoutineFlowFactory(String flowName) {
        this.flowName = flowName;
    }

    public Flow<RoutineAdverseEventInputCommand> createFlow(RoutineAdverseEventInputCommand command) {
        if (command.getStudy() != null && command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA) {
            return getMeddraFlow();
        } else {
            return getCtepFlow();
        }
    }

    private Flow<RoutineAdverseEventInputCommand> createEmptyFlow() {
        return new Flow<RoutineAdverseEventInputCommand>(flowName);
    }

    protected void addPreBasicTabs(Flow<RoutineAdverseEventInputCommand> flow) {
    }

    private Flow<RoutineAdverseEventInputCommand> getMeddraFlow() {
        if (meddraFlow == null) {
            meddraFlow = createEmptyFlow();
            addPreBasicTabs(meddraFlow);
            meddraFlow.addTab(new RoutineAeMeddraTab());
            meddraFlow.addTab(new SaveRoutineAeTab());
        }
        return meddraFlow;
    }

    private Flow<RoutineAdverseEventInputCommand> getCtepFlow() {
        if (ctepFlow == null) {
            ctepFlow = createEmptyFlow();
            addPreBasicTabs(ctepFlow);
            ctepFlow.addTab(new CategoriesTab());
            ctepFlow.addTab(new RoutineAeTab());
            ctepFlow.addTab(new SaveRoutineAeTab());
        }
        return ctepFlow;
    }
}
