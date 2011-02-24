package gov.nih.nci.ess.sr;

import ess.caaers.nci.nih.gov.Bl;
import ess.caaers.nci.nih.gov.Id;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException;
import gov.nih.nci.ess.safetyreporting.rdm.common.SafetyReportDefinitionManagementI;
import gov.nih.nci.ess.safetyreporting.types.ReportDefinition;
import gov.nih.nci.ess.safetyreporting.types.ReportDeliveryDefinition;

import java.rmi.RemoteException;

import org.oasis.wsrf.lifetime.Destroy;
import org.oasis.wsrf.lifetime.DestroyResponse;
import org.oasis.wsrf.lifetime.SetTerminationTime;
import org.oasis.wsrf.lifetime.SetTerminationTimeResponse;

import _21090.org.iso.ST;

public class SafetyReportDefinitionManagementImpl implements SafetyReportDefinitionManagementI {
	
	private ReportDefinitionDao reportDefinitionDao;
	private SafetyToExpeditedReportConverter safetyToExpeditedReportConverter;

	public ReportDefinition createSafetyReportDefinition(ReportDefinition safetyReportDefinition) throws RemoteException, SafetyReportingServiceException {
		gov.nih.nci.cabig.caaers.domain.report.ReportDefinition rd = safetyToExpeditedReportConverter.convertSafetyReportDefinition(safetyReportDefinition,null);
		for (ReportDeliveryDefinition srdd:safetyReportDefinition.getReportDeliveryDefinitions()) {
			rd.addReportDeliveryDefinition(safetyToExpeditedReportConverter.convertReportDeliveryDefinition(srdd));
		}
		reportDefinitionDao.save(rd);
		return null;
	}

	public ReportDefinition deactivateSafetyReportDefinition(Id reportDefinitionId, ST reasonForDeactivation) throws RemoteException, SafetyReportingServiceException {
		gov.nih.nci.cabig.caaers.domain.report.ReportDefinition rd = reportDefinitionDao.getById(ISO21090Helper.value(reportDefinitionId));
		rd.setEnabled(false);
		reportDefinitionDao.save(rd);
		return null;
	}
	public ReportDefinition updateSafetyReportDefinitionDeliveryDetails(ReportDefinition reportDefinition) throws RemoteException, SafetyReportingServiceException {
		gov.nih.nci.cabig.caaers.domain.report.ReportDefinition rd = reportDefinitionDao.getById(ISO21090Helper.value(reportDefinition.getIdentifier()));

		for (ReportDeliveryDefinition srdd:reportDefinition.getReportDeliveryDefinitions()) {
			gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition rdd = safetyToExpeditedReportConverter.convertReportDeliveryDefinition(srdd);
			rd.addReportDeliveryDefinition(rdd);
		}
		reportDefinitionDao.save(rd);
		return null;
	}

	public ReportDefinition updateSafetyReportDefinitionDetails(ReportDefinition safetyReportDefinition) throws RemoteException, SafetyReportingServiceException {
		gov.nih.nci.cabig.caaers.domain.report.ReportDefinition rd = reportDefinitionDao.getById(ISO21090Helper.value(safetyReportDefinition.getIdentifier()));
		rd = safetyToExpeditedReportConverter.convertSafetyReportDefinition(safetyReportDefinition,  rd);
		reportDefinitionDao.save(rd);
		return null;
	}

	public ReportDefinition updateSafetyReportDefinitionMandatoryFields(ReportDefinition reportDefinition) throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Bl updateSafetyReportTerminologyForStudy(Id reportDefinitionId, Id studyId, Id reportTerminologyId) throws RemoteException, SafetyReportingServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DestroyResponse destroy(Destroy params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public SetTerminationTimeResponse setTerminationTime(SetTerminationTime params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



}
