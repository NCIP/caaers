package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.report.Report;

/**
 * This is the command class for ReviewAeReportController
 * @author Sameer Sawant
 */

public class ReviewAeReportCommand extends EditExpeditedAdverseEventCommand{
	
	private Integer reportId;
	private ReportDao rpDao;
	
	public ReviewAeReportCommand(ExpeditedAdverseEventReportDao reportDao, ReportDao rpDao){
		super(reportDao);
		this.rpDao = rpDao;
	}
	
	@Override
	public void reassociate(){
		if(this.aeReport != null && this.aeReport.getId() != null){
			reportDao.reassociate(this.aeReport);
			if(reportId != null){
				for(Report r: this.aeReport.getReports())
					if(r.getId().equals(reportId))
						rpDao.reassociate(r);
			}
				
		}
	}
	
	public void setReportId(Integer reportId){
		this.reportId = reportId;
	}
	
	public Integer getReportId(){
		return reportId;
	}
}