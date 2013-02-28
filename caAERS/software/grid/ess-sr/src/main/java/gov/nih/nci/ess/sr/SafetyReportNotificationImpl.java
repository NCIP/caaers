/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.sr;

import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException;
import gov.nih.nci.ess.safetyreporting.notification.common.SafetyReportNotificationI;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportDefinitionNotification;

import java.rmi.RemoteException;

import org.oasis.wsrf.lifetime.Destroy;
import org.oasis.wsrf.lifetime.DestroyResponse;
import org.oasis.wsrf.lifetime.SetTerminationTime;
import org.oasis.wsrf.lifetime.SetTerminationTimeResponse;

import _21090.org.iso.ST;

public class SafetyReportNotificationImpl implements SafetyReportNotificationI {
	
	private ReportDefinitionDao reportDefinitionDao;
	private SafetyToExpeditedReportConverter safetyToExpeditedReportConverter;

	public void createSafetyReportDefinitionNotification(SafetyReportDefinitionNotification safetyReportDefinitionNotification, Id reportDefinitionId) throws RemoteException, SafetyReportingServiceException {
		ReportDefinition rd = reportDefinitionDao.getById(ISO21090Helper.value(reportDefinitionId));
		PlannedNotification pn = safetyToExpeditedReportConverter.convertReportDefinitionNotification(safetyReportDefinitionNotification);
		rd.addPlannedNotification(pn);
		reportDefinitionDao.save(rd);
	}

	public void deactivateSafetyReportDefinitionNotification(Id notificationId, ST reasonForDeactivation) throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		
	}

	public void updateSafetyReportDefinitionNotification(SafetyReportDefinitionNotification safetyReportDefinitionNotification) throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		
	}
	
	public DestroyResponse destroy(Destroy params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public SetTerminationTimeResponse setTerminationTime(SetTerminationTime params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReportDefinitionDao getReportDefinitionDao() {
		return reportDefinitionDao;
	}

	public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
		this.reportDefinitionDao = reportDefinitionDao;
	}

	public SafetyToExpeditedReportConverter getSafetyToExpeditedReportConverter() {
		return safetyToExpeditedReportConverter;
	}

	public void setSafetyToExpeditedReportConverter(
			SafetyToExpeditedReportConverter safetyToExpeditedReportConverter) {
		this.safetyToExpeditedReportConverter = safetyToExpeditedReportConverter;
	}


}
