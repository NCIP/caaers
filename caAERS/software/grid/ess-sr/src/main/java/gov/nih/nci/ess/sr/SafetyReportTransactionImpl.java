package gov.nih.nci.ess.sr;

import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException;
import gov.nih.nci.ess.safetyreporting.tx.common.SafetyReportTransactionI;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;

import java.rmi.RemoteException;

import org.oasis.wsrf.lifetime.Destroy;
import org.oasis.wsrf.lifetime.DestroyResponse;
import org.oasis.wsrf.lifetime.SetTerminationTime;
import org.oasis.wsrf.lifetime.SetTerminationTimeResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import _21090.org.iso.DSET_II;
import _21090.org.iso.ST;

public class SafetyReportTransactionImpl implements SafetyReportTransactionI,
	MessageSourceAware {
	
	private ExpeditedAdverseEventReportDao adverseEventReportDao;
	
	public SafetyReportVersion amendSafetyReport(Id safetyReportId, Id amendmentId, ST reasonForAmend) throws RemoteException, SafetyReportingServiceException {
		ExpeditedAdverseEventReport expeditedAdverseEventReport =  adverseEventReportDao.getById(ISO21090Helper.value(safetyReportId));
		//expeditedAdverseEventReport.get
		// TODO Auto-generated method stub
		return null;
	}

	public SetTerminationTimeResponse setTerminationTime(SetTerminationTime params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public SafetyReportVersion submitSafetyReport(Id safetyReportId, Id submitterId, DSET_II additionalRecipientIds) throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public SafetyReportVersion withdrawSafetyReport(Id safetyReportId, Id withdrawerId, ST reasonForWithdraw) throws RemoteException, SafetyReportingServiceException {
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



}
