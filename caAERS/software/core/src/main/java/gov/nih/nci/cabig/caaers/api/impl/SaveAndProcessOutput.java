package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.SaveAndEvaluateAEsOutputMessage;

public class SaveAndProcessOutput {

	private SaveAndEvaluateAEsOutputMessage msg;
	private AdverseEventReportingPeriod period;
	
	public SaveAndProcessOutput() {
	}
	
	public SaveAndProcessOutput(SaveAndEvaluateAEsOutputMessage msg, AdverseEventReportingPeriod period) {
		this.msg = msg;
		this.period = period;
	}
	
	public SaveAndEvaluateAEsOutputMessage getMsg() {
		return msg;
	}
	public void setMsg(SaveAndEvaluateAEsOutputMessage msg) {
		this.msg = msg;
	}
	public AdverseEventReportingPeriod getPeriod() {
		return period;
	}
	public void setPeriod(AdverseEventReportingPeriod period) {
		this.period = period;
	}
}
