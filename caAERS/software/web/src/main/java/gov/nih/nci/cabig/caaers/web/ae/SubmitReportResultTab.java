/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sameer Sawant
 */
public class SubmitReportResultTab extends TabWithFields<SubmitExpeditedAdverseEventCommand> {
	
	protected EvaluationService evaluationService;
	protected ReportDao reportDao;
	
	public SubmitReportResultTab() {
        super("Submission Result", "Submission Status", "ae/submitReportResult");

    }
	
	@Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(SubmitExpeditedAdverseEventCommand command) {
		InputFieldGroupMap map = new InputFieldGroupMap();
		return map;
	}
	
	@Override
	public Map<String, Object> referenceData(SubmitExpeditedAdverseEventCommand command) {
		 // Set the correct ReportVersion
		 command.setSubmissionInprogress(true);
		 Map<String, Object> refdata = super.referenceData(command);
	     return refdata;
	 }

	 public void setReportDao(ReportDao reportDao){
		 this.reportDao = reportDao;
	 }
}
