package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;

/**
 * @author Rhett Sutphin
 */
public class SubmitExpeditedAdverseEventCommand extends EditExpeditedAdverseEventCommand {
    private StudyParticipantAssignmentDao assignmentDao;
    
    // Used in SubmitReport
    private String reportIndex;
    private String reportId;

    ////// LOGIC

    public SubmitExpeditedAdverseEventCommand(
        ExpeditedAdverseEventReportDao expeditedAeReportDao,
        ReportDefinitionDao reportDefinitionDao,
        StudyParticipantAssignmentDao assignmentDao
    ) {
        super(expeditedAeReportDao, reportDefinitionDao,assignmentDao);
    }
    
    @Override
    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        super.setAeReport(aeReport);
        getReportIndex();
        aeReport.getReports().get(((int)(Integer.parseInt(reportIndex)))).getLastVersion().addSubmitter();
    }

   
	public String getReportIndex() {
		for(int i=0; i< getAeReport().getReports().size(); i++){
			if (getAeReport().getReports().get(i).getId().toString().equals(reportId)){
				this.reportIndex = String.valueOf(i);
			}
		}
		return reportIndex;
	}

	public void setReportIndex(String reportIndex) {
		this.reportIndex = reportIndex;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	
    
    
}
