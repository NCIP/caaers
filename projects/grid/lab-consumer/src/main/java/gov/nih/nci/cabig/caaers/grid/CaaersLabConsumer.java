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
import org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse;
import org.oasis.wsrf.properties.GetMultipleResourceProperties_Element;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.QueryResourcePropertiesResponse;
import org.oasis.wsrf.properties.QueryResourceProperties_Element;

import services.AckStatus;
import services.Acknowledgement;
import services.LabResult;
import services.PerformedActivity;
import services.StudySubject;
import services.WsError;
import services.WsErrors;

public class CaaersLabConsumer implements LabConsumerServiceI {
	
	private List<WsError> errorList = null;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	private LabLoadRepository labLoadRepository;
	private AuthorizationSwitch authorizationSwitch;
	//private StudyParticipantAssignmentAspect assignmentAspect;
	//private OpenSessionInViewInterceptor openSessionInViewInterceptor;

	public services.Acknowledgement loadLabs(services.LoadLabsRequest loadLabsRequest) throws RemoteException {
		System.out.println("In loadlabs Implementation.....");
		errorList = new ArrayList<WsError>();
		LabResult[] results = loadLabsRequest.getLabResult();
		for (int i=0 ; i<results.length; i++ ) {
			LabResult result = results[i];
			loadLab(result);	 
		}
		Acknowledgement ack = buildAcknowledgement();
		return ack;
	}
    
	private void loadLab(LabResult result) {
		//WebRequest stubWebRequest = null;
		boolean authorizationOnByDefault = enableAuthorization(false);
		
		try {
			//stubWebRequest = preProcess();
			switchUser("ROLE_caaers_super_user", "ROLE_caaers_super_user");
			StudySubject studySubject = result.getStudySubject();
			String participantId = studySubject.getParticipant().getII(0).getExtension();
			//lookup participant.
			Identifier i = new Identifier();
			//i.setType("MRN");
			i.setValue(participantId);

			Participant p = participantDao.getByIdentifier(i);
			
			if (p == null) {
				addError(services.ErrorCodes.InvalidStudyOrPatient,"Participant "+participantId+" not found in caAERS");
				return;				
			}


			String studyId = studySubject.getPerformedStudy().getDocumentation(0).getII(0).getExtension();
			//lookup study.
			Identifier i2 = new Identifier();
			//i2.setType("Protocol Authority Identifier");
			i2.setValue(studyId);

			Study s = studyDao.getByIdentifier(i2);
			

			if (s == null) {
				addError(services.ErrorCodes.InvalidStudyOrPatient,"Study "+studyId+" not found in caAERS");
				return;
			}

			//lookup assignment
			StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getAssignment(p,s);

			if (assignment == null) {
				addError(services.ErrorCodes.InvalidStudyOrPatient,"Participant "+ participantId+ " is not assigned to Study "+studyId+" in caAERS");
				return;			  
			}
			
			Float nResult = result.getNumericResult();
			String numericResult = "";
			if (nResult != null) {
				numericResult = result.getNumericResult()+"";
			}
			System.out.println("numeric result "+numericResult);
			 
			String units = result.getNumericUnit();
			Date labDate = null;
			if (result.getReportedDateTime() != null) {
				labDate = result.getReportedDateTime().getTime();
			}
			System.out.println("date "+labDate);

			PerformedActivity[] performedActivities = studySubject.getPerformedActivity();
			String labName = "";
			if (performedActivities.length>0) {
				PerformedActivity performedActivity = performedActivities[0];
				labName = performedActivity.getName();				
			}
			System.out.println("name "+labName);
			
			LabLoad toCreate = new LabLoad();
			toCreate.setName(labName);
			toCreate.setResult(numericResult);
			toCreate.setUnits(units);
			toCreate.setAssignment(assignment);
			toCreate.setLabDate(labDate);
			labLoadRepository.save(toCreate);
			System.out.println("saved ");
		} catch (Exception e) {
			e.printStackTrace();
			addError(services.ErrorCodes.ApplicationError,e.getMessage());
		} finally {
            //postProcess(stubWebRequest);
			enableAuthorization(authorizationOnByDefault);
			switchUser(null);
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

	private void addError(services.ErrorCodes errorCode,String errorDesc) {
		WsError wsError = new WsError();
		wsError.setErrorCode(errorCode);
		wsError.setErrorDesc(errorDesc);
		errorList.add(wsError);
	}

	private void switchUser(String userName, String... roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
	
	private boolean enableAuthorization(boolean on) {
        //AuthorizationSwitch sw = (AuthorizationSwitch) this.applicationContext.getBean("authorizationSwitch");
        if (authorizationSwitch == null) throw new RuntimeException("Authorization switch not found");
        boolean current = authorizationSwitch.isOn();
        authorizationSwitch.setOn(on);
        return current;
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

	public void setAuthorizationSwitch(AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}
	/*
	public void setAssignmentAspect(
			StudyParticipantAssignmentAspect assignmentAspect) {
		this.assignmentAspect = assignmentAspect;
	}

	public void setOpenSessionInViewInterceptor(
			OpenSessionInViewInterceptor openSessionInViewInterceptor) {
		this.openSessionInViewInterceptor = openSessionInViewInterceptor;
	}
	*/
	/*
	private static class StubWebRequest implements WebRequest {
        public String getParameter(final String paramName) {
            return null;
        }

        public String[] getParameterValues(final String paramName) {
            return null;
        }

        public Map getParameterMap() {
            return Collections.emptyMap();
        }

        public Locale getLocale() {
            return null;
        }

        public Object getAttribute(final String name, final int scope) {
            return null;
        }

        public void setAttribute(final String name, final Object value, final int scope) {
        }

        public void removeAttribute(final String name, final int scope) {
        }

        public void registerDestructionCallback(final String name, final Runnable callback,
                        final int scope) {
        }

        public String getSessionId() {
            return null;
        }

        public Object getSessionMutex() {
            return null;
        }
    }
    private WebRequest preProcess() {
        assignmentAspect.setSecurityInterceptor(new AspectJSecurityInterceptorStub());
        authorizationSwitch.setOn(false);
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        authorities[0] = new GrantedAuthorityImpl("ROLE_caaers_super_user");

        Authentication auth = new TestingAuthenticationToken("ROLE_caaers_super_user", "ignored",
                        authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        WebRequest stubWebRequest = new StubWebRequest();
        openSessionInViewInterceptor.preHandle(stubWebRequest);
        return stubWebRequest;
    }

    private void postProcess(WebRequest stubWebRequest) {
        openSessionInViewInterceptor.afterCompletion(stubWebRequest, null);
    }
    */

}
