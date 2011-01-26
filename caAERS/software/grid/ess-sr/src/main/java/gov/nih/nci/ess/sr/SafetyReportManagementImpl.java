/**
 * 
 */
package gov.nih.nci.ess.sr;

import java.rmi.RemoteException;

import _21090.org.iso.DSET_II;
import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI;
import gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException;
import gov.nih.nci.ess.safetyreporting.types.AdverseEventReportingPeriod;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;

/**
 * @author Denis G. Krylov
 *
 */
public final class SafetyReportManagementImpl implements
		SafetyReportManagementI {

	/* (non-Javadoc)
	 * @see gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI#initiateSafetyReport(ess.caaers.nci.nih.gov.Id, ess.caaers.nci.nih.gov.Id, ess.caaers.nci.nih.gov.Id, _21090.org.iso.DSET_II, _21090.org.iso.DSET_II, gov.nih.nci.ess.safetyreporting.types.AdverseEventReportingPeriod)
	 */
	public SafetyReportVersion initiateSafetyReport(Id studyId, Id subjectId,
			Id patientId, DSET_II adverseEventIds, DSET_II problemIds,
			AdverseEventReportingPeriod adverseEventReportingPeriod)
			throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI#associateAdverseEventsToSafetyReport(ess.caaers.nci.nih.gov.Id, _21090.org.iso.DSET_II)
	 */
	public SafetyReportVersion associateAdverseEventsToSafetyReport(
			Id safetyReportId, DSET_II adverseEventIds) throws RemoteException,
			SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI#dissociateAdverseEventsFromSafetyReport(ess.caaers.nci.nih.gov.Id, _21090.org.iso.DSET_II)
	 */
	public SafetyReportVersion dissociateAdverseEventsFromSafetyReport(
			Id safetyReportId, DSET_II adverseEventIds) throws RemoteException,
			SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.ess.safetyreporting.management.common.SafetyReportManagementI#updateAdverseEventInformationInSafetyReport(ess.caaers.nci.nih.gov.Id, ess.caaers.nci.nih.gov.AdverseEvent)
	 */
	public SafetyReportVersion updateAdverseEventInformationInSafetyReport(
			Id safetyReportId, AdverseEvent adverseEvent)
			throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
