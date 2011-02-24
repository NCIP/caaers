package gov.nih.nci.ess.sr;

import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.query.ReportQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.ess.safetyreporting.tx.common.SafetyReportTransactionI;

import java.rmi.RemoteException;
import java.util.List;

import org.oasis.wsrf.lifetime.Destroy;
import org.oasis.wsrf.lifetime.DestroyResponse;
import org.oasis.wsrf.lifetime.SetTerminationTime;
import org.oasis.wsrf.lifetime.SetTerminationTimeResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import _21090.org.iso.DSET_TEL;

public class SafetyReportTransactionImpl implements SafetyReportTransactionI,
	MessageSourceAware {
	
	private ExpeditedAdverseEventReportDao adverseEventReportDao;
	private ReportRepository reportRepository;	
	private ReportDefinitionDao reportDefinitionDao;
	private ReportDao reportDao;
	private ReportSubmissionService reportSubmissionService;
	
	private Report createReport(Id safetyReportVersionId , Id safetyReportDefinitionId) {
		ExpeditedAdverseEventReport aeReport = adverseEventReportDao.getById(ISO21090Helper.value(safetyReportVersionId));
		gov.nih.nci.cabig.caaers.domain.report.ReportDefinition rd = reportDefinitionDao.getById(ISO21090Helper.value(safetyReportDefinitionId));
		return reportRepository.createReport(rd, aeReport);
	}
	
	private Report getReport(Integer expeditedReportId, Integer reportDefinitionId) {
		ReportQuery qry = new ReportQuery();
		qry.filterByExpeditedReportAndReportDefinition(expeditedReportId, reportDefinitionId);
		List<Report> reports = reportDao.search(qry);
		return  reports.size() == 0 ? reports.get(0):null;
		
	}
	
	public void submitSafetyReport(Id safetyReportVersionId , Id safetyReportDefinitionId , Id submitterId, DSET_TEL additionalRecipientEmails) throws RemoteException, SafetyReportingServiceException {
		Report r  = getReport(ISO21090Helper.value(safetyReportVersionId) , ISO21090Helper.value(safetyReportDefinitionId)); // get report from DB ...
		if (r == null ) {
			r = createReport (safetyReportVersionId,safetyReportDefinitionId);
			reportSubmissionService.submitReport(r);
		}
	}
	
	public void amendSafetyReport(Id safetyReportVersionId , Id safetyReportDefinitionId) throws RemoteException, SafetyReportingServiceException {
		Report r  = getReport(ISO21090Helper.value(safetyReportVersionId) , ISO21090Helper.value(safetyReportDefinitionId)); // get report from DB ...
		if (r == null ) {
			r = createReport (safetyReportVersionId,safetyReportDefinitionId);
			reportRepository.amendReport(r);
		}

	}

	public void withdrawSafetyReport(Id safetyReportVersionId , Id safetyReportDefinitionId) throws RemoteException, SafetyReportingServiceException {
		Report r  = getReport(ISO21090Helper.value(safetyReportVersionId) , ISO21090Helper.value(safetyReportDefinitionId)); // get report from DB ...
		if (r == null ) {
			r = createReport (safetyReportVersionId,safetyReportDefinitionId);
			reportRepository.amendReport(r);
		}

	}
	

	public SetTerminationTimeResponse setTerminationTime(SetTerminationTime params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMessageSource(MessageSource arg0) {
		// TODO Auto-generated method stub
		
	}
	public DestroyResponse destroy(Destroy params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ExpeditedAdverseEventReportDao getAdverseEventReportDao() {
		return adverseEventReportDao;
	}

	public void setAdverseEventReportDao(
			ExpeditedAdverseEventReportDao adverseEventReportDao) {
		this.adverseEventReportDao = adverseEventReportDao;
	}


	public ReportDefinitionDao getReportDefinitionDao() {
		return reportDefinitionDao;
	}

	public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
		this.reportDefinitionDao = reportDefinitionDao;
	}





}
