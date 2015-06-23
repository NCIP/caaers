package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.integration.schema.saerules.EvaluateAndInitiateInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.EvaluateAndInitiateOutputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.SaveAndEvaluateAEsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.SaveAndEvaluateAEsOutputMessage;

public class SAEServiceMessageConverter {

	public SaveAndEvaluateAEsInputMessage SAEInputMessage(
			EvaluateAndInitiateInputMessage in) {
		SaveAndEvaluateAEsInputMessage out = new SaveAndEvaluateAEsInputMessage();
		out.setCriteria(in.getCriteria());
		out.setAdverseEvents(in.getAdverseEvents());
		return out;
	}

	public EvaluateAndInitiateOutputMessage EvaluateAndInitiateOutput(
			SaveAndEvaluateAEsOutputMessage in) {
		EvaluateAndInitiateOutputMessage out = new EvaluateAndInitiateOutputMessage();
    	out.setEvaluatedAdverseEventResults(in.getEvaluatedAdverseEventResults());
    	out.setLinkToReport(in.getLinkToReport());
    	out.setHasSAE(in.isHasSAE());
    	out.setRecommendedActions(in.getRecommendedActions());
    	
    	return out;
	}

}
