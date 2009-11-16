package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.repository.LabLoadRepository;
import gov.nih.nci.cabig.ccts.domain.loadlabs.AckStatus;
import gov.nih.nci.cabig.ccts.domain.loadlabs.Acknowledgement;
import gov.nih.nci.cabig.ccts.domain.loadlabs.ErrorCodes;
import gov.nih.nci.cabig.ccts.domain.loadlabs.LabResult;
import gov.nih.nci.cabig.ccts.domain.loadlabs.LoadLabsRequest;
import gov.nih.nci.cabig.ccts.domain.loadlabs.PerformedActivity;
import gov.nih.nci.cabig.ccts.domain.loadlabs.StudySubject;
import gov.nih.nci.cabig.ccts.domain.loadlabs.WsError;
import gov.nih.nci.cabig.ccts.domain.loadlabs.WsErrors;
import gov.nih.nci.ccts.grid.common.LabConsumerServiceI;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse;
import org.oasis.wsrf.properties.GetMultipleResourceProperties_Element;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.QueryResourcePropertiesResponse;
import org.oasis.wsrf.properties.QueryResourceProperties_Element;

public class CaaersLabConsumer implements LabConsumerServiceI {
	
	private List<WsError> errorList = null;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	private LabLoadRepository labLoadRepository;
	private AuthorizationSwitch authorizationSwitch;
	private static final Log log = LogFactory.getLog(CaaersLabConsumer.class);

	public Acknowledgement loadLabs(LoadLabsRequest loadLabsRequest) throws RemoteException {
		log.info("In loadlabs Implementation.....");
		errorList = new ArrayList<WsError>();
		
		LabResult[] results = loadLabsRequest.getLabResult();
		for (int i=0 ; i<results.length; i++ ) {
			LabResult result = results[i];
			loadLab(result);	 
		}
		Acknowledgement ack = buildAcknowledgement();
		
		log.info("**** BEGIN ACK ***** ");
		  log.info("Status " +ack.getStatus());
		  WsError[] errors = ack.getErrors().getWsError();
		  if (errors != null) {
			  for (int i=0;i<errors.length;i++) {
				  log.info(errors[i].getErrorCode() + " - " + errors[i].getErrorDesc());
			  }
		  }
		log.info("**** END ACK ***** ");
		
		return ack;
	}
    
	private void loadLab(LabResult result) {
		
		try {
			StudySubject studySubject = result.getStudySubject();
			String participantId = studySubject.getParticipant().getII(0).getExtension();
			//lookup participant.
			Identifier i = new Identifier();
			i.setValue(participantId);

			Participant p = participantDao.getByIdentifier(i);
			
			if (p == null) {
				addError(ErrorCodes.InvalidStudyOrPatient,"Participant "+participantId+" not found in caAERS");
				return;				
			}


			String studyId = studySubject.getPerformedStudy().getDocumentation(0).getII(0).getExtension();
			//lookup study.
			Identifier i2 = new Identifier();
			i2.setValue(studyId);

			Study s = studyDao.getByIdentifier(i2);

			if (s == null) {
				addError(ErrorCodes.InvalidStudyOrPatient,"Study "+studyId+" not found in caAERS");
				return;
			}

			//lookup assignment
			StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getAssignment(p,s);

			if (assignment == null) {
				addError(ErrorCodes.InvalidStudyOrPatient,"Participant "+ participantId+ " is not assigned to Study "+studyId+" in caAERS");
				return;			  
			}
			
			Float nResult = result.getNumericResult();
			String numericResult = "";
			if (nResult != null) {
				numericResult = result.getNumericResult()+"";
			}
			log.debug("numeric result "+numericResult);
			 
			String units = result.getNumericUnit();
			Date labDate = null;
			if (result.getReportedDateTime() != null) {
				labDate = result.getReportedDateTime().getTime();
			}
			log.debug("date "+labDate);

			PerformedActivity[] performedActivities = studySubject.getPerformedActivity();
			String labName = "";
			if (performedActivities.length>0) {
				PerformedActivity performedActivity = performedActivities[0];
				labName = performedActivity.getName();				
			}
			log.debug("name "+labName);
			
			LabLoad toCreate = new LabLoad();
			toCreate.setName(labName);
			toCreate.setResult(numericResult);
			toCreate.setUnits(units);
			toCreate.setAssignment(assignment);
			toCreate.setLabDate(labDate);
			labLoadRepository.save(toCreate);
			log.debug("saved ");
		} catch (Exception e) {
			log.error(e);
			addError(ErrorCodes.ApplicationError,e.getMessage());
		} 
	}

	private Acknowledgement buildAcknowledgement() {
		Acknowledgement ack = new Acknowledgement();
		WsError[]  errors = new WsError[errorList.size()];
		for (int i=0;i<errorList.size();i++) {
			errors[i] = errorList.get(i);
		}
		WsErrors wsErrors = new WsErrors();
		wsErrors.setWsError(errors);
		ack.setErrors(wsErrors);
		if (errorList.size() == 0 ) ack.setStatus(AckStatus.Processed);
		
		return ack;
	}

	private void addError(ErrorCodes errorCode,String errorDesc) {
		WsError wsError = new WsError();
		wsError.setErrorCode(errorCode);
		wsError.setErrorDesc(errorDesc);
		errorList.add(wsError);
	}

	public void setLabLoadRepository(
			LabLoadRepository labLoadRepository) {
		this.labLoadRepository = labLoadRepository;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public void setStudyParticipantAssignmentDao(
			StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
		this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
	}

	public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(GetMultipleResourceProperties_Element params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public GetResourcePropertyResponse getResourceProperty(QName params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryResourcePropertiesResponse queryResourceProperties(QueryResourceProperties_Element params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
