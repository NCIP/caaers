package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.ctms.web.tabs.*;
import gov.nih.nci.cabig.caaers.domain.Term;

public class ExpeditedFlowFactory<C> implements FlowFactory<C> {
	private Flow<C> flow;
	private Flow<C> secondaryFlow;

	public ExpeditedFlowFactory() {
	}

	public ExpeditedFlowFactory(Flow<C> flow) {
		this.flow = flow;
	}

	// //// LOGIC

	public Flow<C> createFlow(C command) {
		if (((AdverseEventInputCommand) command).getStudy() != null && 
				((AdverseEventInputCommand) command).getStudy().getTerminology().getTerm() == Term.MEDDRA) {
			return getSecondaryFlow();
		}
		return getFlow();
	}

	// //// BEAN PROPERTIES

	public Flow<C> getFlow() {
		return flow;
	}

	public void setFlow(Flow<C> flow) {
		this.flow = flow;
	}

	public Flow<C> getSecondaryFlow() {
		return secondaryFlow;
	}

	public void setSecondaryFlow(Flow<C> secondaryFlow) {
		this.secondaryFlow = secondaryFlow;
	}
	
	
	
}
